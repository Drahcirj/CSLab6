package arraystack;

import java.util.EmptyStackException;
import student.TestCase;

/**
 * Tests the ArrayBasedStack class
 * 
 * @author Jason Mills jrmillses
 * @version 2019.10.4
 */
public class ArrayBasedStackTest extends TestCase {
    private ArrayBasedStack<String> stack;
    private ArrayBasedStack<String> empty;


    /**
     * sets up for the tests
     */
    public void setUp() {
        stack = new ArrayBasedStack<String>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");
        stack.push("f");
        empty = new ArrayBasedStack<String>();
    }


    /**
     * tests that items are correctly added to the top of the stack
     */
    public void testPush() {
        stack.push("g");
        assertEquals(7, stack.size());
        while (stack.size() < 100) {
            stack.push("a");
        }
        stack.push("z");
        assertTrue(stack.size() > 100);
    }


    /**
     * tests that items are correctly removed from the top of the stack
     */
    public void testPop() {
        assertEquals(stack.pop(), "f");
        assertEquals(stack.pop(), "e");
        assertEquals(4, stack.size());
        Exception thrown = null;
        try {
            empty.pop();
        }
        catch (Exception e) {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof EmptyStackException);
    }


    /**
     * tests that the top item can be viewed without being removed
     */
    public void testPeek() {
        assertEquals("f", stack.peek());
        Exception thrown = null;
        try {
            empty.peek();
        }
        catch (Exception e) {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof EmptyStackException);
    }


    /**
     * tests that the correct size is returned
     */
    public void testSize() {
        assertEquals(6, stack.size());
    }


    /**
     * tests that the contains() method returns whether an item is in the stack
     */
    public void testContains() {
        assertTrue(stack.contains("a"));
        assertFalse(stack.contains("j"));
        String nothing = null;
        assertFalse(stack.contains(nothing));
    }


    /**
     * tests that the isEmpty() method returns whether the stack is empty
     */
    public void testIsEmpty() {
        assertTrue(empty.isEmpty());
        assertFalse(stack.isEmpty());
    }


    /**
     * tests that the stack is cleared correctly
     */
    public void testClear() {
        stack.clear();
        assertEquals(0, stack.size());
    }


    /**
     * tests that the array form of the stack is correctly returned
     */
    public void testToArray() {

        String[] temp = { "a", "b", "c", "d", "e", "f" };
        Object[] array = stack.toArray();
// assertEquals(stack.toArray(),temp);
// String[] array = stack.toArray();
        assertEquals(temp.length, array.length);
        for (int i = 0; i < temp.length; i++) {
            assertEquals(temp[i], array[i]);
        }
// assertEquals(temp, array);
    }


    /**
     * test that the string form of the stack is correctly returned
     */
    public void testToString() {
        String temp = "[a, b, c, d, e, f]";
        assertEquals(temp, stack.toString());
    }


    /**
     * tests that two stacks can be accurately compared
     */
    public void testEquals() {
        ArrayBasedStack<String> nullStack = null;
        ArrayBasedStack<String> other = new ArrayBasedStack<String>();
        other.push("a");
        other.push("b");
        other.push("c");
        other.push("d");
        other.push("e");
        other.push("f");
        ArrayBasedStack<String> diff = new ArrayBasedStack<String>();
        diff.push("g");
        diff.push("h");
        diff.push("i");
        diff.push("j");
        diff.push("k");
        diff.push("l");
        ArrayBasedStack<String> diffSize = new ArrayBasedStack<String>();
        diffSize.push("a");
        diffSize.push("b");
        diffSize.push("c");

        assertTrue(stack.equals(stack));
        assertFalse(stack.equals(nullStack));
        assertTrue(stack.equals(other));
        assertFalse(stack.equals(diffSize));
        assertFalse(stack.equals(diff));
        assertFalse(stack.equals(new Object()));
    }
}
