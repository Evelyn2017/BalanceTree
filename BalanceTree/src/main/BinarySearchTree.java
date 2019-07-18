
import java.util.*;

/**
 * @author evelyn
 * @description BST: left < root < right
 * boolean isEmpty();
 * int height();
 * int size();get the number of nodes
 * void put(K,V); add one node
 * void remove(K);delete one node
 * boolean containKey(K);determine if key is included
 * set(K, V);modify
 * @date 2019-07-18 10:39
 **/
public class BinarySearchTree<K extends Comparable, V extends Comparable> implements Tree<K, V> {

    private Node<K, V> root;
    private int size;
    private int height;

    public Node<K, V> getRoot() {
        return root;
    }

    public void setRoot(Node<K, V> root) {
        this.root = root;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public int height() {
        this.height = calHeight(this.root);
        return this.height;
    }

    private int calHeight(Node node) {
        if (node == null)
            return 0;
        int left_height = calHeight(node.left);
        int right_height = calHeight(node.right);
        return  left_height > right_height ? left_height + 1 : right_height + 1;
    }

    /**
     * @param keys [1,2,3]
     * @param values [1,2,3] 1
     *            2
     *              3
     */
    @Override
    public void build(K[] keys, V[] values) {
        if (keys.length == 0 || values.length == 0)
            throw new NullPointerException("key and value should not be null!");
        if (keys.length != values.length)
            throw new ArrayIndexOutOfBoundsException("the number of keys and values should be equal!");
//        this.root = new BinarySearchTree.Node<>(keys[0], values[0]);
//        this.size ++;
        for (int i = 0; i < keys.length; i++) {
            put(keys[i], values[i]);
        }
    }

    /**
     * TODO:remove a node
     * @param key
     */
    @Override
    public void remove(K key) {

    }

    /**
     * TODO: set a node<K, V>
     * @param key
     * @param value
     */
    @Override
    public void set(K key, V value) {

    }

    /**
     * TODO:containKey
     * @param key
     * @return
     */
    @Override
    public boolean containKey(K key) {
        return false;
    }


    /**
     * 插入元素 key，不能插入key值重复的元素
     *
     * @param key key
     * @param val -1
     */
    public boolean put(K key, V val) {
        if (key == null || val == null)
            throw new NullPointerException("key and value should not be null!");

        boolean flag = true;

        Node<K, V> node = new Node<K, V>(key, val);
        if (this.root == null) {
            this.root = node;
            this.size++;
        } else {
            Node<K, V> tmp = this.root;
            while (tmp != null) {
                if (key.compareTo(tmp.key) == 0)
                    flag = false;
                if (key.compareTo(tmp.key) < 0) {
                    if (tmp.left == null) {
                        tmp.left = node;
                        this.size ++;
                        break;
                    } else
                        tmp = tmp.left;
                } else {
                    if (tmp.right == null) {
                        tmp.right = node;
                        this.size ++;
                        break;
                    } else
                        tmp = tmp.right;
                }
            }
        }
        return flag;
    }

    public static void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }


    /**
     * @param node     8
     *               6    9
     *             4  7     10
     * @return [8, 6, 4, 7, 9, 10]
     */
    public List<K> preOrderIter(Node node) {
        List<K> res = new ArrayList<>();
        if (node == null)
            return res;
        Stack<Node> s = new Stack<>();
        s.push(node);
        while (!s.isEmpty()) {
            Node<K, V> tmp = s.pop();
            if (tmp != null) {
                res.add(tmp.key);
                s.push(tmp.right);
                s.push(tmp.left);
            }
        }

        return res;
    }

    public static void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    public static void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.key + " ");
        }
    }

    public List<K> levelOrder(Node node) {
        List<K> res = new ArrayList<>();
        if (node == null)
            return res;
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            int level_nodes = q.size();
            while (level_nodes > 0) {
                Node<K, V> t = q.poll();
                res.add(t.key);
                if (t.left != null)
                    q.add(t.left);
                if (t.right != null)
                    q.add(t.right);
                level_nodes--;
            }
        }
        return res;
    }


    /**
     * TreeNode definition
     *
     * @param <K> key
     * @param <V> value
     */
    static class Node<K, V> {
        K key;
        V val;
        Node<K, V> left;
        Node<K, V> right;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Integer[] keys = {8, 6, 9, 4, 7, 10};
        Integer[] values = {-1, -1, -1, -1, -1, -1};
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();
        tree.build(keys, values);
    }


}
