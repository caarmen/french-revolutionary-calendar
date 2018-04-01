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
import java.util.TimeZone
import kotlin.math.roundToInt

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

    private val utcTimeZone = TimeZone.getTimeZone("UTC")
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
     * @param gregorianDate a date in the Gregorian calendar
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
     * @param frenchDate a date in the French Revolutionary Calendar
     *
     * @return the Gregorian calendar date, in the default time zone, corresponding to the given French Revolutionary Calendar date
     */
    fun getDate(frenchDate: FrenchRevolutionaryCalendarDate): GregorianCalendar? {
        val result = when {
            calculationMethod == CalculationMethod.EQUINOX || frenchDate.year in 1..19 -> getDateEquinox(frenchDate)
            calculationMethod == CalculationMethod.ROMME -> getDateRomme(frenchDate)
            calculationMethod == CalculationMethod.VON_MADLER -> getDateVonMadler(frenchDate)
            else ->
                throw IllegalArgumentException("Can't convert date $frenchDate using method $calculationMethod")
        } ?: return null

        val timeInDay = get24HourTime(frenchDate)
        result[Calendar.HOUR_OF_DAY] = timeInDay[0]
        result[Calendar.MINUTE] = timeInDay[1]
        result[Calendar.SECOND] = timeInDay[2]
        result[Calendar.MILLISECOND] = 0
        return result
    }

    /**
     * @param gregorianDate a date in the Gregorian calendar
     *
     * @return The French date corresponding to the Gregorian calendar date,
     *         using the equinox method to determine the Gregorian date for the
     *         first day of the French year.
     */
    private fun getDateEquinox(gregorianDate: Calendar): FrenchRevolutionaryCalendarDate {

        val gyear = gregorianDate[Calendar.YEAR]
        val gAutumnEquinox = getAutumnEquinox(gyear, gregorianDate.timeZone) ?: throw IllegalArgumentException("Date not supported: $gregorianDate")

        // Determine the first day of the French year.
        var g1stVendemiaire: Calendar?
        g1stVendemiaire = gAutumnEquinox
        // Case 1, date from January to September, use the equinox date from
        // last year
        if (gregorianDate < gAutumnEquinox) {
            g1stVendemiaire = getAutumnEquinox(gyear - 1, gregorianDate.timeZone)
            if (g1stVendemiaire == null) throw IllegalArgumentException("Date not supported: $gregorianDate")
        }
        // Case 2, date from September to December
        else {

        }
        // The year in the French date: Gregorian year at 1st vendemiaire -
        // 1792. This is the number of years elapsed since the French calendar
        // had been started.
        val frenchYear = g1stVendemiaire[Calendar.YEAR] - frenchEraYearOne[Calendar.YEAR] + 1
        // The DAY_OF_YEAR in the French year, from 0 to 365. This is the
        // number of days elapsed between 1stVendemiaire of the French year and the
        // given timestamp.

        // Take into account DST offset difference between the equinox and today.
        // Example: if today in Paris is November 29, our DST offset is 0, but the DST offset at the equinox in September was 1 hour.
        // We consider the September equinox was to be at 23 sept 0:00, which is 22 sept 22:00 GMT.
        // And now is 29 November 22:30 GMT.
        // If we don't take into account the DST difference, we'll think that now is OVER 68 days since the equinox,
        // but it's really just under 68 days.
        val numberDaysInFrenchYear = ((gregorianDate.timeInMillis + gregorianDate[Calendar.DST_OFFSET]
                - g1stVendemiaire.timeInMillis - g1stVendemiaire[Calendar.DST_OFFSET]) / NUM_MILLISECONDS_IN_DAY).toInt()
        // Create and return the French calendar object.
        return createFrenchDate(frenchYear, numberDaysInFrenchYear)
    }

    /**
     * @param frenchDate a date in the French Revolutionary Calendar
     *
     * @return The Gregorian calendar date corresponding to the French date,
     *         using the equinox method to determine the Gregorian date for the
     *         first day of the French year.
     */
    private fun getDateEquinox(frenchDate: FrenchRevolutionaryCalendarDate): GregorianCalendar? {
        // Start out with a Gregorian calendar object in 1792 (year 1 of the French calendar)
        val result = Calendar.getInstance(utcTimeZone) as GregorianCalendar
        result.timeInMillis = frenchEraYearOne.inUtc().timeInMillis

        // Move the calendar object forward by the number of years in our French date.
        // Now our Gregorian calendar is in the right year.
        result[Calendar.YEAR] += frenchDate.year - 1

        // Set our Gregorian calendar to the date of the autumn equinox, in its given year
        val equinoxDay = getAutumnEquinox(result[Calendar.YEAR], utcTimeZone) ?: return null
        result.timeInMillis = equinoxDay.timeInMillis

        // Determine how much time passed, in the French date, since the beginning of the year (which is the autumn equinox)
        val millisSinceYearStart = (frenchDate.dayInYear - 1) * NUM_MILLISECONDS_IN_DAY

        // Add this passed time to our Gregorian calendar result
        result.timeInMillis += millisSinceYearStart
        result.timeInMillis -= result[Calendar.DST_OFFSET] - equinoxDay[Calendar.DST_OFFSET]
        return result.inDefaultTimeZone() as GregorianCalendar
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
        // not the desired result.
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
        val fakeFrenchDate = Calendar.getInstance(gregorianDate.timeZone)
        fakeFrenchDate.timeInMillis = fakeFrenchTimestamp

        // Extract the year, and day in year from the French date.
        val frenchYear = fakeFrenchDate[Calendar.YEAR]
        val frenchDayInYear = fakeFrenchDate[Calendar.DAY_OF_YEAR]

        // Create and return a French calendar object.
        return createFrenchDate(frenchYear - 2000, frenchDayInYear - 1)
    }

    /**
     * @param frenchDate a date in the French Revolutionary Calendar
     *
     * @return The Gregorian calendar date, in the UTC timezone, corresponding to the French date,
     *         using the Romme method to determine the Gregorian date for the
     *         first day of the French year.
     */
    private fun getDateRomme(frenchDate: FrenchRevolutionaryCalendarDate): GregorianCalendar {
        // Create a fake calendar object (fake because this is not really a
        // Gregorian date), corresponding to the end of the French calendar era.
        // This was in the French year 20. Since in the Gregorian year 20, there
        // were no leap years yet, we add 2000 to the year, so that the
        // Gregorian calendar implementation can handle the leap years.
        val fakeEndFrenchEraEndCalendar = createGregorianDateUtc(2020, 0, 1)

        // Create a fake calendar object for the given French date
        val fakeFrenchCalendar = createGregorianDateUtc(2000 + frenchDate.year, 0, 1)
        fakeFrenchCalendar[Calendar.DAY_OF_YEAR] = frenchDate.dayInYear

        // Determine how much time passed since the end of the French calendar (its year 20, Gregorian year 1811) and the given French date
        val numMillisSinceFrenchEraEnd = fakeFrenchCalendar.timeInMillis + fakeFrenchCalendar[Calendar.DST_OFFSET] - fakeEndFrenchEraEndCalendar.timeInMillis - fakeEndFrenchEraEndCalendar[Calendar.DST_OFFSET]

        // Create the Gregorian calendar object starting with 1811 and adding this time passed
        val resultUtc = Calendar.getInstance(utcTimeZone) as GregorianCalendar
        resultUtc.timeInMillis = frenchEraEnd.inUtc().timeInMillis + numMillisSinceFrenchEraEnd
        return resultUtc.inDefaultTimeZone() as GregorianCalendar
    }

    /**
     * @param gregorianDate
     *
     * @return The French date corresponding to the Gregorian calendar date,
     *         using the 128 method to determine the leap years:
     *         Years divisible by 4 but not divisible by 128 are leap years.
     */
    private fun getDateVonMadler(gregorianDate: Calendar): FrenchRevolutionaryCalendarDate {
        // Time elapsed between the end of the French calendar and the given
        // date. We have to include the daylight savings offset, because back in
        // 1792-1811,
        // daylight savings time wasn't being used. If we don't take into
        // account the offset, a calculation like 8/5/1996 00:00:00 - 8/5/1796
        // 00:00:00 will not return 200 years, but 200 years - 1 hour, which is
        // not the desired result.
        val numMillisSinceBeginningOfFrenchEra = gregorianDate.inUtc().timeInMillis - frenchEraYearZero!!.inUtc().timeInMillis
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
        return createFrenchDate(frenchYear, frenchDayOfYear)
    }

    /**
     * @param frenchDate a date in the French Revolutionary Calendar
     *
     * @return The Gregorian calendar date corresponding to the French date,
     *         using the 128 method to determine the leap years:
     *         Years divisible by 4 but not divisible by 128 are leap years.
     */
    private fun getDateVonMadler(frenchDate: FrenchRevolutionaryCalendarDate): GregorianCalendar {
        // The number of days from day 0 of the French calendar (1791-09-23) to 1 Vendemiaire of the given year.
        val numDaysFromFrenchEraBeginTo1VendemiaireThisYear = frenchDate.year * 365 + (frenchDate.year - 1) / 4 - (frenchDate.year - 1) / 128

        // The number of days from day 0 of the French calendar (1791-09-23), to the given French date.
        val numDaysSinceFrenchEraBegin = numDaysFromFrenchEraBeginTo1VendemiaireThisYear + frenchDate.dayInYear - 1

        // The number of milliseconds from day 0 of the French calendar (1791-09-23), to the given French date.
        val numMillisSinceBeginningOfFrenchEra = numDaysSinceFrenchEraBegin * NUM_MILLISECONDS_IN_DAY

        val resultUtc = Calendar.getInstance(utcTimeZone) as GregorianCalendar
        resultUtc.timeInMillis = numMillisSinceBeginningOfFrenchEra + frenchEraYearZero.inUtc().timeInMillis
        return resultUtc.inDefaultTimeZone() as GregorianCalendar
    }

    /**
     * @param frenchYear the year in the French calendar
     * @param numberDaysInFrenchYear  number of days since 1st Vendemiare of the given year, starting with 0.
     *
     * @return A French calendar object for the given French day.
     */
    private fun createFrenchDate(frenchYear: Int, numberDaysInFrenchYear: Int): FrenchRevolutionaryCalendarDate {
        // Find the month in the French year, starting from 0.
        val numberMonthInFrenchYear = numberDaysInFrenchYear / 30

        // Find the day in the French month, starting from 0.
        val numberDaysInFrenchMonth = numberDaysInFrenchYear - numberMonthInFrenchYear * 30

        // Create and return the French calendar object.
        return FrenchRevolutionaryCalendarDate(locale, frenchYear, numberMonthInFrenchYear + 1,
                numberDaysInFrenchMonth + 1, 0, 0, 0)
    }

    /**
     * @param year the year in the Gregorian calendar
     * @param month the month in the Gregorian calendar, starting from 0
     * @param day the day in the month, in the Gregorian calendar
     *
     * @return a Gregorian calendar, in the UTC time zone, for the given year, month, and day
     */
    private fun createGregorianDateUtc(year: Int, month: Int, day: Int): GregorianCalendar {
        val result = Calendar.getInstance(utcTimeZone) as GregorianCalendar
        result[Calendar.YEAR] = year
        result[Calendar.MONTH] = month
        result[Calendar.DAY_OF_MONTH] = day
        result[Calendar.HOUR_OF_DAY] = 0
        result[Calendar.MINUTE] = 0
        result[Calendar.SECOND] = 0
        result[Calendar.MILLISECOND] = 0
        return result
    }

    private fun Calendar.inUtc(): Calendar {
        return inTimeZone(utcTimeZone)
    }

    private fun Calendar.inDefaultTimeZone(): Calendar {
        return inTimeZone(TimeZone.getDefault())
    }

    /**
     * Create a Calendar with the given time zone, but with year, month, day, hour, minute, and seconds
     * remaining the same as the original calendar. This is different from just setting the time zone field on a
     * Calendar object. This extension creates a new Calendar with a different timeInMillis than the original.
     */
    private fun Calendar.inTimeZone(timeZone : TimeZone) : Calendar {
        val result = GregorianCalendar(timeZone)
        result[Calendar.YEAR] = get(Calendar.YEAR)
        result[Calendar.MONTH] = get(Calendar.MONTH)
        result[Calendar.DAY_OF_MONTH] = get(Calendar.DAY_OF_MONTH)
        result[Calendar.HOUR_OF_DAY] = get(Calendar.HOUR_OF_DAY)
        result[Calendar.MINUTE] = get(Calendar.MINUTE)
        result[Calendar.SECOND] = get(Calendar.SECOND)
        result[Calendar.MILLISECOND] = 0
        return result
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
            val fsec = ((dayFraction * 10 - (fhour + fmin.toFloat() / 100)) * 10000).roundToInt()
            return intArrayOf(fhour, fmin, fsec)
        }

        /**
         * @return a triplet of the hour, minutes, and seconds of the time of the given date object.
         *         Returns three ints for hour, minutes, seconds, respectively. The hour is
         *         from 0 to 23, the minute is from 0 to 59, and the second is from
         *         0 to 59.
         */
        @JvmStatic
        fun get24HourTime(frenchDate: FrenchRevolutionaryCalendarDate): IntArray {
            val fhour = frenchDate.hour
            val fmin = frenchDate.minute
            val fsec = frenchDate.second

            val dayFraction = fhour.toFloat() / 10 + fmin.toFloat() / 1000 + fsec.toFloat() / 100000
            val ghour = (dayFraction * 24).toInt()
            val gmin = ((dayFraction * 24 - ghour) * 60).toInt()
            val gsec = ((dayFraction * 24 - (ghour + gmin.toFloat() / 60)) * 3600).roundToInt()
            return intArrayOf(ghour, gmin, gsec)
        }


        /**
         * @param gyear a year in the Gregorian calendar
         * @param timeZone the returned calendar object will have this time zone
         *
         * @return the day, in the Gregorian calendar, of the autumn equinox (at midnight) in the given timezone, for the given year, if the given year is between -1000 and 3000 inclusive. Returns null otherwise.
         */
        private fun getAutumnEquinox(gyear: Int, timeZone: TimeZone): Calendar? {
            // Create a date object for the autumn equinox date (at midnight) in the
            // given timezone.
            val gAutumnEquinoxDay = EquinoxDates.getAutumnEquinox(gyear) ?: return null
            val gAutumnEquinox = Calendar.getInstance(timeZone)
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
