import java.awt.Color;

/**
 * This class is the blueprint for 
 * L-Block Tetriminos.
 * 
 * @author Cindy Li
 * @since Monday, September 4, 2017
 *
 */
public class LBlock extends Tetrimino {

	// fields
	private final static int[][] STARTING_COORDINATES = {{0,3},{0,4},{0,5},{1,3}};
	private final static Color COLOR = new Color(255, 150, 40);
	
	
	// constructor
	public LBlock() {
		super(COLOR, STARTING_COORDINATES);
	}
	
	
	@Override
	public int[][] getRotatedCoordinates() {
		
		int[][] coor = new int[4][2];
		
		
		/* The L-Block can be in 1 of the following 4 states:
		 *	1) horizontal, tip on bottom left
		 *	2) vertical, tip on top left
		 *	3) horizontal, tip on top right
		 *	4) vertical, tip on bottom right
		 */
		
		
		// if the L-Block is in state 1: rotate to state 2
		if (coordinates[0][0] < coordinates[3][0]) {
			
			// first xy pair
			coor[0][0] = coordinates[0][0] - 1;
			coor[0][1] = coordinates[0][1] + 1;
		
			// second xy pair
			coor[1][0] = coordinates[1][0];
			coor[1][1] = coordinates[1][1];
			
			// third xy pair
			coor[2][0] = coordinates[2][0] + 1;
			coor[2][1] = coordinates[2][1] - 1;
			
			// fourth xy pair
			coor[3][0] = coordinates[3][0] - 2;
			coor[3][1] = coordinates[3][1];
		}
		
		// else if the L-Block is in state 2: rotate to state 3
		else if (coordinates[0][1] > coordinates[3][1]) {
			
			// first xy pair
			coor[0][0] = coordinates[0][0] + 1;
			coor[0][1] = coordinates[0][1] + 1;
			
			// second xy pair
			coor[1][0] = coordinates[1][0];
			coor[1][1] = coordinates[1][1];
			
			// third xy pair
			coor[2][0] = coordinates[2][0] - 1;
			coor[2][1] = coordinates[2][1] - 1;
			
			// fourth xy pair
			coor[3][0] = coordinates[3][0];
			coor[3][1] = coordinates[3][1] + 2;
		}
		
		// else if the L-Block is in state 3: rotate to state 4
		else if (coordinates[0][0] > coordinates[3][0]) {
			
			// first xy pair
			coor[0][0] = coordinates[0][0] + 1;
			coor[0][1] = coordinates[0][1] - 1;
			
			// second xy pair
			coor[1][0] = coordinates[1][0];
			coor[1][1] = coordinates[1][1];
			
			// third xy pair
			coor[2][0] = coordinates[2][0] - 1;
			coor[2][1] = coordinates[2][1] + 1;
			
			// fourth xy pair
			coor[3][0] = coordinates[3][0] + 2;
			coor[3][1] = coordinates[3][1];
		}
		
		// else the L-Block is in state 4: rotate to state 1
		else {
			
			// first xy pair
			coor[0][0] = coordinates[0][0] - 1;
			coor[0][1] = coordinates[0][1] - 1;
			
			// second xy pair
			coor[1][0] = coordinates[1][0];
			coor[1][1] = coordinates[1][1];
			
			// third xy pair
			coor[2][0] = coordinates[2][0] + 1;
			coor[2][1] = coordinates[2][1] + 1;
			
			// fourth xy pair
			coor[3][0] = coordinates[3][0];
			coor[3][1] = coordinates[3][1] - 2;
		}
		
		return coor;		
	}
	
	

}
