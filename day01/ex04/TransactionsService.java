import java.util.UUID;


public class TransactionsService {
    UsersList list;
    TransactionsLinkedList successTransactionsList;
    TransactionsLinkedList unpairedTransactionsList;

    TransactionsService() {
        this.list = new UsersArrayList();
        this.successTransactionsList = new TransactionsLinkedList();
        this.unpairedTransactionsList = new TransactionsLinkedList();
    }

    public void addNewUser(User user) {
        list.add(user);
    }

    public int getUserBalance(int id) throws UserNotFoundException {
        return list.getById(id).getBalance();
    }

    public void transfer(int senderId, int recipientId, int transferAmount) throws Transaction.IllegalTransactionException, UserNotFoundException {
        String transactionId = UUID.randomUUID().toString();

        Transaction transaction = new Transaction(list.getById(senderId), list.getById(recipientId), Transaction.TransactionType.DEBIT, transferAmount, transactionId);
        try {
            transaction.start();
            successTransactionsList.add(transaction);
        } catch (Transaction.IllegalTransactionException e) {
            unpairedTransactionsList.add(transaction);
        }
    }

    public Transaction[] getUserTransactionsList(int id) {
        Transaction[] success = successTransactionsList.toArray();
        Transaction[] failed = unpairedTransactionsList.toArray();
        TransactionsLinkedList transactionsLinkedList = new TransactionsLinkedList();
        for (int i = 0; i < success.length; i++) {
            if (success[i].getSender().getId() == id || success[i].getRecipient().getId() == id) {
                transactionsLinkedList.add(success[i]);
            }
        }

        for (int i = 0; i < failed.length; i++) {
            if (failed[i].getSender().getId() == id || failed[i].getRecipient().getId() == id) {
                transactionsLinkedList.add(failed[i]);
            }
        }
        return transactionsLinkedList.toArray();
    }

    public void removeUserTransactions(String transactionId, int userId) {
        Transaction[] success = successTransactionsList.toArray();
        Transaction[] failed = unpairedTransactionsList.toArray();



        for (int i = 0; i < success.length; i++) {
            if ((success[i].getSender().getId() == userId || success[i].getRecipient().getId() == userId)
            && success[i].getIdentifier().equals(transactionId)) {
                successTransactionsList.remove(success[i].getIdentifier());
            }
        }

        for (int i = 0; i < failed.length; i++) {
            if ((failed[i].getSender().getId() == userId || failed[i].getRecipient().getId() == userId)
                    && failed[i].getIdentifier().equals(transactionId)) {
                unpairedTransactionsList.remove(failed[i].getIdentifier());
            }
        }
    }

    public Transaction[] getUnpairedTransactionsList() {
        return unpairedTransactionsList.toArray();
    }

}
