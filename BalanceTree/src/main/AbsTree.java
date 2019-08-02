/**
 * @author evelyn
 * @description
 * @date 2019-07-26 12:22
 **/
public abstract class AbsTree<K extends Comparable<? super K>, V extends Comparable<? super V>> {
    private Node<K, V> root;
    private int height;
    private int size;

    public boolean isEmpty() {
        return this.root == null;
    }

    public int size() {
        return this.size;
    }

    public int height() {
        return this.height;
    }

    public abstract Object containKey(K key);

    public abstract boolean put(K key, V value);

    public abstract boolean remove(K key);

    public abstract boolean set(K key, K set_key, V set_value);

    public abstract Object getValue(K key);

    public abstract void build(K[] keys, V[] values);

    public abstract void clear();
}
