public interface Tree<K extends Comparable<? super K>, V extends Comparable<? super V>> {
    boolean isEmpty();

    int height();

    int size();

    boolean put(K key, V value);

    Object remove(K key);

    boolean set(K key, K key1, V value);

    Object containKey(K key);

    Object getValue(K key);

    void build(K[] keys, V[] values);

    void clear();
}
