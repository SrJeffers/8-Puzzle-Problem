package edu.smcm.ai.sliding_block;

//Sean Jeffers and Chris Lynch
//AI Project 1
//Artificial Intelligence
//COSC 370

import edu.smcm.ai.search.Heuristic;
import edu.smcm.ai.search.State;

/**
 * A heuristic that computes the sum of the Manhattan distances between the
 * tiles and their correct positions. See Russell and Norvig, Artificial
 * Intelligence: A Modern Approach, Third Edition, p.103.
 */
public class ManhattanDistance extends Heuristic {

	/**
	 * Sum of Manhattan distances of tiles.
	 * 
	 * The sum for each tile of the difference in the row and column between
	 * actual and goal positions.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.smcm.ai.search.Heuristic#cost(edu.smcm.ai.search.State)
	 */
	@Override
	public double cost(State state) {
		// TODO Implement cost function of Manhattan distance heuristic
		
		SlidingBlockState actual;
		
		actual = (SlidingBlockState) state;
		
		//creates an instance of the goal state
		SlidingBlockProblem sbp = new SlidingBlockProblem(actual.size());
		double cost = 0;
		
		//used for iterating through the given state.
		for (int i = 0; i < actual.size(); i++){
			for (int j = 0; j < actual.size(); j++){
				
				//used for iterating through the goal state. 
				for (int a = 0; a < actual.size(); a++){
					for (int b = 0; b < actual.size(); b++){
						//finds the Manhattan Distance between each tile and their goal coordinate.
						if(actual.tileAt(i, j) == sbp.initialState().tileAt(a, b)){
							cost += Math.abs(i-a);
							cost += Math.abs(j-b);
							
						}
					}
				}
			}
		}
		
		return cost;
	}

}
