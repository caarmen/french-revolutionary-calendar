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

            // Verify 2 Vendemiaire
            GregorianCalendar gregDate = getGregorianDate(gregorianYear, 9, 24);
            FrenchRevolutionaryCalendarDate fcd = frCal.getDate(gregDate);
            if (fcd.year != frenchYear || fcd.month != 1 || fcd.dayOfMonth != 2) {
                failures.add("Expected " + frenchYear + "-01-02 but got " + fcd.year + "-" + fcd.month + "-" + fcd.dayOfMonth);
            }
            fcd = new FrenchRevolutionaryCalendarDate(Locale.ENGLISH, frenchYear, 1, 2, 0, 0, 0);
            GregorianCalendar reverseGregDate = frCal.getDate(fcd);
            if (reverseGregDate.get(Calendar.YEAR) != gregorianYear || reverseGregDate.get(Calendar.MONTH) != 8 || reverseGregDate.get(Calendar.DAY_OF_MONTH) != 24) {
                failures.add("Expected " + gregorianYear + "-09-24 but got " + reverseGregDate.get(Calendar.YEAR) + "-" + (reverseGregDate.get(Calendar.MONTH) + 1) + "-" + reverseGregDate.get(Calendar.DAY_OF_MONTH));
            }

            // Verify 9 Vendemiaire
            gregDate = getGregorianDate(gregorianYear, 10, 1);
            fcd = frCal.getDate(gregDate);
            if (fcd.year != frenchYear || fcd.month != 1 || fcd.dayOfMonth != 9) {
                failures.add("Expected " + frenchYear + "-01-09 but got " + fcd.year + "-" + fcd.month + "-" + fcd.dayOfMonth);
            }
            fcd = new FrenchRevolutionaryCalendarDate(Locale.ENGLISH, frenchYear, 1, 9, 0, 0, 0);
            reverseGregDate = frCal.getDate(fcd);
            if (reverseGregDate.get(Calendar.YEAR) != gregorianYear || reverseGregDate.get(Calendar.MONTH) != 9 || reverseGregDate.get(Calendar.DAY_OF_MONTH) != 1) {
                failures.add("Expected " + gregorianYear + "-10-01 but got " + reverseGregDate.get(Calendar.YEAR) + "-" + (reverseGregDate.get(Calendar.MONTH) + 1) + "-" + reverseGregDate.get(Calendar.DAY_OF_MONTH));
            }

            // Verify 1er Vendemiaire: New Year
            gregDate = getGregorianDate(gregorianYear, 9, 23);
            fcd = frCal.getDate(gregDate);
            if (fcd.year != frenchYear || fcd.month != 1 || fcd.dayOfMonth != 1) {
                failures.add("Expected " + frenchYear + "-01-01 but got " + fcd.year + "-" + fcd.month + "-" + fcd.dayOfMonth);
            }
            fcd = new FrenchRevolutionaryCalendarDate(Locale.ENGLISH, frenchYear, 1, 1, 0, 0, 0);
            reverseGregDate = frCal.getDate(fcd);
            if (reverseGregDate.get(Calendar.YEAR) != gregorianYear || reverseGregDate.get(Calendar.MONTH) != 8 || reverseGregDate.get(Calendar.DAY_OF_MONTH) != 23) {
                failures.add("Expected " + gregorianYear + "-09-23 but got " + reverseGregDate.get(Calendar.YEAR) + "-" + (reverseGregDate.get(Calendar.MONTH) + 1) + "-" + reverseGregDate.get(Calendar.DAY_OF_MONTH));
            }

            // Verify the day before 1er Vendemiaire: the last day of the previous year
            gregDate.add(Calendar.DAY_OF_YEAR, -1);
            fcd = frCal.getDate(gregDate);
            int expectedDayOfMonth = (frenchYear - 1) % 4 == 0 && (frenchYear - 1) % 128 != 0 ? 6 : 5;
            if (fcd.year != frenchYear - 1 || fcd.month != 13 || fcd.dayOfMonth != expectedDayOfMonth) {
                failures.add("Expected " + (frenchYear - 1) + "-13-" + expectedDayOfMonth + ", but got " + fcd.year + "-" + fcd.month + "-" + fcd.dayOfMonth);
            }
            boolean isPreviousYearLeapYear = ((frenchYear - 1) % 4 == 0) && ((frenchYear - 1) % 128 != 0);
            int lastDayOfWeekOfLastYear = isPreviousYearLeapYear ? 6 : 5;
            fcd = new FrenchRevolutionaryCalendarDate(Locale.ENGLISH, frenchYear - 1, 13, lastDayOfWeekOfLastYear, 0, 0, 0);
            reverseGregDate = frCal.getDate(fcd);
            if (reverseGregDate.get(Calendar.YEAR) != gregorianYear || reverseGregDate.get(Calendar.MONTH) != 8 || reverseGregDate.get(Calendar.DAY_OF_MONTH) != 22) {
                failures.add("Expected " + gregorianYear + "-09-22 but got " + reverseGregDate.get(Calendar.YEAR) + "-" + (reverseGregDate.get(Calendar.MONTH) + 1) + "-" + reverseGregDate.get(Calendar.DAY_OF_MONTH));
            }

            // Verify 1er Frimaire: no more daylight savings
            gregDate.add(Calendar.DAY_OF_YEAR, 61);
            fcd = frCal.getDate(gregDate);
            if (fcd.year != frenchYear || fcd.month != 3 || fcd.dayOfMonth != 1) {
                failures.add("Expected " + frenchYear + "-03-01 but got " + fcd.year + "-" + fcd.month + "-" + fcd.dayOfMonth);
            }
            // non-daylight savings times only seem to work after 1940...
            fcd = new FrenchRevolutionaryCalendarDate(Locale.ENGLISH, frenchYear, 3, 1, 0, 0, 0);
            reverseGregDate = frCal.getDate(fcd);
            if (reverseGregDate.get(Calendar.YEAR) != gregorianYear || reverseGregDate.get(Calendar.MONTH) != 10 || reverseGregDate.get(Calendar.DAY_OF_MONTH) != 22) {
                failures.add("Expected " + gregorianYear + "-11-22 but got " + reverseGregDate.get(Calendar.YEAR) + "-" + (reverseGregDate.get(Calendar.MONTH) + 1) + "-" + reverseGregDate.get(Calendar.DAY_OF_MONTH));
            }

            // Verify 1er Pluviose: this is in the following Gregorian year (around January 20).
            gregDate.add(Calendar.DAY_OF_YEAR, 60);
            fcd = frCal.getDate(gregDate);
            if (fcd.year != frenchYear || fcd.month != 5 || fcd.dayOfMonth != 1) {
                failures.add("Expected " + frenchYear + "-05-01 but got " + fcd.year + "-" + fcd.month + "-" + fcd.dayOfMonth);
            }
            // non-daylight savings times only seem to work after 1940...
            fcd = new FrenchRevolutionaryCalendarDate(Locale.ENGLISH, frenchYear, 5, 1, 0, 0, 0);
            reverseGregDate = frCal.getDate(fcd);
            if (reverseGregDate.get(Calendar.YEAR) != (gregorianYear + 1) || reverseGregDate.get(Calendar.MONTH) != 0 || reverseGregDate.get(Calendar.DAY_OF_MONTH) != 21) {
                failures.add("Expected " + (gregorianYear + 1) + "-1-21 but got " + reverseGregDate.get(Calendar.YEAR) + "-" + (reverseGregDate.get(Calendar.MONTH) + 1) + "-" + reverseGregDate.get(Calendar.DAY_OF_MONTH));
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
        gregDate.set(Calendar.MONTH, month - 1);
        gregDate.set(Calendar.DAY_OF_MONTH, day);
        gregDate.set(Calendar.HOUR_OF_DAY, 0);
        gregDate.set(Calendar.MINUTE, 0);
        gregDate.set(Calendar.SECOND, 0);
        gregDate.set(Calendar.MILLISECOND, 0);
        return gregDate;
    }

    // https://en.wikipedia.org/wiki/French_Republican_Calendar
    @Test
    public void testFrenchDate1() throws Exception {
        validateDates("1806-09-23", "15-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate2() throws Exception {
        validateDates("1807-09-24", "16-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate3() throws Exception {
        validateDates("1808-09-23", "17-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate4() throws Exception {
        validateDates("1809-09-23", "18-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate5() throws Exception {
        validateDates("1810-09-23", "19-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate6() throws Exception {
        validateDates("1811-09-23", "20-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate7() throws Exception {
        validateDates("2016-09-23", "225-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate8() throws Exception {
        validateDates("2017-09-23", "226-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate9() throws Exception {
        validateDates("2018-09-23", "227-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate10() throws Exception {
        validateDates("2019-09-23", "228-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }


    // Other dates
    @Test
    public void testFrenchDate17910924() throws Exception {
        validateDates("1791-09-24", "0-01-02", "Duodi", "Vendémiaire", "Safran", "Saffron", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate17920922() throws Exception {
        validateDates("1792-09-22", "1-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate19201921() throws Exception {
        validateDates("1920-09-22", "128-13-05", "Quintidi", "Sansculotides", "Récompenses", "Honors", DailyObjectType.CONCEPT, 1);
        validateDates("1920-09-23", "129-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
        validateDates("1920-10-23", "129-02-01", "Primidi", "Brumaire", "Pomme", "Apple", DailyObjectType.PLANT, 1);
        validateDates("1920-10-24", "129-02-02", "Duodi", "Brumaire", "Céleri", "Celery", DailyObjectType.PLANT, 1);
        validateDates("1920-10-25", "129-02-03", "Tridi", "Brumaire", "Poire", "Pear", DailyObjectType.PLANT, 1);
        validateDates("1920-10-28", "129-02-06", "Sextidi", "Brumaire", "Héliotrope", "Heliotrope", DailyObjectType.PLANT, 1);
        validateDates("1920-11-07", "129-02-16", "Sextidi", "Brumaire", "Chervis", "Skirret", DailyObjectType.PLANT, 2);
        validateDates("1920-11-22", "129-03-01", "Primidi", "Frimaire", "Raiponce", "Rampion", DailyObjectType.PLANT, 1);
        validateDates("1921-01-21", "129-05-01", "Primidi", "Pluviôse", "Lauréole", "Spurge-laurel", DailyObjectType.PLANT, 1);
        validateDates("1921-02-20", "129-06-01", "Primidi", "Ventôse", "Tussilage", "Coltsfoot", DailyObjectType.PLANT, 1);
        validateDates("1921-03-12", "129-06-21", "Primidi", "Ventôse", "Mandragore", "Mandrake", DailyObjectType.PLANT, 3);
        validateDates("1921-03-14", "129-06-23", "Tridi", "Ventôse", "Cochléaria", "Scurvy-grass", DailyObjectType.PLANT, 3);
        validateDates("1921-03-15", "129-06-24", "Quartidi", "Ventôse", "Pâquerette", "Daisy", DailyObjectType.PLANT, 3);
        validateDates("1921-03-17", "129-06-26", "Sextidi", "Ventôse", "Pissenlit", "Dandelion", DailyObjectType.PLANT, 3);
        validateDates("1921-03-22", "129-07-01", "Primidi", "Germinal", "Primevère", "Primrose", DailyObjectType.PLANT, 1);
        validateDates("1921-09-23", "130-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate19220923() throws Exception {
        validateDates("1922-09-23", "131-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate19230923() throws Exception {
        validateDates("1923-09-23", "132-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate19240923() throws Exception {
        validateDates("1924-09-23", "133-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate1939XXXX() throws Exception {
        validateDates("1939-04-15", "147-07-25", "Quintidi", "Germinal", "Pigeon", "Pigeon", DailyObjectType.ANIMAL, 3);
        validateDates("1939-04-16", "147-07-26", "Sextidi", "Germinal", "Lilas", "Lilac", DailyObjectType.PLANT, 3);
        validateDates("1939-04-20", "147-07-30", "Décadi", "Germinal", "Greffoir", "Knife", DailyObjectType.TOOL, 3);
        validateDates("1939-04-21", "147-08-01", "Primidi", "Floréal", "Rose", "Rose", DailyObjectType.PLANT, 1);
        validateDates("1939-09-18", "147-13-01", "Primidi", "Sansculotides", "Vertu", "Virtue", DailyObjectType.CONCEPT, 1);
        validateDates("1939-09-22", "147-13-05", "Quintidi", "Sansculotides", "Récompenses", "Honors", DailyObjectType.CONCEPT, 1);
        validateDates("1939-09-23", "148-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
        validateDates("1939-10-23", "148-02-01", "Primidi", "Brumaire", "Pomme", "Apple", DailyObjectType.PLANT, 1);
        validateDates("1939-10-31", "148-02-09", "Nonidi", "Brumaire", "Alisier", "Chequer Tree", DailyObjectType.PLANT, 1);
        validateDates("1939-11-01", "148-02-10", "Décadi", "Brumaire", "Charrue", "Plough", DailyObjectType.TOOL, 1);
        validateDates("1939-11-11", "148-02-20", "Décadi", "Brumaire", "Herse", "Harrow", DailyObjectType.TOOL, 2);
        validateDates("1939-11-16", "148-02-25", "Quintidi", "Brumaire", "Faisan", "Pheasant", DailyObjectType.ANIMAL, 3);
        validateDates("1939-11-18", "148-02-27", "Septidi", "Brumaire", "Macjonc", "Tuberous pea", DailyObjectType.PLANT, 3);
        validateDates("1939-11-19", "148-02-28", "Octidi", "Brumaire", "Coing", "Quince", DailyObjectType.PLANT, 3);
        validateDates("1939-11-21", "148-02-30", "Décadi", "Brumaire", "Rouleau", "Roller", DailyObjectType.TOOL, 3);
    }

    @Test
    public void testFrenchDateXXXX1023() throws Exception {

        validateDates("1920-10-23", "129-02-01", "Primidi", "Brumaire", "Pomme", "Apple", DailyObjectType.PLANT, 1);
        validateDates("1921-10-23", "130-02-01", "Primidi", "Brumaire", "Pomme", "Apple", DailyObjectType.PLANT, 1);
        validateDates("1922-10-23", "131-02-01", "Primidi", "Brumaire", "Pomme", "Apple", DailyObjectType.PLANT, 1);
        validateDates("1923-10-23", "132-02-01", "Primidi", "Brumaire", "Pomme", "Apple", DailyObjectType.PLANT, 1);
        validateDates("1930-10-23", "139-02-01", "Primidi", "Brumaire", "Pomme", "Apple", DailyObjectType.PLANT, 1);
        validateDates("1935-10-23", "144-02-01", "Primidi", "Brumaire", "Pomme", "Apple", DailyObjectType.PLANT, 1);
        validateDates("1937-10-23", "146-02-01", "Primidi", "Brumaire", "Pomme", "Apple", DailyObjectType.PLANT, 1);
        validateDates("1938-10-23", "147-02-01", "Primidi", "Brumaire", "Pomme", "Apple", DailyObjectType.PLANT, 1);
        validateDates("1939-10-23", "148-02-01", "Primidi", "Brumaire", "Pomme", "Apple", DailyObjectType.PLANT, 1);
        validateDates("1940-10-23", "149-02-01", "Primidi", "Brumaire", "Pomme", "Apple", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate19700923() throws Exception {
        validateDates("1970-09-23", "179-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate19810923() throws Exception {
        validateDates("1981-09-23", "190-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate19870923() throws Exception {
        validateDates("1987-09-23", "196-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate19900923() throws Exception {
        validateDates("1990-09-23", "199-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate19910923() throws Exception {
        validateDates("1991-09-23", "200-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate19920923() throws Exception {
        validateDates("1992-09-23", "201-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate19930923() throws Exception {
        validateDates("1993-09-23", "202-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDate2016XXXX() throws Exception {
        validateDates("2016-08-18", "224-12-01", "Primidi", "Fructidor", "Prune", "Plum", DailyObjectType.PLANT, 1);
        validateDates("2016-09-17", "224-13-01", "Primidi", "Sansculotides", "Vertu", "Virtue", DailyObjectType.CONCEPT, 1);
        validateDates("2016-09-22", "224-13-06", "Sextidi", "Sansculotides", "Révolution", "Revolution", DailyObjectType.CONCEPT, 1);
        validateDates("2016-09-24", "225-01-02", "Duodi", "Vendémiaire", "Safran", "Saffron", DailyObjectType.PLANT, 1);
        validateDates("2016-10-29", "225-02-07", "Septidi", "Brumaire", "Figue", "Common Fig", DailyObjectType.PLANT, 1);
        validateDates("2016-10-30", "225-02-08", "Octidi", "Brumaire", "Scorsonère", "Black Salsify", DailyObjectType.PLANT, 1);
        validateDates("2016-10-31", "225-02-09", "Nonidi", "Brumaire", "Alisier", "Chequer Tree", DailyObjectType.PLANT, 1);
        validateDates("2016-11-11", "225-02-20", "Décadi", "Brumaire", "Herse", "Harrow", DailyObjectType.TOOL, 2);
    }

    @Test
    public void testFrenchDate20170401() throws Exception {
        validateDates("2017-04-01", "225-07-11", "Primidi", "Germinal", "Pervenche", "Periwinkle", DailyObjectType.PLANT, 2);
    }

}
