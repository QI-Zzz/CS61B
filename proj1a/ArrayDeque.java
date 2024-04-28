public class ArrayDeque<T> {

    private T[] array;
    private int size;
    private int length;
    private int first;
    private int last;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        length = 8;
        first = 0;
        last = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private int plusOne(int index, int module) {
        index %= module;
        if (index == module - 1) {
            return 0;
        }
        return index + 1;
    }

    private int minusOne(int index) {
        if (index == 0) {
            return length - 1;
        }
        return index - 1;
    }


    private void grow() {
        T[] newArray = (T[]) new Object[length * 2];
        int ptr1 = first;
        int ptr2 = length;
        while (ptr1 != last) {
            newArray[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length * 2);
        }
        first = length;
        last = ptr2;
        array = newArray;
        length *= 2;
    }

    private void shrink(){
        T[] newArray = (T[]) new Object[length / 2];
        int ptr1 = first;
        int ptr2 = length / 4;
        while (ptr1 != last) {
            newArray[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length / 2);
        }
        first = length / 4;
        last = ptr2;
        array = newArray;
        length /= 2;
    }

    public void addFirst(T item) {
        if (size == length) {
            grow();
        }
        first = minusOne(first);
        array[first] = item;
        if (size == 0) {
            last = first;
        }
        if (size == 1) {
            last = plusOne(first, length);
        }
        size++;
    }

    public void addLast(T item) {
        if (size == length) {
            grow();
        }
        array[last] = item;
        last = plusOne(last, length);
        size++;
        if (size == 1) {
            last = minusOne(last);
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = array[first];
        array[first] = null;
        first = plusOne(first, length);
        size--;
        if (length >= 16 && length / size >= 4) {
            shrink();
        }
        return item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = array[last];
        array[last] = null;
        last = minusOne(last);
        size--;
        if (size == 0) {
            last = first = 0; // Ensure 'last' also points to the only element
        }
        if (length >= 16 && length / size >= 4) {
            shrink();
        }
        return item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int ptr = first;
        for (int i = 0; i < index; i++) {
            ptr = plusOne(ptr, length);
        }
        return array[ptr];
    }

    public void printDeque() {
        int i = first;
        while (i != last) {
            System.out.print(array[i] + " ");
            i = plusOne(i, length);
        }
        System.out.println();
    }
}
