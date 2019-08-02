package Tree;

public interface Tree<K extends Comparable, V extends Comparable> {
    boolean isEmpty();

    int height();

    int size();

    boolean put(K key, V value);

    boolean remove(K key);

    boolean set(K key, K key1, V value);

    Object containKey(K key);

    void build(K[] keys, V[] values);

    void clear();
}
