import java.util.UUID;

public class Transaction {

    public static enum TransactionType {
        DEBIT,
        CREDIT
    }

    private String identifier;
    private User recipient;
    private User sender;
    private TransactionType transactionType;
    private Integer transferAmount;

    public Transaction (User sender, User recipient, TransactionType transactionType, Integer transferAmount) {
        this.sender = sender;
        this.recipient = recipient;
        this.identifier = UUID.randomUUID().toString();
        this.transactionType = transactionType;
        this.transferAmount = transferAmount;
    }

    public Transaction (User sender, User recipient, TransactionType transactionType, Integer transferAmount, String identifier) {
        this.sender = sender;
        this.recipient = recipient;
        this.identifier = identifier;
        this.transactionType = transactionType;
        this.transferAmount = transferAmount;
    }

    public void start() throws IllegalTransactionException {
        if (sender.getBalance() >= transferAmount) {
            sender.setBalance(transferAmount * (-1));
            recipient.setBalance(transferAmount);
        } else
            throw new IllegalTransactionException(this.identifier);
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

    @Override
    public String toString() {
        return "Transaction{" +
                "identifier='" + identifier + '\'' +
                ", recipient=" + recipient +
                ", sender=" + sender +
                ", transactionType=" + transactionType +
                ", transferAmount=" + transferAmount +
                '}';
    }

    class IllegalTransactionException extends Exception {
        IllegalTransactionException(String id)
        {
                System.out.println("Transaction " + id + " can't be done");
        }
    }
}

