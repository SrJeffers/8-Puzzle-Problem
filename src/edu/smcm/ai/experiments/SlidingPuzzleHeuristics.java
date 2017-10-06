package edu.smcm.ai.experiments;

import java.util.List;

import edu.smcm.ai.search.AStar;
import edu.smcm.ai.search.Action;
import edu.smcm.ai.search.Problem;
import edu.smcm.ai.search.Search;
import edu.smcm.ai.sliding_block.ManhattanDistance;
import edu.smcm.ai.sliding_block.OutOfPlace;
import edu.smcm.ai.sliding_block.SlidingBlockProblem;

/**
 * Compare the effectiveness of the Manhattan distance and out-of-place
 * heuristics.
 */
public class SlidingPuzzleHeuristics {

	/**
	 * The number of trials to make of each heuristic.
	 */
	public static final int number_of_trials;

	/**
	 * The number of random moves to make to create the initial state.
	 */
	public static final int number_of_random_moves;

	/**
	 * The size of the puzzles to be solved. The code currently only works for
	 * the size 3.
	 */
	public static final int problem_size;

	/**
	 * The static initialiser block to initialise all constants.
	 */
	static {
		number_of_trials = 300;
		number_of_random_moves = 20;
		problem_size = 3;
	}

	/**
	 * Run a sequence of searches.
	 * 
	 * @param search_engine
	 *            search to use, including heuristic
	 * @param results
	 *            resulting data
	 */
	public static void run(Search search_engine, Results results) {
		Problem problem; // The problem to be solved
		List<Action> solution; // The actions necessary to solve the problem

		for (int count = 0; count < number_of_trials; count++) {
			problem = new SlidingBlockProblem(problem_size, number_of_random_moves);
			System.out.println("Problem: " + count);
			System.out.println("Initial State:");
			System.out.println(problem.initialState());
			solution = search_engine.search(problem);
			System.out.println("Solution:");
			for (Action action : solution) {
				System.out.println(action);
			}
			System.out.println("----------------");

			results.add(solution.size(), search_engine.nodesGenerated());
		}
	}

	/**
	 * A program to run the experiments.
	 *
	 * @param args
	 *            command line arguments (unused)
	 */
	public static void main(String[] args) {
		Search search_engine; // The engine to do searches
		Results manhattan_distance_results;
		Results out_of_place_results;
		
		System.out.println("=======================");
		System.out.println("Manhattan Distance");
		System.out.println("=======================");
		
		search_engine = new AStar(new ManhattanDistance());
		manhattan_distance_results = new Results(number_of_random_moves + 1);
		run(search_engine, manhattan_distance_results);

		System.out.println("=======================");
		System.out.println("Out Of Place");
		System.out.println("=======================");

		search_engine = new AStar(new OutOfPlace());
		out_of_place_results = new Results(number_of_random_moves + 1);
		run(search_engine, out_of_place_results);

		// Print a table of all of the results
		for (int index = 0; index < manhattan_distance_results.size(); index++) {
			System.out.printf("%3d : %10.1f %10.1f\n", index, manhattan_distance_results.mean(index),
					out_of_place_results.mean(index));
		}
	}
}
