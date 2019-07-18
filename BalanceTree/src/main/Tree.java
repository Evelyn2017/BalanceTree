

public interface Tree<K extends Comparable, V extends Comparable> {
    boolean isEmpty();

    int height();

    int size();

    boolean put(K key, V value);

    void remove(K key);

    boolean set(K key, V value);

    boolean containKey(K key);

    void build(K[] keys, V[] values);
}
