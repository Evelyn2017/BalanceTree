

/**
 * @author evelyn
 * @description TODO
 * @date 2019-09-02 20:55
 **/
public class Main {
    Nodes root;
    public boolean isBalance(Nodes root) {
        if (root == null)
            return true;
        int right= height(root.left);
        int left = height(root.right);
        int gap = Math.abs(right - left);
        if (gap > 1)
            return false;
        return isBalance(root.left) && isBalance(root.right);
    }

    public int height(Nodes node) {
        if (node == null)
            return 0;
        int left_height = height(node.left);
        int right_height = height(node.right);
        return left_height > right_height ? left_height + 1 : right_height + 1;
    }
}

class Nodes{
    int val;
    Nodes left;
    Nodes right;
    public Nodes() {

    }
}