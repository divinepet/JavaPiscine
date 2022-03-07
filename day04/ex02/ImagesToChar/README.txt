curl -s -o lib/jcommander-1.78.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.78/jcommander-1.78.jar
curl -s -o lib/JCDP-2.0.3.1.jar https://repo1.maven.org/maven2/com/diogonunes/JCDP/2.0.3.1/JCDP-2.0.3.1.jar

mkdir -p target/resources
cp -R ./src/resources target
jar xf lib/jcommander-1.78.jar com/
jar xf lib/JCDP-2.0.3.1.jar com/
mv com target/

javac -cp target/ -d target/  src/java/edu/school21/printer/app/Main.java src/java/edu/school21/printer/logic/ConvertImage.java
jar cvfm target/images-to-chars-printer.jar src/manifest.txt -C target/ .
java -jar target/images-to-chars-printer.jar --white=BLACK --black=YELLOW