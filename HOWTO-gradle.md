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
    * `./library/build/libs/lib-french-revolutionary-calendar-1.0.5.jar` (library to include in your project)
    * `./cli/build/libs/french-revolutionary-calendar-cli-1.0.5-standalone.jar` (command-line program)


To run the command-line program:
--------------------------------
```
java -jar ./cli/build/libs/french-revolutionary-calendar-cli-1.0.5-standalone.jar
```
