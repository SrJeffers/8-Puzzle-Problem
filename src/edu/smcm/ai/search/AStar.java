package edu.smcm.ai.search;

import java.util.PriorityQueue;

/**
 * An A* search is a Uniform Cost Search where the frontier queue is replaced by
 * a frontier queue sorted with a heuristic. See Russell and Norvig, Artificial
 * Intelligence: A Modern Approach, Third Edition, p. 93.
 */
public class AStar extends UniformCostSearch {

	/**
	 * Constructor for the A* search.
	 * 
	 * @param h
	 *            heuristic for comparison of states in frontier
	 */
	public AStar(Heuristic h) {
		super();
		frontierQueue(new PriorityQueue<Node>(new HeuristicComparator(h)));
	}
}
