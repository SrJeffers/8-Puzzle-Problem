package edu.smcm.ai.sliding_block;

//Sean Jeffers and Chris Lynch
//AI Project 1
//Artificial Intelligence
//COSC 370

import edu.smcm.ai.search.Heuristic;
import edu.smcm.ai.search.State;

/**
 * A heuristic that simply counts the number of tiles that are not in their
 * correct place in the puzzle. See page Russell and Norvig, Artificial
 * Intelligence: A Modern Approach, Third Edition, p. 103.
 *
 */
public class OutOfPlace extends Heuristic {

	/**
	 * Number of tiles out of place.
	 * 
	 * A count of the total number of tiles that are out of place.
	 */
	/* (non-Javadoc)
	 * @see edu.smcm.ai.search.Heuristic#cost(edu.smcm.ai.search.State)
	 */
	@Override
	public double cost(State state) {
		// TODO Implement cost method of OutOfPlace heuristic
		
		
		
		SlidingBlockState actual;
		actual = (SlidingBlockState) state;
		
		//gets the goal state of the puzzle
		SlidingBlockProblem sbp = new SlidingBlockProblem(actual.size());
		double cost = 0;
		
		//goes through each tile and compares them with that of the goal state. 
		//If they don't match, cost is incremented.
		for (int i = 0; i < actual.size(); i++){
			for(int j = 0; j < actual.size(); j++){
				if(actual.tileAt(i, j) != sbp.initialState().tileAt(i, j)){
					cost++;	
				}
				
			}
		}
		
		
		return cost;
	}
}
