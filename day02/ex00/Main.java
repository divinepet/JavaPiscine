package ex00;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void printInFile(String greetings) throws IOException {
        String path =
                "/Users/elaronda/Desktop/javapiscine/day02/src/ex00/result.txt";
        try (FileOutputStream fileOutputStream = new FileOutputStream(path, true)) {
            fileOutputStream.write(greetings.getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<String> readFromFile(String path) throws IOException {
        int i;
        int count = 0;
        ArrayList<String> list = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            while ((i = fileInputStream.read()) != -1) {
                list.add(Integer.toHexString(i));
                count++;
                if (count > 10)
                    break;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static HashMap<String, String> readSignatures(String path) throws IOException {
        int i;
        int count = 0;
        HashMap<String, String> hashMaplist = new HashMap<>();
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            char[] array = new char[fileInputStream.available()];
            while ((i = fileInputStream.read()) != -1) {
                if ((char) i == ' ')
                    continue;
                array[count++] = (char) i;
                if ((char) i == '\n') {
                    String[] str = new String(array).split(",");
                    hashMaplist.put(str[0], str[1].substring(0, str[1].indexOf('\n')));
                    count = 0;
                }
            }
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return hashMaplist;
    }

    public static boolean searchType(ArrayList<String> list, HashMap<String, String> signaturesList) throws IOException {
        String out = "";
        for (String s : list)
            out += s;
        for (HashMap.Entry<String, String> entry : signaturesList.entrySet()) {
            int size = entry.getValue().toString().length();
            String s = out.substring(0, size);
            if (s.equals(entry.getValue().toString().toLowerCase())) {
                printInFile(entry.getKey().toString());
                return true;
            }
        }
        System.out.println("I dont know what file's signature is this");
        return false;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String path;
        String signatureFilePath =
                "/Users/elaronda/Desktop/javapiscine/day02/src/ex00/signatures.txt";
        HashMap<String, String> signaturesList = readSignatures(signatureFilePath);
        ArrayList<String> list;
        while (sc.hasNextLine()) {
            path = sc.nextLine();
            if (path.equals("42"))
                break;
            list = readFromFile(path);
            if (!list.isEmpty())
                if (searchType(list, signaturesList))
                    System.out.println("PROCESSED");
        }
    }
}