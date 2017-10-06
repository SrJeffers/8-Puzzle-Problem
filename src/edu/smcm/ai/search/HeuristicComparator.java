package edu.smcm.ai.search;

import java.util.Comparator;

/**
 * A comparator based on a heuristic. It is used in keeping a PriorityQueue in
 * an appropriate order.
 */
public class HeuristicComparator implements Comparator<Node> {

	/**
	 * The heuristic to be used.
	 */
	private Heuristic h;

	/**
	 * The constructor.
	 * 
	 * @param h the heuristic function to be used by this comparator
	 */
	public HeuristicComparator(Heuristic h) {
		this.h = h;
	}

	/**
	 * Compare method.
	 * 
	 * Use both the actual path cost to the Node and the heuristic estimate of the path cost from the state.
	 * 
	 * @param left first Node to be compared
	 * @param right second Node to be compared
	 * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second
	 */
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Node left, Node right) {
		double left_cost;
		double right_cost;

		left_cost = left.cost() + h.cost(left.state());
		right_cost = right.cost() + h.cost(right.state());

		return Double.compare(left_cost, right_cost);
	}
}
