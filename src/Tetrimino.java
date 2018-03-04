import java.awt.Color;

/**
 * This class is the blueprint for Tetrimino 
 * shapes in the game Tetris.
 * 
 * @author Cindy Li
 * @since Monday, September 4, 2017
 *
 */
public abstract class Tetrimino {
	
	// fields
	protected final Color COLOR; // can be any color EXCEPT FOR Tetris.GRID_COLOR
	protected int[][] coordinates;
	
	
	// constructor
	public Tetrimino(Color c, int[][] coor) {
		
		COLOR = c;
		
		coordinates = new int[4][2];
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				
				coordinates[i][j] = coor[i][j];
			}
		}
	}
	
	
	/**
	 * Generates a random Tetrimino.
	 * @return a Tetrimino
	 */
	public static Tetrimino randomTetrimino() {
		
		switch ((int)(Math.random() * 7)) {
			case 0:
				return new IBlock();
			case 1:
				return new JBlock();
			case 2:
				return new LBlock();
			case 3:
				return new OBlock();
			case 4:
				return new SBlock();
			case 5:
				return new ZBlock();
			case 6:
				return new TBlock();
			
			// the code should never reach this point
			default:
				return new IBlock();
		}
	}
	
	
	/**
	 * Returns the color of the Tetrimino.
	 * @return COLOR
	 */
	public Color getColor() {
		return COLOR;
	}
	
	
	/**
	 * Returns the coordinates of the Tetrimino.
	 * @return coordinates
	 */
	public int[][] getCoordinates() {
		return coordinates;
	}
	
	
	/**
	 * Returns the coordinates of the Tetrimino 
	 * if it were rotated clockwise.
	 */
	public abstract int[][] getRotatedCoordinates();
	
}
