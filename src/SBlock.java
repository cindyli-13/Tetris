import java.awt.Color;

/**
 * This class is the blueprint for 
 * Z-Block Tetriminos.
 * 
 * @author Cindy Li
 * @since Monday, September 4, 2017
 *
 */
public class SBlock extends Tetrimino {

	// fields
	private final static int[][] STARTING_COORDINATES = {{0,4},{0,5},{1,3},{1,4}};
	private final static Color COLOR = new Color(30, 200, 40);
	
	
	// constructor
	public SBlock() {
		super(COLOR, STARTING_COORDINATES);
	}


	@Override
	public int[][] getRotatedCoordinates() {
		
		int[][] coor = new int[4][2];
		
		
		// if S-Block is horizontal: rotate to vertical
		if (coordinates[0][0] == coordinates[1][0]) {
			
			// first xy pair
			coor[0][0] = coordinates[0][0];
			coor[0][1] = coordinates[0][1] + 1;
			
			// second xy pair
			coor[1][0] = coordinates[1][0] + 1;
			coor[1][1] = coordinates[1][1];
			
			// third xy pair
			coor[2][0] = coordinates[2][0] - 2;
			coor[2][1] = coordinates[2][1] + 1;
			
			// fourth xy pair
			coor[3][0] = coordinates[3][0] - 1;
			coor[3][1] = coordinates[3][1];
		}
		
		// else S-Block is vertical: rotate to horizontal
		else {
			
			// first xy pair
			coor[0][0] = coordinates[0][0];
			coor[0][1] = coordinates[0][1] - 1;
			
			// second xy pair
			coor[1][0] = coordinates[1][0] - 1;
			coor[1][1] = coordinates[1][1];
			
			// third xy pair
			coor[2][0] = coordinates[2][0] + 2;
			coor[2][1] = coordinates[2][1] - 1;
			
			// fourth xy pair
			coor[3][0] = coordinates[3][0] + 1;
			coor[3][1] = coordinates[3][1];
		}
		
		return coor;
	}
}
