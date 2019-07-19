
import java.util.*;

/**
 * @author evelyn
 * @description BST: left < root < right
 * boolean isEmpty();
 * int height();
 * int size();get the number of nodes
 * void put(K,V); add one node
 * void remove(K);delete one node
 * boolean containKey(K);determine if key exists
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
     * @param values [1,2,3]
     *          1
     *            2
     *              3
     */
    @Override
    public void build(K[] keys, V[] values) {
        if (keys.length == 0 || values.length == 0)
            throw new NullPointerException("key and value should not be null!");
        if (keys.length != values.length)
            throw new ArrayIndexOutOfBoundsException("the number of keys and values should be equal!");
        for (int i = 0; i < keys.length; i++) {
            put(keys[i], values[i]);
        }
    }


    @Override
    public boolean remove(K key){
        if (this.root == null)
            throw new NullPointerException("can not remove from an empty tree!");
        if (containKey(key) == null)
            throw new NullPointerException("key not contained in tree!");
        Node<K, V> tmp = this.root;
        while (tmp != null) {
            if (key.compareTo(tmp.key) == 0) {
                // case1: leaf node
                if (tmp.hasRight() && tmp.hasLeft()) {
                    if (tmp.parent.key.compareTo(tmp.key) < 0)
                        tmp.parent.right = null;
                    else
                        tmp.parent.left = null;
                    size --;
                    return true;
                }

                //TODO: case 2,3,4
                //case 2: left child == null

                //case 3: right child == null

                //case 4: left child && right child != null
            }
            if (key.compareTo(tmp.key) < 0)
                tmp = tmp.left;
            else
                tmp = tmp.right;

        }

        return false;
    }

//    public static void main(String[] args) {
//        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();
//        Integer[] keys = {8, 6, 9, 4, 7, 10};
//        Integer[] values = {-1, -1, -1, -1, -1, -1};
//        tree.build(keys, values);
//        tree.remove(4);
//    }


    /** TODO: after set -> left<root<right
     * @param key 8
     * @param value true
     */
    @Override
    public boolean set(K key, K key1, V value) {
        if (containKey(key) == null)
            throw new NullPointerException("key not found in tree!");
        Node node = root;
        while (node != null) {
            if (key.compareTo(node.key) == 0) {
                node.key = key1;
                node.val = value;
                return true;
            }
            if (key.compareTo(node.key) < 0)
                node = node.left;
            else
                node = node.right;
        }
        return false;
    }

    /**
     * @param key 10
     * @return true
     */
    @Override
    public Node<K, V> containKey(K key) {
        if (root == null)
            throw new NullPointerException("tree is empty!");
        Node<K,V> node = this.root;
        while (node != null) {
            if (node.key.compareTo(key) == 0)
                return node;
            if (node.key.compareTo(key) < 0)
                node = node.right;
            else
                node = node.left;
        }
        return null;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.root = null;
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

        Node<K, V> node = new Node<K, V>(key, val);
        if (this.root == null) {
            this.root = node;
            this.size++;
        } else {
            Node<K, V> tmp = this.root;
            while (tmp != null) {
                if (key.compareTo(tmp.key) == 0)
                    return false;
                if (key.compareTo(tmp.key) < 0) {
                    if (tmp.left == null) {
                        tmp.left = node;
                        tmp.left.parent = tmp;
                        this.size ++;
                        break;
                    } else {
                        tmp = tmp.left;
                    }

                } else {
                    if (tmp.right == null) {
                        tmp.right = node;
                        tmp.right.parent = tmp;
                        this.size ++;
                        break;
                    } else {
                        tmp = tmp.right;
                    }

                }
            }
        }
        return true;
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

        Node<K, V> parent;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }


        public boolean hasRight() {
            return right == null;
        }

        public boolean hasLeft() {
            return left == null;
        }
    }
}
