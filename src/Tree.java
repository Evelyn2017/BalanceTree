public interface Tree <T extends Comparable> {
    /**
     * @return 0/1
     */
    boolean isEmpty();

    /**
     * number of nodes,start from 1
     *       0
     *    1    2
     * 3     5   6
     * @return 6
     */
    int size();

    /**
     * height of tree, start from 1
     *       0
     *    1    2
     * 3     5   6
     * @return 2
     */
    int height();

    void insert(T data);
    void remove(T data);
    T findMin();
    T findMax();
    Tree fineNode(T data);
    boolean contains(T data);
    void clear();
    void build();
}
