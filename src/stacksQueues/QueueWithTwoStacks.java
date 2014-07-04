/**
 * 
 */
package stacksQueues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Stack;

/**
 * @author Lea
 * @file QueueWithTwoStacks.java
 * @date 13. 3. 2014.
 * @time 14:38:02
 * @version 1.1
 * @description Implement a queue with two stacks so that each queue operations
 *              takes a constant amortized number of stack operations.
 * @modified 13. 3. 2014.
 */
public class QueueWithTwoStacks<Item> implements Iterable<Item>{

	private Stack<Item> tail;
	private Stack<Item> head;
	private int size = 0;
	
	
	public QueueWithTwoStacks(){
		tail = new Stack<Item>();
		head = new Stack<Item>();
	}
	
	public void enqueue(Item item){
		if(item == null){
			throw new NullPointerException();
		}
		tail.push(item);
		size++;
	}
	
	public Item dequeue(){
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		if(head.isEmpty()){
			while(!tail.isEmpty()){
				head.push(tail.pop());
			}
		}
		size--;
		return head.pop();
	}
	
	
	public boolean isEmpty(){
		return head.isEmpty() && tail.isEmpty();
	}
	
	public int size(){
		return size;
	}
	
	private class TwoStackIterator implements Iterator<Item>{

		@SuppressWarnings("unchecked")
        Item[] items = (Item[]) new Object[size];
		private int index = 0;
		
		private TwoStackIterator(){
			int i = 0;
			while(!head.isEmpty()){
				items[i] = (head.pop());
				i++;
			}
			int tailSize = tail.size();
			while(!tail.isEmpty()){
				items[i + tailSize - 1] = tail.pop();
				i--;
			}
			for(int k = items.length - 1; k >= 0; k--){
				head.push(items[k]);
			}
		}

		public boolean hasNext(){
			if(index < size){
				return true;
			}
			return false;
		}

		public Item next() {
			if(hasNext()){
				Item item = items[index];
				index++;
				return item;
			}
			throw new NoSuchElementException();
		}

		public void remove() {
			throw new UnsupportedOperationException();
			
		}
		
	}
	
	public Iterator<Item> iterator() {
		return new TwoStackIterator();
	}

	/**
	 * @description unit testing
	 * @param args
	 */
	public static void main(String[] args) {

		QueueWithTwoStacks<Integer> q = new QueueWithTwoStacks<Integer>();
		print(q);
		q.enqueue(1);
		print(q);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		q.enqueue(5);
		print(q);
		q.dequeue();
		q.dequeue();
		print(q);
		q.enqueue(1);
		print(q);
		q.enqueue(2);
		print(q);
		
	}
	
	public static void print(QueueWithTwoStacks<Integer> q){

		for(Integer i : q){
			System.out.print(i +" ");
		}
		System.out.println();
	}

}
