import java.util.UUID;

public class Transaction {
    static enum TransactionType {
        DEBIT,
        CREDIT
    }

    String identifier = UUID.randomUUID().toString();
    User recipient;
    User sender;
    TransactionType transactionType;
    Integer transferAmount;
    Integer startBalance;

    Transaction (User recipient, User sender, TransactionType transactionType, Integer transferAmount) {
        this.startBalance = sender.getBalance();
        this.recipient = recipient;
        this.sender = sender;
        if (sender.getBalance() >= transferAmount) {
            sender.setBalance(transferAmount * (-1));
            recipient.setBalance(transferAmount);
        }
        this.transactionType = transactionType;
        this.transferAmount = transferAmount;
    }

    public String getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Integer getTransferAmount() {
        return transferAmount;
    }

    public Integer getStartBalance() {
        return startBalance;
    }
}
