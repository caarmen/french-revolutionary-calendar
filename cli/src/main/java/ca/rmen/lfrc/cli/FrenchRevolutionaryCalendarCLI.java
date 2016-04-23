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

import ca.rmen.lfrc.FrenchRevolutionaryCalendar;
import ca.rmen.lfrc.FrenchRevolutionaryCalendar.CalculationMethod;
import ca.rmen.lfrc.FrenchRevolutionaryCalendarDate;

import java.io.File;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

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
        String outputFormat = "%E, %dd-%MMMM-%y, %H:%mm:%ss, %T:%DDDD";

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("now")) {
                now(method, outputFormat);
            } else if (args[i].equals("g2f")) {
                if (i != args.length - 2) usage();
                print(g2f(args[++i], method, outputFormat));
            } else if (args[i].equals("-method")) {
                String methodName = args[++i];
                try {
                    method = CalculationMethod.valueOf(methodName.toUpperCase(Locale.US));
                } catch (IllegalArgumentException e) {
                    usage();
                }
                if (args.length - 1 == i) usage();
            } else if (args[i].equals("-output-format")) {
                outputFormat = args[++i];
            } else {
                usage();
            }
        }
    }

    /**
     * Print the current date and time in the French Revolutionary Calendar
     */
    private static void now(CalculationMethod method, String outputFormat) {
        FrenchRevolutionaryCalendar frc = new FrenchRevolutionaryCalendar(Locale.getDefault(), method);
        GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();
        FrenchRevolutionaryCalendarDate frenchDate = frc.getDate(now);
        print(format(frenchDate, outputFormat));
    }

    /**
     * Convert the given date or timestamp in the Gregorian calendar to the French Revolutionary Calendar and print the result.
     */
    static String g2f(String gregorianDateString, CalculationMethod method, String outputFormat) {
        // Try all our possible date and time formats until one works.
        for (String format : FORMATS) {
            try {
                // If we were given only the Gregorian time, only display the French time.
                if (FORMAT_TIME_ONLY.equals(format)) outputFormat = "%H:%mm:%ss";
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                Date date = sdf.parse(gregorianDateString);
                GregorianCalendar cal = new GregorianCalendar();
                cal.setTimeZone(sdf.getTimeZone());
                cal.setTime(date);
                FrenchRevolutionaryCalendar frc = new FrenchRevolutionaryCalendar(Locale.getDefault(), method);
                FrenchRevolutionaryCalendarDate frenchDate = frc.getDate(cal);
                System.err.println("Parsing using format " + format);
                return format(frenchDate, outputFormat);
            } catch (ParseException e) {}
        }
        System.err.println("Unrecognized Gregorian date format: " + gregorianDateString + ". Supported formats are:");
        for (String format : FORMATS)
            System.err.println("  " + format);
        return null;
    }

    /**
     * @return true if the given calendar only has time components. In other words, it is in day 0 of year 0.
     */
    static boolean isTimeOnly(GregorianCalendar cal) {
        return cal.get(Calendar.YEAR) == 0 && cal.get(Calendar.MONTH) == 0 && cal.get(Calendar.DAY_OF_MONTH) == 0;
    }

    /**
     * @return a String representation of the given French date, using the given format.
     */
    static String format(FrenchRevolutionaryCalendarDate frenchDate, String outputFormat) {
        String result = outputFormat;
        result = result.replaceAll("%y", String.format("%d", frenchDate.year));
        result = result.replaceAll("%MMMM", frenchDate.getMonthName());
        result = result.replaceAll("%MM", String.format("%02d", frenchDate.month));
        result = result.replaceAll("%M", String.format("%d", frenchDate.month));

        result = result.replaceAll("%dd", String.format("%02d", frenchDate.dayOfMonth));
        result = result.replaceAll("%d", String.format("%d", frenchDate.dayOfMonth));
        result = result.replaceAll("%H", String.format("%d", frenchDate.hour));
        result = result.replaceAll("%mm", String.format("%02d", frenchDate.minute));
        result = result.replaceAll("%ss", String.format("%02d", frenchDate.second));

        result = result.replaceAll("%E", frenchDate.getWeekdayName());
        result = result.replaceAll("%W", String.format("%d", frenchDate.getWeekInMonth()));
        result = result.replaceAll("%T", frenchDate.getObjectTypeName());
        result = result.replaceAll("%DDDD", frenchDate.getDayOfYear());
        return result;
    }

    /**
     * Display the given String on the console, using UTF-8 encoding.
     */
    private static void print(String string) {
        try {
            PrintStream ps = new PrintStream(System.out, true, "UTF-8");
            ps.println(string);
        } catch (UnsupportedEncodingException e) {
            System.out.println(string);
        }
    }

    /**
     * Display the program options and exit.
     */
    private static void usage() {
        System.err.println(getProgramName() + " [options] now | g2f <Gregorian date>");
        System.err.println("options:");
        System.err.println("-method <romme|equinox|von_madler>");
        System.err.println("-output-format <output format>: default: %E, %dd-%MMMM-%y, %H:%mm:%ss, %T:%DDDD");
        System.err.println("  Supported tags:");
        System.err.println("  %y: year: 219");
        System.err.println("  %MMMM: month name: Thermidor");
        System.err.println("  %MM: 2-digit month: 09");
        System.err.println("  %M: month: 9");
        System.err.println("  %dd: d-digit day: 02");
        System.err.println("  %d: day: 2");
        System.err.println("  %H: hour: 5");
        System.err.println("  %mm: 2-digit minute: 08");
        System.err.println("  %ss: 2-digit second: 03");
        System.err.println("  %E: weekday name: Duodi");
        System.err.println("  %W: week in month: 2");
        System.err.println("  %T: type of object of the day: plant");
        System.err.println("  %DDDD: day of year: Cherry");
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
