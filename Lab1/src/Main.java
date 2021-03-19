public class Main {
    public static void main(String[] args) {
        SortedLinkedList<String> list = new SortedLinkedList<>();
        list.add("a");
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("b");
        list.add("c");
        list.delete("b");
        System.out.println(list.search("b"));
        System.out.println(list.getSize());

    }
}
