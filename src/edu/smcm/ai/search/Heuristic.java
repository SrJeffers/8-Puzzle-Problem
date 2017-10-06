package edu.smcm.ai.search;

/**
 * An abstraction of a heuristic which determines the estimated cost of the best
 * solution from a given state. See Russell and Norvig, Artificial Intelligence:
 * A Modern Approach, Third Edition, page 102.
 */
abstract public class Heuristic {

	/**
	 * Compute the value of the heuristic.
	 * 
	 * @param state
	 *            state for which the heuristic should be computed
	 * @return value of heuristic for the given state
	 */
	abstract public double cost(State state);
}
