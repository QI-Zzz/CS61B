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
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    private int plusOne(int index) {
        return (index + 1) % length;
    }

    private int minusOne(int index) {
        return (index - 1 + length) % length;
    }


    private void grow() {
        T[] newarray = (T[]) new Object[length * 2];
        int start = (first + length) % (length * 2);
        for (int i = 0; i < size; i++) {
            newarray[start + i] = array[(first + i) % length];
        }
        first = start;
        last = (first + size) % (length * 2);
        array = newarray;
        length *= 2;
    }

    public void shrink(){
        T[] newarray = (T[]) new Object[length / 2];
        int start = (length / 4) % (length / 2);
        for (int i = 0; i < size; i++) {
            newarray[start + i] = array[(first + i) % length];
        }
        first = start;
        last = (first + size) % (length / 2);
        array = newarray;
        length /= 2;
    }

    public void addFirst(T item) {
        if (size == length) {
            grow();
        }
        first = minusOne(first);
        array[first] = item;
        size++;
    }

    public void addLast(T item) {
        if (size == length) {
            grow();
        }
        array[last] = item;
        last = plusOne(last);
        size++;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = array[first];
        array[first] = null;
        first = plusOne(first);
        size--;
        if (size < length / 4 && length > 8) {
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
        if (size < length / 4 && length > 8) {
            shrink();
        }
        return item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return array[(first + index) % length];
    }

    public void printDeque() {
        int i = first;
        while (i != last) {
            System.out.print(array[i] + " ");
            i = plusOne(i);
        }
        System.out.println();
    }
}
