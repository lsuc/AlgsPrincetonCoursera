/**
 * 
 */
package analysisOfAlgs;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

/**
 * @author Lea
 * @file BitonicArraySearch.java
 * @problem An array is bitonic if it is comprised of an increasing sequence of
 *          integers followed immediately by a decreasing sequence of integers.
 *          Write a program that, given a bitonic array of N distinct integer
 *          values, determines whether a given integer is in the array. 3logN
 * @date 28.02.2014.
 * @version 1.1
 * 
 */
public class BitonicArraySearch {
	
	public static boolean exists(int[] a, int element){
	    int N = a.length;
		int indexMax = max(a, 0, N - 1);
		boolean isLeft = binarySearch(a, element, 0, indexMax, true);
		if(isLeft){
			return true;
		}
		boolean isRight = binarySearch(a, element, indexMax + 1, N - 1, false);
		if(isRight){
			return true;
		}
		return false;
	}
	
	public static int max(int[] a, int lo, int hi) {
        if (hi == lo) return hi;
        int mid = lo + (hi - lo) / 2;
        if (a[mid] < a[mid + 1]) return max(a, mid+1, hi);
        if (a[mid] > a[mid + 1]) return max(a, lo, mid);
        else return mid;
    } 

	
	public static boolean binarySearch(int[] a, int element, int lo, int hi, boolean asc) {
	   
	    if (lo > hi) return false;
	    if (lo == hi) {
	        if (a[hi] == element) {
	            return true;
	        }
	           
	        else return false;
	    }
	    int mid = lo + (hi - lo) / 2; 
	    boolean bs = false;
	    if (a[mid] < element) {
	        if (asc) bs = binarySearch(a, element, mid + 1, hi, asc);
	        else  bs = binarySearch(a, element, lo, mid - 1, asc);
	    }
	    else if (a[mid] > element) {
	        if (asc) bs = binarySearch(a, element, lo, mid - 1, asc);
	        else bs = binarySearch(a, element, mid + 1, hi, asc);
	    }
	    else return true;
	    return bs;
	}

	
	
	/**
	 * @description O(2logN), recursively find element using 2 binary search
	 * @param a
	 * @param element
	 * @return
	 */
	public static boolean findAdvanced (int[] a, int element) {
	    
	    int lo = 0;
	    int hi = a.length - 1;
	    boolean exists = bitonicSearch(a, element, lo, hi);
	    return exists;	    
	}

	/**
     * @description 
     * @param a
     * @param element
     * @param lo
     * @param hi
     * @return
     */
    private static boolean bitonicSearch(int[] a, int element, int lo, int hi) {
        if (lo > hi) return false;
        if (lo == hi) {
            if (a[hi] == element) return true;
            else return false;
        }
        
        int mid = lo + (hi - lo) / 2;
        if (a[mid] == element) return true;
        if (mid - 1 < 0) {
            if (a[mid+1] == element) return true;
            else return false;
        }
        if (mid + 1 > a.length - 1) {
            if (a[mid-1] == element) return true;
            else return false;
        }
        
        boolean isLeft = false, isRight = false;
        if (a[mid] > element && a[mid] > a[mid-1] && a[mid] > a[mid + 1]) { // max element, binary search left and right
            isLeft = binarySearch(a, element, lo, mid - 1, true);
            isRight = binarySearch(a, element, mid + 1, hi, false);
        }
        else if (a[mid] > element) { 
            if (a[mid] > a[mid-1]) { // ascending
                isLeft = binarySearch(a, element, lo, mid - 1, true);
                isRight = bitonicSearch(a, element, mid + 1, hi);
            }
            else { // descending
                isRight = binarySearch(a, element, mid + 1, hi, false);
                isLeft = bitonicSearch(a, element, lo, mid - 1);
            }
            
        }
        else if (a[mid] < element) {
            if (a[mid] > a[mid-1]) { // ascending, in the interval [mid + 1, max, hi], split between left and right
                isRight = bitonicSearch(a, element, mid + 1, hi);
                
            }
            else { // descending, in interval [lo, max, mid - 1]
                isRight = bitonicSearch(a, element, lo, mid - 1);
            }
        }
        
        return (isLeft || isRight);
     
    }


    /**
	 * @param args
	 */
	public static void main(String[] args) {
		In input = new In(args[0]);
		int[] array = input.readAllInts();
		for(int i = 0; i < array.length; i++){
			StdOut.print(array[i] + " ");
		}
		StdOut.println();
		int element = -1;
		StdOut.println("Element " + element + " exists: " + findAdvanced(array, element));

	}

}
