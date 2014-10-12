To build:
---------
From the root directory of the project:
```
./gradlew build
```

This will:
* compile the library
* compile the command-line program
* compile and run unit tests
* output (for version 1.0.5, for example):
    * ./library/build/libs/lib-french-revolutionary-calendar-1.0.5.jar
    * ./cli/build/libs/french-revolutionary-calendar-cli-1.0.5.jar


To run the command-line program:
--------------------------------
From the root directory of the project:
```
./gradlew distZip
```
This will create (for version 1.0.5, for example):
* ./cli/build/distributions/french-revolutionary-calendar-cli-1.0.5.zip

This zip file can be extracted to a directory, and the program can be run as follows:

```
./french-revolutionary-calendar-cli-1.0.5/bin/french-revolutionary-calendar-cli
```
