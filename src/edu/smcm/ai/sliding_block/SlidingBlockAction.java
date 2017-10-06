package edu.smcm.ai.sliding_block;

import java.util.ArrayList;
import java.util.List;

import edu.smcm.ai.search.Action;

/**
 * Actions in the Sliding Block Puzzle.
 * 
 * There are really only four actions in the Sliding Block Puzzle -- slide the
 * tile from the right; slide the tile from the left; slide the tile from above
 * (up) and slide the tile from below (down). A design decision has been made to
 * use an enum to represent the value of the action so that it can be used in
 * switch statements; however this would also have been true if the values had
 * been represented by the more common fixed integer constants. However, this
 * method provides an illustration of the enum class. This class cannot be an
 * enum because an enum cannot extend a class or implement an interface
 * (annoyingly).
 *
 */
public class SlidingBlockAction extends edu.smcm.ai.search.Action {

	/**
	 * The actual value of this action.
	 */
	private ActionType value;

	/**
	 * A public constant for the "from the left" action.
	 */
	public static final SlidingBlockAction left;

	/**
	 * A public constant for the "from the right" action.
	 */
	public static final SlidingBlockAction right;

	/**
	 * A public constant for the "from above" action.
	 */
	public static final SlidingBlockAction up;

	/**
	 * A public constant for the "from below" action.
	 */
	public static final SlidingBlockAction down;

	/**
	 * A convenience constant that is a list of all possible actions.
	 */
	private static final List<SlidingBlockAction> actions;

	/**
	 * The enumeration of the possible actions.
	 * 
	 * These can be obtained with the value() method and used in a switch
	 * statement.
	 */
	public static enum ActionType {
		Left, Right, Up, Down;
	}

	/**
	 * Initialise all the static variables.
	 * 
	 * NOTE: We wouldn't be able to initialise actions where it's declared. We
	 * need to use a static initialiser block.
	 */
	static {
		left = new SlidingBlockAction(ActionType.Left);
		right = new SlidingBlockAction(ActionType.Right);
		up = new SlidingBlockAction(ActionType.Up);
		down = new SlidingBlockAction(ActionType.Down);

		actions = new ArrayList<SlidingBlockAction>(4);
		actions.add(left);
		actions.add(right);
		actions.add(up);
		actions.add(down);
	}

	/**
	 * Obtain the value of this action.
	 * 
	 * The value can be used in switch statements.
	 * 
	 * @param value
	 *            the value of this action
	 */
	private SlidingBlockAction(ActionType value) {
		this.value = value;
	}

	public ActionType value() {
		return value;
	}

	static public List<Action> actions() {
		return new ArrayList<Action>(actions);
	}
	
	public String toString() {
		return value().toString();
	}
}