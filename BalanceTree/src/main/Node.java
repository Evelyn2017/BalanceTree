import java.util.List;

/**
 * @author evelyn
 * @description TODO
 * @date 2019-07-25 16:46
 **/
public abstract class Node<K extends Comparable, V extends Comparable> {
    private K key;
    private V value;
    private Node<K, V> left;
    private Node<K, V> right;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    Node() {}

    boolean hasRight(Node node) {
        return node.right != null;
    }

    boolean hasLeft(Node node) {
        return node.left != null;
    }

    static void preOrder(Node node) {
        if (node != null) {
            System.out.println(node.key);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    static void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.key);
            inOrder(node.right);
        }
    }

    static void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.key);
        }
    }

    abstract Node<K, V> findMin(Node node);

    abstract Node<K, V> findMax(Node node);
}
