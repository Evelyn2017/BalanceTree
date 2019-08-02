/**
 * @author evelyn
 * @description Node description <>Key-Value</>
 * @date 2019-07-25 16:46
 **/
public class Node<K extends Comparable<? super K>, V extends Comparable<? super V>> {
    K key;
    V value;
    Node<K, V> left;
    Node<K, V> right;
    int height = 1;
    int balanceFactor;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    Node() {}

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", value=" + value +
                ", height=" + height +
                '}';
    }

    boolean hasRight(Node node) {
        return node.right != null;
    }

    boolean hasLeft(Node node) {
        return node.left != null;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<K, V> getLeft() {
        return left;
    }

    public void setLeft(Node<K, V> left) {
        this.left = left;
    }

    public Node<K, V> getRight() {
        return right;
    }

    public void setRight(Node<K, V> right) {
        this.right = right;
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

    public Node<K, V> findMin(Node<K, V> node) {
        Node<K, V> res = node;
        while(res.left != null)
            res = res.left;
        return res;
    }

    public  Node<K, V> findMax(Node<K, V> node) {
        Node<K, V> res = node;
        while(res.right != null) {
            res = res.right;
        }
        return res;
    }
}
