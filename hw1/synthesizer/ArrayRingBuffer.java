// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;
import synthesizer.AbstractBoundedQueue;

import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];

    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if(isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        rb[last] = x;
//        last = (last + 1) % capacity;
        if(last == capacity - 1) {
            last = -1;
        }
        last += 1;
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if(isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        T item = rb[first];
        rb[first] = null;
//        first = (first + 1) % capacity;
        if(first == capacity - 1) {
            first = -1;
        }
        first += 1;
        fillCount -= 1;
        return item;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        return rb[first];
    }

    private class BufferInterator implements Iterator<T> {
        private int ptr;
        private int count;

        public BufferInterator() {
            ptr = 0;
            count = 0;
        }

        public boolean hasNext() {
            return (count < fillCount);
        }

        public T next() {
            T returnItem = rb[ptr];
            ptr = (ptr + 1) % capacity;
            count++;
            return returnItem;
        }
    }
    // TODO: When you get to part 5, implement the needed code to support iteration.

    @Override
    public Iterator<T> iterator() {
        return new BufferInterator();
    }
}
