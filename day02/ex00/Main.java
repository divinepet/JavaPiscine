package ex00;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    public static void printInFile(String greetings) throws IOException {
        String path = System.getenv("PWD") + "/result.txt";
        try (FileOutputStream fileOutputStream = new FileOutputStream(path, true)) {
            fileOutputStream.write(greetings.getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<String> readFromFile(String path) throws IOException {
        try (FileInputStream fis = new FileInputStream(path)) {

            int i = 0;

            do {

                byte[] buf = new byte[11];
                i = fis.read(buf, 0, 10);

                String value = new String(buf, StandardCharsets.UTF_8);
                System.out.print(buf[0]);
                System.out.print(buf[1]);

            } while (i != -1);
        }
//        int i;
//        int count = 0;
        ArrayList<String> list = new ArrayList<>();
//        byte[] array = new byte[10];
//
//
//        try (FileInputStream fileInputStream = new FileInputStream(path, StandardCharsets.UTF_8)) {
//            while ((i = fileInputStream.read()) != -1) {
//                System.out.println("i:" + i + ", hex: " + Integer.toHexString(i) + " ");
//                System.out.print("i char: " + (char) i);
//                list.add(Integer.toHexString(i));
//                count++;
//                if (count > 10)
//                    break;
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
        return list;
    }

    public static HashMap<String, String> readSignatures(String path) {
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
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return hashMaplist;
    }

    public static boolean searchType(ArrayList<String> list, HashMap<String, String> signaturesList) throws IOException {
        String out = "";
        for (String s : list)
            out += s;
        System.out.println("out: " + out);
        for (HashMap.Entry<String, String> entry : signaturesList.entrySet()) {
            int size = entry.getValue().length();
            System.out.println("key:" + entry.getKey() + ", value:" + entry.getValue() + ", size: " + size);
            String s = out.substring(0, size - 1);
            if (s.equals(entry.getValue().toLowerCase())) {
                printInFile(entry.getKey());
                return true;
            }
        }
        System.out.println("I dont know what file's signature is this");
        return false;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String path;
        String signatureFilePath = System.getenv("PWD") + "/signatures.txt";
        System.out.println(signatureFilePath);
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