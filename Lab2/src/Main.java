public class Main {
    public static void main(String[] args) {
        BalancedBinarySearchTree tree = new BalancedBinarySearchTree();
        tree.addNode(9);
        tree.addNode(13);
        tree.addNode(8);
        tree.addNode(14);
        tree.addNode(10);
        tree.addNode(12);
        tree.addNode(11);
        tree.addNode(10);
        tree.deleteNode(10);

        tree.printPreorderTraversal();

    }
}