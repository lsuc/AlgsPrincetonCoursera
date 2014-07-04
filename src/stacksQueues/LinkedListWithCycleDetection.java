/**
 * 
 */
package stacksQueues;

/**
 * @author Lea
 * @file LinkedListWithCycleDetection.java
 * @date 13. 3. 2014.
 * @time 18:48:55
 * @version 1.1
 * @description
 * @modified 13. 3. 2014.
 */
public class LinkedListWithCycleDetection<Item> {

	private Node first;
	private Node tortoise;
	private Node hare;
	private Node cycleStart;

	public LinkedListWithCycleDetection() {
	}

	public class Node {
		private Item item;
		private Node next;
	}

	public void connect(Node a, Node b) {
		a.next = b;
	}

	public Node create(Item item) {
		Node n = new Node();
		n.next = null;
		n.item = item;

		if (isEmpty()) {
			first = n;
		}
		return n;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void print() {
		if (!hasCycle()) {
			Node temp = first;
			System.out.print("first" + " ");
			for (; temp != null; temp = temp.next) {
				System.out.print(temp.item + " ");
			}
			System.out.println();
		} else {
			System.out.println("There is a cycle!");
			printCycleStartNode();

		}

	}

	public boolean hasCycle() {
		hare = tortoise = first;
		while (hare != null) {
			hare = hare.next;
			if (hare == null) {
				return false;
			}
			hare = hare.next;
			tortoise = tortoise.next;
			if (hare == tortoise) {
				return true;
			}
		}
		return false;
	}

	public void printCycleStartNode() {

		int cycleLength = 1;
		tortoise = tortoise.next;
		while (tortoise != hare) {
			cycleLength++;
			tortoise = tortoise.next;
		}
		System.out.println("Cycle length: " + cycleLength);
		tortoise = hare = first;
		int c = 0;
		while(c < cycleLength){
			tortoise = tortoise.next;
			c++;
		}
		while(tortoise != hare){
			tortoise = tortoise.next;
			hare = hare.next;
		}
		cycleStart = hare;
		System.out.println("First node in cycle: "+ cycleStart.item);
	}

	/**
	 * @description
	 * @param args
	 */
	public static void main(String[] args) {

		LinkedListWithCycleDetection<Integer> l = new LinkedListWithCycleDetection<Integer>();
		LinkedListWithCycleDetection<Integer>.Node n1 = l.create(1);
		LinkedListWithCycleDetection<Integer>.Node n2 = l.create(2);
		LinkedListWithCycleDetection<Integer>.Node n3 = l.create(3);
		LinkedListWithCycleDetection<Integer>.Node n4 = l.create(4);

		l.connect(n1, n2);
		l.connect(n2, n3);
		l.connect(n3, n4);
		l.connect(n4, n2);
		l.print();

	}

}
