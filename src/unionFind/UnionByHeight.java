/**
 * 
 */
package unionFind;

/**
 * @author Lea
 *
 */
public class UnionByHeight {


	private int[] id;
	private int[] height;

	// N array accesses
	public UnionByHeight(int N) {
		id = new int[N];
		height = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			height[i] = 1;
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
		if (height[pRoot] < height[qRoot]) {
			id[pRoot] = qRoot;
		} else if(height[pRoot] > height[qRoot]){
			id[qRoot] = pRoot;
		}
		else{
			id[pRoot] = qRoot;
			height[qRoot]++;
		}

	}

	public void print() {
		System.out.print("set: ");
		for (int i = 0; i < id.length; i++) {
			System.out.print(id[i] + " ");
		}
		System.out.println();
	}
	
	public void printHeight(){
		System.out.print("height: ");
		for (int i = 0; i < height.length; i++) {
			System.out.print(height[i] + " ");
		}
		System.out.println();
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 8;
		UnionByHeight huf = new UnionByHeight(N);
		union(2, 5, huf);
		union(4, 5, huf);
		union(1, 3, huf);
		union(6, 7, huf);
		union(3, 7, huf);
	}
	
	public static void union(int i, int j, UnionByHeight huf){
		System.out.println("union("+ i + ", "+ j + ")");
		huf.union(i, j);
		huf.print();
		huf.printHeight();
		System.out.println();
	}

}
