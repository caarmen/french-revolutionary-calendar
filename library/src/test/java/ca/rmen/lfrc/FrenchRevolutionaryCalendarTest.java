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

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.rmen.lfrc.FrenchRevolutionaryCalendar.CalculationMethod;
import ca.rmen.lfrc.FrenchRevolutionaryCalendar.DailyObjectType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public abstract class FrenchRevolutionaryCalendarTest {

    private final SimpleDateFormat simpleDateFormat;
    private final SimpleDateFormat simpleDateTimeFormat;
    private final Pattern frenchDatePattern;
    private final Pattern frenchDateTimePattern;
    private FrenchRevolutionaryCalendar frcalFR;
    private FrenchRevolutionaryCalendar frcalEN;
    CalculationMethod calculationMethod;

    FrenchRevolutionaryCalendarTest() {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        frenchDatePattern = Pattern.compile("(-?\\d*)-(\\d*)-(\\d*)");
        frenchDateTimePattern = Pattern.compile("(-?\\d*)-(\\d*)-(\\d*) (\\d*):(\\d*):(\\d*)");
    }

    @Before
    public void setUp() {
        frcalFR = new FrenchRevolutionaryCalendar(Locale.FRENCH, calculationMethod);
        frcalEN = new FrenchRevolutionaryCalendar(Locale.ENGLISH, calculationMethod);
    }

    @Test
    public void testFrenchDate() throws Exception {
        validateDates("1796-08-04", "4-11-17", "Septidi", "Thermidor", "Lin", "Flax", DailyObjectType.PLANT, 2);
    }

    @Test
    public void testFrenchTime() throws Exception {
        validateDateAndTime("1796-08-04 11:30:30", "4-11-17 04:79:51", "Septidi", "Thermidor", "Lin", "Flax", DailyObjectType.PLANT, 2);
        validateDateAndTime("1796-08-07 11:30:30", "4-11-20 04:79:51", "Décadi", "Thermidor", "Écluse", "Lock", DailyObjectType.TOOL, 2);
        validateTime(11, 30, 30, 4, 79, 51);
    }

    void validateDates(String gregorian, String expectedFrench, String expectedDayOfWeek, String expectedMonthName, String expectedDayOfYearFR,
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

        // Test reverse conversion
        GregorianCalendar reverseExpectedGregorian = parseGregorianDate(gregorian, simpleDateFormat);
        GregorianCalendar reverseActualGregorian = getGregorianDate(frcalEN, Locale.ENGLISH, expectedFrench, frenchDatePattern);
        validateGregorianDates(reverseExpectedGregorian, reverseActualGregorian);
    }

    void validateDateAndTime(String gregorian, String expectedFrench, String expectedDayOfWeek, String expectedMonthName, String expectedDayOfYearFR,
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

        // Test reverse conversion
        GregorianCalendar reverseExpectedGregorian = parseGregorianDate(gregorian, simpleDateTimeFormat);
        GregorianCalendar reverseActualGregorian = getGregorianDate(frcalEN, Locale.ENGLISH, expectedFrench, frenchDateTimePattern);
        validateGregorianDates(reverseExpectedGregorian, reverseActualGregorian);
    }

    private void validateTime(int gregorianHour, int gregorianMinute, int gregorianSecond, int expectedDecimalHour, int expectedDecimalMinute, int expectedDecimalSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 0);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, gregorianHour);
        calendar.set(Calendar.MINUTE, gregorianMinute);
        calendar.set(Calendar.SECOND, gregorianSecond);
        calendar.set(Calendar.MILLISECOND, 0);
        int[] actualDecimalTime = FrenchRevolutionaryCalendar.getFrenchTime(calendar);
        assertEquals(expectedDecimalHour, actualDecimalTime[0]);
        assertEquals(expectedDecimalMinute, actualDecimalTime[1]);
        assertEquals(expectedDecimalSecond, actualDecimalTime[2]);

        FrenchRevolutionaryCalendarDate frenchDate = new FrenchRevolutionaryCalendarDate(Locale.US,
                225, 1, 1,
                expectedDecimalHour, expectedDecimalMinute, expectedDecimalSecond);
        int[] actualReverseGregorianTime = FrenchRevolutionaryCalendar.get24HourTime(frenchDate);
        assertEquals(gregorianHour, actualReverseGregorianTime[0]);
        assertEquals(gregorianMinute, actualReverseGregorianTime[1]);
        assertTrue(Math.abs(gregorianSecond - actualReverseGregorianTime[2]) <= 1);
    }

    private void validateDateAttributes(FrenchRevolutionaryCalendarDate actual, String expectedDayOfWeek, String expectedMonthName, String expectedDayOfYear,
                                        DailyObjectType expectedDailyObjectType, int expectedWeekInMonth) {
        assertEquals(expectedDayOfWeek, actual.getWeekdayName());
        assertEquals(expectedMonthName, actual.getMonthName());
        assertEquals(expectedDayOfYear, actual.getObjectOfTheDay());
        assertEquals(expectedDailyObjectType, actual.getObjectType());
        assertEquals(expectedWeekInMonth, actual.getWeekInMonth());
        validateSerialization(actual);
    }

    private GregorianCalendar parseGregorianDate(String gregorian, SimpleDateFormat parser) throws ParseException {
        Date testDate = parser.parse(gregorian);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(testDate);
        return cal;
    }

    private FrenchRevolutionaryCalendarDate getFrenchDate(FrenchRevolutionaryCalendar frcal, String gregorian, SimpleDateFormat parser) throws ParseException {
        GregorianCalendar cal = parseGregorianDate(gregorian, parser);
        return frcal.getDate(cal);
    }

    private GregorianCalendar getGregorianDate(FrenchRevolutionaryCalendar frcal, Locale locale, String french, Pattern pattern) throws ParseException {
        Matcher matcher = pattern.matcher(french);
        if (matcher.find()) {
            int year, month, day;
            int hour = 0, minute = 0, second = 0;

            if (matcher.groupCount() == 3 || matcher.groupCount() == 6) {
                year = Integer.valueOf(matcher.group(1));
                month = Integer.valueOf(matcher.group(2));
                day = Integer.valueOf(matcher.group(3));
                if (matcher.groupCount() == 6) {
                    hour = Integer.valueOf(matcher.group(4));
                    minute = Integer.valueOf(matcher.group(5));
                    second= Integer.valueOf(matcher.group(6));
                }
                FrenchRevolutionaryCalendarDate frenchDate = new FrenchRevolutionaryCalendarDate(locale, year, month, day, hour, minute, second);
                return frcal.getDate(frenchDate);
            }
        }
        throw new ParseException("Can't parse french date " + french, 0);
    }

    private void validateGregorianDates(GregorianCalendar expected, GregorianCalendar actual) {
        assertEquals("Wrong year", expected.get(Calendar.YEAR), actual.get(Calendar.YEAR));
        assertEquals("Wrong month", expected.get(Calendar.MONTH), actual.get(Calendar.MONTH));
        assertEquals("Wrong day",expected.get(Calendar.DAY_OF_MONTH), actual.get(Calendar.DAY_OF_MONTH));
        assertEquals("Wrong hour", expected.get(Calendar.HOUR_OF_DAY), actual.get(Calendar.HOUR_OF_DAY));
        assertEquals("Wrong minute", expected.get(Calendar.MINUTE), actual.get(Calendar.MINUTE));
        assertTrue("Wrong second", Math.abs(expected.get(Calendar.SECOND) - actual.get(Calendar.SECOND)) <= 1);
    }

    private void validateSerialization(FrenchRevolutionaryCalendarDate expected) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(os).writeObject(expected);
            FrenchRevolutionaryCalendarDate actual = (FrenchRevolutionaryCalendarDate) new ObjectInputStream(new ByteArrayInputStream(os.toByteArray())).readObject();
            assertEquals(expected, actual);
        } catch (IOException e) {
            fail(e.getMessage());
        } catch (ClassNotFoundException e) {
            fail(e.getMessage());
        }
    }
}
