#!/usr/bin/env bash
appname='BracketTree-01'
appfolder="/home/misha/workspace/"${appname}
M2_HOME='/opt/apache-maven-3.2.2/'
export M2_HOME
M2=${M2_HOME}/bin
export M2
PATH=${PATH}:${M2}
export PATH
export JAVA_HOME='/usr/lib/jvm/java-7-oracle'
if [ ! -e ${appfolder} ]; then echo 'ERROR: no appfolder' ${appfolder} 'found';exit 1; fi
cd ${appfolder}
mvn clean install $@ | tee out.txt ; test ${PIPESTATUS[0]} -eq 0
if [ ${PIPESTATUS[0]} -ne "0" ]; then
    echo ===================================================
    echo maven build failed, see output for details;exit 1;
fi
if [ -e ${appfolder}/dist ]; then
    rm -rd ${appfolder}/dist
    echo ===================================================
    echo remove stale distribution...
fi
mkdir ${appfolder}/dist
mkdir ${appfolder}/dist/resources
echo ===================================================
echo make distribution folder...
cp  ${appfolder}/target/bool.jar ${appfolder}/dist/bool.jar
cp -r ${appfolder}/src/main/resources/rule.properties ${appfolder}/dist/resources
cp -r ${appfolder}/target/dependency-jars ${appfolder}/dist
echo ===================================================
echo copy a mince into the patty...
cd ${appfolder}/dist
echo ===================================================
echo let\'s taste it...
echo ===================================================
java -jar bool.jar




