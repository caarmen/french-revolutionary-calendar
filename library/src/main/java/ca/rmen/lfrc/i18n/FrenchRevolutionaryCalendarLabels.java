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
package ca.rmen.lfrc.i18n;

import ca.rmen.lfrc.FrenchRevolutionaryCalendar;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 
 * Provides trnaslations of weekday names, month names, and day of year names.
 * 
 * All translations come from Wikipedia.
 * Wikipedia article: http://en.wikipedia.org/wiki/French_Republican_Calendar
 * 
 * @author calvarez
 */
public abstract class FrenchRevolutionaryCalendarLabels {

    private static Map<String, FrenchRevolutionaryCalendarLabels> instances = new HashMap<String, FrenchRevolutionaryCalendarLabels>();

    private final String[] weekdays;
    private final String[] months;
    private final String[][] daysOfYear;
    private final String[] dailyObjectTypes;

    FrenchRevolutionaryCalendarLabels(String[] weekdays, String[] months, String[][] daysOfYear, String[] dailyObjectTypes) {
        this.weekdays = weekdays;
        this.months = months;
        this.daysOfYear = daysOfYear;
        this.dailyObjectTypes = dailyObjectTypes;
    }

    /**
     * @param weekday from 1 to 10.
     */
    public String getWeekdayName(int weekday) {
        return weekdays[weekday - 1];
    }

    /**
     * @param month from 1 to 13
     */
    public String getMonthName(int month) {
        return months[month - 1];
    }

    /**
     * @param month from 1 to 13
     * @param dayOfMonth from 1 to 30
     */
    public String getDayOfYear(int month, int dayOfMonth) {
        return daysOfYear[month - 1][dayOfMonth - 1];
    }

    /**
     * @param dayOfYear from 1 to 366
     */
    public String getDayOfYear(int dayOfYear) { // NO_UCD (use default)
        int month = ((dayOfYear - 1) / 30) + 1;
        int dayOfMonth = ((dayOfYear - 1) - (month - 1) * 30) + 1;
        return getDayOfYear(month, dayOfMonth);
    }

    public String getDailyObjectTypeName(FrenchRevolutionaryCalendar.DailyObjectType type) {
        return dailyObjectTypes[type.ordinal()];
    }

    public static FrenchRevolutionaryCalendarLabels getInstance(Locale locale) {
        String language = locale.getLanguage();
        FrenchRevolutionaryCalendarLabels result = instances.get(language);
        if (result != null) return result;
        if (Locale.ENGLISH.getLanguage().equals(language)) result = new FrenchRevolutionaryCalendarLabelsEN();
        else if ("es".equals(language)) result = new FrenchRevolutionaryCalendarLabelsES();
        else if ("ca".equals(language)) result = new FrenchRevolutionaryCalendarLabelsCA();
        else if ("it".equals(language)) result = new FrenchRevolutionaryCalendarLabelsIT();
        else if ("de".equals(language)) result = new FrenchRevolutionaryCalendarLabelsDE();
        else
            result = new FrenchRevolutionaryCalendarLabelsFR();
        instances.put(language, result);
        return result;

    }
}
