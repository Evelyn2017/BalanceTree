# 结点定义Node

```java
public class Node<K extends Comparable<? super K>, V extends Comparable<? super V>> {
    K key;
    V value;
    Node<K, V> left;
    Node<K, V> right;
    int height = 1;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    Node() {}
}

```



树的结点为key-value结构

# Tree接口

```java
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
```

实现了Binary Search Tree和AVL tree。

其中BST的删除法则为用删除结点右孩子的最小结点代替。BST删除使用非递归方式（很自闭）。