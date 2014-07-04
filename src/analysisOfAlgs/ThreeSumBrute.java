/**
 * 
 */
package analysisOfAlgs;

import edu.princeton.cs.introcs.*;

/**
 * @author Lea
 * @file ThreeSumBrute.java
 * @problem Given N distinct integers, how many triples sum to exactly zero? O(N^3)
 * @date 28.02.2014.
 * @version 1.1
 *
 */
public class ThreeSumBrute {

	public static int countTriples(int[] numbers){
		int N = numbers.length;
		int count = 0;
		for(int i = 0; i < N; i++){
			for(int j = i+1; j < N; j++){
				for(int k = j+1; k < N; k++){
					if(numbers[i] + numbers[j] + numbers[k] == 0){
						count++;
					}
				}
			}
		}
		return count;
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		In input = new In(args[0]);
		int[] numbers = input.readAllInts();
		for(int i = 0; i < numbers.length; i++){
			StdOut.print(numbers[i] + " ");
		}
		StdOut.println();
		StdOut.println("Count of triples that sum to zero: "+countTriples(numbers));
	}

}
