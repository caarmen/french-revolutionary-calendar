French Revolutionary Calendar Library
=====================================

Library providing conversion between Gregorian calendar dates
and French Revolutionary calendar dates.

Also converts 24-hour time to decimal time.

Reference: http://en.wikipedia.org/wiki/French_Republican_Calendar

Library
-------
To use this in your project:

Maven:
```
<dependency>
  <groupId>ca.rmen</groupId>
  <artifactId>lib-french-revolutionary-calendar</artifactId>
  <version>1.7.0</version>
  <type>pom</type>
</dependency>
```

Gradle:
```
compile 'ca.rmen:lib-french-revolutionary-calendar:1.7.0'

```

Usage:
```java
FrenchRevolutionaryCalendar frc =
    new FrenchRevolutionaryCalendar(
            Locale.getDefault(),
            FrenchRevolutionaryCalendar.CalculationMethod.ROMME):

FrenchRevolutionaryCalendarDate frenchDate =
    frc.getDate((GregorianCalendar) Calendar.getInstance());

String text = "Today is " + frenchDate.getWeekdayName()
    + ", " + frenchDate.dayOfMonth
    + " " + frenchDate.getMonthName() + " " + frenchDate.year;
```

Command-line program
--------------------
A command-line program is available.

Build it with: `mvn clean package`

Run it with `java -jar ./cli/target/french-revolutionary-calendar-cli-1.7.0.jar`

With no arguments, it will print out a usage text.

Examples:

Display the current time in the French Revolutionary Calendar:
```shell
$java -jar ./cli/target/french-revolutionary-calendar-cli-1.7.0.jar now
Quartidi, 04-Thermidor-225, 8:70:99, The plant:Ryegrass
```
Display an arbitrary date in the French Revolutionary Calendar:
```
$ java -jar ./cli/target/french-revolutionary-calendar-cli-1.7.0.jar g2f 1998-06-10
Parsing using format yyyy-MM-dd
Duodi, 22-Prairial-206, 0:00:00, The plant:Camomile
```
