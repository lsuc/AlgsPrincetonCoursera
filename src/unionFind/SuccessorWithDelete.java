/**
 * 
 */
package unionFind;

/**
 * @author Lea
 * @file SuccessorWithDelete.java
 * @problem Given a set of N integers S = {0,1,...,N-1} and a sequence of
 *          requests of the following form: - (Remove x from S), - (Find the
 *          successor of x: the smallest y in S such that y>=x) design a data
 *          type so that all operations (except construction) should take
 *          logarithmic time or better.
 * @date 28.02.2014.
 * @version 1.1
 * 
 */
public class SuccessorWithDelete {
	private int[] id;
	private int[] size;
	private int[] largestElement;
	private int N;

	// N array accesses
	public SuccessorWithDelete(int N) {
		this.N = N;
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
	private boolean isConnected(int p, int q) {
		if (root(p) == root(q)) {
			return true;
		} else {
			return false;
		}
	}

	// depth of p and q array accesses
	// improved - weighting
	private void union(int p, int q) {
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
	 * @description return the largest element in a component containing
	 *              requested element i
	 */
	public int find(int i) {
		return largestElement[root(i)];
	}

	/**
	 * @param i
	 * @description delete element i from set
	 */
	public void delete(int i) {
		if(largestElement[i] == i){ //if it exists in the set
			if(i < N-1){
				union(i, i+1);
			}
			else{
				largestElement[i] = -1;
			}
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int N = 8;
		SuccessorWithDelete suf = new SuccessorWithDelete(N);
		
		System.out.println("find(2) = " + suf.find(2));
		System.out.println("delete 2");
		suf.delete(2);
		System.out.println("find(2) = " + suf.find(2));
		System.out.println("delete 2");
		suf.delete(2);
		System.out.println("find(2) = " + suf.find(2));
		
		System.out.println("find(7) = " + suf.find(7));
		System.out.println("delete(7)");
		suf.delete(7);
		System.out.println("find(7) = " + suf.find(7));
		System.out.println("find(1) = " + suf.find(1));
		System.out.println("delete(1)");
		suf.delete(1);
		System.out.println("find(1) = " + suf.find(1));
	}

}
