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
package ca.rmen.lfrc.i18n

import ca.rmen.lfrc.FrenchRevolutionaryCalendar

import java.util.HashMap
import java.util.Locale

/**

 * Provides trnaslations of weekday names, month names, and day of year names.

 * All translations come from Wikipedia.
 * Wikipedia article: http://en.wikipedia.org/wiki/French_Republican_Calendar

 * @author calvarez
 */
internal abstract class FrenchRevolutionaryCalendarLabels internal constructor(private val weekdays: Array<String>, private val months: Array<String>, private val daysOfYear: Array<Array<String>>, private val dailyObjectTypes: Array<String>) {

    /**
     * @param weekday from 1 to 10.
     */
    fun getWeekdayName(weekday: Int): String {
        return weekdays[weekday - 1]
    }

    /**
     * @param month from 1 to 13
     */
    fun getMonthName(month: Int): String {
        return months[month - 1]
    }

    /**
     * @param month from 1 to 13
     *
     * @param dayOfMonth from 1 to 30
     */
    fun getDayOfYear(month: Int, dayOfMonth: Int): String {
        return daysOfYear[month - 1][dayOfMonth - 1]
    }

    /**
     * @param dayOfYear from 1 to 366
     */
    fun getDayOfYear(dayOfYear: Int): String { // NO_UCD (use default)
        val month = (dayOfYear - 1) / 30 + 1
        val dayOfMonth = dayOfYear - 1 - (month - 1) * 30 + 1
        return getDayOfYear(month, dayOfMonth)
    }

    fun getDailyObjectTypeName(type: FrenchRevolutionaryCalendar.DailyObjectType): String {
        return dailyObjectTypes[type.ordinal]
    }

    companion object {

        private val instances = HashMap<String, FrenchRevolutionaryCalendarLabels>()

        @JvmStatic
        fun getInstance(locale: Locale): FrenchRevolutionaryCalendarLabels {
            val language = locale.language
            var result: FrenchRevolutionaryCalendarLabels? = instances[language]
            if (result != null) return result
            when (language) {
                Locale.ENGLISH.language -> result = FrenchRevolutionaryCalendarLabelsEN()
                "es" -> result = FrenchRevolutionaryCalendarLabelsES()
                "ca" -> result = FrenchRevolutionaryCalendarLabelsCA()
                "it" -> result = FrenchRevolutionaryCalendarLabelsIT()
                "de" -> result = FrenchRevolutionaryCalendarLabelsDE()
                "eu" -> result = FrenchRevolutionaryCalendarLabelsEU()
                else -> result = FrenchRevolutionaryCalendarLabelsFR()
            }
            instances.put(language, result)
            return result

        }
    }
}
