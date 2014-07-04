/**
 * 
 */
package elementarySorts;

import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.introcs.StdRandom;

/**
 * @author Lea
 * @file Permutation.java
 * @date 14. 3. 2014.
 * @time 11:18:57
 * @version 1.1
 * @description Given two integer arrays of size N, design a subquadratic
 *              algorithm to determine whether one is a permutation of the
 *              other. That is, do they contain exactly the same entries but,
 *              possibly, in a different order.
 * @modified 14. 3. 2014.
 */
public class Permutation {

	public static boolean isPermutation(Integer[] a, Integer[] b){
		if(a.length != b.length){
			return false;
		}
		Shell.sort(a);
		Shell.sort(b);
		for(Integer i = 0; i < a.length; i++){
			if(a[i] != b[i]){
				return false;
			}
		}
		return true;
		
	}
	/**
	 * @description
	 * @param args
	 */
	public static void main(String[] args) {
		
		Integer N = 10;
		Integer[] a = new Integer[N];
		Integer[] b = new Integer[N];
		for(Integer i = 0; i < N; i++){
			a[i] = i + 1;
			b[i] = i;
		}
		StdRandom.shuffle(a);
		StdRandom.shuffle(b);
		
		System.out.println("Array a: ");
		for(Integer i = 0; i < N; i++){
			System.out.print(a[i] + " ");
		}
		System.out.println();
		System.out.println("Array b: ");
		for(Integer i = 0; i < N; i++){
			System.out.print(b[i] + " ");
		}
		System.out.println();
		
		if(Permutation.isPermutation(a, b)){
			System.out.println("Arrays a and b are permutations!");
		}
		else{
			System.out.println("Arrays a and b are NOT permutations!");
		}
	}

}
