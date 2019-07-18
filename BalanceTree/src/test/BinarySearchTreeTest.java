import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest<K extends Comparable, V extends Comparable> {
    private BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();

    private BinarySearchTree.Node<Integer, Integer> node_pass = new BinarySearchTree.Node<>(1, -1);
    private BinarySearchTree.Node<Integer, Integer> node_fail = new BinarySearchTree.Node<>(10, -1);

    private Integer[] keys = {8, 6, 9, 4, 7, 10};
    private Integer[] values = {-1, -1, -1, -1, -1, -1};
    private Integer containKey_pass = new Integer(10);
    private Integer containKey_fail = new Integer(-1);

    @Before
    public void setUp() throws Exception {
        tree.build(keys, values);
    }

    @Test
    public void build() {
        Assert.assertNotEquals(tree.getRoot(), null);
    }


    @Test
    public void isEmpty() {
        BinarySearchTree.Node root = tree.getRoot();
        Assert.assertNotEquals(root, null);
    }

    @Test
    public void size() {
        BinarySearchTree.Node root = tree.getRoot();
        Assert.assertEquals(tree.size(), 6);
    }

    @Test
    public void height() {
        Assert.assertEquals(tree.height(), 3);
    }


    @Test
    public void remove() {
    }

    @Test
    public void set() {
    }

    @Test
    public void containKey() {
        Assert.assertTrue(tree.containKey(containKey_pass));
        Assert.assertFalse(tree.containKey(containKey_fail));
    }

    @Test
    public void put() {
        Assert.assertTrue(tree.put(node_pass.key, node_pass.val));
        Assert.assertFalse(tree.put(node_fail.key, node_fail.val));
    }
}