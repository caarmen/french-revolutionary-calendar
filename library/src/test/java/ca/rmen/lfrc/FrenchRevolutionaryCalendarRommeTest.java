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

import ca.rmen.lfrc.FrenchRevolutionaryCalendar.CalculationMethod;
import ca.rmen.lfrc.FrenchRevolutionaryCalendar.DailyObjectType;

import java.io.FileNotFoundException;

/**
 * Validate date conversions between the Gregorian and French Revolutionary Calendars, using the Romme method.
 */
public class FrenchRevolutionaryCalendarRommeTest extends FrenchRevolutionaryCalendarTest {
    public FrenchRevolutionaryCalendarRommeTest(String name) throws FileNotFoundException {
        super(name, CalculationMethod.ROMME);
    }

    public void testFrenchDate1() throws Exception {
        validateDates("2011-07-08", "219-10-20", "Décadi", "Messidor", "Parc", "Pen", DailyObjectType.TOOL, 2);
    }

    public void testFrenchDate2() throws Exception {
        validateDates("2009-11-17", "218-02-27", "Septidi", "Brumaire", "Macjonc", "Tuberous pea", DailyObjectType.PLANT, 3);
    }

    public void testFrenchDate4() throws Exception {
        validateDates("1812-09-23", "21-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    public void testFrenchDate5() throws Exception {
        validateDates("2011-09-23", "220-01-02", "Duodi", "Vendémiaire", "Safran", "Saffron", DailyObjectType.PLANT, 1);
    }

    public void testFrenchDate6() throws Exception {
        validateDates("2010-12-25", "219-04-05", "Quintidi", "Nivôse", "Chien", "Dog", DailyObjectType.ANIMAL, 1);
    }

    public void testFrenchDate7() throws Exception {
        validateDates("1792-09-21", "0-13-06", "Sextidi", "Sanculotides", "Révolution", "Revolution", DailyObjectType.CONCEPT, 1);
    }

    public void testFrenchDate8() throws Exception {
        validateDates("1791-09-21", "-1-13-05", "Quintidi", "Sanculotides", "Récompenses", "Honors", DailyObjectType.CONCEPT, 1);
    }

    public void testFrenchDate9() throws Exception {
        validateDates("1791-09-22", "0-01-01", "Primidi", "Vendémiaire", "Raisin", "Grape", DailyObjectType.PLANT, 1);
    }

    public void testFrenchDate10() throws Exception {
        validateDates("2016-01-01", "224-04-12", "Duodi", "Nivôse", "Argile", "Clay", DailyObjectType.MINERAL, 2);
        validateDates("2016-01-20", "224-05-01", "Primidi", "Pluviôse", "Lauréole", "Spurge-laurel", DailyObjectType.PLANT, 1);
        validateDates("2016-02-19", "224-06-01", "Primidi", "Ventôse", "Tussilage", "Coltsfoot", DailyObjectType.PLANT, 1);
        validateDates("2016-02-28", "224-06-10", "Décadi", "Ventôse", "Bêche", "Spade", DailyObjectType.TOOL, 1);
        validateDates("2016-02-29", "224-06-11", "Primidi", "Ventôse", "Narcisse", "Narcissus", DailyObjectType.PLANT, 2);
        validateDates("2016-03-05", "224-06-16", "Sextidi", "Ventôse", "Épinard", "Spinach", DailyObjectType.PLANT, 2);
        validateDates("2016-03-20", "224-07-01", "Primidi", "Germinal", "Primevère", "Primrose", DailyObjectType.PLANT, 1);
        validateDates("2016-03-21", "224-07-02", "Duodi", "Germinal", "Platane", "Plane Tree", DailyObjectType.PLANT, 1);
        validateDates("2016-04-20", "224-08-02", "Duodi", "Floréal", "Chêne", "Oak Tree", DailyObjectType.PLANT, 1);
        validateDates("2016-04-24", "224-08-06", "Sextidi", "Floréal", "Ancolie", "Common Columbine", DailyObjectType.PLANT, 1);
    }
}
