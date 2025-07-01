import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import RedBlackTree.RBTNode;

class testBlackUncleConflictingNodeOnOppositeSide extends RedBlackTree{

    @Test
    void testBlackUncleConflictingNodeOnOppositeSide() {
        //outcome: rotate inserted node and it's parent
        //rotate original child and original grandparent
        //change colors of nodes accordingly
        RBTNode<Integer> grandparent = new RBTNode<>(82);
        grandparent.blackHeight = 1;
        
        RBTNode<Integer> parent = new RBTNode(67);
        grandparent.down[0] = parent;
        parent.up = grandparent;
        parent.blackHeight = 0;
        
        RBTNode<Integer> uncle = new RBTNode(194);
        grandparent.down[1] = uncle;
        uncle.up = grandparent;
        uncle.blackHeight = 1;
        
        RedBlackTree<Integer> rbt = new RedBlackTree<>();
        rbt.root = grandparent;
        rbt.insert(77);
        //tree should look like this before enforce:
        //                       82(1)
        //                      /    \
        //                    67(0)   194(1)
        //                      \
        //                       77(0)   <------- inserted node
        //tree should look like this after enforce:
        //                    77(1)
        //                   /   \
        //                  67(0)  82(0)
        //                           \
        //                            (194)(1)
        Assertions.assertEquals("[ 77, 67, 82, 194 ]", rbt.toLevelOrderString());
        Assertions.assertEquals(1, ((RBTNode<Integer>)rbt.root).blackHeight);
        Assertions.assertEquals(77, ((RBTNode<Integer>)rbt.root).data);
        //color of 77(inserted node) should be black
        Assertions.assertEquals(0, ((RBTNode<Integer>)rbt.root.down[0]).blackHeight);
        Assertions.assertEquals(67, ((RBTNode<Integer>)rbt.root.down[0]).data);
        
        Assertions.assertEquals(0, ((RBTNode<Integer>)rbt.root.down[1]).blackHeight);
        Assertions.assertEquals(82, ((RBTNode<Integer>)rbt.root.down[1]).data);
        
        Assertions.assertEquals(1, ((RBTNode<Integer>)rbt.root.down[1].down[1]).blackHeight);
        Assertions.assertEquals(194, ((RBTNode<Integer>)rbt.root.down[1].down[1]).data);
        
        
    }

}
