package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF site;
    private WeightedQuickUnionUF protype;
    private boolean[] isOpen;
    private int top;
    private int bottom;
    private int numOpen;
    private int N;

    //create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        top = N * N;
        bottom = top + 1;
        numOpen = 0;
        site = new WeightedQuickUnionUF(N * N + 2);
        protype = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N; i++) {
            protype.union(top, signId(0, i));
            protype.union(bottom, signId(N-1, i));
        }
        isOpen = new boolean [N * N + 2];
    }

    //give each box a unique number
    private int signId(int row, int col) {
        int id = row * N + col;
        return id;
    }

    //open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isValid(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        int id = signId(row, col);

        if (isOpen(row, col)) {
            return;
        }

        isOpen[id] = true;
        numOpen += 1;

        if (row == 0) {
            site.union(id, top);
        }

        if (row == N - 1) {
            site.union(id, bottom);
        }

        unionNeighbor(row, col);

    }

    private void unionNeighbor (int row, int col) {
        int [][] neibors = {
                {row - 1, col},
                {row, col + 1},
                {row + 1, col},
                {row, col - 1}
        };

        for (int[] nei: neibors) {
            if (isValid(nei[0], nei[1]) && isOpen(nei[0], nei[1])) {
                int connectId = signId(nei[0], nei[1]);
                site.union(connectId, signId(row, col));
            }
        }

    }

    //check whether the row, col is valid
    private boolean isValid (int row, int col) {
        if (row >= 0 && row  < N && col >= 0 && col < N) {
            return true;
        }
        return false;
    }

    // is the site (row, col) open?
    public boolean isOpen (int row, int col) {
        if (!isValid(row, col)) {
            throw new IndexOutOfBoundsException();
        }

        int id = signId(row, col);
        if (isOpen[id]) {
            return true;
        }
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isValid(row, col)) {
            throw new IndexOutOfBoundsException();
        }

        if (site.connected(top, signId(row, col))) {
            return true;
        }
        return false;
    };

    // number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    };

    // does the system percolate?
    public boolean percolates(){
        if (site.connected(top, bottom)) {
            return true;
        }
        return false;
    };

    // use for unit testing (not required)
    public static void main(String[] args) {};
}
