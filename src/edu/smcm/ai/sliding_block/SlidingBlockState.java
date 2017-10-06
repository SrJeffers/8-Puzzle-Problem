package edu.smcm.ai.sliding_block;

//Sean Jeffers and Chris Lynch
//AI Project 1
//Artificial Intelligence
//COSC 370

import java.util.List;
import java.util.Random;

import edu.smcm.ai.search.Action;
import edu.smcm.ai.sliding_block.SlidingBlockAction.ActionType;

/**
 * Sliding Block Puzzle State.
 * 
 * The position of the various tiles in an instance of the puzzle. The rows are
 * numbered from the top left corner. The blank should therefore end up at (0,
 * 0).
 */
public class SlidingBlockState extends edu.smcm.ai.search.State {
	int size;
	/**
	 * Tiles of puzzle.
	 */
	private int[][] tiles;

	/**
	 * A random number generator for initial states.
	 */
	private static Random oracle;

	/**
	 * Static initialisation block.
	 * 
	 * Can be used as a way of initialising static variables. It is more
	 * flexible than initialising them where they are declared.
	 */
	static {
		oracle = new Random();
	}

	/**
	 * Coordinate of a tile in the puzzle.
	 * 
	 * This is used primarily to allow findBlank to return a single value.
	 *
	 */
	public static class Coordinate {
		private int row;
		private int column;

		public Coordinate(int row, int column) {
			this.row = row;
			this.column = column;
		}

		public int row() {
			return row;
		}

		public int column() {
			return column;
		}
	}

	/**
	 * Create an initial state for Sliding Block Puzzle.
	 * 
	 * The state created is the goal state. That is all tiles are in order with
	 * blank at the top left corner.
	 * 
	 * @param size
	 *            length of the sides
	 */
	public SlidingBlockState(int size) {
		// TODO Implement single argument constructor for SlidingBlockState
		
		
		//creates a goal state to be met for the program 
		
		tiles = new int[size][size];
		for(int i = 0; i< size; i++){
			for(int j = 0; j< size; j++){
				tiles[i][j]= j+(i*size);
				
			}
		}
			
	}

	/** 
	 * Create an initial state for the Sliding Puzzle using random moves.
	 * 
	 * Apply a supplied number of random moves to the puzzle to create an
	 * initial starting point for the puzzle. NOTE: the puzzle is trivially
	 * solvable in no more than the number of moves supplied. It may take fewer
	 * moves because the random moves may contain moves that undo a previous
	 * move, for example.
	 * 
	 * @param size
	 *            size of puzzle to create
	 * @param moves
	 *            number of random moves to apply
	 */
	public SlidingBlockState(int size, int moves) {
		// TODO Implement random SlidingBlockState constructor
		tiles = new int[size][size];

		int move;
		//creates the default state for the puzzle 
		for (int i = 0; i< size; i++){
			for (int j = 0; j< size; j++){
				tiles[i][j]= j+(i*size);
			}
		}
		
		
		int temp;
		Coordinate blank;
		
		//applies a number of random moves to the tiles to create a puzzle to be solved. 
		for (int i = 0; i < moves; i++){
			blank = findBlank();
			
			move = oracle.nextInt(4);
			
			switch(move){
			case 0:
				//up
				if(blank.row()== 0){
					//moves down if the blank can't move up
					temp = tiles[blank.row()][blank.column()];
					tiles[blank.row()][blank.column()] = tiles[blank.row()+1][blank.column()];
					tiles[blank.row()+1][blank.column()] = temp;
					
				}else{
					temp = tiles[blank.row()][blank.column()];
					tiles[blank.row()][blank.column()] = tiles[blank.row()-1][blank.column()];
					tiles[blank.row()-1][blank.column()] = temp;
					
				}
				break;
			case 1:
				//down
				if(blank.row()== 2){
					//moves up if the blank can't move down
					temp = tiles[blank.row()][blank.column()];
					tiles[blank.row()][blank.column()] = tiles[blank.row()-1][blank.column()];
					tiles[blank.row()-1][blank.column()] = temp;
					
				}else{
					temp = tiles[blank.row()][blank.column()];
					tiles[blank.row()][blank.column()] = tiles[blank.row()+1][blank.column()];
					tiles[blank.row()+1][blank.column()] = temp;
					
				}
				break;
			case 2:
				//left
				if(blank.column()== 0){
					//moves right if the blank can't move left
					temp = tiles[blank.row()][blank.column()];
					tiles[blank.row()][blank.column()] = tiles[blank.row()][blank.column()+1];
					tiles[blank.row()][blank.column()+1] = temp;
					
				}else{
					temp = tiles[blank.row()][blank.column()];
					tiles[blank.row()][blank.column()] = tiles[blank.row()][blank.column()-1];
					tiles[blank.row()][blank.column()-1] = temp;
					
				}
				break;
			case 3:
				//right
				if(blank.column()== 2){
					//moves left if the blank can't move right
					temp = tiles[blank.row()][blank.column()];
					tiles[blank.row()][blank.column()] = tiles[blank.row()][blank.column()-1];
					tiles[blank.row()][blank.column()-1] = temp;
					
				}else{
					temp = tiles[blank.row()][blank.column()];
					tiles[blank.row()][blank.column()] = tiles[blank.row()][blank.column()+1];
					tiles[blank.row()][blank.column()+1] = temp;
					
				}				
				break;
			default:
				break;
			}	
			
		}
		
		
	}

	/**
	 * Generate the list of <I>legal</I> actions in this state.
	 * 
	 * Generates a list of all actions possible in any state, then removes those
	 * that are illegal in this state because the blank is on an edge (or worse
	 * in a corner). This method strictly belongs in the Problem subclass, but
	 * is used in generating random starting states.
	 * 
	 * @return list of <I>legal</I> actions
	 */
	public List<Action> actions() {
		// TODO Implement actions() method of SlidingBlockState
		Coordinate blank;
		
			
		List<Action> legal = SlidingBlockAction.actions();
		
		
		
		//Depending on where the blank is, certain actions are removed.
		blank = findBlank();
		if((int)blank.row() == 0){
			//remove up
			legal.remove(2);
		}
		
		if((int)blank.row() == 2){
			//remove down
			legal.remove(3);
		}
		
		if(blank.column() == 0){
			//remove left
			legal.remove(0);
		}
		
		if(blank.column() == 2){
			//remove right
			legal.remove(1);
		}
		return legal;
	}

	/**
	 * Copy the state for Sliding Block Puzzle.
	 * 
	 * The state created is an exact copy of the supplied state. This copy can
	 * be changed without changing the original.
	 * 
	 * @param original
	 *            original puzzle instance to be copied
	 */
	public SlidingBlockState(SlidingBlockState original) {
		this.tiles = new int[original.size()][original.size()];

		for (int row = 0; row < tiles.length; row++) {
			for (int column = 0; column < tiles[row].length; column++) {
				this.tiles[row][column] = original.tiles[row][column];
			}
		}
	}

	/**
	 * Obtain the length of the sides of the Sliding Block Puzzle.
	 * 
	 * @return length of sides of puzzle
	 */
	public int size() {
		return tiles.length;
	}

	/**
	 * Accessor for tile value.
	 * 
	 * This particular accessor is useful for iterating through all of the tiles
	 * on the grid. NOTE: the top left corner is (0, 0).
	 * 
	 * @param row
	 *            the row being accessed
	 * @param column
	 *            the column being accessed
	 * @return the value on the tile at that position
	 */
	public int tileAt(int row, int column) {
		return tiles[row][column];
	}

	/**
	 * Accessor for a tile value.
	 * 
	 * This accessor is here for completeness sake. It is not expected that you
	 * will use it since you must create a Coordinate object to do so.
	 * 
	 * @param coordinate
	 *            the coordinates of the tile being accessed
	 * @return the value of the tile at the supplied coordinates
	 */
	public int tileAt(Coordinate coordinate) {
		return tiles[coordinate.row()][coordinate.column()];
	}

	/**
	 * Find the blank tile on the board.
	 * 
	 * This method returns a Coordinate object so we don't have to search the
	 * board twice -- once for the row and another time for the column.
	 * 
	 * @return the Coordinate of the blank tile
	 */
	public Coordinate findBlank() {
		for (int row = 0; row < tiles.length; row++) {
			for (int column = 0; column < tiles[row].length; column++) {
				if (0 == tiles[row][column]) {
					return new Coordinate(row, column);
				}
			}
		}

		throw new ImplementationException("No blank");
	}

	/**
	 * Mutator method that applies a SlidingBlockAction to the current tiles.
	 * 
	 * NOTE: This method should be used with care since it mutates the current
	 * state's tiles. It should only be used when generating an initial board
	 * and when updating a state that has been copied to make the next state (in
	 * the result method).
	 * 
	 * @param action
	 *            the action to be applied to the tile
	 */
	private void takeAction(SlidingBlockAction action) {
		// TODO Implement takeAction() method of SlidingBlockState
		Coordinate blank = findBlank();
		int temp;
		

		
		if(action.toString().equals("Left")){
			//move left
			temp = tiles[blank.row()][blank.column()];
			tiles[blank.row()][blank.column()] = tiles[blank.row()][blank.column()-1];
			tiles[blank.row()][blank.column()-1] = temp;
		}
		
		if(action.toString().equals("Right")){
			//move right
			temp = tiles[blank.row()][blank.column()];
			tiles[blank.row()][blank.column()] = tiles[blank.row()][blank.column()+1];
			tiles[blank.row()][blank.column()+1] = temp;
		}
		
		if(action.toString().equals("Up")){
			//move up
			temp = tiles[blank.row()][blank.column()];
			tiles[blank.row()][blank.column()] = tiles[blank.row()-1][blank.column()];
			tiles[blank.row()-1][blank.column()] = temp;
		}
		
		if(action.toString().equals("Down")){
			//move Down
			temp = tiles[blank.row()][blank.column()];
			tiles[blank.row()][blank.column()] = tiles[blank.row()+1][blank.column()];
			tiles[blank.row()+1][blank.column()] = temp;
			
		}
		

	}

	/**
	 * Derive a new state from this state by applying an action.
	 * 
	 * This method should really be part of the SlidingBlockProblem class, but
	 * that means exposing a lot about the SlidingBlockState. This approach
	 * leads to more modularity, though re-implementation would be necessary if
	 * the SlidingBlockProblem's environment description were to change
	 * significantly.
	 * 
	 * @param action
	 *            the action to be taken
	 * @return a new state with the action taken
	 */
	public SlidingBlockState result(SlidingBlockAction action) {
		SlidingBlockState result;

		result = new SlidingBlockState(this);

		result.takeAction(action);

		return result;
	}

	/**
	 * Equality method.
	 * 
	 * In order to store SlidingBlockStates in a Set&lt;&gt; we need to supply
	 * an appropriate equals() method, otherwise equality will be reference
	 * equality, which is not what we intend. The method simply ensures the
	 * tiles are in the same place in both objects.
	 * 
	 * @param that
	 *            the other state being compared
	 * @return true if the tiles are in the same place
	 */
	public boolean equals(SlidingBlockState that) {
		boolean result;

		result = true;
		for (int row = 0; row < tiles.length; row++) {
			for (int column = 0; column < tiles[0].length; column++) {
				result = result && (tiles[row][column] == that.tiles[row][column]);
			}
		}
		
		return result;
	}

	/**
	 * Hash code.
	 * 
	 * In order to use the SlidingBlockState in a hash table (such as
	 * HashSet&lt;&gt;) or a Map (such as HashMap&lt;&gt;) we need to provide a
	 * hash function. The hash should be the same for two objects that are the
	 * same, but as different as possible for other objects. We simply use the
	 * tiles to create a number using a radix of the board size.
	 * 
	 * @return the hash value
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Implement hashCode() method of SlidingBlockState
		
		
		//creates a random result for each type of state in the array
		int result = (67 % (tiles[0][0]+ 23)) - (43 % (tiles[0][1]+ 17)) % (89 % (tiles[0][2]+ 79)) *
					 (103 % (tiles[1][0]+ 19)) * (79 % (tiles[1][1]+ 37)) + (97 % (tiles[1][2]+ 31)) *
					 (101 % (tiles[2][0]+ 53)) + (83 % (tiles[2][1]+ 13)) * (129 % (tiles[2][2]+ 53));
		
		return result;
	}

	/**
	 * A toString method.
	 * 
	 * Creates a String with numbers in the appropriate places for tiles. NOTE:
	 * This only works for the 8-puzzle currently.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String result;

		result = "";
		for (int row = 0; row < tiles.length; row++) {
			for (int column = 0; column < tiles[0].length; column++) {
				result = result + tiles[row][column];
			}
			result = result + '\n';
		}

		return result;
	}
}
