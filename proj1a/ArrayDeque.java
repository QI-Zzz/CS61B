public class ArrayDeque<T>{

    public T[] array;
    public int size;
    public int length;
    public int first;
    public int last;

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


    public void resize() {
        T[] newarray = (T[]) new Object[length * 2];
        int start = (length * 2 - size) / 2;
        for (int i = 0; i < size; i ++) {
            newarray[start+i] = array[(first+i)%length];
        }
        first = start;
        last = start+size-1;
        array = newarray;
        length *= 2;
    }

    public void addFirst(T item) {
        if (size == length) resize();
        array[first] = item;
        first = minusOne(first);
        size++;
    }

    public void addLast(T item){
        if (size == length) resize();
        array[last] = item;
        last = plusOne(last);
        size++;
    }

    public T removeFirst() {
        T item = array[first];
        first = plusOne(first);
        size--;
        return item;
    }

    public T removeLast() {
        T item = array[last];
        last = minusOne(last);
        size--;
        return item;
    }

    public T get(int index) {
        return array[(first + index) % length];
    }

    public void printDeque() {
        for (int i = 0; i <= size; i++) {
            System.out.println(array[(first + i) % length]);
        }
    }
}
