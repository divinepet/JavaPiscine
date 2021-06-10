public class Program {
    public static void main(String[] args) throws UserNotFoundException {
        TransactionsService service = new TransactionsService();

        service.addNewUser("Igor", 1400);
        service.addNewUser("Oleg", 700);

        System.out.println(service.getUserBalance(1));
        System.out.println(service.getUserBalance(2));

        service.outcomeTransaction(1, 2, 100);

        System.out.println(service.getUserBalance(1));
        System.out.println(service.getUserBalance(2));

        service.getTransactionsList();
    }
}
