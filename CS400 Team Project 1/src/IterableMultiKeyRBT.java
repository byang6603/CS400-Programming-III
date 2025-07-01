import java.util.Iterator;
import java.util.Stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
/**
 * This class creates a red black tree that holds lists in it nodes.
 * If there is ever a duplicate value entered into a tree, instead of 
 * making a new node it is just added to a list that hold that 
 * specific value. This red black tree can also be iterated through to find 
 * values above a certain threshold
 * @author byang
 *
 * @param <T>
 */
public class IterableMultiKeyRBT<T extends Comparable<T>> extends RedBlackTree<KeyListInterface<T>>implements IterableMultiKeySortedCollectionInterface<T> {
    private T iterationStartPoint;
    private int numKeys;


    /**
     * Inserts value into tree that can store multiple objects per key by keeping
     * lists of objects in each node of the tree.
     * 
     * @param key object to insert
     * @return true if obj was inserted
     */
    @Override
    public boolean insertSingleKey(T key) {
        KeyList<T> kl = new KeyList<>(key);
        if (findNode(kl) != null) {
            findNode(kl).data.addKey(key);
            //adding key to a list that already has that key
            numKeys++;
            return false;
        } else if (findNode(kl) == null) {
            insert(kl);
            //inserting a new node with a list that has the key
            numKeys++;
            return true;
        }
        return false;
    }

    /**
     * @return the number of values in the tree.
     */
    @Override
    public int numKeys() {
        return numKeys;
    }

    /**
     * @Returns an iterator that does an in-order iteration over the tree.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            
            Stack<Node<KeyListInterface<T>>> startStack = getStartStack();
            Iterator<T> stackIterator = (startStack.isEmpty())?null : startStack.peek().data.iterator();
            //stackIterator is null if startStack is empty and is an iterator if startStack is not empty
            
            @Override
            public boolean hasNext() {
                if(!startStack.isEmpty()){
                    Node<KeyListInterface<T>> currentNode = startStack.pop();
                    if(currentNode.down[1] != null) {
                        //get right child of current if it exists
                        Node<KeyListInterface<T>> nextNode = currentNode.down[1];
                        startStack.push(nextNode);
                        //get left node and push it to the stack
                        while(nextNode.down[0] != null) {
                            nextNode = nextNode.down[0];
                            startStack.push(nextNode);
                            //keep finding left nodes until there are no more left nodes
                            //after finding a left node push it to the stack
                        }
                    }
                    return true;
                }
                return false;
            }

            @Override
            public T next() {
                if(!hasNext()) {
                    throw new IllegalArgumentException("There are no more elements in stack");
                }else {
                    return stackIterator.next();
                }
            }
        };
    }
/**
 * This method is a helper method that gets the stack that will be used 
 * to iterate through the red black tree.
 * @return a stack of the elements that contains the nodes that are greater
 * than the iterationStartPoint as you travel from the root to the start point
 */
    protected Stack getStartStack() {
        Stack<Node<KeyListInterface<T>>> startStack = new Stack<>();
        Node<KeyListInterface<T>> currentNode = root;
        if (iterationStartPoint == null) {
            // nothing is put into iteration start point
            while (currentNode != null) {
                startStack.push(currentNode);
                currentNode = currentNode.down[0];
                //keep finding left children and pushing them on stack
                //until there are no more left children
            }
        } else if (iterationStartPoint != null) {
            // there is something in iteration start point
            while (currentNode != null) {
                int x = iterationStartPoint.compareTo(currentNode.data.iterator().next());
                if (x <= 0) {
                    // iterationStartPoint is smaller than or equal to the value in the node
                    startStack.push(currentNode);
                    //push because value of the node is greater than the iteration start point
                    currentNode = currentNode.down[0];
                    // go to left child to try to find the iteration start point
                } else {
                    // iterationStartPoint is greater than the value in the node
                    currentNode = currentNode.down[1];
                    // go to right child to try to find the iteration start point
                    //don't push because the iteration start point is larger, we are
                    //not interested in the node
                }
            }
        }
        return startStack;
    }

    /**
     * Sets the starting point for iterations. Future iterations will start at the
     * starting point or the key closest to it in the tree. This setting is
     * remembered until it is reset. Passing in null disables the starting point.
     * 
     * @param startPoint the start point to set for iterations
     */
    @Override
    public void setIterationStartPoint(Comparable startPoint) {
        this.iterationStartPoint = (T) startPoint;

    }
/**
 * This method clears the red black tree and also sets the number of keys to zero
 */
    @Override
    public void clear() {
        super.clear();
        this.numKeys = 0;
    }

    @Test
    void testNumKeys() {
        IterableMultiKeyRBT<Integer> imkRBT = new IterableMultiKeyRBT<Integer>();
        imkRBT.insertSingleKey(1);
        imkRBT.insertSingleKey(2);
        imkRBT.insertSingleKey(3);
        imkRBT.insertSingleKey(4);
        Assertions.assertEquals(4, imkRBT.numKeys());
        //after inserting 4 values the number of keys should be 4
    }

    @Test
    void testRBTSizeAfterInsertSameEqualKey() {
        IterableMultiKeyRBT<Integer> imkRBT = new IterableMultiKeyRBT<Integer>();
        imkRBT.insertSingleKey(5);
        imkRBT.insertSingleKey(17);
        imkRBT.insertSingleKey(23);
        imkRBT.insertSingleKey(23);
        Assertions.assertEquals(3, imkRBT.size());
        //number of keys is 4 but since you add 2 of the same value the size is still 3
    }

    @Test
    void testGetStartStack() {
        IterableMultiKeyRBT<Integer> imkRBT = new IterableMultiKeyRBT<Integer>();
        imkRBT.insertSingleKey(7);
        imkRBT.insertSingleKey(12);
        imkRBT.insertSingleKey(43);
        imkRBT.insertSingleKey(51);
        imkRBT.insertSingleKey(67);
        imkRBT.insertSingleKey(78);
        imkRBT.insertSingleKey(80);
        imkRBT.insertSingleKey(92);
        imkRBT.setIterationStartPoint(67);
        Stack actual = getStartStack();
        Stack<Integer> expected = new Stack<>();
       
        //should return an empty stack since getStartStack will iterate and pop
        //all elements off the stack
        Assertions.assertEquals(expected, actual);
    }

}
