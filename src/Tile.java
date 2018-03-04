import javax.swing.JButton;

/**
 * This class is the blueprint for 
 * a Tile on the Tetris game grid.
 * 
 * @author Cindy Li
 * @since Monday, September 4, 2017
 *
 */
@SuppressWarnings("serial")
public class Tile extends JButton {
	
	// fields
	private boolean partOfCurrPiece;
	
	
	// constructor
	public Tile() {
		super();
		
		partOfCurrPiece = false;
		setBackground(Tetris.GRID_COLOR);
		
		setBorder(null);
		setEnabled(false);
	}
	
	
	/**
	 * Sets whether or not the tile is part  
	 * of the current Tetrimino.
	 * @param b  true for yes, false for no
	 */
	public void setPartOfCurrPiece(boolean b) {
		
		partOfCurrPiece = b;
	}
	
	
	/**
	 * Returns whether or not the tile is part 
	 * of the current Tetrimino.
	 * @return partOfCurrPiece
	 */
	public boolean getPartOfCurrPiece() {
		
		return partOfCurrPiece;
	}
}
