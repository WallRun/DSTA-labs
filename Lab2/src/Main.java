public class Main {
    public static void main(String[] args) {
        BalancedBinarySearchTree tree = new BalancedBinarySearchTree();
        tree.addNode(25);
        tree.addNode(35);
        tree.addNode(17);
        tree.addNode(30);
        tree.addNode(40);

        tree.printPreorderTraversal();
        System.out.println(tree.getSize());
        tree.deleteNode(35);

        tree.printPreorderTraversal();
        System.out.println(tree.getSize());
    }
}