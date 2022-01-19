package ex00;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void printInFile(String greetings) {
        String path = System.getenv("PWD") + "/result.txt";
        try (FileOutputStream fileOutputStream = new FileOutputStream(path, true)) {
            fileOutputStream.write(greetings.getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<String> readFromFile(String path) {
        int i;
        int count = 0;
        List<String> list = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            while ((i = fileInputStream.read()) != -1) {
                String hex = Integer.toHexString(i);
                if (hex.length() == 1)
                    hex = "0" + hex;
                list.add(hex);
                count++;
                if (count > 8)
                    break;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static Map<String, String> readSignatures(String path) {
        int i;
        int count = 0;
        Map<String, String> signatureMap = new HashMap<>();
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            char[] array = new char[fileInputStream.available()];
            while ((i = fileInputStream.read()) != -1) {
                if ((char) i == ' ')
                    continue;
                array[count++] = (char) i;
                if ((char) i == '\n') {
                    String[] str = new String(array).split(",");
                    signatureMap.put(str[0], str[1].substring(0, str[1].indexOf('\n')));
                    count = 0;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return signatureMap;
    }

    public static boolean searchType(List<String> fileSignatureList, Map<String, String> signaturesList) throws IOException {
        String fileSignature = fileSignatureList.stream()
                .map(String::valueOf)
                .map(String::toLowerCase)
                .collect(Collectors.joining());

        for (Map.Entry<String, String> entry : signaturesList.entrySet()) {
            if (fileSignature.startsWith(entry.getValue().toLowerCase(Locale.ROOT))) {
                printInFile(entry.getKey() + "\n");
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String path;
        String signatureFilePath = System.getenv("PWD") + "/signatures.txt";
        Map<String, String> signaturesList = readSignatures(signatureFilePath);
        List<String> list;
        while (sc.hasNextLine()) {
            path = sc.nextLine();
            if (path.equals("42"))
                break;
            list = readFromFile(path);
            if (!list.isEmpty()) {
                if (searchType(list, signaturesList))
                    System.out.println("PROCESSED");
                else
                    System.out.println("UNDEFINED");
            }
        }
    }
}