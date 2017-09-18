Change Log
==========

1.7.1  *(2017-09-18)*
--------------------
* Fixed spelling of Sansculotides, to match the spelling in the [original decree](http://1789-1799.blogspot.fr/2011/12/le-calendrier-republicain-14.html)

1.7.0  *(2017-07-33)*
--------------------
* Added a a function `getDate()` to convert from a `FrenchRevolutionaryCalendarDate` to a `GregorianCalendar`.
* Fixed failed unit tests in the command-line program, resolving some issues regarding the local time zone.
* API changes:
  - `getDayOfYear()` has been renamed to `getObjectOfTheDay()`
  - A new method `getDayInYear()` returns the number of days elapsed since the beginning of the year, inclusive.
  
1.6.1  *(2017-05-25)*
--------------------
* Remove META-INF kotlin files from the library jar

1.6.0  *(2017-05-24)*
--------------------
* Rewritten in Kotlin

1.5.2  *(2017-01-07)*
--------------------
* Fix typo for one of the objects of the day, in French.

1.5.1  *(2016-09-24)*
--------------------
* Fix for weird Europe/Paris time zone with a DST offset in 1811

1.5.0  *(2016-04-24)*
--------------------
* Added von Mädler calculation

1.4.1  *(2015-09-22)*
--------------------
* Fix spelling error for "Travail"

1.2.0  *(2015-04-18)*
--------------------
* Translated daily object types (plant, animal, etc)

1.1.1  *(2014-12-06)*
--------------------
* Added unit tests for Italian

1.1.0  *(2014-12-06)*
--------------------
* Added Italian translations

1.0.7  *(2014-11-30)*
--------------------
* Fixed an equinox calculation bug due to different DST offset between a given date and the equinox date.

1.0.6  *(2014-11-23)*
--------------------
* Correction for Romme date calculation: Between midnight and 1am on November 23 2014 was incorrectly calculated as 2 Frimaire 223 instead of 3 Frimaire 223

1.0.5  *(2014-07-31)*
--------------------
* Added a command-line program

1.0.4  *(2014-07-26)*
--------------------
* Added new enum DailyObjectType

1.0.3  *(2014-07-20)*
--------------------
* Added the objects of the day
* Added Spanish and Catalan translations


