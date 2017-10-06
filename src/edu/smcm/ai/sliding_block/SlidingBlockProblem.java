package edu.smcm.ai.sliding_block;

//Sean Jeffers and Chris Lynch
//AI Project 1
//Artificial Intelligence
//COSC 370

import java.util.List;

import edu.smcm.ai.search.Action;
import edu.smcm.ai.search.Problem;
import edu.smcm.ai.search.State;

/**
 * Implementation of the abstract Problem class for the Sliding Block Puzzle.
 *
 * Many of the methods are simply "pass through" methods that call a method of
 * the SlidingBlockState. This is for modularity reasons to make sure that
 * SlidingBlockStates are immutable.
 */
public class SlidingBlockProblem extends Problem {

	/**
	 * The initial state of the problem.
	 */
	private SlidingBlockState initial_state;

	/**
	 * Constructor that makes an initial state that is also the goal state.
	 * 
	 * @param size
	 *            the length of the sides of the puzzle
	 */
	public SlidingBlockProblem(int size) {
		initial_state = new SlidingBlockState(size);
	}

	/**
	 * Constructor that makes an initial puzzle by applying random actions to
	 * the goal state.
	 * 
	 * @param size
	 *            the length of the sides of the puzzle
	 * @param moves
	 *            the number of moves to make
	 */
	public SlidingBlockProblem(int size, int moves) {
		initial_state = new SlidingBlockState(size, moves);
	}

	/**
	 * The initial state of the Sliding Block Puzzle.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.smcm.ai.search.Problem#initialState()
	 */
	@Override
	public SlidingBlockState initialState() {
		return initial_state;
	}

	/**
	 * Obtain the <I>legal</I> actions in this state.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.smcm.ai.search.Problem#actions(edu.smcm.ai.search.State)
	 */
	@Override
	public List<Action> actions(State state) {
		return ((SlidingBlockState) state).actions();
	}

	/**
	 * Determine if the state is in fact a goal state.
	 * 
	 * There is only one goal state for the Sliding Block Puzzle.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.smcm.ai.search.Problem#isGoalState(edu.smcm.ai.search.State)
	 */
	@Override
	public boolean isGoalState(State state) {
		// TODO Implement isGoalState(State state)
	
		SlidingBlockState actual;
		
		actual = (SlidingBlockState) state;
		
		//Creates an instance of the goal state
		SlidingBlockProblem sbp = new SlidingBlockProblem(actual.size());
		
		for (int i = 0; i < actual.size(); i++){
			for(int j = 0; j < actual.size(); j++){
				//Compares the given state with the goal state. 
				//If a tile doesn't match the goal state, the program returns false
				if(actual.tileAt(i, j) != sbp.initialState().tileAt(i, j)){
					return false;
				}
			}
		}
		
		return true;
	}

	/**
	 * Obtain the state resulting from the application of an action to this
	 * state.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.smcm.ai.search.Problem#result(edu.smcm.ai.search.State,
	 * edu.smcm.ai.search.Action)
	 */
	@Override
	public SlidingBlockState result(State state, Action action) {
		return ((SlidingBlockState) state).result((SlidingBlockAction) action);
	}

	/**
	 * Obtain the cost of applying an action in this state
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.smcm.ai.search.Problem#cost(edu.smcm.ai.search.State,
	 * edu.smcm.ai.search.Action)
	 */
	@Override
	public double cost(State start, Action action, State end) {
		// Assume that end can be reached from start via action
		return 1.0;
	}
}
