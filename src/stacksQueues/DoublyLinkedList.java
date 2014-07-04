/**
 * 
 */
package stacksQueues;

import java.util.NoSuchElementException;

/**
 * @author Lea
 * @file DoublyLinkedList.java
 * @date 13. 3. 2014.
 * @time 20:07:48
 * @version 1.1
 * @description Clone a linked structure with two pointers per node. Suppose
 *              that you are given a reference to the first node of a linked
 *              structure where each node has two pointers: one pointer to the
 *              next node in the sequence (as in a standard singly-linked list)
 *              and one pointer to an arbitrary node. Design a linear-time
 *              algorithm to create a copy of the doubly-linked structure. You
 *              may modify the original linked structure, but you must end up
 *              with two copies of the original.
 * @modified 13. 3. 2014.
 */
public class DoublyLinkedList {

	private class Node {
		private Node next;
		private Node random;
		private String item;
	}

	public void connect(Node a, Node b, Node r) {
		a.next = b;
		a.random = r;
	}

	public void connectNext(Node a, Node b) {
		a.next = b;
	}

	public void connectRandom(Node a, Node b) {
		a.random = b;
	}

	public Node create(String item) {
		Node n = new Node();
		n.next = null;
		n.random = null;
		n.item = item;
		return n;
	}

	public void printNext(Node first) {
		Node temp = first;
		System.out.print("next" + " ");
		for (; temp != null; temp = temp.next) {
			System.out.print(temp.item + " ");
		}
		System.out.println();
	}
	
	public void printRandom(Node first) {
		Node temp = first;
		System.out.print("rand" + " ");
		for (; temp != null; temp = temp.next) {
			if(temp.random != null){
				System.out.print(temp.random.item + " ");
			}
			else{
				System.out.print(null + " ");
			}
			
		}
		System.out.println();
	}
	
	public Node clone(Node first){
		
		if(first != null){
			Node temp = first;			
			// first pass: insert current node copies between current and next node
			while(temp != null){
				Node n = new Node();
				n.item = temp.item + "'";
				n.next = temp.next;
				temp.next = n;
				temp = temp.next.next;
			}
			
			// second pass: mirror the random link
			temp = first;
			while(temp != null){
				Node copy = temp.next;
				if(temp.random != null){
					copy.random = temp.random.next;
				}
				temp = temp.next.next;
			}
			
			// third pass: detach the new list from the original
			temp = first;
			Node copyFirst = temp.next;
			Node copy = copyFirst;
			
			while(temp != null){
				Node temp2 = temp.next.next;
				temp.next = temp2;
				if(temp.next != null){
					copy.next = temp2.next;	
				}			
				temp = temp.next;
				copy = copy.next;
			}
			
			return copyFirst;
		}
		
		throw new NoSuchElementException("Cannot clone an empty list!");
		
	}

	/**
	 * @description
	 * @param args
	 */
	public static void main(String[] args) {

		DoublyLinkedList l = new DoublyLinkedList();
		DoublyLinkedList.Node A1 = l.create("1");
		DoublyLinkedList.Node A2 = l.create("2");
		DoublyLinkedList.Node A3 = l.create("3");
		DoublyLinkedList.Node A4 = l.create("4");
		DoublyLinkedList.Node A5 = l.create("5");
		
		l.connect(A1, A2, A3);
		l.connect(A2, A3, A1);
		l.connect(A3, A4, null);
		l.connect(A4, A5, A5);
		l.connect(A5, null, A3);
		
		l.printNext(A1);
		l.printRandom(A1);
		
		DoublyLinkedList.Node cloned = l.clone(A1);
		System.out.println("Original list after cloning: ");
		l.printNext(A1);
		l.printRandom(A1);
		
		System.out.println("Cloned list: ");
		l.printNext(cloned);
		l.printRandom(cloned);
		
	}

}
