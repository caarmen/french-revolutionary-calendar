To build:
---------
From the root directory of the project:
```
mvn clean package
```

This will:
* compile the library
* compile the command-line program
* compile and run unit tests
* output (for version 1.0.5, for example):
    * `./library/target/lib-french-revolutionary-calendar-1.0.5.jar` (library to include in your project)
    * `./cli/target/french-revolutionary-calendar-cli-1.0.5.jar` (command-line program)


To run the command-line program:
--------------------------------
```
java -jar ./cli/target/french-revolutionary-calendar-cli-1.0.5.jar
```
