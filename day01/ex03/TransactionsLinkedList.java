public class TransactionsLinkedList implements TransactionsList {
    private Node first;
    private Node last;
    private int N;

    public TransactionsLinkedList() {
        first = null;
        last = null;
        N = 0;
    }

    @Override
    public void add(Transaction item) {
        if (item == null) {
            System.out.println("error");
        }
        if (!isEmpty()) {
            Node prev = last;
            last = new Node(item, null);
            prev.next = last;
        } else {
            last = new Node(item, null);
            first = last;
        }
        N++;
    }

    @Override
    public void remove(String item) throws TransactionNotFoundException {
        if (isEmpty()) {
            throw new TransactionNotFoundException();
        }
        Node curr = first;
        Node prev = first;
        while (curr != null || curr == last) {
            if (curr.data.getIdentifier().equals(item)) {
                if (N == 1) {
                    first = null;
                    last = null;
                }
                else if (curr.equals(first)) {
                    first = first.next;
                }
                else if (curr.equals(last)) {
                    last = prev;
                    last.next = null;
                }
                else {
                    prev.next = curr.next;
                }
                N--;
                return;
            }
            prev = curr;
            curr = prev.next;
        }
        throw new TransactionNotFoundException();
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public Transaction[] toArray()
    {
        Node copy_head = first;
        int size = size() - 1;
        int i = 0;
        Transaction[] array = new Transaction[size];
        while (copy_head != null)
        {
            array[i] = copy_head.getData();
            copy_head = copy_head.next;
        }
        return array;
    }

    private static class Node {
        private Transaction data;
        private Node next;

        public Node(Transaction data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Transaction getData()
        {
            return data;
        }
    }
}

class TransactionNotFoundException extends RuntimeException {
    public static void getError() {
        System.out.println("Transaction with your ID doesn't exist");
    }
}