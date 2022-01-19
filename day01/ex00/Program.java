public class Program {
    public static void main(String[] args) {
        User oleg = new User(1, "Oleg", 1500);
        User anton = new User(2, "Anton", 1500);
        System.out.println("Баланс Олега: " + oleg.getBalance());
        System.out.println("Баланс Антона: " + anton.getBalance());
<<<<<<< HEAD
        System.out.println("Антон переводит 1000 Олегу");
        Transaction transfer = new Transaction(oleg, anton, 0, 1500);
=======
        System.out.println("Антон переводит 1500 Олегу:");
        Transaction transfer = new Transaction(oleg, anton, Transaction.TransactionType.DEBIT, 1500);
>>>>>>> f926bb0f0175de7bf11f2e5fd668469dd3037222
        System.out.println("Баланс Олега: " + oleg.getBalance());
        System.out.println("Баланс Антона: " + anton.getBalance());
        System.out.println("Антон переводит ещё 500 Олегу:");
        Transaction transfer2 = new Transaction(oleg, anton, Transaction.TransactionType.DEBIT, 500);
        System.out.println("Баланс Олега: " + oleg.getBalance());
        System.out.println("Баланс Антона: " + anton.getBalance());
    }
}
