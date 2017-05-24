#!/bin/sh
logprefix=$0
echo "$logprefix: Rebuilding project..."
mvn clean package site site:stage
echo "$logprefix: Project rebuilt".
echo "$logprefix: Extracting version from pom file..."
version=`mvn -q exec:exec -Dexec.executable="echo" -Dexec.args='${project.version}' --non-recursive`
echo "$logprefix: Version is $version."
echo "$logprefix: Installing to local maven repository..."
mvn install:install-file -DpomFile=publish.pom -Dfile=library/target/lib-french-revolutionary-calendar-$version.jar -Djavadoc=library/target/lib-french-revolutionary-calendar-$version-javadoc.jar -Dsources=library/target/lib-french-revolutionary-calendar-$version-sources.jar
echo "$logprefix: Done."
