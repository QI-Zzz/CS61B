public class LinkedListDeque<T> {

    private class Node<T> {
        private T item;
        private Node<T> pre;
        private Node<T> next;

        public Node(T item, Node<T> pre, Node<T> next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }

        public  Node(Node<T> pre, Node<T> next) {
            this.pre = pre;
            this.next = next;
        }
    }

    private Node<T> sentinel;
    private int size;


    public LinkedListDeque() {
        sentinel = new Node<>(null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node<T> first = new Node<>(item, sentinel, sentinel.next);
        sentinel.next = first;
        sentinel.next.pre = first;
        size++;

    }


    public void addLast(T item) {
        Node<T> last = new Node<>(item, sentinel.pre, sentinel);
        sentinel.pre = last;
        sentinel.pre.next = last;
        size++;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.next.pre = sentinel;
        size--;
        return item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = sentinel.pre.item;
        sentinel.pre = sentinel.pre.pre;
        sentinel.pre.pre.next = sentinel;
        return item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node<T> temp = sentinel;

        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public void printDeque() {
        Node<T> temp = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.println(temp.item);
            temp = temp.next;
        }
    }

    private T getRecursiveHelper(int index, Node<T> p) {
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(index - 1, p.next);
    }

    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }
}
