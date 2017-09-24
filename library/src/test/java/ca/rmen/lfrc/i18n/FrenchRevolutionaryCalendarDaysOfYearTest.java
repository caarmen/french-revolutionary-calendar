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
package ca.rmen.lfrc.i18n;

import java.util.Locale;

import ca.rmen.lfrc.FrenchRevolutionaryCalendar;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * These tests make sure that we have the correct names for the days in the year.
 * 
 */
public class FrenchRevolutionaryCalendarDaysOfYearTest {

    @Test
    public void testNumberOfDaysOfYearFR() {
        assertEquals(FrenchRevolutionaryCalendarLabelsFR.DAY_OF_YEAR.length, 13);
        for (int i = 0; i < 12; i++) {
            assertEquals(FrenchRevolutionaryCalendarLabelsFR.DAY_OF_YEAR[i].length, 30);
        }
        assertEquals(FrenchRevolutionaryCalendarLabelsFR.DAY_OF_YEAR[12].length, 6);
    }

    @Test
    public void testNumberOfDaysOfYearEN() {
        assertEquals(FrenchRevolutionaryCalendarLabelsEN.DAY_OF_YEAR.length, 13);
        for (int i = 0; i < 12; i++) {
            assertEquals(FrenchRevolutionaryCalendarLabelsEN.DAY_OF_YEAR[i].length, 30);
        }
        assertEquals(FrenchRevolutionaryCalendarLabelsEN.DAY_OF_YEAR[12].length, 6);
    }

    @Test
    public void testNumberOfDaysOfYearES() {
        assertEquals(FrenchRevolutionaryCalendarLabelsES.DAY_OF_YEAR.length, 13);
        for (int i = 0; i < 12; i++) {
            assertEquals(FrenchRevolutionaryCalendarLabelsES.DAY_OF_YEAR[i].length, 30);
        }
        assertEquals(FrenchRevolutionaryCalendarLabelsES.DAY_OF_YEAR[12].length, 6);
    }

    @Test
    public void testNumberOfDaysOfYearCA() {
        assertEquals(FrenchRevolutionaryCalendarLabelsCA.DAY_OF_YEAR.length, 13);
        for (int i = 0; i < 12; i++) {
            assertEquals(FrenchRevolutionaryCalendarLabelsCA.DAY_OF_YEAR[i].length, 30);
        }
        assertEquals(FrenchRevolutionaryCalendarLabelsCA.DAY_OF_YEAR[12].length, 6);
    }

    @Test
    public void testNumberOfDaysOfYearIT() {
        assertEquals(FrenchRevolutionaryCalendarLabelsIT.DAY_OF_YEAR.length, 13);
        for (int i = 0; i < 12; i++) {
            assertEquals(FrenchRevolutionaryCalendarLabelsIT.DAY_OF_YEAR[i].length, 30);
        }
        assertEquals(FrenchRevolutionaryCalendarLabelsIT.DAY_OF_YEAR[12].length, 6);
    }

    @Test
    public void testNumberOfDaysOfYearEU() {
        assertEquals(FrenchRevolutionaryCalendarLabelsEU.DAY_OF_YEAR.length, 13);
        for (int i = 0; i < 12; i++) {
            assertEquals(FrenchRevolutionaryCalendarLabelsEU.DAY_OF_YEAR[i].length, 30);
        }
        assertEquals(FrenchRevolutionaryCalendarLabelsEU.DAY_OF_YEAR[12].length, 6);
    }

    @Test
    public void testDayOfYear1() {
        testDayOfYear(Locale.ENGLISH, 69, "Juniper");
    }

    @Test
    public void testDayOfYear2() {
        testDayOfYear(Locale.ENGLISH, 125, "Bull");
    }

    @Test
    public void testDayOfYear3() {
        testDayOfYear(Locale.ENGLISH, 366, "Revolution");
    }

    @Test
    public void testDayOfYear4() {
        testDayOfYear(Locale.ENGLISH, 1, "Grape");
    }

    @Test
    public void testDayOfYear5() {
        testDayOfYear(Locale.FRENCH, 360, "Panier");
    }

    @Test
    public void testDayOfYear6() {
        testDayOfYear(Locale.FRENCH, 302, "Bouillon blanc");
    }

    @Test
    public void testDayOfYear7() {
        testDayOfYear(Locale.FRENCH, 91, "Tourbe");
    }

    @Test
    public void testDayOfYear8() {
        testDayOfYear(Locale.FRENCH, 1, "Raisin");
    }

    @Test
    public void testDayOfYear9() {
        testDayOfYear(new Locale("es", "MX"), 31, "Manzana");
    }

    @Test
    public void testDayOfYear10() {
        testDayOfYear(new Locale("es", "MX"), 158, "Violeta");
    }

    @Test
    public void testDayOfYear11() {
        testDayOfYear(new Locale("es", "ES"), 211, "Rosa");
    }

    @Test
    public void testDayOfYear12() {
        testDayOfYear(new Locale("es", "ES"), 358, "Maíz");
    }

    @Test
    public void testDayOfYear13() {
        testDayOfYear(new Locale("ca", "ES"), 2, "Safrà");
    }

    @Test
    public void testDayOfYear14() {
        testDayOfYear(new Locale("ca", "ES"), 34, "Remolatxa");
    }

    @Test
    public void testDayOfYear15() {
        testDayOfYear(new Locale("ca", "ES"), 5, "Cavall");
    }

    @Test
    public void testDayOfYear16() {
        testDayOfYear(new Locale("ca", "ES"), 9, "Xirivia");
    }

    @Test
    public void testDayOfYear17() {
        testDayOfYear(new Locale("it", "IT"), 364, "Opinione");
    }

    @Test
    public void testDayOfYear18() {
        testDayOfYear(new Locale("it", "IT"), 79, "Sabina");
    }

    @Test
    public void testDayOfYear19() {
        testDayOfYear(new Locale("it", "IT"), 203, "Ippocastano");
    }

    @Test
    public void testDayOfYear20() {
        testDayOfYear(new Locale("de", "DE"), 49, "Granatapfel");
    }

    @Test
    public void testDayOfYear21() {
        testDayOfYear(new Locale("de", "DE"), 83, "Schilfrohr");
    }

    @Test
    public void testDayOfYear22() {
        testDayOfYear(new Locale("de", "DE"), 354, "Sorghum");
    }

    @Test
    public void testNumberDailyObjectTypeEN() {
        assert(FrenchRevolutionaryCalendarLabelsEN.DAILY_OBJECT_TYPES.length == FrenchRevolutionaryCalendar.DailyObjectType.values().length);
    }

    @Test
    public void testNumberDailyObjectTypeFR() {
        assert(FrenchRevolutionaryCalendarLabelsFR.DAILY_OBJECT_TYPES.length == FrenchRevolutionaryCalendar.DailyObjectType.values().length);
    }

    @Test
    public void testNumberDailyObjectTypeES() {
        assert(FrenchRevolutionaryCalendarLabelsES.DAILY_OBJECT_TYPES.length == FrenchRevolutionaryCalendar.DailyObjectType.values().length);
    }

    @Test
    public void testNumberDailyObjectTypeIT() {
        assert(FrenchRevolutionaryCalendarLabelsIT.DAILY_OBJECT_TYPES.length == FrenchRevolutionaryCalendar.DailyObjectType.values().length);
    }

    @Test
    public void testNumberDailyObjectTypeCA() {
        assert(FrenchRevolutionaryCalendarLabelsCA.DAILY_OBJECT_TYPES.length == FrenchRevolutionaryCalendar.DailyObjectType.values().length);
    }

    @Test
    public void testNumberDailyObjectTypeEU() {
        assert(FrenchRevolutionaryCalendarLabelsEU.DAILY_OBJECT_TYPES.length == FrenchRevolutionaryCalendar.DailyObjectType.values().length);
    }

    private void testDayOfYear(Locale locale, int dayOfYear, String expectedName) {
        FrenchRevolutionaryCalendarLabels labels = FrenchRevolutionaryCalendarLabels.getInstance(locale);
        assertEquals(expectedName, labels.getDayOfYear(dayOfYear));
    }

}
