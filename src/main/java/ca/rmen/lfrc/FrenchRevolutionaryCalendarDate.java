/*
 * French Revolutionary Calendar Library
 * 
 * Copyright (2012-2014) Carmen Alvarez
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

/**
 * A timestamp in the french revolutionary calendar. Months are from 1 to 13,
 * days are from 1 to 30, hours are from 1 to 10, minutes and seconds are from 1
 * to 100.
 * 
 * @author calvarez
 * 
 */
public class FrenchRevolutionaryCalendarDate {
    public final int year;
    public final int month;
    public final int dayOfMonth;
    public final int hour;
    public final int minute;
    public final int second;

    /**
     * @param year The year in the French Revolutionary Calendar.
     * @param month The month in the year, from 1 to 11
     * @param dayOfMonth The day in the month, from 1 to 30.
     * @param hour The hour of the day, from 0 to 9.
     * @param minute The minute of the hour, from 0 to 100.
     * @param second The second of the minute, from 0 to 100.
     */
    public FrenchRevolutionaryCalendarDate(int year, int month, int dayOfMonth, int hour, int minute, int second) {
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
        return (this.dayOfMonth - 1) % 10 + 1;
    }

    /**
     * @return a number from 1 to 3.
     */
    public int getWeekInMonth() {
        return this.dayOfMonth % 10;
    }

    public String getMonthName() {
        return FrenchRevolutionaryCalendar.MONTHS[month - 1];

    }

    public String getWeekdayName() {
        // The day of the week starting from 0:
        int dayInWeek = (dayOfMonth - 1) % 10;
        return FrenchRevolutionaryCalendar.WEEKDAYS[dayInWeek];
    }

    public String toString() {
        return year + "-" + (month) + "-" + (dayOfMonth) + " " + hour + ":" + minute + ":" + second;
    }
}
