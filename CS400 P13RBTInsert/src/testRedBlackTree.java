
public class testRedBlackTree extends RedBlackTree{
    public static void Main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();
        RBTNode<Integer> RBTRoot = new RBTNode<>(75);
        RBTRoot.blackHeight = 1;
        
        RBTRoot.down[0] = new RBTNode<>(50);
        RBTRoot.down[0].up = RBTRoot;
        
        RBTRoot.down[1] = new RBTNode<>(100);
        RBTRoot.down[1].up = RBTRoot;
        ((RBTNode<Integer>)RBTRoot.down[1]).blackHeight = 1;
        
        rbt.root = RBTRoot;
        rbt.insert(25);
        rbt.enforceRBTreePropertiesAfterInsert(((RBTNode)rbt.root.down[0].down[0]));
        System.out.println(rbt.toLevelOrderString());
        
        
//        Assertions.assertEquals("[ 50, 25, 75, 100 ]", rbt.toLevelOrderString());
//        Assertions.assertEquals(0, ((RBTNode<Integer>)rbt.root.down[0]).blackHeight);
//        Assertions.assertEquals(1, ((RBTNode<Integer>)rbt.root.down[1].down[1]).blackHeight);
    }
}
