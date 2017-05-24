#!/bin/sh
logprefix=$0
set -e
echo "$logprefix: Rebuilding project..."
mvn clean package org.jetbrains.dokka:dokka-maven-plugin:javadocJar
mvn site site:stage
echo "$logprefix: Project rebuilt".
echo "$logprefix: Extracting version from pom file..."
version=`mvn -q exec:exec -Dexec.executable="echo" -Dexec.args='${project.version}' --non-recursive`
echo "$logprefix: Version is $version."
echo "$logprefix: Installing to local maven repository..."
mvn install:install-file -DpomFile=publish.pom -Dfile=library/target/lib-french-revolutionary-calendar-$version.jar -Djavadoc=library/target/lib-french-revolutionary-calendar-$version-javadoc.jar -Dsources=library/target/lib-french-revolutionary-calendar-$version-sources.jar
mvn install:install-file -Dversion=$version -Dfile=library/target/lib-french-revolutionary-calendar-$version-core.jar -Dclassifier=core -DgroupId=ca.rmen -DartifactId=lib-french-revolutionary-calendar
echo "$logprefix: Done."
