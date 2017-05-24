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

/**
 * Validate date conversions between the Gregorian and French Revolutionary Calendars, using the Equinox method.
 */
public class FrenchRevolutionaryCalendarEquinoxTest extends FrenchRevolutionaryCalendarTest{
    public FrenchRevolutionaryCalendarEquinoxTest() {
        calculationMethod = CalculationMethod.EQUINOX;
    }

    @Test
    public void testFrenchDate1() throws Exception {
        validateDates("2011-07-08", "219-10-19", "Nonidi", "Messidor", "Cerise", "Cherry", DailyObjectType.PLANT, 2);
    }

    @Test
    public void testFrenchDate2() throws Exception {
        validateDates("2009-11-17", "218-02-27", "Septidi", "Brumaire", "Macjonc", "Tuberous pea", DailyObjectType.PLANT, 3);
    }

    @Test
    public void testFrenchDate6() throws Exception {
        validateDates("2010-12-25", "219-04-04", "Quartidi", "Nivôse", "Soufre", "Sulphur", DailyObjectType.MINERAL, 1);
    }

    @Test
    public void testFrenchDate7() throws Exception {
        validateDates("1792-09-21", "0-13-05", "Quintidi", "Sanculotides", "Récompenses", "Honors", DailyObjectType.CONCEPT, 1);
    }

    @Test
    public void testFrenchDate8() throws Exception {
        validateDates("1791-09-21", "-1-13-05", "Quintidi", "Sanculotides", "Récompenses", "Honors", DailyObjectType.CONCEPT, 1);
    }

    @Test
    public void testFrenchDate9() throws Exception {
        validateDates("1791-09-22", "-1-13-06", "Sextidi", "Sanculotides", "Révolution", "Revolution", DailyObjectType.CONCEPT, 1);
    }

    @Test
    public void testFrenchDate10() throws Exception {
        validateDates("2014-11-29", "223-03-08", "Octidi", "Frimaire", "Miel", "Honey", DailyObjectType.PLANT, 1);
    }
    @Test
    public void testFrenchDateAndTime1() throws Exception {
        validateDateAndTime("2014-11-29 22:59:59", "223-03-08 09:58:32", "Octidi", "Frimaire", "Miel", "Honey", DailyObjectType.PLANT, 1);
    }

    @Test
    public void testFrenchDateAndTime2() throws Exception {
        validateDateAndTime("2014-11-29 23:59:59", "223-03-08 09:99:98", "Octidi", "Frimaire", "Miel", "Honey", DailyObjectType.PLANT, 1);
    }
}
