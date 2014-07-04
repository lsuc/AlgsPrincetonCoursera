/**
 * 
 */
package unionFind;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.introcs.*;

/**
 * @author Lea
 * @file SocialNetwork.java
 * @problem Given a social network containing N members and a log file
 *          containing M timestamps at which times pairs of members formed
 *          friendships, design an algorithm to determine the earliest time at
 *          which all members are connected. (time MlogN, space N)
 *          ALTERNATIVE SOLUTION: NETWORK WILL BE CONNECTED AFTER EXACTLY N-1 SUCCESSFUL UNION OPERATIONS (SUCCESSFUL MEANING ELEMENTS WEREN'T CONNECTED)
 * @date 27.02.2014.
 * @version 1.1
 * 
 */
public class SocialNetwork {

	private WeightedQuickUnionUF uf; // union find structure used for solving the problem
	private String timeStamp = null; // the timestamp when the network becomes
										// fully connected
	private final int N;

	/**
	 * @param N The number of members on the social network
	 */
	public SocialNetwork(int N) {
		uf = new WeightedQuickUnionUF(N);
		this.N = N;
	}

	/**
	 * @description Connect 2 members on the social network
	 * @param logTime When were the members connected
	 * @param memberA
	 * @param memberB
	 */
	// Connects 2 members and sets time to the logTime if this is the first time
	// the network is fully connected
	public void connect(String logTime, int memberA, int memberB) {
		if (memberA < 0 || memberA >= N || memberB < 0 || memberB >= N) {
			throw new IndexOutOfBoundsException();
		}
		uf.union(memberA, memberB);
		if (timeStamp == null && networked()) {
			timeStamp = logTime;
		}		
	}

	 /**
     * @return boolean Whether all members of the social network are connected
     */
	public boolean networked() {
		return uf.count() == 1;
	}

	public String timeStamp() {
		return timeStamp;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		In input = new In(args[0]); // logfile to process

		int N = input.readInt();
		SocialNetwork net = new SocialNetwork(N);

		while (!input.isEmpty() && !net.networked()) {
			net.connect(input.readString(), input.readInt(), input.readInt());
		}
		if (net.networked()) {
			StdOut.println("Fully networked at time: " + net.timeStamp());
		} else {
			StdOut.println("Not fully networked!");
		}

	}

}
