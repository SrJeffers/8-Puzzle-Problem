package edu.smcm.ai.search;

//Sean Jeffers and Chris Lynch
//AI Project 1
//Artificial Intelligence
//COSC 370


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import edu.smcm.ai.sliding_block.OutOfPlace;

/**
 * Implementation of the Uniform Cost Search. See Russell and Norvig, Artificial
 * Intelligence: A Modern Approach, Third Edition, p 84. The frontier is
 * represented using both a PriorityQueue and a Map&lt;State, Node&gt; to make
 * determining membership quicker.
 */
public class UniformCostSearch extends Search {

	/**
	 * Set of states explored so far.
	 */
	private Set<State> explored;

	/**
	 * A Map that associates States with Nodes
	 */
	private Map<State, Node> frontier_map;

	/**
	 * The queue of Nodes in the frontier
	 */
	private PriorityQueue<Node> frontier_queue;

	/**
	 * Counter for the number of nodes generated
	 */
	private int nodes_generated;

	/**
	 * Default Constructor.
	 */
	public UniformCostSearch() {
		
		this.explored = new HashSet<State>();
		this.frontier_map = new HashMap<State, Node>();
		this.frontier_queue = new PriorityQueue<Node>();
	}

	/**
	 * Replace the frontier queue with another.
	 * 
	 * This allows the Uniform Costs Search to be transformed into another
	 * search that orders the frontier queue differently.
	 * 
	 * @param frontier_queue
	 *            new frontier queue
	 */
	protected void frontierQueue(PriorityQueue<Node> frontier_queue) {
		this.frontier_queue = frontier_queue;
	}

	/**
	 * Get the number of nodes generated in this search.
	 *
	 * @return number of nodes generated
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.smcm.ai.search.Search#nodesGenerated()
	 */
	public int nodesGenerated() {
		return nodes_generated;
	}

	/**
	 * Perform a Uniform Cost Search.
	 * 
	 * Pseudocode for this method can be found in Russell and Norvig, Artificial
	 * Intelligence: A Modern Approach, Third Edition, p. 84. Note that the
	 * frontier is made up of both a priority queue and a has table.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.smcm.ai.search.Search#search(edu.smcm.ai.search.Problem)
	 */
	public List<Action> search(Problem problem) {
		// TODO Implement Uniform Cost Search
		//System.out.println("Implement Uniform Cost Search");
		
		
		Node child;
		
		Node node;//= new Node(problem.initialState());
		
		nodes_generated = 1;
		
		
		//frontier_map and frontier_queue are both cleared to remove elements from previous games
		frontier_map.clear();
		frontier_queue.clear();
		
		
		//the initial states and notes are placed into frontier_map and frontier_queue
		frontier_map.put(problem.initialState(), problem.initialNode());
		frontier_queue.add(problem.initialNode());
		
		//explored is initialized
		explored = new HashSet<State>();
		
		for(;;){
			
			if(frontier_queue.isEmpty()){
				
				return null;
				
			}
			
			//the lowest cost node from frontier_queue is set to node
			node = frontier_queue.poll();
			
			//if the goal state is met, the solution is returned
			if(problem.isGoalState(node.state())){
				
				return node.solution();
				
			}
			
			//the newest node is added to explored
			explored.add(node.state());
			
			
			//iterates through every possible action of the node
			for(Action act: problem.actions(node.state())){
				
				//creates the child node
				child = problem.childNode(node, act);
				
				nodes_generated++;
				
				
				//adds nodes if they have not been explored yet
				if(!explored.contains(child.state()) || !frontier_map.containsKey(child.state())){
				
					
					
					frontier_map.put(child.state(), child);
					frontier_queue.add(child);
					
					//tests to see if this is the lowest cost node to get to that state
				}else if(child.cost() > frontier_map.get(child.state()).cost()){
					
					//removes the path with the higher cost
					frontier_queue.remove(child.state());
					frontier_map.remove(frontier_map.get(child.state()));
					
					//adds the more preferable node to the frontier
					frontier_map.put(child.state(), child);
					frontier_queue.add(child);
					
				}				
			}
		}		
	}
}
