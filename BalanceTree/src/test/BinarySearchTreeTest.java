import org.junit.After;
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

    private BinarySearchTree.Node<Integer, Integer> set_pass = new BinarySearchTree.Node<>(8, 1);
    private BinarySearchTree.Node<Integer, Integer> set_fail = new BinarySearchTree.Node<>(15, 1);


    @Before
    public void setUp() throws Exception {
        tree.build(keys, values);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("pre order traverse:");
        BinarySearchTree.preOrder(tree.getRoot());
        System.out.println("\nin order traverse:");
        BinarySearchTree.inOrder(tree.getRoot());
    }

    @Test
    public void build() {
        Assert.assertNotEquals(tree.getRoot(), null);
    }


    @Test
    public void isEmpty() {
         Assert.assertFalse(tree.isEmpty());
         BinarySearchTree tree2 = new BinarySearchTree();
         Assert.assertTrue(tree2.isEmpty());
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
        Assert.assertTrue(tree.remove(10));
    }

    @Test
    public void set() {
        Assert.assertTrue(tree.set(set_pass.key, 11, 2));
        Assert.assertFalse(tree.set(set_fail.key, 12,2));
        BinarySearchTree.preOrder(tree.getRoot());
    }

    @Test
    public void containKey() {
        Assert.assertNotNull(tree.containKey(containKey_pass));
        Assert.assertNull(tree.containKey(containKey_fail));
    }

    @Test
    public void clear() {
        tree.clear();
        Assert.assertEquals(tree.size(), 0);
    }

    @Test
    public void put() {
        Assert.assertTrue(tree.put(node_pass.key, node_pass.val));
        Assert.assertFalse(tree.put(node_fail.key, node_fail.val));
    }
}