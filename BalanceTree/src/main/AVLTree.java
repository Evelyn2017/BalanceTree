/**
 * @author evelyn
 * @description TODO
 * @date 2019-07-25 11:12
 **/
public class AVLTree<K extends Comparable,V extends Comparable> implements Tree<K, V> {
    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean put(K key, V value) {
        return false;
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
    public Object containKey(K key) {
        return null;
    }

    @Override
    public void build(K[] keys, V[] values) {

    }

    @Override
    public void clear() {

    }

    private static class Node<K, V> {
        private K key;
        private V value;
        Node<K, V> left;
        Node<K, V> right;

        Node (K key, V value) {
            this.key = key;
            this.value = value;
        }
        Node () {}

        public boolean hasLeft(Node node) {
            return node.left != null;
        }

        public boolean hasRight(Node node) {
            return node.right != null;
        }

        int node_height(Node node) {
            if (node == null)
                return 0;
            int left_height = node_height(node.left);
            int right_height = node_height(node.right);

            return Math.max(left_height, right_height) + 1;
        }

    }
}
