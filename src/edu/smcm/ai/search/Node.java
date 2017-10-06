package edu.smcm.ai.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Node in a Search Tree for Classical Search. See Russell and Norvig,
 * Artificial Intelligence: A Modern Approach, Third Edition, p. 79.
 */
public class Node {

	/**
	 * The node for the state from which this node's state is derived.
	 */
	private Node parent;

	/**
	 * The action on the previous state which lead to this state.
	 */
	private Action action;

	/**
	 * The state of the environment represented by this Node.
	 */
	private State state;

	/**
	 * The <I>path</I>-cost from the initial state to this state.
	 */
	private double cost;

	/**
	 * Generate a new node given a parent node.
	 * 
	 * We should only need to supply problem, previous node and action,
	 * everything else can be worked out from them.
	 * 
	 * @param parent
	 *            node representing the previous state
	 * @param action
	 *            action leading to the current state
	 * @param state
	 *            current state
	 * @param cost
	 *            <I>path</I>-cost of this node
	 */
	public Node(Node parent, Action action, State state, double cost) {
		this.parent = parent;
		this.action = action;
		this.state = state;
		this.cost = cost;
	}

	/**
	 * Generate a Node representing the initial state.
	 * 
	 * This cannot be made part of Problem since Problem uses it to create the
	 * initial state. Or does it?
	 * *node* is done
	 * 
	 * @param state
	 *            initial state
	 */
	public Node(State state) {
		this.parent = null;
		this.action = null;
		this.state = state;
		this.cost = 0.0;
	}

	/**
	 * Get path cost of this Node.
	 * 
	 * @return path cost of this Node
	 */
	public double cost() {
		return cost;
	}

	/**
	 * Get parent (or predecessor) of this Node.
	 * 
	 * @return predecessor of this Node
	 */
	public Node parent() {
		return parent;
	}

	/**
	 * Get action resulting in this Node.
	 * 
	 * @return action resulting in this Node
	 */
	public Action action() {
		return action;
	}

	/**
	 * Get state represented by this Node.
	 * 
	 * @return state represented by this Node.
	 */
	public State state() {
		return state;
	}

	/**
	 * Get sequence of actions resulting in this Node.
	 * 
	 * The sequence of actions from the initial state to the state represented
	 * by this Node in order first to last.
	 * 
	 * @return sequence of actions from initial state
	 */
	public List<Action> solution() {
		List<Action> result;

		if (null == parent) {
			result = new ArrayList<Action>();
		} else {
			result = parent.solution();
			result.add(action);
		}

		return result;
	}
}