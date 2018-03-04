import java.awt.Color;

/**
 * This class is the blueprint for 
 * O-Block Tetriminos.
 * 
 * @author Cindy Li
 * @since Monday, September 4, 2017
 *
 */
public class OBlock extends Tetrimino {

	// fields
	private final static int[][] STARTING_COORDINATES = {{0,4},{0,5},{1,4},{1,5}};
	private final static Color COLOR = new Color(240, 235, 30);
	
	
	// constructor
	public OBlock() {
		super(COLOR, STARTING_COORDINATES);
	}
	
	
	@Override
	public int[][] getRotatedCoordinates() {
		
		// the O-Block is the same after being rotated
		return coordinates;
	}
}
