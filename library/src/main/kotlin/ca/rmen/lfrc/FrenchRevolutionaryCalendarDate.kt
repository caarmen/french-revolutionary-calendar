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

import java.io.Serializable
import java.util.Locale

import ca.rmen.lfrc.FrenchRevolutionaryCalendar.DailyObjectType
import ca.rmen.lfrc.i18n.FrenchRevolutionaryCalendarLabels

/**
 * A timestamp in the french revolutionary calendar. Months are from 1 to 13,
 * days are from 1 to 30, hours are from 1 to 10, minutes and seconds are from 1
 * to 100.
 * @author calvarez
 */
/**
 * @param year The year in the French Revolutionary Calendar.
 *
 * @param month The month in the year, from 1 to 13
 *
 * @param dayOfMonth The day in the month, from 1 to 30.
 *
 * @param hour The hour of the day, from 0 to 9.
 *
 * @param minute The minute of the hour, from 0 to 100.
 *
 * @param second The second of the minute, from 0 to 100.
 */
data class FrenchRevolutionaryCalendarDate(
        @JvmField val locale: Locale,
        @JvmField val year: Int,
        @JvmField val month: Int,
        @JvmField val dayOfMonth: Int,
        @JvmField val hour: Int,
        @JvmField val minute: Int,
        @JvmField val second: Int) : Serializable {

    /**
     * A number from 1 to 10.
     */
    val dayInWeek: Int = (dayOfMonth - 1) % 10 + 1

    /**
     * A number from 1 to 3.
     */
    val weekInMonth: Int = (dayOfMonth - 1) / 10 + 1

    /**
     * The number of days since the beginning of the year, starting with 1.
     */
    val dayInYear: Int = (month - 1) * 30 + dayOfMonth

    val monthName: String = FrenchRevolutionaryCalendarLabels.getInstance(locale).getMonthName(month)

    val weekdayName: String = FrenchRevolutionaryCalendarLabels.getInstance(locale).getWeekdayName(dayInWeek)

    /**
     * The name of the object of the day in the year, in the given locale.
     */
    val objectOfTheDay: String = FrenchRevolutionaryCalendarLabels.getInstance(locale).getDayOfYear(month, dayOfMonth)

    val objectType: DailyObjectType

    val objectTypeName: String

    init {
        when {
            month == 13 -> objectType = DailyObjectType.CONCEPT
            dayOfMonth % 10 == 0 -> objectType = DailyObjectType.TOOL
            dayOfMonth %5 == 0 -> objectType = DailyObjectType.ANIMAL
            month == 4 -> objectType = DailyObjectType.MINERAL
            else -> objectType = DailyObjectType.PLANT
        }
        objectTypeName = FrenchRevolutionaryCalendarLabels.getInstance(locale).getDailyObjectTypeName(objectType)
    }
}
