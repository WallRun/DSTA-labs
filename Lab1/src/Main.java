public class Main {
    public static void main(String[] args) {
        SortedLinkedList<Object> list = new SortedLinkedList<>();
        list.add("c");
        list.add("b");
        list.add("b");
        list.add("a");

        list.delete("b");
        System.out.println(list.search("b"));
        System.out.println(list.getSize());
        list.print();

    }
}
