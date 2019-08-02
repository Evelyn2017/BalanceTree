import org.junit.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author evelyn
 * @description
 * @date 2019-07-25 11:12
 **/
public class AVLTree<K extends Comparable<? super K>,V extends Comparable<? super V>> extends AbsTree<K, V> {
    private int size;
    private Node<K, V> root;
    private LinkedList<Node<K, V>> stack = new LinkedList<Node<K, V>>();

    public Node<K, V> getRoot() {
        return root;
    }

    public void setRoot(Node<K, V> root) {
        this.root = root;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }
    @Override
    public int height() {
        return root.height;
    }

    public int getHeight(Node node) {
        return node == null ? 0:node.height;
    }

    /**
     * LL旋转 （右旋转）
     */
    private Node<K, V> rotateLL(Node<K, V> node) {
        Node<K, V> tmp = node.left;
        node.left = tmp.right;
        tmp.right = node;
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        tmp.height = Math.max(getHeight(tmp.left), getHeight(tmp.right)) + 1;
        node = tmp;
        return node;
    }

    private Node<K, V> rotateRR(Node<K, V> node) {
        Node<K, V> tmp = node.right;
        node.right = tmp.left;
        tmp.left = node;
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        tmp.height = Math.max(getHeight(tmp.left), getHeight(tmp.right)) + 1;
        node = tmp;
        return node;
    }

    private Node<K, V> rotateLR(Node<K, V> node) {
        node.left = rotateRR(node.left);
        node = rotateLL(node);
        return node;
    }

    private Node<K, V> rotateRL(Node<K, V> node) {
        node.right = rotateLL(node.right);
        node = rotateRR(node);
        return node;
    }

    private void fixAfterInsertion(K key) {
        Node<K, V> node = root;
        while(!stack.isEmpty()) {
            node = stack.pop();
            int newHeight = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
            // node is not a leaf node
            if (node.height > 1 && newHeight == node.height) {
                stack.clear();
                return;
            }
            node.height = newHeight;
            // balance factor
            int d = getHeight(node.left) - getHeight(node.right);
            if (Math.abs(d) <= 1)
                continue;
            else {
                //左边深度大于右边深度
                if (d == 2) {
                    if (key.compareTo(node.left.key) < 0)
                        node = rotateLL(node);
                    else
                        node = rotateRL(node);
                }
                //右边深度大于左边深度
                else {
                    // r->r
                    if (key.compareTo(node.right.key) > 0)
                        node = rotateRR(node);
                    else
                        node = rotateLR(node);

                }
                if (!stack.isEmpty()) {
                    if (key.compareTo(stack.peek().key) < 0)
                        stack.peek().left = node;
                    else
                        stack.peek().right = node;
                }
            }
        }
        root = node;
    }

    public List<List<K>> levelOrder(Node node) {
        List<List<K>> res = new ArrayList<>();
        if (node == null)
            return res;
        Queue<Node> q = new LinkedList<>();
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

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean put(K key, V value) {
        if (key == null || value == null)
            throw new NullPointerException("key and value should not be null");
        Node<K, V> node = new Node<>(key, value);
        if (root == null) {
            root = node;
            stack.push(root);
            size ++;
            return true;
        }
        Node<K, V> tmp = root;
        while (tmp != null) {
            stack.push(tmp);
            if (key.compareTo(tmp.key) == 0) {
                tmp.value = value;
                return true;
            }
            if (key.compareTo(tmp.key) < 0) {
                if (tmp.left == null) {
                    tmp.left = node;
                    size ++;
                    stack.push(tmp.left);
                    fixAfterInsertion(key);
                    return true;
                } else
                    tmp = tmp.left;
            }
            else {
                if (tmp.right == null) {
                    tmp.right = node;
                    size ++;
                    stack.push(tmp.right);
                    fixAfterInsertion(key);
                    return true;
                } else
                    tmp = tmp.right;
            }
        }

        return false;
    }

    public void checkBalance() {
        postOrderCheckBalance(root);
    }

    public void postOrderCheckBalance(Node<K, V> node) {
        if (node != null) {
            postOrderCheckBalance(node.left);
            postOrderCheckBalance(node.right);
            Assert.assertTrue(Math.abs(getHeight(node.left) - getHeight(node.right)) <= 1);
        }
    }

    @Override
    public boolean remove(K key) {
        return false;
    }

    @Override
    public boolean set(K key, K key1, V value) {
        return false;
    }

    @Override
    public V getValue(K key) {
        Node<K, V> tmp = root;
        while (tmp != null) {
            if (key.compareTo(tmp.key) == 0)
                return tmp.value;
            else if (key.compareTo(tmp.key) < 0)
                tmp = tmp.left;
            else
                tmp = tmp.right;
        }
        return null;
    }

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
    public void build(K[] keys, V[] values) {
        if (keys.length == 0 || values.length == 0)
            throw new NullPointerException("keys and values should not be null!");
        if (keys.length != values.length)
            throw new NullPointerException("the number of keys and values should be equal!");
        for (int i = 0; i < keys.length; i++) {
            put(keys[i], values[i]);
        }
    }

    @Override
    public void clear() {

    }

}
