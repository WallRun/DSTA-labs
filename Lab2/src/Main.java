public class Main {
    public static void main(String[] args) {
        BalancedBinarySearchTree tree = new BalancedBinarySearchTree();
        tree.addNode(4);
        tree.addNode(6);
        System.out.println(tree.search(6));
        System.out.println(tree.search(34));
    }
}
