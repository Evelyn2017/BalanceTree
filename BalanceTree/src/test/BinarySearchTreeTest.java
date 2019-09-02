import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest<K extends Comparable, V extends Comparable> {
    private BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();

    private Node<Integer, Integer> node_pass = new Node<>(1, -1);
    private Node<Integer, Integer> node_fail = new Node<>(10, -1);

    private Integer[] keys = {18, 14, 30, 8, 16, 25, 33, 5, 10, 17, 20, 31, 36, 6,9, 7, 37};
    private Integer[] values = {-1, -1, -1, -1, -1, -1,-1,-1 ,-1,-1,-1, -1, -1,-1,-1, -1, -1};
    private Integer containKey_pass = new Integer(10);
    private Integer containKey_fail = new Integer(-1);

    private Node<Integer, Integer> set_pass = new Node<>(18, 1);
    private Node<Integer, Integer> set_fail = new Node<>(15, 1);


    @Before
    public void setUp() throws Exception {
        tree.build(keys, values);
        System.out.println("\n---------before------- ");
        System.out.println("\npre order traverse:");
        BinarySearchTree.preOrder(tree.getRoot());
        System.out.println("\ntree size : " + tree.size());
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("-------after-------");
        System.out.println(tree.getRoot().levelOrder(tree.getRoot()));
        System.out.println("\ntree size: " + tree.size());
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
        Node root = tree.getRoot();
        Assert.assertEquals(tree.size(), 17);
    }

    @Test
    public void height() {
        Assert.assertEquals(tree.height(), 6);
//        Assert.assertFalse(tree.isBalance(tree.getRoot()));
    }


    @Test
    public void remove() {
        int del_key = 7;
        Assert.assertNotNull(tree.containKey(del_key));
        tree.remove(del_key);
        Assert.assertEquals(16, tree.size());
//        Assert.assertNull(tree.containKey(del_key));
    }

    @Test
    public void set() {
        Assert.assertTrue(tree.set(set_pass.key, 11, 2));
//        Assert.assertFalse(tree.set(set_fail.key, 12,2));
        BinarySearchTree.preOrder(tree.getRoot());
    }

    @Test
    public void containKey() {
        Assert.assertNotNull(tree.containKey(containKey_pass));
        Assert.assertNull(tree.containKey(containKey_fail));
    }
//
//    @Test
//    public void clear() {
//        tree.clear();
//        Assert.assertEquals(tree.size(), 0);
//    }

    @Test
    public void put() {
        Assert.assertTrue(tree.put(node_pass.key, node_pass.value));
        Assert.assertFalse(tree.put(node_fail.key, node_fail.value));
    }

//    @Test
//    public void removeK() {
//        tree.removeK(30);
//        Assert.assertEquals(16, tree.size());
//    }
}