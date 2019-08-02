import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class AVLTreeTest {
    private Node<Integer, Integer> node = new Node<>();

    private Integer[] keys = {1,2,3,4,5,6};
    private Integer[] values = {1,2,3,4,5,6};

    AVLTree<Integer, Integer> tree = new AVLTree<>();

    @Before
    public void setUp() throws Exception {
        tree.build(keys, values);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println(node.levelOrder(tree.getRoot()));
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
    public void testDelete() {
        tree.remove(4);
    }


}