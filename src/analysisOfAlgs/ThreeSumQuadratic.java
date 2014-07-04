/**
 * 
 */
package analysisOfAlgs;

import java.util.Arrays;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

/**
 * @author Lea
 * @file ThreeSumQuadratic.java
 * @problem Given N distinct integers, how many triples sum to exactly zero? O(N^2)
 * @date 28.02.2014.
 * @version 1.1
 */
public class ThreeSumQuadratic {

	public static int countTriples(int[] numbers){

		int N = numbers.length;
		Arrays.sort(numbers);
		int count = 0;
	
		for(int i = 0; i < N; i++){
			int target = -numbers[i];
			int lo = i + 1; // next item
			int hi = N - 1; // last item
			// we search for pairs such that numbers[lo] + numbers[hi] = -numbers[i]
			while(lo < hi){
				int sum = numbers[lo] + numbers[hi];
				if(sum == target){
					count++;
					lo++;
					hi--;
				}
				else if(sum < target){
					lo++;
				}
				else{
					hi--;
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
