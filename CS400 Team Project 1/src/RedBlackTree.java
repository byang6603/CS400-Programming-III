// --== CS400 Fall 2023 File Header Information ==--
// Name: <Benjamin Chuepeng Yang>
// Email: <bcyang2@wisc.edu>
// Group: <B16>
// TA: <Casey Ford>
// Lecturer: <Gary Dahl>
// Notes to Grader: <N/A>

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
/**
 * This class creates a red black tree data structure that has and maintains the properties 
 * of a red black tree. These include: node are considered red or black(labeled with 0 and 1)
 * there cannot be 2 red nodes with the same parent to child relationship, and going down
 * to each leaf node in the tree will pass through the same number of black nodes.
 * @author byang
 *
 * @param <T> the generic data that will be stored in the red black tree
 */
public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T>{
    
    /**
     * This is a constructor for a red black tree node that inherits the properties
     * and methods of a binary search tree node. This constructor also has a field that
     * keeps track of the color of the node, 0 being red and 1 being black.
     * @author byang
     *
     * @param <T> generic type for data that is going to be stored inside of a node
     */
    protected static class RBTNode<T> extends Node<T> {
        public int blackHeight = 0;
        public RBTNode(T data) { super(data); }
        public RBTNode<T> getUp() { return (RBTNode<T>)this.up; }
        public RBTNode<T> getDownLeft() { return (RBTNode<T>)this.down[0]; }
        public RBTNode<T> getDownRight() { return (RBTNode<T>)this.down[1]; }
    }
    
    /**
     * This method fixes any red black tree violations whenever a node is added to the tree
     * @param nodeOfInterest is the red black tree node that is passed into the method
     * to check of there are any red black tree violations on or around the node
     */
    public void enforceRBTreePropertiesAfterInsert(RBTNode<T> nodeOfInterest) {
        
        if(nodeOfInterest == null) {
            return;
        }
        //base case: if node passed in is null then we are above the root so we return
        
        RBTNode<T> parent = nodeOfInterest.getUp();
        //improves readability, makes code easier to understand, ease of use
        
        if(this.root != null && this.root.equals(nodeOfInterest)) { 
            // base case: checks if node was added to empty tree, if so then return
            return;
        }
        
        
        if(nodeOfInterest.blackHeight == 0 && parent.blackHeight == 1) {
            return;
            //base case: no RBT violations caused
        }
        
        RBTNode<T> grandparent = nodeOfInterest.getUp().getUp();
        //grandparent cannot be null at this point
        //improves readability, makes code easier to understand, ease of use
 
        //////////////////////////// Case 1 ///////////////////////////////////////////////
        
        if(!parent.isRightChild()) {
            //parent is left child
            if(parent.blackHeight == 0 && grandparent.getDownRight() != null 
                    && grandparent.getDownRight().blackHeight == 0) {
                //parent is red with red uncle on right side double red violation
                //solution: color uncle and parent black, color grandparent red
                parent.blackHeight = 1;
                grandparent.blackHeight = 0;
                grandparent.getDownRight().blackHeight = 1; 
                
                enforceRBTreePropertiesAfterInsert(grandparent);
                //checks to see if changing the grandparent to red breaks another RBT rule higher
                //up in the tree
            }
                
        }else if(parent.isRightChild()) { 
            // parent is right child
            if(parent.blackHeight == 0 && grandparent.getDownLeft() != null
                    && grandparent.getDownLeft().blackHeight == 0) {
                //parent is red with red uncle on left side double red violation
                //solution: color uncle and parent black, color grandparent red
                parent.blackHeight = 1;
                grandparent.blackHeight = 0;
                grandparent.getDownLeft().blackHeight = 1;
                
                enforceRBTreePropertiesAfterInsert(grandparent);
                //checks to see if changing the grandparent to red breaks another RBT rule higher
                //up in the tree
            }
            
        }
        
        /////////////////////////// Case 2 /////////////////////////////////////////////////
        
        if(parent.blackHeight == 0 && nodeOfInterest.blackHeight == 0) {//double red violation
            if(!nodeOfInterest.isRightChild() && !parent.isRightChild() && (grandparent.getDownRight() == null
                    || grandparent.getDownRight().blackHeight == 1)) {
                //checks if there is a double red violation, checks to see if child and parent are both
                //left children, checks if uncle right child and is black or null
                //solution: color parent black, color grandparent red, rotate parent and grandparent
                parent.blackHeight = 1;
                grandparent.blackHeight = 0;
                rotate(parent, grandparent);
                
                
            }
            
            if(nodeOfInterest.isRightChild() && parent.isRightChild() && (grandparent.getDownLeft() == null
                    || grandparent.getDownLeft().blackHeight == 1)) {
                //checks if there is a double red violation, checks to see if child and parent are both
                //right children, checks if uncle left child and is black or null
                //solution: color parent black, color grandparent red, rotate parent and grandparent
                parent.blackHeight = 1;
                grandparent.blackHeight = 0;
                rotate(parent, grandparent);
            }
        }
        
        ////////////////////////////// Case 3 ///////////////////////////////////////////////
        
        if(nodeOfInterest.blackHeight == 0 && parent.blackHeight == 0) {
            if(nodeOfInterest.isRightChild() && !parent.isRightChild() && (grandparent.getDownRight() == null ||
                    grandparent.blackHeight == 1)) {
                //red child node is right node, red parent node is a left node
                //uncle node is either null or black
                //solution: rotate child and parent node to make tree look like case 2,
                //then recursively call enforce method on parent
                rotate(nodeOfInterest, parent);
                enforceRBTreePropertiesAfterInsert(parent);
            }
            
            if(!nodeOfInterest.isRightChild() && parent.isRightChild() && (grandparent.getDownLeft() == null ||
                    grandparent.getDownLeft().blackHeight == 1)) {
                //red child node is left node, red parent node is a right node
                //uncle node is either null or black
                //solution: rotate child and parent node to make tree look like case 2,
                //then recursively call enforce method on parent
                rotate(nodeOfInterest, parent);
                enforceRBTreePropertiesAfterInsert(parent);
            }
            
        }
        
    }
        
        
    /**
     * This method takes in some type T data as a parameter and adds it to a red black tree.
     * Once the data is added to the tree, an enforce method is called on the node that was
     * just added to fix any red black tree violations that may have appeared after adding 
     * the data to the tree
     * @param data is the data that is going to be inserted into the tree
     * @return true if the data was successfully inserted in the tree
     */
    public boolean insert(T data) throws NullPointerException {
        if (data == null)
            throw new NullPointerException("Cannot insert data value null into the tree.");
        
        RBTNode<T> newNode = new RBTNode<>(data);
        insertHelper(newNode);
        enforceRBTreePropertiesAfterInsert(newNode);
        ((RBTNode<T>)this.root).blackHeight = 1;
        return this.insertHelper(new Node<>(data));
    }
    
    @Test
    void testAddNodeWithRedUncle() {
        //testing case 1
        RBTNode<Integer> grandparent = new RBTNode<>(94);
        grandparent.blackHeight = 1;
        
        RBTNode<Integer> parent = new RBTNode<>(73);
        grandparent.down[0] = parent;
        parent.up = grandparent;
        
        RBTNode<Integer> uncle = new RBTNode<>(139);
        grandparent.down[1] = uncle;
        uncle.up = grandparent;
        
        RedBlackTree<Integer> rbt = new RedBlackTree<>();
        rbt.root = grandparent;
        rbt.insert(7);
        //Tree should look like this before enforce:
        //                        94(1)
        //                       /    \
        //                      73(0)  139(0)
        //                     /
        //                    7(0) <------- inserted node
        Assertions.assertEquals("[ 94, 73, 139, 7 ]", rbt.toLevelOrderString());
        //Tree should look like this after enforce:
        //                           94(1) <----since is root, color is changed to black(1)
        //                          /   \
        //                        73(1)  139(1)
        //                       /
        //                      7(0)
        Assertions.assertEquals(1, ((RBTNode<Integer>)rbt.root).blackHeight);
        Assertions.assertEquals(94, ((RBTNode<Integer>)rbt.root).data);
        
        Assertions.assertEquals(0, ((RBTNode<Integer>)rbt.root.down[0].down[0]).blackHeight);
        Assertions.assertEquals(7, ((RBTNode<Integer>)rbt.root.down[0].down[0]).data);
        
        Assertions.assertEquals(1, ((RBTNode<Integer>)rbt.root.down[0]).blackHeight);
        Assertions.assertEquals(73, ((RBTNode<Integer>)rbt.root.down[0]).data);
        
        Assertions.assertEquals(1, ((RBTNode<Integer>)rbt.root.down[1]).blackHeight);
        Assertions.assertEquals(139, ((RBTNode<Integer>)rbt.root.down[1]).data);
    }
    
    @Test
    void testBlackUncleConflictingNodeOnSameSide(){
        //testing case 2
        
        RBTNode<Integer> grandparent = new RBTNode<>(87);
        grandparent.blackHeight = 1;
        
        RBTNode<Integer> parent = new RBTNode(49);
        grandparent.down[0] = parent;
        parent.up = grandparent;
        
        RBTNode<Integer> uncle = new RBTNode(121);
        grandparent.down[1] = uncle;
        uncle.up = grandparent;
        uncle.blackHeight = 1;
        
        RedBlackTree<Integer> rbt = new RedBlackTree<>();
        rbt.root = grandparent;
        rbt.insert(13);
        
        //tree should look like this before enforce:
        //                   87(1)
        //                  /     \
        //                49(0)   121(1)
        //               /
        //              13(0) <--------- inserted node
        //tree should look like this after enforce:
        //                             49(1)
        //                            /  \
        //                          13(0)  87(0)
        //                                    \
        //                                    121(1)
        Assertions.assertEquals("[ 49, 13, 87, 121 ]", rbt.toLevelOrderString());
        Assertions.assertEquals(1, ((RBTNode<Integer>)rbt.root).blackHeight);
        Assertions.assertEquals(49, ((RBTNode<Integer>)rbt.root).data);
        
        Assertions.assertEquals(0, ((RBTNode<Integer>)rbt.root.down[0]).blackHeight);
        Assertions.assertEquals(13, ((RBTNode<Integer>)rbt.root.down[0]).data);
        
        Assertions.assertEquals(0, ((RBTNode<Integer>)rbt.root.down[1]).blackHeight);
        Assertions.assertEquals(87, ((RBTNode<Integer>)rbt.root.down[1]).data);
        
        Assertions.assertEquals(1, ((RBTNode<Integer>)rbt.root.down[1].down[1]).blackHeight);
        Assertions.assertEquals(121, ((RBTNode<Integer>)rbt.root.down[1].down[1]).data);
    }
    
    
    
    @Test
    void testBlackUncleConflictingNodeOnOppositeSide() {
        //testing case 3
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
