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

import ca.rmen.lfrc.FrenchRevolutionaryCalendar.CalculationMethod;

/**
 * Validate date conversions between the Gregorian and French Revolutionary Calendars, using the Romme method.
 */
public class FrenchRevolutionaryCalendarRommeTest extends FrenchRevolutionaryCalendarTest {
    public FrenchRevolutionaryCalendarRommeTest(String name) throws FileNotFoundException {
        super(name, CalculationMethod.ROMME);
    }

    public void testFrenchDate1() throws Exception {
        validateDates("2011-07-08", "219-10-20", "Décadi", "Messidor");
    }

    public void testFrenchDate2() throws Exception {
        validateDates("2009-11-17", "218-02-27", "Septidi", "Brumaire");
    }

    public void testFrenchDate4() throws Exception {
        validateDates("1812-09-23", "21-01-01", "Primidi", "Vendémiaire");
    }

    public void testFrenchDate5() throws Exception {
        validateDates("2011-09-23", "220-01-02", "Duodi", "Vendémiaire");
    }

    public void testFrenchDate6() throws Exception {
        validateDates("2010-12-25", "219-04-05", "Quintidi", "Nivôse");
    }

    public void testFrenchDate7() throws Exception {
        validateDates("1792-09-21", "0-13-06", "Sextidi", "Sanculotides");
    }

    public void testFrenchDate8() throws Exception {
        validateDates("1791-09-21", "-1-13-05", "Quintidi", "Sanculotides");
    }

    public void testFrenchDate9() throws Exception {
        validateDates("1791-09-22", "0-01-01", "Primidi", "Vendémiaire");
    }
}
