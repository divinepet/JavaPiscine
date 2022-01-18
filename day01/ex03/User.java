public class User {
    private Integer id;
    private String name;
    private Integer balance;

    public User (String name, Integer balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = balance;
    }

    public void setBalance(Integer balance) {
        this.balance += balance;
    }

    public int getBalance()
    {
        return balance;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
