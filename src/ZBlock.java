import java.awt.Color;

/**
 * This class is the blueprint for 
 * Z-Block Tetriminos.
 * 
 * @author Cindy Li
 * @since Monday, September 4, 2017
 *
 */
public class ZBlock extends Tetrimino {

	// fields
	private final static int[][] STARTING_COORDINATES = {{0,3},{0,4},{1,4},{1,5}};
	private final static Color COLOR = new Color(250, 35, 35);
	
	
	// constructor
	public ZBlock() {
		super(COLOR, STARTING_COORDINATES);
	}


	@Override
	public int[][] getRotatedCoordinates() {
		
		int[][] coor = new int[4][2];
		
		
		// if Z-Block is horizontal: rotate to vertical
		if (coordinates[0][0] == coordinates[1][0]) {
			
			// first xy pair
			coor[0][0] = coordinates[0][0] - 1;
			coor[0][1] = coordinates[0][1] + 2;
			
			// second xy pair
			coor[1][0] = coordinates[1][0];
			coor[1][1] = coordinates[1][1] + 1;
			
			// third xy pair
			coor[2][0] = coordinates[2][0] - 1;
			coor[2][1] = coordinates[2][1];
			
			// fourth xy pair
			coor[3][0] = coordinates[3][0];
			coor[3][1] = coordinates[3][1] - 1;
		}
		
		// else Z-Block is vertical: rotate to horizontal
		else {
			
			// first xy pair
			coor[0][0] = coordinates[0][0] + 1;
			coor[0][1] = coordinates[0][1] - 2;
			
			// second xy pair
			coor[1][0] = coordinates[1][0];
			coor[1][1] = coordinates[1][1] - 1;
			
			// third xy pair
			coor[2][0] = coordinates[2][0] + 1;
			coor[2][1] = coordinates[2][1];
			
			// fourth xy pair
			coor[3][0] = coordinates[3][0];
			coor[3][1] = coordinates[3][1] + 1;
		}
		
		return coor;
	}
}
