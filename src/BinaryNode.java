import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryNode<T extends Comparable> implements Serializable {

    private class Node {
        private int val;
        private Node left;
        private Node right;

        private Node(int value) {
            this.val = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public BinaryNode() {
        this.root = null;
    }

    public void buildTree(Node node, int value) {
        if (node == null)
            root = new Node(value);
        else {
            if (value < node.val) {
                if (node.left == null)
                    node.left = new Node(value);
                else
                    buildTree(node.left, value);

            } else {
                if (node.right == null)
                    node.right = new Node(value);
                else
                    buildTree(node.right, value);
            }

        }
    }

    public void preOrder(Node node) {
        if (node != null) {
            System.out.println(node.val + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public List<Integer> levelOrder(Node node) {
        List<Integer> res = new ArrayList<>();
        if (node == null)
            return res;
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            Node n = q.poll();
            res.add(n.val);
            if (n.left != null)
                q.add(n.left);
            if (n.right != null)
                q.add(n.right);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {20, 4, 3, 12, 45, 21, 111};
        BinaryNode bTree = new BinaryNode();
        for (int i : a)
            bTree.buildTree(bTree.root, i);
        bTree.preOrder(bTree.root);
        List<Integer> m = bTree.levelOrder(bTree.root);
        System.out.println(m);
    }
}
