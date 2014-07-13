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

import junit.framework.TestCase;

public abstract class FrenchCalendarTest extends TestCase {

    private FrenchCalendarUtil util = null;
    private SimpleDateFormat simpleDateFormat = null;
    private SimpleDateFormat simpleDateTimeFormat = null;

    public FrenchCalendarTest(String name, int mode) throws FileNotFoundException {
        super(name);
        System.out.println(getClass().getName());
        util = new FrenchCalendarUtil(mode);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    protected void setUp() {}

    protected void tearDown() {}

    public void testFrenchDate3() throws Exception {

        assertTrue(datesAreEqual("1796-08-04", "4-11-17"));
    }

    public void testFrenchTime1() throws Exception {
        assertTrue(dateAndTimeAreEqual("1796-08-04 11:30:30", "4-11-17 04:79:51"));
    }

    protected boolean datesAreEqual(String gregorian, String french) throws ParseException {
        FrenchRevolutionaryCalendarDate fcd = getFrenchDate(gregorian, simpleDateFormat);
        String frenchCalculated = String.format("%d-%02d-%02d", fcd.year, fcd.month, fcd.day);
        return areEqual(frenchCalculated, french);
    }

    protected boolean dateAndTimeAreEqual(String gregorian, String french) throws ParseException {
        FrenchRevolutionaryCalendarDate fcd = getFrenchDate(gregorian, simpleDateTimeFormat);
        String frenchCalculated = String.format("%d-%02d-%02d %02d:%02d:%02d", fcd.year, fcd.month, fcd.day, fcd.hour, fcd.minute, fcd.second);
        return areEqual(frenchCalculated, french);
    }

    private boolean areEqual(String frenchCalculated, String french) {
        boolean result = frenchCalculated.equals(french);
        System.out.println((result ? "OK" : "KO!!") + ": " + frenchCalculated + " vs " + french);
        return result;
    }

    private FrenchRevolutionaryCalendarDate getFrenchDate(String gregorian, SimpleDateFormat parser) throws ParseException {
        Date testDate = parser.parse(gregorian);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(testDate);
        FrenchRevolutionaryCalendarDate fcd = util.getDate(cal);
        return fcd;
    }

}
