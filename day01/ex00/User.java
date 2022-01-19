public class User {
    private Integer id;
    private String name;
    private Integer balance;

    public User (Integer id, String name, Integer balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

<<<<<<< HEAD
    public void setBalance(Integer balance)
    {
        if (balance > 0)
            this.balance += balance;
        else {
            balance *= -1;
            if (this.balance - balance < 0)
                System.out.println("Not enough money!");
            else
                this.balance -= balance;
        }
=======
    public void setBalance(Integer balance) {
        this.balance += balance;
>>>>>>> f926bb0f0175de7bf11f2e5fd668469dd3037222
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
