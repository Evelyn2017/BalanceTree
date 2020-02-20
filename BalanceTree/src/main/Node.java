import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    boolean hasRight() {
        return right != null;
    }

    boolean hasLeft() {
        return left != null;
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

    public List<List<K>> levelOrder(Node node) {
        List<List<K>> res = new ArrayList<>();
        if (node == null)
            return res;
        Queue<Node<K ,V>> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            List<K> tmp = new ArrayList<>();
            int level_nodes = q.size();
            while (level_nodes > 0) {
                Node<K, V> t = q.poll();
                tmp.add(t.key);
                if (t.left != null)
                    q.add(t.left);
                if (t.right != null)
                    q.add(t.right);
                level_nodes--;
            }
            res.add(tmp);
        }
        return res;
    }


}
