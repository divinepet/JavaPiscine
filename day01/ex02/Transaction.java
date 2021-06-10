import java.util.Enumeration;
import java.util.UUID;

public class Transaction {
    String identifier = UUID.randomUUID().toString();
    User recipient;
    User sender;
    Integer isDebit;
    Integer transferAmount;
    Integer startBalance;

    Transaction (User recipient, User sender, Integer isDebit, Integer transferAmount) {
        this.startBalance = sender.getBalance();
        this.recipient = recipient;
        this.sender = sender;
        this.sender.setBalance(transferAmount * (-1));
        if (this.sender.getBalance() != startBalance)
            this.recipient.setBalance(transferAmount);
        this.isDebit = isDebit;
        this.transferAmount = transferAmount;
    }

}

