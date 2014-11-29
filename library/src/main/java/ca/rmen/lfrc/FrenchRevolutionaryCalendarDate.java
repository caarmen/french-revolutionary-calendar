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

import java.util.Locale;

import ca.rmen.lfrc.FrenchRevolutionaryCalendar.DailyObjectType;
import ca.rmen.lfrc.i18n.FrenchRevolutionaryCalendarLabels;

/**
 * A timestamp in the french revolutionary calendar. Months are from 1 to 13,
 * days are from 1 to 30, hours are from 1 to 10, minutes and seconds are from 1
 * to 100.
 * 
 * @author calvarez
 * 
 */
public class FrenchRevolutionaryCalendarDate { // NO_UCD (use default)
    private final Locale locale;
    public final int year;
    public final int month;
    public final int dayOfMonth;
    public final int hour;
    public final int minute;
    public final int second;

    /**
     * @param year The year in the French Revolutionary Calendar.
     * @param month The month in the year, from 1 to 13
     * @param dayOfMonth The day in the month, from 1 to 30.
     * @param hour The hour of the day, from 0 to 9.
     * @param minute The minute of the hour, from 0 to 100.
     * @param second The second of the minute, from 0 to 100.
     */
    public FrenchRevolutionaryCalendarDate(Locale locale, int year, int month, int dayOfMonth, int hour, int minute, int second) {
        this.locale = locale;
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    /**
     * @return a number from 1 to 10.
     */
    public int getDayInWeek() {
        return (dayOfMonth - 1) % 10 + 1;
    }

    /**
     * @return a number from 1 to 3.
     */
    public int getWeekInMonth() {
        return ((dayOfMonth - 1) / 10) + 1;
    }

    public DailyObjectType getObjectType() {
        if (month == 13) return DailyObjectType.CONCEPT;
        else if (dayOfMonth % 10 == 0) return DailyObjectType.TOOL;
        else if (dayOfMonth % 5 == 0) return DailyObjectType.ANIMAL;
        else if (month == 4) return DailyObjectType.MINERAL;
        else
            return DailyObjectType.PLANT;
    }

    public String getMonthName() {
        return FrenchRevolutionaryCalendarLabels.getInstance(locale).getMonthName(month);
    }

    public String getWeekdayName() {
        // The day of the week starting from 1:
        int dayInWeek = (dayOfMonth - 1) % 10 + 1;
        return FrenchRevolutionaryCalendarLabels.getInstance(locale).getWeekdayName(dayInWeek);
    }

    /**
     * @return the name of this day in the year, in French
     */
    public String getDayOfYear() {
        return FrenchRevolutionaryCalendarLabels.getInstance(locale).getDayOfYear(month, dayOfMonth);
    }

    public String toString() {
        String timeString = String.format("%d:%02d:%02d", hour, minute, second);
        return getWeekdayName() + ", " + dayOfMonth + "-" + (getMonthName()) + "-" + (year) + ", " + timeString + ", "
                + getObjectType().name().toLowerCase(locale) + ":" + getDayOfYear();
    }
}
