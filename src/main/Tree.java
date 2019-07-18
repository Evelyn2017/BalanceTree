

public interface Tree<K extends Comparable, V extends Comparable> {
    boolean isEmpty();

    int height();

    int size();

    Tree<K,V> build(K[] keys, V[] values);

    void put(K key, V value);

    void remove(K key);

    void set(K key, V value);

    boolean containKey(K key);
}
