/*
 * French Revolutionary Calendar Library
 * 
 * Copyright (c) 2012-2017 Carmen Alvarez
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 */
package ca.rmen.lfrc

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.Locale

/**

 * Provides conversion between Gregorian calendar dates and French Revolutionary Calendar dates.
 * Wikipedia article: http://en.wikipedia.org/wiki/French_Republican_Calendar
 * @param calculationMethod the method to calculate the first day of the French year, in the Gregorian calendar.
 * @author calvarez
 */
class FrenchRevolutionaryCalendar(
        private val locale: Locale,
        private val calculationMethod: FrenchRevolutionaryCalendar.CalculationMethod) {

    enum class CalculationMethod { // NO_UCD (use default)
        EQUINOX, ROMME, VON_MADLER
    }

    enum class DailyObjectType { // NO_UCD (use default)
        PLANT, ANIMAL, TOOL, MINERAL, CONCEPT
    }

    private val frenchEraYearZero = Calendar.getInstance()
    private val frenchEraYearOne = Calendar.getInstance()
    private var frenchEraEnd = Calendar.getInstance()

    init {
        // How will we calculate the Gregorian date of the beginning of each
        // French year?

        // Read in the dates of the beginning and end of the French calendar,
        // for which equinoxes were used.
        val sdf = SimpleDateFormat(DATE_FORMAT)
        try {
            frenchEraYearZero.time = sdf.parse(FRENCH_ERA_YEAR_ZERO)
            frenchEraYearOne.time = sdf.parse(FRENCH_ERA_YEAR_ONE)
            frenchEraEnd.time = sdf.parse(FRENCH_ERA_END)
        } catch (e: ParseException) {
            debug("Error reading French epoch " + FRENCH_ERA_YEAR_ONE, e)
        }

    }

    /**
     * @param gregorianDate
     *
     * @return the French date corresponding to the Gregorian calendar date.
     */
    fun getDate(gregorianDate: GregorianCalendar): FrenchRevolutionaryCalendarDate {

        // Determine the method of calculating the first day of the French year.

        // If we are using the equinox calculation method, or if we are within the dates the
        // calendar was used (regardless of the selected calculation method), use the equinox
        // calculation method.
        val result = when {
            calculationMethod == CalculationMethod.EQUINOX || gregorianDate.after(frenchEraYearOne) && gregorianDate.before(frenchEraEnd) -> getDateEquinox(gregorianDate)
            calculationMethod == CalculationMethod.ROMME -> getDateRomme(gregorianDate)
            calculationMethod == CalculationMethod.VON_MADLER -> getDateVonMadler(gregorianDate)
            else ->
                throw IllegalArgumentException("Can't convert date $gregorianDate using method $calculationMethod")
        }
        // Get the decimal time portion of the French date
        val timeInDay = getFrenchTime(gregorianDate)
        return FrenchRevolutionaryCalendarDate(locale, result.year, result.month, result.dayOfMonth, timeInDay[0], timeInDay[1], timeInDay[2])
    }

    /**
     * @param gregorianDate
     *
     * @return The French date corresponding to the Gregorian calendar date,
     *         using the equinox method to determine the Gregorian date for the
     *         first day of the French year.
     */
    private fun getDateEquinox(gregorianDate: Calendar): FrenchRevolutionaryCalendarDate {

        val gyear = gregorianDate[Calendar.YEAR]
        val gAutumnEquinox = getAutumnEquinox(gyear) ?: throw IllegalArgumentException("Date not supported: $gregorianDate")

        // Determine the first day of the French year.
        var g1stVendemiaire : Calendar?
        g1stVendemiaire = gAutumnEquinox
        // Case 1, date from January to September, use the equinox date from
        // last year
        if (gregorianDate < gAutumnEquinox) {
            g1stVendemiaire = getAutumnEquinox(gyear - 1)
            if (g1stVendemiaire == null) throw IllegalArgumentException("Date not supported: $gregorianDate")
        } else {

        }
        // Case 2, date from September to December
        // The year in the French date: Gregorian year at 1st vendemiaire -
        // 1792. This is the number of years elapsed since the French calendar
        // had been started.
        val frenchYear = g1stVendemiaire[Calendar.YEAR] - frenchEraYearOne[Calendar.YEAR] + 1
        // The DAY_OF_YEAR in the French year, from 0 to 365. This is the
        // number of days elapsed 1stVendemiaire of the French year and the
        // given timestamp.

        // Take into account DST offset difference between the equinox and today.
        // Example: if today in Paris is November 29, our DST offset is 0, but the DST offset at the equinox in September was 1 hour.
        // We consider the September equinox was to be at 23 sept 0:00, which is 22 sept 22:00 GMT.
        // And now is 29 November 22:30 GMT.
        // If we don't take into account the DST difference, we'll think that now is OVER 68 days since the equinox,
        // but it's really just under 68 days.
        val numberDaysInFrenchYear = ((gregorianDate.timeInMillis + gregorianDate[Calendar.DST_OFFSET]
                - g1stVendemiaire.timeInMillis - g1stVendemiaire[Calendar.DST_OFFSET].toLong()) / NUM_MILLISECONDS_IN_DAY).toInt()
        // Create and return the French calendar object.
        return getFrenchDate(frenchYear, numberDaysInFrenchYear)
    }

    /**
     * @param gregorianDate
     *
     * @return The French date corresponding to the Gregorian calendar date,
     *         using the Romme method to determine the Gregorian date for the
     *         first day of the French year.
     */
    private fun getDateRomme(gregorianDate: Calendar): FrenchRevolutionaryCalendarDate {
        // Time elapsed between the end of the French calendar and the given
        // date. We have to include the daylight savings offset, because back in
        // 1792-1811,
        // daylight savings time wasn't being used. If we don't take into
        // account the offset, a calculation like 8/5/1996 00:00:00 - 8/5/1796
        // 00:00:00 will not return 200 years, but 200 years - 1 hour, which is
        // not
        // the desired result.
        val numMillisSinceEndOfFrenchEra = gregorianDate.timeInMillis + gregorianDate[Calendar.DST_OFFSET] - frenchEraEnd!!.timeInMillis - frenchEraEnd!![Calendar.DST_OFFSET].toLong()

        // The Romme method applies the same
        // rules (mostly) of the Gregorian calendar to the French calendar.
        // One difference is the year 4000, which is a leap year in the
        // Gregorian system, but not in the French system. For now we ignore
        // this difference. We may address it and fix this code in the year
        // 3999 :)

        // Create a fake calendar object (fake because this is not really a
        // Gregorian date), corresponding to the end of the French calendar era.
        // This was in the French year 20. Since in the Gregorian year 20, there
        // were no leap years yet, we add 2000 to the year, so that the
        // Gregorian calendar implementation can handle the leap years.

        // The end of the French calendar system was the beginning of the year
        // 20.
        val fakeEndFrenchEraTimestamp = GregorianCalendar(2020, 0, 1).timeInMillis
        // Add the elapsed time to the French date.
        val fakeFrenchTimestamp = fakeEndFrenchEraTimestamp + numMillisSinceEndOfFrenchEra

        // Create a calendar object for the French date
        val fakeFrenchDate = Calendar.getInstance()
        fakeFrenchDate.timeInMillis = fakeFrenchTimestamp

        // Extract the year, and day in year from the French date.
        val frenchYear = fakeFrenchDate[Calendar.YEAR]
        val frenchDayInYear = fakeFrenchDate[Calendar.DAY_OF_YEAR]

        // Create and return a French calendar object.
        return getFrenchDate(frenchYear - 2000, frenchDayInYear - 1)
    }

    /**
     * @param gregorianDate
     *
     * @return The French date corresponding to the Gregorian calendar date,
     * using the 128 method to determine the leap years:
     * Years divisible by 4 but not divisible by 128 are leap years.
     */
    private fun getDateVonMadler(gregorianDate: Calendar): FrenchRevolutionaryCalendarDate {
        // Time elapsed between the end of the French calendar and the given
        // date. We have to include the daylight savings offset, because back in
        // 1792-1811,
        // daylight savings time wasn't being used. If we don't take into
        // account the offset, a calculation like 8/5/1996 00:00:00 - 8/5/1796
        // 00:00:00 will not return 200 years, but 200 years - 1 hour, which is
        // not
        // the desired result.
        val numMillisSinceBeginningOfFrenchEra = gregorianDate.timeInMillis - frenchEraYearZero!!.timeInMillis + gregorianDate[Calendar.DST_OFFSET]
        // The number of days since day 0 of the French calendar (1791-09-23).
        val numDaysSinceFrenchEraBegin = (numMillisSinceBeginningOfFrenchEra / NUM_MILLISECONDS_IN_DAY).toInt()

        var frenchYear = ((numDaysSinceFrenchEraBegin + 1) / VON_MADLER_DAYS_IN_YEAR).toInt()
        // The number of days since 1791-09-23 to 1 Vendemiaire of the given year.
        var numDaysFromFrenchEraBeginTo1VendemiaireThisYear = 365 * frenchYear + (frenchYear - 1) / 4 - (frenchYear - 1) / 128
        // The number of the day in the given year, starting from 0
        var frenchDayOfYear = numDaysSinceFrenchEraBegin - numDaysFromFrenchEraBeginTo1VendemiaireThisYear

        // If the day was negative, this means it's in the previous year.  Rewind the year and recalculate the
        // day of the year.
        if (frenchDayOfYear < 0) {
            frenchYear--
            numDaysFromFrenchEraBeginTo1VendemiaireThisYear = 365 * frenchYear + (frenchYear - 1) / 4 - (frenchYear - 1) / 128
            frenchDayOfYear = numDaysSinceFrenchEraBegin - numDaysFromFrenchEraBeginTo1VendemiaireThisYear
        }
        return getFrenchDate(frenchYear, frenchDayOfYear)
    }

    /**
     * @param frenchYear
     *            the year in the French calendar
     *
     * @param numberDaysInFrenchYear
     *            number of days since 1st Vendemiare of the given year,
     *            starting with 0.
     *
     * @return A French calendar object for the given French day.
     */
    private fun getFrenchDate(frenchYear: Int, numberDaysInFrenchYear: Int): FrenchRevolutionaryCalendarDate {
        // Find the month in the French year, starting from 0.
        val numberMonthInFrenchYear = numberDaysInFrenchYear / 30

        // Find the day in the French month, starting from 0.
        val numberDaysInFrenchMonth = numberDaysInFrenchYear - numberMonthInFrenchYear * 30

        // Create and return the French calendar object.
        return FrenchRevolutionaryCalendarDate(locale, frenchYear, numberMonthInFrenchYear + 1,
                numberDaysInFrenchMonth + 1, 0, 0, 0)
    }

    private fun debug(o: Any, t: Throwable?) {
        println(javaClass.name + ": " + o)
        t?.printStackTrace()
    }

    companion object {

        private const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
        private const val FRENCH_ERA_YEAR_ZERO = "1791-09-23 00:00:00"
        private const val FRENCH_ERA_YEAR_ONE = "1792-09-22 00:00:00"
        private const val FRENCH_ERA_END = "1811-09-23 00:00:00"
        private const val NUM_MILLISECONDS_IN_DAY = (1000 * 60 * 60 * 24).toLong()
        private const val EQUINOX_MONTH = 8 // September

        // Von Madler eap years are divisible by 4 but not by 128.
        private val VON_MADLER_DAYS_IN_YEAR = 365 + 1.0 / 4 - 1.0 / 128

        /**
         * @param gtime
         *
         * @return a decimal representation of the time within this day. Returns
         *         three ints for hour, minutes, seconds, respectively. The hour is
         *         from 0 to 9, the minute is from 0 to 99, and the second is from
         *         0 to 99.
         */
        @JvmStatic
        fun getFrenchTime(gtime: Calendar): IntArray {
            val ghour = gtime[Calendar.HOUR_OF_DAY]
            val gmin = gtime[Calendar.MINUTE]
            val gsec = gtime[Calendar.SECOND]

            val dayFraction = ghour.toFloat() / 24 + gmin.toFloat() / 1440 + gsec.toFloat() / 86400
            val fhour = (dayFraction * 10).toInt()
            val fmin = ((dayFraction * 10 - fhour) * 100).toInt()
            val fsec = ((dayFraction * 10 - (fhour + fmin.toFloat() / 100)) * 10000).toInt()
            return intArrayOf(fhour, fmin, fsec)
        }


        /**
         * @param gyear a year in the Gregorian calendar
         *
         * @return the day, in the Gregorian calendar, of the autumn equinox (at midnight) in the current timezone, for the given year, if the given year is between -1000 and 3000 inclusive. Returns null otherwise.
         */
        private fun getAutumnEquinox(gyear: Int): Calendar? {
            // Create a date object for the autumn equinox date (at midnight) in the
            // current timezone.
            val gAutumnEquinoxDay = EquinoxDates.getAutumnEquinox(gyear) ?: return null
            val gAutumnEquinox = Calendar.getInstance()
            gAutumnEquinox.set(Calendar.YEAR, gyear)
            gAutumnEquinox.set(Calendar.MONTH, EQUINOX_MONTH)
            gAutumnEquinox.set(Calendar.DAY_OF_MONTH, gAutumnEquinoxDay)
            gAutumnEquinox.set(Calendar.HOUR_OF_DAY, 0)
            gAutumnEquinox.set(Calendar.MINUTE, 0)
            gAutumnEquinox.set(Calendar.SECOND, 0)
            gAutumnEquinox.set(Calendar.MILLISECOND, 0)
            return gAutumnEquinox
        }

    }
}
