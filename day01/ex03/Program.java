public class Program {
    public static void main(String[] args) {
        TransactionsLinkedList list = new TransactionsLinkedList();
        User user1 = new User("alex", 2200);
        User user2 = new User("john", 5200);
        Transaction trans = new Transaction(user1, user2, Transaction.TransactionType.DEBIT, 100);
        Transaction trans2 = new Transaction(user1, user2, Transaction.TransactionType.DEBIT, 100);
        Transaction trans3 = new Transaction(user1, user2, Transaction.TransactionType.DEBIT, 100);
        list.add(trans);
        list.add(trans3);
        try {
            list.remove(trans2.getIdentifier());
        } catch (TransactionNotFoundException e) {
            e.getError();
        }
        Transaction[] array = list.toArray();
        for (Transaction item : array)
            System.out.println(item);
    }
}
