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

    Transaction (User recipient, User sender, TransactionType transactionType, Integer transferAmount) {
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

    @Override
    public String toString() {
        return "Transaction{" +
                "identifier='" + identifier + '\'' +
                ", recipient=" + recipient +
                ", sender=" + sender +
                ", transactionType=" + transactionType +
                ", transferAmount=" + transferAmount +
                ", startBalance=" + startBalance +
                '}';
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

