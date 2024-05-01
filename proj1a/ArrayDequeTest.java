import java.awt.desktop.SystemEventListener;

/** Performs some basic linked list tests. */
public class ArrayDequeTest {

	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Prints a nice message based on whether a test passed.
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct,
	  * finally printing the results.
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");
		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

		ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(1);

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast(2);

		lld1.addLast(3);
//		passed = checkSize(3, lld1.size()) && passed;

		lld1.addFirst(4);
//		passed = checkSize(4, lld1.size()) && passed;

		lld1.addFirst(5);
		System.out.println(lld1.first);
		System.out.println(lld1.last);
		lld1.addLast(6);
		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);

	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");

		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

		ArrayDeque<Integer> lld2 = new ArrayDeque<Integer>();
		// should be empty
//		boolean passed = checkEmpty(true, lld2.isEmpty());

		lld2.addFirst(1);
		lld2.addLast(2);
		lld2.addLast(3);


		// should not be empty
//		passed = checkEmpty(false, lld2.isEmpty()) && passed;

//		System.out.println(lld2.removeLast());

		lld2.removeLast();
        System.out.println("first+last");
        System.out.println(lld2.first);
        System.out.println(lld2.last);
//		lld2.removeFirst();
//		lld2.removeLast();
//		lld2.removeLast();
//		lld2.removeFirst();


		lld2.printDeque();
//		System.out.println("first+last");
//		System.out.println(lld2.first);
//		System.out.println(lld2.last);
		// should be empty
//		passed = checkEmpty(true, lld2.isEmpty()) && passed;

//		lld2.printDeque();
//		lld2.addLast(0);
//		lld2.removeFirst();
//		lld2.addLast(2);
//		lld2.removeLast();
//		lld2.addFirst(4);
//		lld2.addLast(5);
//		lld2.addLast(6);
//		lld2.printDeque();
////		lld2.get(1);
//		System.out.println(lld2.get(1));
//		printTestStatus(passed);

	}

	public static void main(String[] args) {
		System.out.println("Running tests.\n");
//		addIsEmptySizeTest();
		addRemoveTest();
	}
}