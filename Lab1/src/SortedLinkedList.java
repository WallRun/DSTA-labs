public class SortedLinkedList<T> {

    private Node first;
    private int size;


    public void add(T value) {
        if (!(value instanceof Comparable)) {
            throw new IllegalArgumentException("the class " + value.getClass().getSimpleName() + " is not comparable");
        }


        Node newNode = new Node();
        newNode.setValue(value);

        if (isEmpty()) {
            first = newNode;
            size++;
        } else {
            Node prev = null;
            Node current = first;

            for (int i = 0; i < size; i++) {
                Comparable newVal = (Comparable) newNode.getValue();
                Comparable currVal = (Comparable) current.getValue();
                if (newVal.compareTo(currVal) <= 0) {
                    if (i==0) {
                        first = newNode;
                        first.setNext(current);
                    } else {
                        prev.setNext(newNode);
                        newNode.setNext(current);
                    }

                    size++;
                    return;
                }

                prev = current;
                current = current.getNext();
                if (current==null) {
                    prev.setNext(newNode);
                    size++;
                    return;
                }
            }
        }

    }

    public void delete(T value) {
        Node current = first;
        Node prev = null;
        int fixedSize = getSize();
        boolean wasDeleted = false;

        for (int i = 0; i < fixedSize; i++) {
            boolean flag = false;
            if (current.getValue()==value) {
                if (current.equals(first)) first = current.getNext();
                if (prev!=null) prev.setNext(current.getNext());
                size--;
                flag = true;
                wasDeleted = true;
            }

            if (!flag) {
                if (prev == null) prev = first;
                else prev = current;
                current = current.getNext();
            } else {
                if (prev == null) current = first;
                else current = current.getNext();

            }
        }

        if (!wasDeleted) {
            System.out.println("the item was not deleted, as it is not in the list");
        }
    }

    public int getSize() {
        return size;
    }

    public void clear() {
        first = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean search(T value) {
        Node current = first;
        for (int i = 0; i < size; i++) {
            if (current.getValue() == value) return true;
            else current = current.getNext();
        }

        return false;
    }

    public void print() {
        Node current = first;
        System.out.print("(" + size + ")" + " [");

        for (int i = 0; i < size; i++) {
            if (current==null) break;
            if (current.next!=null) System.out.print(current.getValue() + ", ");
            else System.out.print(current.getValue());
            current = current.getNext();
        }

        System.out.print("]");
        System.out.println();
    }

    public int sumItems() {
        if (first == null) return 0;
        if (!(first.getValue() instanceof Integer)) {
            throw new IllegalStateException("this list is not an integer list");
        }
        return sumItems(first, 0);
    }

    private int sumItems(Node thisNode, int sum) {
        if (thisNode == null) return sum;
        return sumItems(thisNode.next, sum + (Integer) thisNode.getValue());
    }


    public class Node {
        private T value;
        private Node next;

        private Node() {

        }

        private Node(T value) {
            this.value = value;
        }

        private Node(Node next) {
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public boolean equals(Object other) {

            if (getClass()!= other.getClass()) return false;

            return next == (((Node) other).next) && value == (((Node) other).value);

         }
    }
}
