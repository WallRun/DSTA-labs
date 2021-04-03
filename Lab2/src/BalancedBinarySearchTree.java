import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class BalancedBinarySearchTree {
    private Node root;
    private int size;

    public void addNode(int item) {
        root = addNodeRec(item, root, root);
        size++;
        balance();
    }

    private void addNodeUnbalanced(int item) {
        List<Node> nodes = getLeafNodes();
        Node min = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            Node temp = nodes.get(i);
            if (temp.getHeight() < min.getHeight()) min = temp;
        }

        addNodeRec(item, min, min);
        size++;
    }

    private Node addNodeRec(int item, Node p, Node parent) {
        if (p == null) {
            p = new Node();
            p.setValue(item);
            p.setParent(parent);

        } else if (item < p.getValue()) {
            p.setLeft(addNodeRec(item, p.getLeft(), p));
        } else {
            p.setRight(addNodeRec(item, p.getRight(), p));
        }

        return p;
    }

    public void deleteNode(int item) {
        while (search(item)) {
            root = deleteNodeRec(item, root, true);
            size--;
            balance();
        }
    }

    private Node deleteNodeRec(int item, Node p, boolean deleteRef) {
        if (item < p.getValue()) p.setLeft(deleteNodeRec(item, p.getLeft(), deleteRef));
        else if (item > p.getValue()) p.right = deleteNodeRec(item, p.getRight(), deleteRef);
        else if (deleteRef){
            p = deleteByRef(p);
        }

        return p;
    }

    private Node deleteByRef(Node p) {
        if (p == null) return p;
        if (p.getLeft() == null) {
            return p.getRight();
        } else if (p.getRight() == null) {
            return p.getLeft();
        } else {
            Node p1 = p.getRight();
            while (p1.getLeft() != null) {
                p1 = p1.getLeft();
            }

            p.setValue(p1.getValue());
            p.setRight(deleteByRef(deleteNodeRec(p.getValue(), p.getRight(), false)));


            return p;
        }
    }


    private void balance() {
        if (size == 1) return;
        List<Node> list = getNodeList();
        list.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.getValue(), o2.getValue());
            }
        });
        int fixedSize = size;
        int middle = (int) (Math.ceil((float) fixedSize / 2.0) -1);
        makeEmpty();
        root = list.get(middle);
        root.setRight(null);
        root.setLeft(null);
        root.setParent(null);
        size = 1;
        int leftIndex = middle - 1;
        int rightIndex = middle + 1;



        while (leftIndex >= 0 || rightIndex < fixedSize) {
            int leftToInsert = Integer.MIN_VALUE;
            int rightToInsert = Integer.MIN_VALUE;
            if (leftIndex >= 0) {
                leftToInsert = list.get(leftIndex).getValue();
            }
            if (rightIndex < fixedSize) {
                rightToInsert = list.get(rightIndex).getValue();
            }


            if (leftToInsert != Integer.MIN_VALUE) addNodeUnbalanced(leftToInsert);
            if (rightToInsert != Integer.MIN_VALUE) addNodeUnbalanced(rightToInsert);


            leftIndex--;
            rightIndex++;

        }
    }

    private List<Node> getLeafNodes() {
        return getLeafNodesRec(root, new LinkedList<>());
    }

    private List<Node> getLeafNodesRec(Node p, List<Node> list) {
        if (p == null) return list;
        if (p.isLeafNode()) list.add(p);
        list = getLeafNodesRec(p.getLeft(), list);
        list = getLeafNodesRec(p.getRight(), list);

        return list;
    }

    public void printLeafNodes() {
        for (Node n : getLeafNodes()) {
            System.out.println(n.value);
        }
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
        return getNodeListRec(root, new ArrayList<>());
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

    

    private class Node {
        private Node left;
        private int value;
        private Node right;
        private Node parent;

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

        private boolean isLeafNode() {
            return !(right != null && left != null);
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public int getHeight() {
            int height = 0;
            Node temp = this;
            while (temp!= root) {
                temp = temp.getParent();
                height++;
            }

            return height;
        }
    }

}
