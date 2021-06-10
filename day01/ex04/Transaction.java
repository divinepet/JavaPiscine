import java.util.UUID;

public class Transaction {
    String identifier;
    User recipient;
    User sender;
    transactionType type;
    enum transactionType {
      INCOME,
      OUTCOME,
    };
    Integer transferAmount;
    Integer startBalance;

    Transaction (User sender, User recipient, Integer transferAmount) {
        this.recipient = recipient;
        this.sender = sender;
        this.transferAmount = transferAmount;
        this.identifier = UUID.randomUUID().toString();
        this.type = transactionType.OUTCOME;
    }

    Transaction (User sender, User recipient, String identifier, Integer transferAmount) {
        this.recipient = recipient;
        this.sender = sender;
        this.transferAmount = transferAmount;
        this.identifier = identifier;
        this.type = transactionType.INCOME;
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
        if (type == transactionType.OUTCOME)
            return getSender() + " -> " + getRecipient() + ", " + getTransferAmount() + ", " + type + ", ID: " + getIdentifier();
        else
            return getRecipient() + " <- " + getSender() + ", " + getTransferAmount() + ", " + type + ", ID: " + getIdentifier();
    }
}

