/**
 * 
 */
package mergesort;

import java.util.ArrayList;

import edu.princeton.cs.introcs.StdRandom;

/**
 * @author Lea
 * @file ShufflingLinkedList.java
 * @date 20. 3. 2014.
 * @time 18:44:08
 * @version 1.1
 * @description randomized mergesort implementation for shuffling linked list -
 *              Given a singly-linked list containing N items, rearrange the
 *              items uniformly at random. Your algorithm should consume a
 *              logarithmic (or constant) amount of extra memory and run in time
 *              proportional to NlogN in the worst case.
 * @modified 20. 3. 2014.
 */
public class ShufflingLinkedList<Item> {

	public class Node {
		Item item;
		Node next;
	}

	public Node randomize(Node first) {
		
		int size = listLength(first);
		if(size >= 2){
			Node list1 = first;
			Node temp = findLastNodeFromFirstList(first, size);
			
			Node list2 = temp.next;
			temp.next = null;
			

			Node n1 = randomize(list1);	
			Node n2 = randomize(list2);
			
			Node n = randomizedMerge(n1, n2);
			return n;
		}
		return first;
	}
	
	private Node randomizedMerge(Node list1, Node list2){
		if(list1 == null){
			return list2;
		}
		if(list2 == null){
			return list1;
		}
		
		Node first = null;
		double p = StdRandom.uniform();
		if(p < 0.5){
			first = list1;
			list1 = list1.next;
		}
		else{
			first = list2;
			list2 = list2.next;
		}
		Node n = first;
		int size = listLength(list1) + listLength(list2);
		for(int k = 0; k < size; k++){
			if(list1 == null){
				n.next = list2;
				list2 = list2.next;
			}
			else if(list2 == null){
				n.next = list1;
				list1 = list1.next;
			}
			else if(StdRandom.uniform() < 0.5){
				n.next = list1;
				list1 = list1.next;
			}
			else{
				n.next = list2;
				list2 = list2.next;
			}
			n = n.next;
		}
		n.next = null;
		
		return first;
	}
	
	private Node findLastNodeFromFirstList(Node first, int size){
		size /= 2;
		Node n = first;		
		for(int i = 0; i < size - 1; i++){
			n = n.next;
		}
		return n;
	}
	
	private int listLength(Node n){
		int count = 0;
		while(n != null){
			count++;
			n = n.next;
		}
		
		return count;
	}
	
	public void connect(Node a, Node b) {
		a.next = b;
	}

	
	public Node create(Item item) {
		Node n = new Node();
		n.next = null;
		n.item = item;
		return n;
	}

	public void print(Node first) {
		Node temp = first;
		System.out.print("next" + " ");
		for (; temp != null; temp = temp.next) {
			System.out.print(temp.item + " ");
		}
		System.out.println();
	}

	/**
	 * @description
	 * @param args
	 */
	public static void main(String[] args) {

		ShufflingLinkedList<Integer> s = new ShufflingLinkedList<Integer>();
		
		int N = 10;
		@SuppressWarnings("unchecked")
		ArrayList<ShufflingLinkedList<Integer>.Node> nodes = new ArrayList<ShufflingLinkedList<Integer>.Node>();
		
		for(int i = 0; i < N; i ++){
			ShufflingLinkedList<Integer>.Node n = s.create(i);
			nodes.add(n);
		}
		for(int i = 0; i < N - 1; i ++){
			s.connect(nodes.get(i), nodes.get(i+1));
		}
		s.print(nodes.get(0));
		
		ShufflingLinkedList<Integer>.Node first = s.randomize(nodes.get(0));
		s.print(first);
	}

}
