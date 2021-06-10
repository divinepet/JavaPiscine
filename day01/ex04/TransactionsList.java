public interface TransactionsList {
    void            add(Transaction item);
    void            remove(String item);
    boolean         isEmpty();
    public int      size();
    Transaction[]   toArray();

}
