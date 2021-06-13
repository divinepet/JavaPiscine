public class Main {

    public static String ipAddr = "localhost";

    /**
     @param args
     */

    public static void main(String[] args) {
        new ClientStructure(ipAddr, Integer.parseInt(args[0].substring(14)));
    }
}