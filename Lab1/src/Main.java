public class Main {
    public static void main(String[] args) {
        SortedLinkedList<Object> list = new SortedLinkedList<>();
        list.add("b");
        list.add("b");
        list.add("c");
        list.add(new Exception("w"));
        list.add(new Exception("wd"));
        list.add(new Exception("wdd"));
        list.delete("b");
        System.out.println(list.search("b"));
        System.out.println(list.getSize());

    }
}
