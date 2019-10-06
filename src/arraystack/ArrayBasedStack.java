package arraystack;

import java.util.EmptyStackException;

/**
 * Represents a stack implemented using an array
 * 
 * @author Jason Mills jrmillses
 * @version 2019.09.30
 * @param <T>
 *            the type of Object the stack holds
 */
public class ArrayBasedStack<T> implements StackADT<T> {
    private T[] stackArray;
    private int size;
    private int capacity;


    /**
     * creates a new ArrayBasedStack object
     * 
     * @param capacity
     *            the starting capacity of the array
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedStack(int capacity) {
        this.capacity = capacity;
        stackArray = (T[])new Object[this.capacity];
        size = 0;
    }


    /**
     * creates a new ArrayBasedStack with a starting capacity of 100
     */
    public ArrayBasedStack() {
        this(100);
    }


    /**
     * returns whether the stack is empty
     * 
     * @return whether the stack is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Looks at the top item in the stack
     * 
     * @return the top item in the stack
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stackArray[size - 1];
    }


    /**
     * removes and returns the top item in the stack
     * 
     * @return the top item in the stack
     */
    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T temp = stackArray[size - 1];
        stackArray[size - 1] = null;
        size--;
        return temp;
    }


    /**
     * places an item on the top of the stack
     * 
     * @param item
     *            the item to be placed on the stack
     */
    @Override
    public void push(T item) {
        if (size == capacity) {
            expandCapacity();
        }
        stackArray[size] = item;
        size++;
    }


    /**
     * checks if an item is in the stack
     * 
     * @param item
     *            the item to be searched for
     * @return whether the item is in the stack
     */
    @Override
    public boolean contains(T item) {
        if (item == null) {
            return false;
        }
        for (T thing : stackArray) {
            if (thing != null && thing.equals(item)) {
                return true;
            }
        }
        return false;
    }


    /**
     * returns the number of items in the stack
     * 
     * @return the number of items in the stack
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * clears the stack
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        size = 0;
        stackArray = (T[])new Object[capacity];

    }


    /**
     * returns the array that represents the stack
     * 
     * @return the stack in array form
     */
    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] copy = (T[])(new Object[this.size()]);
        for (int i = 0; i < this.size(); i++) {
            copy[i] = this.stackArray[i];
        }
        return copy;
    }


    /**
     * Expands the capacity of the stack by doubling its current capacity.
     */
    private void expandCapacity() {

        @SuppressWarnings("unchecked")
        T[] newArray = (T[])new Object[this.capacity * 2];

        for (int i = 0; i < this.capacity; i++) {
            newArray[i] = this.stackArray[i];
        }

        this.stackArray = newArray;
        this.capacity *= 2;
    }


    /**
     * Returns the string representation of the stack.
     * 
     * [] (if the stack is empty)
     * [bottom, item, ..., item, top] (if the stack contains items)
     * 
     * @return the string representation of the stack.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');

        boolean firstItem = true;
        for (int i = 0; i < this.size(); i++) {
            if (!firstItem) {
                builder.append(", ");
            }
            else {
                firstItem = false;
            }

            // String.valueOf will print null or the toString of the item
            builder.append(String.valueOf(this.stackArray[i]));
        }
        builder.append(']');
        return builder.toString();
    }


    /**
     * Two stacks are equal iff they both have the same size and contain the
     * same elements in the same order.
     *
     * @param other
     *            the other object to compare to this
     *
     * @return {@code true}, if the stacks are equal; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (this.getClass().equals(other.getClass())) {
            ArrayBasedStack<?> otherStack = (ArrayBasedStack<?>)other;
            if (this.size() != otherStack.size()) {
                return false;
            }
            Object[] otherArray = otherStack.toArray();
            for (int i = 0; i < this.size(); i++) {
                if (!(this.stackArray[i].equals(otherArray[i]))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
