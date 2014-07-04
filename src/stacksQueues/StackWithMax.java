/**
 * 
 */
package stacksQueues;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Stack;

/**
 * @author Lea
 * @file StackWithMax.java
 * @date 13. 3. 2014.
 * @time 16:06:42
 * @version 1.1
 * @description Create a data structure that efficiently supports the stack
 *              operations (push and pop) and also a return-the-maximum
 *              operation. Assume the elements are reals numbers so that you can
 *              compare them.
 * @modified 13. 3. 2014.
 */
public class StackWithMax {

	private Stack<Integer> s;
	private Stack<Integer> max;
	
	public StackWithMax() {
		s = new Stack<Integer>();
		max = new Stack<Integer>();
	}
	
	public void push(Integer item){
		s.push(item);
		
		if(max.isEmpty()){
			max.push(item);
		}
		else{
			Integer currentMax = max.pop();
			if(item >= currentMax){
				max.push(currentMax);
				max.push(item);
			}
			else{
				max.push(currentMax);
			}
		}
	}
	
	public Integer pop(){
		if(s.isEmpty()){
			throw new NoSuchElementException();
		}
		Integer item = s.pop();
		Integer currentMax = max.pop();
		if(item < currentMax){
			max.push(currentMax);
		}
		return item;
	}
	
	public boolean isEmpty(){
		return s.isEmpty();
	}
	
	public Integer max(){
		Integer currentMax = max.pop();
		max.push(currentMax);
		return currentMax;
	}
	
	public void print(){
		System.out.println("Stack");
		Stack<Integer> pom = new Stack<Integer>();
		while(!s.isEmpty()){
			pom.push(s.pop());
		}
		while(!pom.isEmpty()){
			Integer item = pom.pop();
			s.push(item);
			System.out.print(item + " ");
		}
		System.out.println();
		
		System.out.println("Max");
		pom = new Stack<Integer>();
		while(!max.isEmpty()){
			pom.push(max.pop());
		}
		while(!pom.isEmpty()){
			Integer item = pom.pop();
			max.push(item);
			System.out.print(item + " ");
		}
		System.out.println();
		if(!max.isEmpty()){
			Integer m = max.pop();
			max.push(m);
			System.out.println("Max element is " +  m);
		}
		
		
	}

	/**
	 * @description
	 * @param args
	 */
	public static void main(String[] args) {
		StackWithMax s = new StackWithMax();
		s.print();
		s.push(1);
		s.push(2);
		s.push(3);
		s.print();
		s.push(1);
		s.push(2);
		s.push(3);
		s.print();
		s.pop();
		s.print();
		s.pop();
		s.print();
		s.pop();
		s.print();
		s.pop();
		s.print();
		s.push(5);
		s.print();
		s.push(5);
		s.print();
	}


}
