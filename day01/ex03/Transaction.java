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

    public String getIdentifier() {
        return identifier;
    }

    public Integer getTransferAmount() {
        return transferAmount;
    }

    public String getRecipient() {
        return recipient.getName();
    }

    public String getSender() {
        return sender.getName();
    }

    public String getFullInfo()
    {
        return getSender() + " -> " + getRecipient() + ", " + getTransferAmount() + "$, ID: " + getIdentifier();
    }
}

