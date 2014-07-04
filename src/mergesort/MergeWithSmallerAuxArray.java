/**
 * 
 */
package mergesort;

import edu.princeton.cs.introcs.StdRandom;

/**
 * @author Lea
 * @file MergeWithSmallerAuxArray.java
 * @date 20. 3. 2014.
 * @time 14:21:29
 * @version 1.1
 * @description Suppose that the subarray a[0] to a[N-1] is sorted and the
 *              subarray a[N] to a[2*N-1] is sorted. How can you merge the two
 *              subarrays so that a[0] to a[2*N-1] is sorted using an auxiliary
 *              array of size N (instead of 2N)
 * @modified 20. 3. 2014.
 */
public class MergeWithSmallerAuxArray {

	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length / 2];
		sort(a, aux, 0, a.length - 1);
	}

	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			sort(a, aux, lo, mid);
			sort(a, aux, mid + 1, hi);
			if (!less(a[mid + 1], a[mid])) {
				return;
			}
			merge(a, aux, lo, mid, hi);
		}
	}

	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
		
		
		for(int i = lo; i <= mid; i++){
			aux[i - lo] = a[i];
		}
		
		int i = lo;
		int j = mid + 1;
		
	
		for(int k = lo; k <= hi; k++){
			if(i > mid){ 
				return; // that means the array is sorted
			}
			else if(j > hi){
				a[k] = aux[i - lo];
				i++;
			}
			
			else if(less(a[j], aux[i-lo])){
				a[k] = a[j];
				j++;
			}
			else {
				a[k] = aux[i - lo];
				i++;
			}
		}
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	/**
	 * @description
	 * @param args
	 */
	public static void main(String[] args) {
	int N = 6;
		
		Integer[] a = new Integer[N];
		for (int i = 0; i < N; i++) {
			a[i] = i;
		}

		StdRandom.shuffle(a);
		System.out.println("Array of Integers before sorting:");
		print(a);
		MergeWithSmallerAuxArray.sort(a);
		System.out.println("Array of Integers after sorting:");
		print(a);
	}
	
	public static void print(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			System.out.print(a[i].toString() + " ");
		}
		System.out.println();
	}

}
