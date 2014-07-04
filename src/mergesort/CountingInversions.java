/**
 * 
 */
package mergesort;

import edu.princeton.cs.introcs.StdRandom;

/**
 * @author Lea
 * @file CountingInversions.java
 * @date 20. 3. 2014.
 * @time 16:12:25
 * @version 1.1
 * @description 
 * @modified 20. 3. 2014.
 */
public class CountingInversions {

	private static int invCount;
	
	public static int getInvCount(Comparable[] a){
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
		return invCount;
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
		
		for(int i = lo; i <= hi; i++){
			aux[i] = a[i];
		}
		
		int i = lo;
		int j = mid + 1;
		
	
		for(int k = lo; k <= hi; k++){
			if(i > mid){ 
				a[k] = aux[j]; 
				j++;
			}
			else if(j > hi){
				a[k] = aux[i];
				i++;				
			}			
			else if(less(a[j], aux[i])){
				invCount += (mid - i + 1); // how many elements are left in a[lo..mid]
				a[k] = aux[j];
				j++;
			}
			else {				
				a[k] = aux[i];
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
		System.out.println("Array of Integers before counting inversions:");
		print(a);
		int inversions = CountingInversions.getInvCount(a);
		System.out.println("Number of inversions " + inversions);
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
