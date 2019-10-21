@echo off

echo This will only generate a Java 1.1 class file if javac 1.1 is used.

if defined JAVA_HOME (
	set JAVAC="%JAVA_HOME%"/bin/javac.exe
) else (
	set JAVAC=javac
)

cd src
echo Compiling with %JAVAC%
"%JAVAC%" -sourcepath src -d ..\classes -g -target 1.1 JGet.java