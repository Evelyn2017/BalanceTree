import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class AVLTreeTest {
    public static final int MAX = 20000;
    private Random random = new Random();

    private Integer[] keys = {1,2,3,4,5,6};
    private Integer[] values = {1,2,3,4,5,6};

    AVLTree<Integer, Integer> tree = new AVLTree<>();

    @Before
    public void setUp() throws Exception {
        tree.build(keys, values);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println(tree.levelOrder(tree.getRoot()));
    }

    @Test
    public void testNull() {

    }

    @Test
    public void testput() {
        tree.put(-1,-1);
        tree.put(-2,-2);
        Assert.assertEquals(tree.size(), 8);
    }

    @Test
    public void testJDKTreeMap() {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < MAX; i++) {
            map.put(random.nextInt(MAX), random.nextInt(MAX));
        }
        for (int i = 0; i < MAX; i++) {
            map.get(random.nextInt(MAX));
        }
    }

    @Test
    public void testAVLTree() {
        AVLTree<Integer, Integer> tre = new AVLTree<>();
        for (int i = 0; i < MAX; i++) {
            tre.put(i, random.nextInt(MAX));
        }
        tre.checkBalance();
        for (int i = 0; i < MAX; i++) {
            tre.getValue(random.nextInt(MAX));
        }
    }
}