#!/bin/bash

javac -d target ./src/java/edu/school21/printer/app/Main.java ./src/java/edu/school21/printer/logic/ConvertImage.java
java -cp target edu.school21.printer.app.Main it.bmp . 0