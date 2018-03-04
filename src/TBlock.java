import java.awt.Color;

/**
 * This class is the blueprint for 
 * T-Block Tetriminos.
 * 
 * @author Cindy Li
 * @since Monday, September 4, 2017
 *
 */
public class TBlock extends Tetrimino {

	// fields
	private final static int[][] STARTING_COORDINATES = {{0,3},{0,4},{0,5},{1,4}};
	private final static Color COLOR = new Color(220, 60, 235);
	
	
	// constructor
	public TBlock() {
		super(COLOR, STARTING_COORDINATES);
	}
	
	
	@Override
	public int[][] getRotatedCoordinates() {
		
		int[][] coor = new int[4][2];
		
		
		/* The T-Block can be in 1 of the following 4 states:
		 *	1) horizontal, tip on bottom
		 *	2) vertical, tip on left
		 *	3) horizontal, tip on top
		 *	4) vertical, tip on right
		 */
		
		// the center point stays the same
		coor[1][0] = coordinates[1][0];
		coor[1][1] = coordinates[1][1];
		
		
		// first, rotate the two follower coordinates:
		coor[2][0] = coordinates[3][0];
		coor[2][1] = coordinates[3][1];
		coor[3][0] = coordinates[0][0];
		coor[3][1] = coordinates[0][1];
		
		
		//next, rotate the leading coordinate:
		
		// if the T-Block is in state 1: rotate to state 2
		if (coordinates[1][0] < coordinates[3][0]) {
			
			coor[0][0] = coordinates[0][0] - 1;
			coor[0][1] = coordinates[0][1] + 1;
		}
		
		// else if the T-Block is in state 2: rotate to state 3
		else if (coordinates[1][1] > coordinates[3][1]) {
			
			coor[0][0] = coordinates[0][0] + 1;
			coor[0][1] = coordinates[0][1] + 1;
		}
		
		// else if the T-Block is in state 3: rotate to state 4
		else if (coordinates[1][0] > coordinates[3][0]) {
			
			coor[0][0] = coordinates[0][0] + 1;
			coor[0][1] = coordinates[0][1] - 1;
		}
		
		// else the T-Block is in state 4: rotate to state 1
		else {
			
			coor[0][0] = coordinates[0][0] - 1;
			coor[0][1] = coordinates[0][1] - 1;
		}
		
		return coor;
	}
}
