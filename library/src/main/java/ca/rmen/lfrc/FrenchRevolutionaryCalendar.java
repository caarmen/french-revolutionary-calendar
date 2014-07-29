/*
 * French Revolutionary Calendar Library
 * 
 * Copyright (c) 2012-2014 Carmen Alvarez
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
package ca.rmen.lfrc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 
 * Provides conversion between Gregorian calendar dates and French Revolutionary Calendar dates.
 * Wikipedia article: http://en.wikipedia.org/wiki/French_Republican_Calendar
 * 
 * @author calvarez
 */
public class FrenchRevolutionaryCalendar { // NO_UCD (use default)

    public static enum CalculationMethod { // NO_UCD (use default)
        EQUINOX, ROMME
    };

    public static enum DailyObjectType { // NO_UCD (use default)
        PLANT, ANIMAL, TOOL, MINERAL, CONCEPT
    }

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String FRENCH_ERA_BEGIN = "1792-09-22 00:00:00";
    private static final String FRENCH_ERA_END = "1811-09-23 00:00:00";
    private static final long NUM_MILLISECONDS_IN_DAY = 1000 * 60 * 60 * 24;
    private static final int EQUINOX_MONTH = 8; // September

    private final Locale locale;
    private Calendar frenchEraBegin;
    private Calendar frenchEraEnd;
    private CalculationMethod calculationMethod;

    public FrenchRevolutionaryCalendar(Locale locale, CalculationMethod calculationMethod) {
        this.locale = locale;
        // How will we calculate the Gregorian date of the beginning of each
        // French year?
        setCalculationMethod(calculationMethod);

        // Read in the dates of the beginning and end of the French calendar,
        // for which equinoxes were used.
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        try {

            frenchEraBegin = Calendar.getInstance();
            frenchEraBegin.setTime(sdf.parse(FRENCH_ERA_BEGIN));
            frenchEraEnd = Calendar.getInstance();
            frenchEraEnd.setTime(sdf.parse(FRENCH_ERA_END));
        } catch (ParseException e) {
            debug("Error reading French epoch " + FRENCH_ERA_BEGIN, e);
        }
    }

    /**
     * @param calculationMethod
     *            the method to calculate the first day of the French
     *            year, in the Gregorian calendar.
     */
    public void setCalculationMethod(CalculationMethod calculationMethod) {
        this.calculationMethod = calculationMethod;
    }

    /**
     * @param gregorianDate
     * @return the French date corresponding to the Gregorian calendar date.
     */
    public FrenchRevolutionaryCalendarDate getDate(GregorianCalendar gregorianDate) {

        // Determine the method of calculating the first day of the French year.

        // If we are using the equinox calculation method, or if we are within the dates the
        // calendar was used (regardless of the selected calculation method), use the equinox
        // calculation method.
        FrenchRevolutionaryCalendarDate result = null;
        if (calculationMethod == CalculationMethod.EQUINOX || (gregorianDate.after(frenchEraBegin) && gregorianDate.before(frenchEraEnd))) {
            result = getDateEquinox(gregorianDate);
        } else if (calculationMethod == CalculationMethod.ROMME) {
            result = getDateRomme(gregorianDate);
        } else {
            throw new IllegalArgumentException("Can't convert date " + gregorianDate + " using method " + calculationMethod);
        }
        // Get the decimal time portion of the French date
        if (result != null) {
            int[] timeInDay = getFrenchTime(gregorianDate);
            result = new FrenchRevolutionaryCalendarDate(locale, result.year, result.month, result.dayOfMonth, timeInDay[0], timeInDay[1], timeInDay[2]);
        }
        return result;
    }

    /**
     * @param gregorianDate
     * @return The French date corresponding to the Gregorian calendar date,
     *         using the equinox method to determine the Gregorian date for the
     *         first day of the French year.
     */
    private FrenchRevolutionaryCalendarDate getDateEquinox(Calendar gregorianDate) {

        int gyear = gregorianDate.get(Calendar.YEAR);
        Calendar gAutumnEquinox = getAutumnEquinox(gyear);
        if (gAutumnEquinox == null) throw new IllegalArgumentException("Date not supported: " + gregorianDate);

        // Determine the first day of the French year.
        Calendar g1stVendemiaire = gAutumnEquinox;
        // Case 1, date from January to September, use the equinox date from
        // last year
        if (gregorianDate.compareTo(gAutumnEquinox) < 0) {
            g1stVendemiaire = getAutumnEquinox(gyear - 1);
            if (g1stVendemiaire == null) throw new IllegalArgumentException("Date not supported: " + gregorianDate);
        }
        // Case 2, date from September to December
        else {

        }
        // The year in the French date: Gregorian year at 1st vendemiaire -
        // 1792. This is the number of years elapsed since the French calendar
        // had been started.
        int frenchYear = g1stVendemiaire.get(Calendar.YEAR) - frenchEraBegin.get(Calendar.YEAR) + 1;
        // The DAY_OF_YEAR in the French year, from 0 to 365. This is the
        // number of days elapsed 1stVendemiaire of the French year and the
        // given timestamp.
        int numberDaysInFrenchYear = (int) ((gregorianDate.getTimeInMillis() - g1stVendemiaire.getTimeInMillis()) / NUM_MILLISECONDS_IN_DAY);
        // Create and return the French calendar object.
        FrenchRevolutionaryCalendarDate result = getFrenchDate(frenchYear, numberDaysInFrenchYear);
        return result;
    }

    /**
     * @param gregorianDate
     * @return The French date corresponding to the Gregorian calendar date,
     *         using the Romme method to determine the Gregorian date for the
     *         first day of the French year.
     */
    private FrenchRevolutionaryCalendarDate getDateRomme(Calendar gregorianDate) {
        // Time elapsed between the end of the French calendar and the given
        // date. We have to include the daylight savings offset, because back in
        // 1792-1811,
        // daylight savings time wasn't being used. If we don't take into
        // account the offset, a calculation like 8/5/1996 00:00:00 - 8/5/1796
        // 00:00:00 will not return 200 years, but 200 years - 1 hour, which is
        // not
        // the desired result.
        long numMillisSinceEndOfFrenchEra = gregorianDate.getTimeInMillis() - frenchEraEnd.getTimeInMillis() + gregorianDate.get(Calendar.DST_OFFSET);

        // The Romme method applies the same
        // rules (mostly) of the Gregorian calendar to the French calendar.
        // One difference is the year 4000, which is a leap year in the
        // Gregorian system, but not in the French system. For now we ignore
        // this difference. We may address it and fix this code in the year
        // 3999 :)

        // Create a fake calendar object (fake because this is not really a
        // Gregorian date), corresponding to the end of the French calendar era.
        // This was in the French year 20. Since in the Gregorian year 20, there
        // were no leap years yet, we add 10000 to the year, so that the
        // Gregorian calendar implementation can handle the leap years.

        // The end of the French calendar system was the beginning of the year
        // 20.
        long fakeEndFrenchEraTimestamp = new GregorianCalendar(10020, 0, 1).getTimeInMillis();
        // Add the elapsed time to the French date.
        long fakeFrenchTimestamp = fakeEndFrenchEraTimestamp + numMillisSinceEndOfFrenchEra;

        // Create a calendar object for the French date
        Calendar fakeFrenchDate = Calendar.getInstance();
        fakeFrenchDate.setTimeInMillis(fakeFrenchTimestamp);

        // Extract the year, and day in year from the French date.
        int frenchYear = fakeFrenchDate.get(Calendar.YEAR);
        int frenchDayInYear = fakeFrenchDate.get(Calendar.DAY_OF_YEAR);

        // Create and return a French calendar object.
        FrenchRevolutionaryCalendarDate result = getFrenchDate(frenchYear - 10000, frenchDayInYear - 1);
        return result;
    }

    /**
     * @param frenchYear
     *            the year in the French calendar
     * @param numberDaysInFrenchYear
     *            number of days since 1st Vendemiare of the given year,
     *            starting with 0.
     * @return A French calendar object for the given French day.
     */
    private FrenchRevolutionaryCalendarDate getFrenchDate(int frenchYear, int numberDaysInFrenchYear) {
        // Find the month in the French year, starting from 0.
        int numberMonthInFrenchYear = numberDaysInFrenchYear / 30;

        // Find the day in the French month, starting from 0.
        int numberDaysInFrenchMonth = numberDaysInFrenchYear - (numberMonthInFrenchYear * 30);

        // Create and return the French calendar object.
        FrenchRevolutionaryCalendarDate result = new FrenchRevolutionaryCalendarDate(locale, frenchYear, numberMonthInFrenchYear + 1,
                numberDaysInFrenchMonth + 1, 0, 0, 0);
        return result;
    }

    /**
     * @param gtime
     * @return a decimal representation of the time within this day. Returns
     *         three ints for hour, minutes, seconds, respectively. The hour is
     *         from 0 to 9, the minute is from 0 to 99, and the second is from
     *         0 to 99.
     */
    public static int[] getFrenchTime(Calendar gtime) {
        int ghour = gtime.get(Calendar.HOUR_OF_DAY);
        int gmin = gtime.get(Calendar.MINUTE);
        int gsec = gtime.get(Calendar.SECOND);

        float dayFraction = ((float) ghour / 24) + ((float) gmin / 1440) + ((float) gsec / 86400);
        int fhour = (int) (dayFraction * 10);
        int fmin = (int) ((dayFraction * 10 - fhour) * 100);
        int fsec = (int) ((dayFraction * 10 - (fhour + (float) fmin / 100)) * 10000);
        return new int[] { fhour, fmin, fsec };
    }

    /**
     * @param year
     *            a year in the Gregorian calendar
     * @return the day, in the Gregorian calendar, of the autumn equinox (at
     *         midnight) in the current timezone, for the given year.
     */
    private Calendar getAutumnEquinox(int gyear) {
        // Create a date object for the autumn equinox date (at midnight) in the
        // current timezone.
        Integer gAutumnEquinoxDay = EquinoxDates.getAutumnEquinox(gyear);
        if (gAutumnEquinoxDay == null) return null;
        Calendar gAutumnEquinox = Calendar.getInstance();
        gAutumnEquinox.set(Calendar.YEAR, gyear);
        gAutumnEquinox.set(Calendar.MONTH, EQUINOX_MONTH);
        gAutumnEquinox.set(Calendar.DAY_OF_MONTH, gAutumnEquinoxDay);
        gAutumnEquinox.set(Calendar.HOUR_OF_DAY, 0);
        gAutumnEquinox.set(Calendar.MINUTE, 0);
        gAutumnEquinox.set(Calendar.SECOND, 0);
        gAutumnEquinox.set(Calendar.MILLISECOND, 0);
        return gAutumnEquinox;
    }

    private void debug(Object o, Throwable t) {
        System.out.println(getClass().getName() + ": " + o);
        if (t != null) t.printStackTrace();
    }
}
