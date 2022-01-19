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

        users.add(new User("Anton", 1000));
        users.add(new User("Igor", 343));
        users.add(new User("Matvey", 51));
        users.add(new User("Evgeny", 515));
        users.add(new User("Sanya", 363));
        users.add(new User("Robot", 67373));
        users.add(new User("Lizard", 252));
        users.add(new User("Stok", 121));
        users.add(new User("Kraken", 232));
        users.add(new User("WildBerry", 3));
        users.add( new User("Querry", 596));

        User newUser = users.getByIndex(4);
        System.out.println("User with ID 4: " + newUser.getName());
        int numberOfUsers = users.getNumberOfUsers();
        System.out.println("Users total count: " + numberOfUsers);
    }
}
