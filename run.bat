@echo off

javac -d bin src/*.java

java -classpath bin Driver

pause