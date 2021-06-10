public class Program {
    public static void main(String[] args) {
        UsersArrayList users = new UsersArrayList();

        User user0 = new User("Anton", 1000);
        User user1 = new User("Igor", 343);
        User user2 = new User("Matvey", 51);
        User user3 = new User("Evgeny", 515);
        User user4 = new User("Sanya", 363);
        User user5 = new User("Robot", 67373);
        User user6 = new User("Lizard", 252);
        User user7 = new User("Stok", 121);
        User user8 = new User("Kraken", 232);
        User user9 = new User("WildBerry", 3);
        User user10 = new User("Querry", 596);

        users.add(user0);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);

        User newuser = users.getByIndex(4);
        System.out.println(newuser.getName());
        Integer NumberOfUsers = users.getNumberOfUsers();
        System.out.println(NumberOfUsers);
    }
}
