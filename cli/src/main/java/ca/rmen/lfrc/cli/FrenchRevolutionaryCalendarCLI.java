/*
 * French Revolutionary Calendar Library
 * 
 * Copyright (c) 2014 Carmen Alvarez
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

import java.io.File;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import ca.rmen.lfrc.FrenchRevolutionaryCalendar;
import ca.rmen.lfrc.FrenchRevolutionaryCalendar.CalculationMethod;
import ca.rmen.lfrc.FrenchRevolutionaryCalendarDate;

/**
 * Command-line interface to the French Revolutionary Calendar library functions.
 */
public class FrenchRevolutionaryCalendarCLI {

    private static final String FORMAT_FULL_TZ = "yyyy-MM-dd HH:mm:ss z";
    private static final String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT_ISO = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final String FORMAT_DATE_ONLY = "yyyy-MM-dd";
    private static final String FORMAT_TIME_ONLY = "HH:mm:ss";
    private static final String[] FORMATS = { FORMAT_ISO, FORMAT_FULL_TZ, FORMAT_FULL, FORMAT_DATE_ONLY, FORMAT_TIME_ONLY };

    public static void main(String[] args) {
        if (args.length == 0) usage();
        CalculationMethod method = CalculationMethod.ROMME;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("now")) {
                now(method);
            } else if (args[i].equals("g2f")) {
                if (i != args.length - 2) usage();
                g2f(args[++i], method);
            } else if (args[i].equals("-method")) {
                String methodName = args[++i];
                try {
                    method = CalculationMethod.valueOf(methodName.toUpperCase(Locale.US));
                } catch (IllegalArgumentException e) {
                    usage();
                }
                if (args.length - 1 == i) usage();
            } else {
                usage();
            }
        }
    }

    /**
     * Print the current date and time in the French Revolutionary Calendar
     */
    private static void now(CalculationMethod method) {
        FrenchRevolutionaryCalendar frc = new FrenchRevolutionaryCalendar(Locale.getDefault(), method);
        GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();
        FrenchRevolutionaryCalendarDate frenchDate = frc.getDate(now);
        print(frenchDate);
    }

    /**
     * Convert the given date or timestamp in the Gregorian calendar to the French Revolutionary Calendar and print the result.
     */
    private static void g2f(String gregorianDateString, CalculationMethod method) {
        // Try all our possible date and time formats until one works.
        for (String format : FORMATS) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                Date date = sdf.parse(gregorianDateString);
                GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
                cal.setTime(date);
                // If we were given only the Gregorian time, only display the French time.
                if (FORMAT_TIME_ONLY.equals(format)) {
                    int[] frenchTime = FrenchRevolutionaryCalendar.getFrenchTime(cal);
                    String frenchTimeStr = String.format("%d:%02d:%02d", frenchTime[0], frenchTime[1], frenchTime[2]);
                    System.out.println(frenchTimeStr);
                }
                // Display the full French date.
                else {
                    FrenchRevolutionaryCalendar frc = new FrenchRevolutionaryCalendar(Locale.getDefault(), method);
                    FrenchRevolutionaryCalendarDate frenchDate = frc.getDate(cal);
                    System.out.println(format);
                    print(frenchDate);
                }
                return;
            } catch (ParseException e) {}
        }
        System.err.println("Unrecognized Gregorian date format: " + gregorianDateString + ". Supported formats are:");
        for (String format : FORMATS)
            System.err.println("  " + format);
    }

    /**
     * Display the French date to the console, using UTF-8 encoding.
     */
    private static void print(FrenchRevolutionaryCalendarDate date) {
        try {
            PrintStream ps = new PrintStream(System.out, true, "UTF-8");
            ps.println(date);
        } catch (UnsupportedEncodingException e) {
            System.out.println(date);
        }
    }

    /**
     * Display the program options and exit.
     */
    private static void usage() {
        System.err.println(getProgramName() + " [options] now | g2f <Gregorian date>");
        System.err.println("options:");
        System.err.println("-method <romme|equinox>");
        System.exit(-1);
    }

    /**
     * Try to return a string like "java -jar target/french-revolutionary-calendar-cli.jar" to be used within the usage.
     */
    private static String getProgramName() {
        try {
            ProtectionDomain protectionDomain = FrenchRevolutionaryCalendarCLI.class.getProtectionDomain();
            if (protectionDomain != null) {
                CodeSource codeSource = protectionDomain.getCodeSource();
                if (codeSource != null) {
                    URL location = codeSource.getLocation();
                    if (location != null) {
                        String path = location.getPath();
                        if (path != null) {
                            File file = new File(path);
                            if (file.isFile()) {
                                String relativeFile = new File(".").toURI().relativize(file.toURI()).getPath();
                                return "java -jar " + relativeFile;
                            }
                        }
                    }
                }
            }
        } catch (Throwable t) {
            // don't really care
        }
        return "java " + FrenchRevolutionaryCalendarCLI.class.getName();
    }
}
