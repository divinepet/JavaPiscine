public class User {
    private Integer id;
    private String name;
    private Integer balance;

    public User (Integer id, String name, Integer balance) {
        this.id = id;
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

    public Integer getIdentifier() {
        return id;
    }

    public String getName() {
        return name;
    }
}
