import java.util.TreeMap;

/**
 * @author evelyn
 * @description TODO
 * @date 2019-08-02 17:55
 **/
public class RBTree {
    public static void main(String[] args) {

    }


    private static final boolean BLACK = true;
    private static final boolean RED = false;

    static final class RBNode<K extends Comparable< ? super K>, V extends Comparable< ? super V>> {
        K key;
        V value;
        boolean color = BLACK;
        RBNode<K, V> left;
        RBNode<K, V> right;
        RBNode<K, V> parent;

        public RBNode(K key, V value, RBNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }
    }
}




