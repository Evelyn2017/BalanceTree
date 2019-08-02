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
public class BinarySearchTree<K extends Comparable<? super K>, V extends Comparable<? super V>> implements Tree<K, V> {

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
     * @param values
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
    public V remove(K key) {
        if (this.root == null)
            throw new NullPointerException("can not remove from an empty tree!");
        if (containKey(key) == null)
            throw new NullPointerException("key not contained in tree!");
        Node<K, V> tmp = this.root;
        Node<K, V> parent = new Node<>();
        while (tmp != null) {
            if (key.compareTo(tmp.key) == 0) {
                // case 1: leaf node
                if (!tmp.hasLeft() && !tmp.hasRight()) {
                    if (parent.key.compareTo(key) < 0)
                        parent.right = null;
                    else
                        parent.left = null;
                    size--;
                    return tmp.value;
                }
                //case 2: left child == null
                else if (!tmp.hasLeft()) {
                    if (tmp.key.compareTo(parent.key) < 0) {
                        parent.left = tmp.right;
                        size--;
                        return tmp.value;
                    } else {
                        parent.right = tmp.right;
                        size--;
                        return tmp.value;
                    }

                }
                //case 3: right child == null
                else if (!tmp.hasRight()) {
                    if (tmp.key.compareTo(key) < 0) {
                        parent.left = tmp.left;
                        size--;
                        return tmp.value;
                    } else {
                        parent.right = tmp.left;
                        size--;
                        return tmp.value;
                    }
                }
                //case 4: tmp.left child && tmp.right child != null
                // find min node in tmp right child and replace tmp
                else if (tmp.hasRight() && tmp.hasLeft()) {
                    Node<K, V> tmp_right_min = tmp.right;
                    Node<K, V> tmp_right_min_parent = tmp.right;
                    while (tmp_right_min.left != null) {
                        tmp_right_min_parent = tmp_right_min;
                        tmp_right_min = tmp_right_min.left;
                    }
                    // case 4-1: tmp node is a left child
                    if (tmp == parent.left) {
                        //case 4-1-1: tmp right child has no left child
                        // min node is tmp.right
                        if (!tmp.right.hasLeft()) {
                            parent.left = tmp_right_min;
                            tmp_right_min.left = tmp.left;
                            size--;
                            return tmp.value;
                        }
                        //case 4-1-2: tmp right has left child
                        // find min(min is left child of some node) and min_parent.left = min.right;
                        else {
                            parent.left = tmp_right_min;
                            tmp_right_min_parent.left = tmp_right_min.right;
                            tmp_right_min.left = tmp.left;
                            tmp_right_min.right = tmp.right;
                            size--;
                            return tmp.value;
                        }
                    }
                    //case 4-2: tmp node is a right child
                    else {
                        //case 4-2-1: tmp right child has no left child
                        // min node is tmp.right
                        if (!tmp.right.hasLeft()) {
                            parent.right = tmp_right_min;
                            tmp_right_min.left = tmp.left;
                            size--;
                            return tmp.value;
                        }
                        //case 4-2-2: tmp right child has left child
                        // min_parent.left = min.right
                        else {
                            parent.right = tmp_right_min;
                            tmp_right_min_parent.left = tmp_right_min.right;
                            tmp_right_min.right = tmp.right;
                            tmp_right_min.left = tmp.left;
                            size--;
                            return tmp.value;
                        }
                    }
                }
            }
            if (key.compareTo(tmp.key) < 0) {
                parent = tmp;
                tmp = tmp.left;
            } else {
                parent = tmp;
                tmp = tmp.right;
            }
        }
        return null;
    }

    /**
     * 寻找中序遍历下node的前驱结点
     * case 1: node.left != null
     *       predecessor = max(node.left)
     * case 2: node.left == null
     *       predecessor = parent
     */
    public Node<K, V> predecessor(Node<K, V> node) {
        if (node == null)
            return null;
        //case 0: node is the min  node
        if (findMax(this.root) == node)
            return null;
        // case 1: node.left != null
        if (node.left != null)
            return findMax(node.left);
        // case 2: node.left == null
        Node<K, V> parent = this.root;
        Node<K, V> tmp = this.root;
        while (parent.left != tmp && parent.right != tmp) {
            if (node.key.compareTo(tmp.key) < 0) {
                parent = tmp;
                tmp = tmp.left;
            }
            else {
                parent = tmp;
                tmp = tmp.right;
            }
        }
        return parent;
    }


    /**
     * 寻找中序遍历下的后继结点
     * case 1: node.right != null
     *       successor = min(node.right)
     * case2: node.right == null
     *       向上回溯到第一个比孩子大的结点
     */
    public Node<K, V> successor(Node<K, V> node) {
        if (node == null)
            return null;
        // case 0: node is the max node;
        if (findMax(this.root) == node)
            return null;
        // case 1: node has right
        if (!node.hasRight())
            return findMin(node.right);
        // case 2: node has no right
        Node<K, V> parent = root;
        Node<K, V> tmp = root;
        while(parent != null) {
            if (parent.key == node.key)
                break;
            else if (node.key.compareTo(parent.key) < 0) {
                tmp = parent;
                parent = parent.left;
            } else
                parent = parent.right;
        }
        return tmp;
    }




//    public static void main(String[] args) {
//        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();
//        Integer[] keys = {18, 14, 30, 8, 16, 25, 33, 5, 10, 17, 20, 31, 36, 6,9, 7, 37};
//        Integer[] values = {-1, -1, -1, -1, -1, -1,-1,-1 ,-1,-1,-1, -1, -1,-1,-1, -1, -1};
//        tree.build(keys, values);
//        tree.remove(7);
//    }


    //TODO
    public V getValue(K key) {
        return null;
    }

    /** TODO: after set -> left<root<right
     * @param key 8
     * @param value true
     */
    @Override
    public boolean set(K key, K key1, V value) {
        if (containKey(key) == null)
            throw new NullPointerException("key not found in tree!");
        Node<K, V> node = root;
        while (node != null) {
            if (key.compareTo(node.key) == 0) {
                node.key = key1;
                node.value = value;
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
//                        tmp.left.parent = tmp;
                        this.size ++;
                        break;
                    } else {
                        tmp = tmp.left;
                    }

                } else {
                    if (tmp.right == null) {
                        tmp.right = node;
//                        tmp.right.parent = tmp;
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


//    /**
//     * TreeNode definition
//     *
//     * @param <K> key
//     * @param <V> value
//     */
//    static class Node<K, V>{
//        K key;
//        V val;
//        Node<K, V> left;
//        Node<K, V> right;
//
////        Node<K, V> parent;
//
//        Node(K key, V val) {
//            this.key = key;
//            this.val = val;
//        }
//
//        Node () {}
//
//        public boolean hasRight() {
//            return right != null;
//        }
//
//        public boolean hasLeft() {
//            return left != null;
//        }
//    }
}
