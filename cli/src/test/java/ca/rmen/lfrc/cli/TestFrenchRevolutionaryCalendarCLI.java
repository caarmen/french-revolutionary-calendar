/*
 * French Revolutionary Calendar Library
 * 
 * Copyright (c) 2014-2017 Carmen Alvarez
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
package ca.rmen.lfrc.cli;

import ca.rmen.lfrc.FrenchRevolutionaryCalendar.CalculationMethod;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestFrenchRevolutionaryCalendarCLI {

    @Test
    public void testG2fDateAndTimeRomme() {
        testG2f("1796-08-04 11:30:30", "Septidi, 17-Thermidor-4, 4:79:51, The plant:Flax", CalculationMethod.ROMME);
    }

    @Test
    public void testG2fDateAndTimeRommeISO() {
        testG2f("2011-07-08T11:30:30+0200", "Décadi, 20-Messidor-219, 4:79:51, The tool:Pen", CalculationMethod.ROMME);
        testG2f("2011-07-08T11:30:30PDT", "Décadi, 20-Messidor-219, 4:79:51, The tool:Pen", CalculationMethod.ROMME);
    }

    @Test
    public void testG2fDateAndTimeRommeFullTimestamp() {
        testG2f("2011-07-08 11:30:30 CEST", "Décadi, 20-Messidor-219, 4:79:51, The tool:Pen", CalculationMethod.ROMME);
        testG2f("2011-07-08 11:30:30 PDT", "Décadi, 20-Messidor-219, 4:79:51, The tool:Pen", CalculationMethod.ROMME);
        testG2f("2014-11-29 22:59:59 CET", "Nonidi, 09-Frimaire-223, 9:58:32, The plant:Juniper", CalculationMethod.ROMME);
        testG2f("2014-11-29 23:59:59 CET", "Nonidi, 09-Frimaire-223, 9:99:99, The plant:Juniper", CalculationMethod.ROMME);
        testG2f("2014-11-30 00:00:01 CET", "Décadi, 10-Frimaire-223, 0:00:01, The tool:Pickaxe", CalculationMethod.ROMME);
    }

    @Test
    public void testG2fDateAndTimeRommeFullTimestamp2() {
        testG2f("2011-07-08 11:30:30 +0200", "Décadi, 20-Messidor-219, 4:79:51, The tool:Pen", CalculationMethod.ROMME);
    }

    @Test
    public void testG2fDateRomme() {
        testG2f("2011-07-08", "Décadi, 20-Messidor-219, 0:00:00, The tool:Pen", CalculationMethod.ROMME);
    }

    @Test
    public void testG2fDateEquinox() {
        testG2f("2011-07-08", "Nonidi, 19-Messidor-219, 0:00:00, The plant:Cherry", CalculationMethod.EQUINOX);
        testG2f("2011-07-08", "19/Messidor/219", CalculationMethod.EQUINOX, "%dd/%MMMM/%y");
        testG2f("2011-07-08", "19/10/219", CalculationMethod.EQUINOX, "%dd/%MM/%y");
        testG2f("2011-07-08", "19/10/219", CalculationMethod.EQUINOX, "%dd/%M/%y");
        testG2f("2011-07-08", "19/10/219 (2)", CalculationMethod.EQUINOX, "%d/%M/%y (%W)");
        testG2f("1792-09-22", "1/1/1 (1)", CalculationMethod.EQUINOX, "%d/%M/%y (%W)");
        testG2f("1792-09-22", "01/01/1 (1)", CalculationMethod.EQUINOX, "%dd/%MM/%y (%W)");
        testG2f("1792-10-01", "10/01/1 (1)", CalculationMethod.EQUINOX, "%dd/%MM/%y (%W)");
    }

    @Test
    public void testG2fDateAndTimeFullTimestamp() {
        testG2f("2014-11-29 22:59:59 CET", "Octidi, 08-Frimaire-223, 9:58:32, The plant:Honey", CalculationMethod.EQUINOX);
        testG2f("2014-11-29 23:59:59 CET", "Octidi, 08-Frimaire-223, 9:99:99, The plant:Honey", CalculationMethod.EQUINOX);
        testG2f("2014-11-30 00:00:01 CET", "Nonidi, 09-Frimaire-223, 0:00:01, The plant:Juniper", CalculationMethod.EQUINOX);
    }

    @Test
    public void testG2TimeRomme() {
        testG2f("11:30:30", "4:79:51", CalculationMethod.ROMME);
    }

    @Test
    public void testG2TimeEquinox() {
        testG2f("11:30:30", "4:79:51", CalculationMethod.EQUINOX);
        testG2f("12:00:00", "5:00:00", CalculationMethod.EQUINOX);
    }

    private void testG2f(String gregorianDateString, String expectedFrenchString, CalculationMethod method) {
        testG2f(gregorianDateString, expectedFrenchString, method, "%E, %dd-%MMMM-%y, %H:%mm:%ss, %T:%DDDD");
    }

    private void testG2f(String gregorianDateString, String expectedFrenchString, CalculationMethod method, String format) {
        String actualFrenchString = FrenchRevolutionaryCalendarCLI.g2f(gregorianDateString, method, format);
        assertEquals(expectedFrenchString, actualFrenchString);
    }

}
