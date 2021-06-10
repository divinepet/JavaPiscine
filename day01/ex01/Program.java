public class Program {
    public static void main(String[] args) {
        User user1 = new User("oleg", 500);
        System.out.println(user1.getId());
        User user2 = new User("anton", 1000);
        System.out.println(user2.getId());
    }
}
