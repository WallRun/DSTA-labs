import java.util.LinkedList;
import java.util.List;

public class BalancedBinarySearchTree {
    private Node root;
    private int size;

    public void addNode(int item) {
        root = addNodeRec(item, root);
        size++;
        balance();
    }

    private Node addNodeRec(int item, Node p) {
        if (p == null) {
            p = new Node();
            p.setValue(item);
        } else if (item < p.getValue()) {
            p.setLeft(addNodeRec(item, p.getLeft()));
        } else {
            p.setRight(addNodeRec(item, p.getRight()));
        }

        return p;
    }

    public void deleteNode(int item) {
        if (search(item)) {
            root = deleteNodeRec(item, root);
            size--;
            balance();
        } else {
            System.out.println("Item not found and not deleted");
        }
    }

    private Node deleteNodeRec(int item, Node p) {
        if (item < p.getValue()) p.setLeft(deleteNodeRec(item, p.getLeft()));
        else if (item > p.getValue()) p.right = deleteNodeRec(item, p.getRight());
        else p = deleteByRef(p);

        return p;
    }

    private Node deleteByRef(Node p) {
        if (p == null)return p;
        if (p.getLeft() == null) {
            return p.getRight();
        } else if (p.getRight() == null) {
            return p.getLeft();
        } else {
            Node p1 = p.getRight();
            while(p1.getLeft()!= null) {
                p1 = p1.getLeft();
            }

            p.setValue(p1.getValue());
            p.setRight(deleteByRef(deleteNodeRec(p.getValue(), p.getRight())));


            return p;
        }
    }


    //todo this
    private void balance() {
        for (Node n:getNodeList()) {
            System.out.print(n.getValue() + " ");
        }
        System.out.println("balanced");
    }


    public boolean search(int item) {
        return searchRec(item, root);
    }

    private boolean searchRec(int item, Node p) {
        if (p == null) return false;
        if (p.getValue() == item) return true;
        if (p.getValue() > item) return searchRec(item, p.getLeft());
        else return searchRec(item, p.getRight());
    }

    public void makeEmpty() {
        size = 0;
        root = null;
    }


    public boolean isEmpty() {
        return root == null && size == 0;
    }

    private List<Node> getNodeList() {
        return getNodeListRec(root, new LinkedList<>());
    }

    private List<Node> getNodeListRec(Node p, List<Node> list) {
        if (p == null) return list;
        list.add(p);
        list = getNodeListRec(p.getRight(), list);
        list = getNodeListRec(p.getLeft(), list);
        return list;
    }

    public void printPreorderTraversal() {
        System.out.print("Preorder: ");
        printPreorderRec(root);
        System.out.println();
    }

    private void printPreorderRec(Node p) {
        if (p == null) return;
        System.out.print(p.value + " ");
        printPreorderRec(p.getLeft());
        printPreorderRec(p.getRight());
    }

    public void printInorderTraversal() {
        System.out.print("Inorder: ");
        printInorderRec(root);
        System.out.println();
    }

    private void printInorderRec(Node p) {
        if (p == null) return;

        printInorderRec(p.getLeft());
        System.out.print(p.value + " ");
        printInorderRec(p.getRight());
    }

    public void printPostorderTraversal() {
        System.out.print("Postorder: ");
        printPostorderRec(root);
        System.out.println();
    }

    private void printPostorderRec(Node p) {
        if (p == null) return;

        printPostorderRec(p.getLeft());
        printPostorderRec(p.getRight());
        System.out.print(p.value + " ");
    }


    public int getSize() {
        return size;
    }

    ///////////////////Node/////////////////////

    private class Node {
        private Node left;
        private int value;
        private Node right;

        public Node() {

        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

}
