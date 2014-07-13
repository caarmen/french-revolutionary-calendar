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

/**
 * Validate date conversions between the Gregorian and French Revolutionary Calendars, using the Equinox method.
 */
public class FrenchRevolutionaryCalendarEquinoxTest extends FrenchRevolutionaryCalendarTest {
    public FrenchRevolutionaryCalendarEquinoxTest(String name) throws FileNotFoundException {
        super(name, FrenchRevolutionaryCalendar.MODE_EQUINOX);
    }

    public void testFrenchDate1() throws Exception {
        assertTrue(datesAreEqual("2011-07-08", "219-10-19"));
    }

    public void testFrenchDate2() throws Exception {
        assertTrue(datesAreEqual("2009-11-17", "218-02-27"));
    }

    public void testFrenchDate6() throws Exception {
        assertTrue(datesAreEqual("2010-12-25", "219-04-04"));
    }

    public void testFrenchDate7() throws Exception {
        assertTrue(datesAreEqual("1792-09-21", "0-13-05"));
    }

    public void testFrenchDate8() throws Exception {
        assertTrue(datesAreEqual("1791-09-21", "-1-13-05"));
    }

    public void testFrenchDate9() throws Exception {
        assertTrue(datesAreEqual("1791-09-22", "-1-13-06"));
    }
}
