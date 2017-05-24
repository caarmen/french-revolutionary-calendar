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
package ca.rmen.lfrc;

import ca.rmen.lfrc.FrenchRevolutionaryCalendar.CalculationMethod;
import ca.rmen.lfrc.FrenchRevolutionaryCalendar.DailyObjectType;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.fail;

/**
 * Validate date conversions between the Gregorian and French Revolutionary Calendars, using the von Madler method.
 */
public class FrenchRevolutionaryCalendarVonMadlerTest extends FrenchRevolutionaryCalendarTest {
    public FrenchRevolutionaryCalendarVonMadlerTest() {
        calculationMethod = CalculationMethod.VON_MADLER;
    }

    // https://en.wikipedia.org/wiki/French_Republican_Calendar#Converting_from_the_Gregorian_Calendar
    // The date of the Republican New Year remains the same (23 September) in the Gregorian calendar every year from 129 to 256 (AD 1920–2047)
    @Test
    public void testNewYears() throws ParseException {
        FrenchRevolutionaryCalendar frCal = new FrenchRevolutionaryCalendar(Locale.ENGLISH, CalculationMethod.VON_MADLER);
        int frenchYear = 129;
        List<String> failures = new ArrayList<String>();
        for (int gregorianYear = 1920; gregorianYear <= 2047; gregorianYear++) {

            // Verify 1er Vendemiaire: New Year
            GregorianCalendar gregDate = getGregorianDate(gregorianYear, 9, 23);
            FrenchRevolutionaryCalendarDate fcd = frCal.getDate(gregDate);
            if (fcd.year != frenchYear || fcd.month != 1 || fcd.dayOfMonth != 1){
                failures.add("Expected " + frenchYear + "-01-01 but got " + fcd.year + "-" + fcd.month + "-" + fcd.dayOfMonth);
            }

            // Verify the day before 1er Vendemiaire: the last day of the previous year
            gregDate.add(Calendar.DAY_OF_YEAR, -1);
            fcd = frCal.getDate(gregDate);
            int expectedDayOfMonth = (frenchYear - 1) % 4 == 0 && (frenchYear - 1) % 128 != 0 ? 6 : 5;
            if (fcd.year != frenchYear-1 || fcd.month != 13 || fcd.dayOfMonth != expectedDayOfMonth){
                failures.add("Expected " + (frenchYear-1) + "-13-" + expectedDayOfMonth + ", but got " + fcd.year + "-" + fcd.month + "-" + fcd.dayOfMonth);
            }

            // Verify 1er Frimaire: no more daylight savings
            gregDate.add(Calendar.DAY_OF_YEAR, 61);
            fcd = frCal.getDate(gregDate);
            if (fcd.year != frenchYear || fcd.month != 3 || fcd.dayOfMonth != 1){
                failures.add("Expected " + frenchYear + "-03-01 but got " + fcd.year + "-" + fcd.month + "-" + fcd.dayOfMonth);
            }

            // Verify 1er Pluviose: this is in the following Gregorian year (around January 20).
            gregDate.add(Calendar.DAY_OF_YEAR, 60);
            fcd = frCal.getDate(gregDate);
            if (fcd.year != frenchYear || fcd.month != 5 || fcd.dayOfMonth != 1){
                failures.add("Expected " + frenchYear + "-05-01 but got " + fcd.year + "-" + fcd.month + "-" + fcd.dayOfMonth);
            }
            frenchYear++;
        }

        if (!failures.isEmpty()) {
            StringBuilder failuresString = new StringBuilder();
            for (String failure : failures) {
                failuresString.append(failure).append("\n");
            }
            fail(failuresString.toString());
        }
    }

    private GregorianCalendar getGregorianDate(int year, int month, int day) {
        GregorianCalendar gregDate = new GregorianCalendar();
        gregDate.set(Calendar.YEAR, year);
        gregDate.set(Calendar.MONTH, month-1);
        gregDate.set(Calendar.DAY_OF_MONTH, day);
        gregDate.set(Calendar.HOUR_OF_DAY, 0);
        gregDate.set(Calendar.MINUTE, 0);
        gregDate.set(Calendar.SECOND, 0);
        gregDate.set(Calendar.MILLISECOND, 0);
        return gregDate;
    }

    @Test
    public void testFrenchDate1() throws Exception {
        validateDates("2016-09-23", "225-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate2() throws Exception {
        validateDates("1792-09-22", "1-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate3() throws Exception {
        validateDates("1791-09-24", "0-01-02", "Duodi", "Vendémiaire", "Safran", "Saffron", DailyObjectType.PLANT, 1);
    }
}
