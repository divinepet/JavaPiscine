public class Program {
    public static void main(String[] args) throws UserNotFoundException {
        TransactionsService service = new TransactionsService();
        User benjamin = new User("Benjamin", 1400);
        User alice = new User("Alice", 400);

        service.addNewUser(benjamin);
        service.addNewUser(alice);

        try {
            service.transfer(benjamin.getId(), alice.getId(), 500);
        } catch (Transaction.IllegalTransactionException e) {
            System.out.println(e.getMessage());
        }
        String transactionIdToRemove = null;

        Transaction[] array = service.getUserTransactionsList(benjamin.getId());
        for (Transaction s : array) {
            System.out.println(s);
            transactionIdToRemove = s.getIdentifier();
        }

        service.removeUserTransactions(transactionIdToRemove, benjamin.getId());
        Transaction[] array2 = service.getUserTransactionsList(benjamin.getId());
        for (Transaction s : array2)
            System.out.println(s);

        System.out.println("Benjamin balance: " + service.getUserBalance(benjamin.getId()));
        System.out.println("Alice balance: " + service.getUserBalance(alice.getId()));

        try {
            service.transfer(benjamin.getId(), alice.getId(), 1500);
        } catch (Transaction.IllegalTransactionException e) {
            System.out.println(e.getMessage());
        }

        Transaction[] array3 = service.getUnpairedTransactionsList();
        for (Transaction s : array3)
            System.out.println(s);
    }
}
