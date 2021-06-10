public class TransactionsService {
    UsersList list;
    TransactionsLinkedList translist;

    TransactionsService() {
        this.list = new UsersArrayList();
        this.translist = new TransactionsLinkedList();
    }

    public void addNewUser(String name, Integer transferAmount) {
        this.list.add(new User(name, transferAmount));
    }

    public int getUserBalance(int id) throws UserNotFoundException {
        return list.getById(id).getBalance();

    }

    public void outcomeTransaction(int id1, int id2, int transferAmount) throws UserNotFoundException {
        Transaction transaction = new Transaction(list.getById(id1), list.getById(id2), transferAmount);
        translist.add(transaction);
        int balanceSender = list.getById(id1).getBalance();
        int balanceRecipient = list.getById(id2).getBalance();
        if ((balanceSender - transferAmount) >= 0) {
            list.getById(id1).setBalance(balanceSender - transferAmount);
            incomeTransaction(id1, id2, transferAmount, transaction);
        }
    }

    private void incomeTransaction(int id1, int id2, int transferAmount, Transaction tr) throws UserNotFoundException {
        Transaction transactionOutcome = new Transaction(list.getById(id1), list.getById(id2), tr.getIdentifier(), transferAmount);
        translist.add(transactionOutcome);
        list.getById(id2).setBalance(list.getById(id2).getBalance() + transferAmount);
    }

    public void getTransactionsList() {
        Transaction[] array = translist.toArray();
        for (Transaction item : array)
            System.out.println(item.getFullInfo());
    }
    public String[] getUsers()
    {
        int size = list.getNumberOfUsers();
        String[] usersArray = new String[size];
        for (int i = 0; i < usersArray.length; i++)
        {
            User user = list.getByIndex(i);
            usersArray[i] = user.getName();
            System.out.println(user.getName());
        }

        return usersArray;
    }
}
