package edu.smcm.ai.search;

import java.util.List;

/**
 * An abstraction of a search algorithm. An instance of this class is
 * essentially a search engine.
 */
abstract public class Search {
	
	/**
	 * Search for a solution to the problem using some search algorithm.
	 * 
	 * @param problem problem to be solved
	 * @return list of the actions necessary to solve the problem
	 */
	abstract public List<Action> search(Problem problem);
	
	/**
	 * The number of nodes generated during the search process.
	 * 
	 * @return number of nodes generated
	 */
	abstract public int nodesGenerated();
	
}
