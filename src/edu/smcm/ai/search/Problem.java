package edu.smcm.ai.search;

import java.util.List;

/**
 * The abstract formulation of a classical search problem. Each problem must
 * implement a specific set of methods. See Russell and Norvig, Artificial
 * Intelligence: A Modern Approach, Third Edition, p 66.
 */
abstract public class Problem {

	/**
	 * The initial state of the problem.
	 * 
	 * @return initial state of the problem
	 */
	abstract public State initialState();

	/**
	 * Obtain a list of actions that are legal in a given state.
	 * 
	 * @param state
	 *            the state for which actions are required
	 * @return a list of legal actions
	 */
	abstract public List<Action> actions(State state);

	/**
	 * A recogniser method for the goal state.
	 * 
	 * @param state
	 *            state to be tested
	 * @return true if the state is a goal state
	 */
	abstract public boolean isGoalState(State state);

	/**
	 * Apply an action to a state.
	 * 
	 * The action is applied to the supplied state to obtain a new state (the
	 * successor state).
	 * 
	 * @param state
	 *            start state
	 * @param action
	 *            action to take
	 * @return resulting state
	 */
	abstract public State result(State state, Action action);

	/**
	 * Compute the step cost of going from one state to another via a specified
	 * action.
	 * 
	 * @param start
	 *            the state in which the action is to be taken
	 * @param action
	 *            the action to be taken
	 * @param end
	 *            the state resulting from the application of the action
	 * @return the cost of the step
	 */
	abstract public double cost(State start, Action action, State end);

	/**
	 * Create a child node in the search tree.
	 * 
	 * See Russell and Norvig, Artificial Intelligence: A Modern Approach, Third
	 * Edition, p. 79.
	 * 
	 * @param parent
	 *            node for initial state
	 * @param action
	 *            action to be taken
	 * @return node for resulting state
	 */
	public Node childNode(Node parent, Action action) {
		State end;
		double step_cost;

		end = result(parent.state(), action);
		step_cost = cost(parent.state(), action, end);

		return new Node(parent, action, end, parent.cost() + step_cost);
	}

	/**
	 * Create the node for the root of the search tree.
	 * 
	 * @return initial node of search
	 */
	public Node initialNode() {
		return new Node(null, null, initialState(), 0);
	}
}
