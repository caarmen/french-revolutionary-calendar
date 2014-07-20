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

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import junit.framework.TestCase;
import ca.rmen.lfrc.FrenchRevolutionaryCalendar.CalculationMethod;

public abstract class FrenchRevolutionaryCalendarTest extends TestCase {

    private FrenchRevolutionaryCalendar frcal = null;
    private SimpleDateFormat simpleDateFormat = null;
    private SimpleDateFormat simpleDateTimeFormat = null;

    public FrenchRevolutionaryCalendarTest(String name, CalculationMethod calculationMethod) throws FileNotFoundException {
        super(name);
        System.out.println(name);
        frcal = new FrenchRevolutionaryCalendar(calculationMethod);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    protected void setUp() {}

    protected void tearDown() {}

    public void testFrenchDate3() throws Exception {
        validateDates("1796-08-04", "4-11-17", "Septidi", "Thermidor", "Lin", "Flax");
    }

    public void testFrenchTime1() throws Exception {
        validateDateAndTime("1796-08-04 11:30:30", "4-11-17 04:79:51", "Septidi", "Thermidor", "Lin", "Flax");
    }

    protected void validateDates(String gregorian, String expectedFrench, String expectedDayOfWeek, String expectedMonthName, String expectedDayOfYearFR,
            String expectedDayOfYearEN) throws ParseException {
        FrenchRevolutionaryCalendarDate fcd = getFrenchDate(gregorian, simpleDateFormat);
        String actualFrench = String.format("%d-%02d-%02d", fcd.year, fcd.month, fcd.dayOfMonth);
        assertEquals(expectedFrench, actualFrench);
        validateDateAttributes(fcd, expectedDayOfWeek, expectedMonthName, expectedDayOfYearFR, expectedDayOfYearEN);
    }

    protected void validateDateAndTime(String gregorian, String expectedFrench, String expectedDayOfWeek, String expectedMonthName, String expectedDayOfYearFR,
            String expectedDayOfYearEN) throws ParseException {
        FrenchRevolutionaryCalendarDate fcd = getFrenchDate(gregorian, simpleDateTimeFormat);
        String actualFrench = String.format("%d-%02d-%02d %02d:%02d:%02d", fcd.year, fcd.month, fcd.dayOfMonth, fcd.hour, fcd.minute, fcd.second);
        assertEquals(expectedFrench, actualFrench);
        validateDateAttributes(fcd, expectedDayOfWeek, expectedMonthName, expectedDayOfYearFR, expectedDayOfYearEN);
    }

    private void validateDateAttributes(FrenchRevolutionaryCalendarDate actual, String expectedDayOfWeek, String expectedMonthName, String expectedDayOfYearFR,
            String expectedDayOfYearEN) {
        assertEquals(expectedDayOfWeek, actual.getWeekdayName());
        assertEquals(expectedMonthName, actual.getMonthName());
        assertEquals(expectedDayOfYearFR, actual.getDayOfYear());
        assertEquals(expectedDayOfYearEN, actual.getDayOfYear(Locale.ENGLISH));
    }

    private FrenchRevolutionaryCalendarDate getFrenchDate(String gregorian, SimpleDateFormat parser) throws ParseException {
        Date testDate = parser.parse(gregorian);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(testDate);
        FrenchRevolutionaryCalendarDate fcd = frcal.getDate(cal);
        return fcd;
    }
}
