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

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import junit.framework.TestCase;
import ca.rmen.lfrc.FrenchRevolutionaryCalendar.CalculationMethod;
import ca.rmen.lfrc.FrenchRevolutionaryCalendar.DailyObjectType;

public abstract class FrenchRevolutionaryCalendarTest extends TestCase {

    private final SimpleDateFormat simpleDateFormat;
    private final SimpleDateFormat simpleDateTimeFormat;
    private final FrenchRevolutionaryCalendar frcalFR;
    private final FrenchRevolutionaryCalendar frcalEN;

    public FrenchRevolutionaryCalendarTest(String name, CalculationMethod calculationMethod) throws FileNotFoundException {
        super(name);
        System.out.println(name);
        frcalFR = new FrenchRevolutionaryCalendar(Locale.FRENCH, calculationMethod);
        frcalEN = new FrenchRevolutionaryCalendar(Locale.ENGLISH, calculationMethod);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    protected void setUp() {}

    protected void tearDown() {}

    public void testFrenchDate() throws Exception {
        validateDates("1796-08-04", "4-11-17", "Septidi", "Thermidor", "Lin", "Flax", DailyObjectType.PLANT, 2);
    }

    public void testFrenchTime() throws Exception {
        validateDateAndTime("1796-08-04 11:30:30", "4-11-17 04:79:51", "Septidi", "Thermidor", "Lin", "Flax", DailyObjectType.PLANT, 2);
    }

    protected void validateDates(String gregorian, String expectedFrench, String expectedDayOfWeek, String expectedMonthName, String expectedDayOfYearFR,
            String expectedDayOfYearEN, DailyObjectType expectedDailyObjectType, int expectedWeekInMonth) throws ParseException {
        // Test in French
        FrenchRevolutionaryCalendarDate fcd = getFrenchDate(frcalFR, gregorian, simpleDateFormat);
        String actualFrench = String.format("%d-%02d-%02d", fcd.year, fcd.month, fcd.dayOfMonth);
        assertEquals(expectedFrench, actualFrench);
        validateDateAttributes(fcd, expectedDayOfWeek, expectedMonthName, expectedDayOfYearFR, expectedDailyObjectType, expectedWeekInMonth);

        // Test in English
        fcd = getFrenchDate(frcalEN, gregorian, simpleDateFormat);
        actualFrench = String.format("%d-%02d-%02d", fcd.year, fcd.month, fcd.dayOfMonth);
        assertEquals(expectedFrench, actualFrench);
        validateDateAttributes(fcd, expectedDayOfWeek, expectedMonthName, expectedDayOfYearEN, expectedDailyObjectType, expectedWeekInMonth);
    }

    protected void validateDateAndTime(String gregorian, String expectedFrench, String expectedDayOfWeek, String expectedMonthName, String expectedDayOfYearFR,
            String expectedDayOfYearEN, DailyObjectType expectedDailyObjectType, int expectedWeekInMonth) throws ParseException {
        // Test in French
        FrenchRevolutionaryCalendarDate fcd = getFrenchDate(frcalFR, gregorian, simpleDateTimeFormat);
        String actualFrench = String.format("%d-%02d-%02d %02d:%02d:%02d", fcd.year, fcd.month, fcd.dayOfMonth, fcd.hour, fcd.minute, fcd.second);
        assertEquals(expectedFrench, actualFrench);
        validateDateAttributes(fcd, expectedDayOfWeek, expectedMonthName, expectedDayOfYearFR, expectedDailyObjectType, expectedWeekInMonth);

        // Test in English
        fcd = getFrenchDate(frcalEN, gregorian, simpleDateTimeFormat);
        actualFrench = String.format("%d-%02d-%02d %02d:%02d:%02d", fcd.year, fcd.month, fcd.dayOfMonth, fcd.hour, fcd.minute, fcd.second);
        assertEquals(expectedFrench, actualFrench);
        validateDateAttributes(fcd, expectedDayOfWeek, expectedMonthName, expectedDayOfYearEN, expectedDailyObjectType, expectedWeekInMonth);
    }

    private void validateDateAttributes(FrenchRevolutionaryCalendarDate actual, String expectedDayOfWeek, String expectedMonthName, String expectedDayOfYear,
            DailyObjectType expectedDailyObjectType, int expectedWeekInMonth) {
        assertEquals(expectedDayOfWeek, actual.getWeekdayName());
        assertEquals(expectedMonthName, actual.getMonthName());
        assertEquals(expectedDayOfYear, actual.getDayOfYear());
        assertEquals(expectedDailyObjectType, actual.getObjectType());
        assertEquals(expectedWeekInMonth, actual.getWeekInMonth());
    }

    private FrenchRevolutionaryCalendarDate getFrenchDate(FrenchRevolutionaryCalendar frcal, String gregorian, SimpleDateFormat parser) throws ParseException {
        Date testDate = parser.parse(gregorian);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(testDate);
        FrenchRevolutionaryCalendarDate fcd = frcal.getDate(cal);
        return fcd;
    }
}
