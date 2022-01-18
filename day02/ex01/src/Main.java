import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {


    public static double getSimilarity(String file1, String file2) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file1));
        String line1 = reader.readLine();
        reader = new BufferedReader(new FileReader(file2));
        String line2 = reader.readLine();
        reader.close();

        Set<String> dictionary = new HashSet<>();
        List<String> fileArr1 = Arrays.asList(line1.split("\\s+"));
        List<String> fileArr2 = Arrays.asList(line2.split("\\s+"));

        dictionary.addAll(fileArr1);
        dictionary.addAll(fileArr2);

        List<Integer> fileFrequency1 = new ArrayList<>(dictionary.size());
        List<Integer> fileFrequency2 = new ArrayList<>(dictionary.size());

        for (String word : dictionary) {
            fileFrequency1.add(Collections.frequency(fileArr1, word));
            fileFrequency2.add(Collections.frequency(fileArr2, word));
        }

        int numerator = 0;
        for (int i = 0; i < dictionary.size(); i++)
            numerator += (fileFrequency1.get(i) * fileFrequency2.get(i));

        double denominator;
        int denominator1 = 0;
        int denominator2 = 0;

        for (Integer n : fileFrequency1)
            denominator1 += n * n;

        for (Integer n : fileFrequency2)
            denominator2 += n * n;

        denominator = Math.sqrt(denominator1) * Math.sqrt(denominator2);

        return numerator / denominator;
    }


    public static void main(String[] args) throws IOException {
        String file1 ="./src/test1.txt";
        String file2 ="./src/test2.txt";

        System.out.println(getSimilarity(file1, file2));
    }
}
