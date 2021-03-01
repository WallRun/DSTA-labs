public class SortedLinkedList<T extends Comparable<T>> {

    private Node first;
    private int size;

    private int getSize() {
        return size;
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
