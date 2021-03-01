public class SortedLinkedList<T extends Comparable<T>> {

    private Node first;
    private int size;

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


    private class Node {
        private T value;
        private Node next;

        private Node(T value, Node next) {
            this.value = value;
            this.next = next;
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
    }
}
