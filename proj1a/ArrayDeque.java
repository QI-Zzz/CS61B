public class ArrayDeque<T> {

    private T[] array;
    private int size;
    private int length;
    private  int first;
    private int last;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        length = 8;
        first = 4;
        last = 5;
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
        if (index == length - 1) {
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

    private void resize() {
        T[] newArray = (T[]) new Object[length * 2];
        int current = 0;
        if (last > first) {
            current = last;
        } else {
            current = first;
        }
        int count = 0;
        while (count < size) {
            newArray[count] = array[current];
            current = plusOne(current);
            count++;
        }
        first = length * 2 - 1;
        last = size;
        array = newArray;
        length *= 2;
    }


    private void shrink() {
        T[] newArray = (T[]) new Object[length / 2];
        int current = first + 1;
//        if (last > first) {
//            current = last;
//        } else {
//            current = first;
//        }
        int count = 0;
        while (count < size) {
            newArray[count] = array[current];
            current = plusOne(current);
            count++;
        }
        first = length / 2 - 1;
        last = size;
        array = newArray;
        length /= 2;
    }

    public void addFirst(T item) {
        if (size == length) {
            resize();
        }
        array[first] = item;
        first = minusOne(first);
        size++;
    }

    public void addLast(T item) {
        if (size == length) {
            resize();
        }
        array[last] = item;
        last = plusOne(last);
        size++;
    }

    public T removeFirst() {
//        if (length >= 16 && length / size >= 4) {
//            shrink();
//        }
        if (size == 0) {
            return null;
        }
        first = plusOne(first);
        T item = array[first];
        array[first] = null;
        size--;
        if (length >= 16 && size < 8) {
            shrink();
        }
        return item;
    }

    public T removeLast() {
//        if (length >= 16 && length / size >= 4) {
//            shrink();
//        }
        if (size == 0) {
            return null;
        }
        last = minusOne(last);
        T item = array[last];
        array[last] = null;
        size--;
        if (length >= 16 && size < 8) {
            shrink();
        }
        return item;
    }

//    public T get(int index) {
//        if (index < 0 || index >= size) {
//            return null;
//        }
//        return array[index];
//    }
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int ptr = first + 1;
        for (int i = 0; i < index; i++) {
            ptr = plusOne(ptr);
        }
        return array[ptr];
    }
    public void printDeque() {
//        int i = first;
//        while (i != last) {
//            System.out.println(array[i]);
//            i = plusOne(i);
//        }
        for (int i = 0; i < length; i++) {
            System.out.println(array[i]);
        }
    }
}
