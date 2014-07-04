/**
 * 
 */
package priorityQueues;

import edu.princeton.cs.algs4.*;

/**
 * @author Lea
 * @file DynamicMedian.java
 * @date 27. 3. 2014.
 * @time 21:55:26
 * @version 1.1
 * @description Design a data type that supports insert in logarithmic time,
 *              find-the-median in constant time, and remove-the-median in
 *              logarithmic time.
 * @modified 27. 3. 2014.
 */
public class DynamicMedian<Key extends Number> {

//  private Key median;

    MaxPQ<Key> maxPQ = new MaxPQ<Key>();
    MinPQ<Key> minPQ = new MinPQ<Key>();

    public void insert(Key k) {
        if(maxPQ.size() == 0 || minPQ.size() == 0){ // handle situation when only one element, or zero elements added
            if(minPQ.size() > 0 && k.doubleValue() > minPQ.min().doubleValue()){
                Key min = minPQ.delMin();
                maxPQ.insert(min);
                minPQ.insert(k);
            }
            else if(minPQ.size() > 0){
                maxPQ.insert(k);
            }
            else if(maxPQ.size() > 0 && k.doubleValue() < maxPQ.max().doubleValue()){
                Key max = maxPQ.delMax();
                minPQ.insert(max);
                maxPQ.insert(k);
            }
            else if(maxPQ.size() > 0){
                minPQ.insert(k);
            }
            else{
                maxPQ.insert(k);
            }
        }
        else {
            if(k.doubleValue() < maxPQ.max().doubleValue()){
                maxPQ.insert(k);
            }
            else {
                minPQ.insert(k);
            }
        }
        
    }
    
    public Number findMed(){
        if (minPQ.size() > maxPQ.size())
            return minPQ.min();
        else if (minPQ.size() < maxPQ.size())
            return maxPQ.max();
        else return (minPQ.min().doubleValue() + maxPQ.max().doubleValue()) / 2;
        
    }
    
    public void deleteMed(){
       
    }


    /**
     * @description
     * @param args
     */
    public static void main(String[] args) {

    }
}