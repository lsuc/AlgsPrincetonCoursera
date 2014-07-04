/**
 * 
 */
package unionFind;

/**
 * @author Lea
 * @file UnionFindLargest.java
 * @problem Add a method find() to the union-find data type so that find(i)
 *          returns the largest element in the connected component containing i.
 *          The operations, union(), connected(), and find() should all take
 *          logarithmic time or better.
 * @date 27.02.2014.
 * @version 1.1
 * 
 */
public class UnionFindLargest {

	private int[] id;
	private int[] size;
	private int[] largestElement;

	// N array accesses
	public UnionFindLargest(int N) {
		id = new int[N];
		size = new int[N];
		largestElement = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			size[i] = 1;
			largestElement[i] = i;
		}

	}

	// chase parent pointers until reach the root
	// depth of i array accesses
	private int root(int i) {
		while (i != id[i]) {
			i = id[i];
		}
		return i;
	}

	// check if p and q have the same root
	// depth of p and q array accesses
	public boolean isConnected(int p, int q) {
		if (root(p) == root(q)) {
			return true;
		} else {
			return false;
		}
	}

	// depth of p and q array accesses
	// improved - weighting
	public void union(int p, int q) {
		int pRoot = root(p);
		int qRoot = root(q);

		if (pRoot == qRoot) {
			return;
		}
		if (size[pRoot] < size[qRoot]) {
			id[pRoot] = qRoot;
			size[qRoot] += size[pRoot];
		} else {
			id[qRoot] = pRoot;
			size[pRoot] += size[qRoot];
		}
		/**
		 * @description Added code for saving largest element in a component
		 */
		if (largestElement[qRoot] < largestElement[pRoot]) {
			largestElement[qRoot] = largestElement[pRoot];
		} else {
			largestElement[pRoot] = largestElement[qRoot];
		}

	}

	public void print() {
		System.out.println();
		for (int i = 0; i < id.length; i++) {
			System.out.print(id[i] + " ");
		}
	}

	/**
	 * @param i
	 * @description added method find that returns the largest element in a
	 *              component containing element i
	 */
	public int find(int i) {
		return largestElement[root(i)];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int N = 8;
		UnionFindLargest ufLargest = new UnionFindLargest(N);
		System.out.println("find(2) = " + ufLargest.find(2));
		System.out.println("union(2, 5)");
		ufLargest.union(2, 5);
		System.out.println("find(2) = " + ufLargest.find(2));
		System.out.println("find(5) = " + ufLargest.find(5));
		System.out.println("find(1) = " + ufLargest.find(1));
		System.out.println("union(1, 2)");
		ufLargest.union(1, 2);
		System.out.println("find(1) = " + ufLargest.find(1));
	}

}
