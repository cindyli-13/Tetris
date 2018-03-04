import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 * This class contains the game and the 
 * GUI for Tetris.
 * 
 * @author Cindy Li
 * @since Monday, September 4, 2017
 *
 */
@SuppressWarnings("serial")
public class Tetris extends JFrame implements KeyListener, ActionListener {
	
	// fields
	private Tetrimino currPiece, nextPiece;
	private Tile[][] tiles;
	private Tile[][] tilesNext;
	private boolean xIsPressed;
	private ArrayList<Integer> linesToClear;
	private int level;
	private int lines;
	private int score;
	
	/*
	 * Scoring System:
	 *    - every piece = (10 * level) points
	 *    - n lines = (n * 50 * level) points
	 */
	
	// ***** CONSTANTS *****
	public static final Border TILE_BORDER = BorderFactory.createRaisedBevelBorder();
	public static final Color GRID_COLOR = new Color(235, 235, 250);
	public static final Color TEXT_COLOR = new Color(70, 70, 90);
	public static final Color BACKGROUND_COLOR = new Color(165, 190, 200);
	public static final Color BUTTON_COLOR = new Color(235, 235, 250);
	public static final Font TEXT_FONT_1 = new Font("Rockwell", Font.BOLD, 20);
	public static final Font TEXT_FONT_2 = new Font("Rockwell", Font.PLAIN, 20);
	public static final Font TEXT_FONT_3 = new Font("Rockwell", Font.BOLD, 30);
	
	
	// GUI components
	private JPanel gamePanel, grid, nextPieceIndicator, pauseCover1, pauseCover2;
	private JLabel levelSign, levelIndicator, linesSign, linesIndicator, scoreSign, scoreIndicator,
			paused;
	private JButton newGame, pause;
	
	// timers
	private Timer speed, wait;
	
	
	// main
	public static void main(String[] args) {
		new Tetris();
	}
	
	
	// constructor
	public Tetris() {
		super("Tetris");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(520,600);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		// set up game panel
		gamePanel = new JPanel();
		gamePanel.setBounds(0, 0, 520, 600);
		gamePanel.setLayout(null);
		gamePanel.setBackground(BACKGROUND_COLOR);
		
		// set up grid
		grid = new JPanel();
		grid.setSize(250, 475);
		grid.setLocation(40, 40);
		grid.setLayout(new GridLayout(19, 10));
		grid.setBackground(GRID_COLOR);
		
		
		// set up tiles
		tiles = new Tile[19][10];
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 10; j++) {
				
				tiles[i][j] = new Tile();
				grid.add(tiles[i][j]);
			}
		}
		
		
		// set up pause cover 1
		pauseCover1 = new JPanel();
		pauseCover1.setSize(250, 475);
		pauseCover1.setLocation(40, 40);
		pauseCover1.setLayout(null);
		pauseCover1.setBackground(GRID_COLOR);
		pauseCover1.setVisible(false);
		
		
		// set up paused label
		paused = new JLabel();
		paused.setSize(150, 40);
		paused.setLocation(70, 220);
		paused.setForeground(TEXT_COLOR);
		paused.setText("Paused");
		paused.setFont(TEXT_FONT_3);
		pauseCover1.add(paused);
		
		
		// set up next piece indicator panel
		nextPieceIndicator = new JPanel();
		nextPieceIndicator.setSize(150, 100);
		nextPieceIndicator.setLocation(325, 40);
		nextPieceIndicator.setLayout(new GridLayout(4, 6));
		nextPieceIndicator.setBackground(GRID_COLOR);
		
		
		// set up next tiles
		tilesNext = new Tile[4][6];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				
				tilesNext[i][j] = new Tile();
				nextPieceIndicator.add(tilesNext[i][j]);
			}
		}
		
		
		// set up pause cover 2
		pauseCover2 = new JPanel();
		pauseCover2.setSize(150, 100);
		pauseCover2.setLocation(325, 40);
		pauseCover2.setBackground(GRID_COLOR);
		pauseCover2.setVisible(false);
		
		
		// set up level sign
		levelSign = new JLabel();
		levelSign.setSize(65, 40);
		levelSign.setLocation(325, 190);
		levelSign.setForeground(TEXT_COLOR);
		levelSign.setText("Level");
		levelSign.setFont(TEXT_FONT_1);
		
		
		// set up level indicator
		levelIndicator = new JLabel();
		levelIndicator.setSize(80, 40);
		levelIndicator.setLocation(390, 190);
		levelIndicator.setForeground(TEXT_COLOR);
		levelIndicator.setFont(TEXT_FONT_2);
		levelIndicator.setHorizontalAlignment(JLabel.RIGHT);
		
		
		// set up lines sign
		linesSign = new JLabel();
		linesSign.setSize(54, 40);
		linesSign.setLocation(325, 240);
		linesSign.setForeground(TEXT_COLOR);
		linesSign.setText("Lines");
		linesSign.setFont(TEXT_FONT_1);
		
		
		// set up lines indicator
		linesIndicator = new JLabel();
		linesIndicator.setSize(91, 40);
		linesIndicator.setLocation(379, 240);
		linesIndicator.setForeground(TEXT_COLOR);
		linesIndicator.setFont(TEXT_FONT_2);
		linesIndicator.setHorizontalAlignment(JLabel.RIGHT);
		
		
		// set up score sign
		scoreSign = new JLabel();
		scoreSign.setSize(53, 40);
		scoreSign.setLocation(325, 290);
		scoreSign.setForeground(TEXT_COLOR);
		scoreSign.setText("Score");
		scoreSign.setFont(TEXT_FONT_1);
			
		
		// set up score indicator
		scoreIndicator = new JLabel();
		scoreIndicator.setSize(92, 40);
		scoreIndicator.setLocation(378, 290);
		scoreIndicator.setForeground(TEXT_COLOR);
		scoreIndicator.setFont(TEXT_FONT_2);
		scoreIndicator.setHorizontalAlignment(JLabel.RIGHT);
		
		
		// set up new game button
		newGame = new JButton("New Game");
		newGame.setSize(150, 40);
		newGame.setLocation(325, 370);
		newGame.setBackground(BUTTON_COLOR);
		newGame.setForeground(TEXT_COLOR);
		newGame.setFont(TEXT_FONT_1);
		newGame.setFocusPainted(false);
		newGame.addActionListener(this);
		
		
		// set up pause button
		pause = new JButton("Pause");
		pause.setSize(150, 40);
		pause.setLocation(325, 420);
		pause.setBackground(BUTTON_COLOR);
		pause.setForeground(TEXT_COLOR);
		pause.setFont(TEXT_FONT_1);
		pause.setFocusPainted(false);
		pause.addActionListener(this);
		
		
		// add components to game panel
		gamePanel.add(grid);
		gamePanel.add(pauseCover1);
		gamePanel.add(nextPieceIndicator);
		gamePanel.add(pauseCover2);
		gamePanel.add(levelSign);
		gamePanel.add(levelIndicator);
		gamePanel.add(linesSign);
		gamePanel.add(linesIndicator);
		gamePanel.add(scoreSign);
		gamePanel.add(scoreIndicator);
		gamePanel.add(newGame);
		gamePanel.add(pause);
		
		// add game panel to frame
		add(gamePanel);
		
		// set up fields
		xIsPressed = false;
		linesToClear = new ArrayList<Integer>();
		
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
		
		setVisible(true);
		startGame();
	}
	
	
	/**
	 * Starts the executions of the game.
	 */
	private void startGame() {
		
		// prompt the player to choose a level
		Object[] options = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		level = JOptionPane.showOptionDialog(null, "Choose level:", "", 
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, 1) + 1;
		
		if (level == 0)
			level = 1;
		
		speed = new Timer(800 - (level - 1) * 80, this);
		wait = new Timer(800 - (level - 1) * 80, this);
		
		lines = 0;
		score = 0;
		
		levelIndicator.setText(Integer.toString(level));
		linesIndicator.setText(Integer.toString(lines));
		scoreIndicator.setText(Integer.toString(score));
		
		// generate starting and in waiting Tetriminos
		currPiece = Tetrimino.randomTetrimino();
		nextPiece = Tetrimino.randomTetrimino();
		
		addPiece();
	}
	
	
	/**
	 * Resets the grid, next piece indicator, 
	 * and game.
	 */
	private void resetGame() {
		
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 10; j++) {
				
				setToNoPiece(tiles[i][j]);
			}
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				
				setToNoPiece(tilesNext[i][j]);
			}
		}
		
		startGame();
	}
	
	
	/**
	 * Adds the current Tetrimino to the 
	 * grid.
	 */
	private void addPiece() {
		
		boolean gameOver = false;
		
		for (int i = 0; i < 4; i++) {
			
			int x = currPiece.coordinates[i][0];
			int y = currPiece.coordinates[i][1];
			
			// check if there is already part of another 
			// piece in this tile (if the Tetriminos are 
			// overlapping)
			if (tiles[x][y].getBackground() != GRID_COLOR)
				gameOver = true;
			
			setToPiece(tiles[x][y], currPiece, true);
		}
		
		if (gameOver) {
			
			speed.stop();
			JOptionPane.showMessageDialog(this, "Game Over");
			resetGame();
		}
		else {
			
			speed.restart();
			setNextPieceIndicator();
		}
	}
	
	
	/**
	 * Checks whether or not the current 
	 * Tetrimino can fall.
	 * @return true for yes, false for no
	 */
	private boolean pieceCanFall() {
		
		// find the lowest point of the Tetrimino
		
		int lowestPoint = currPiece.coordinates[0][0];
		
		for (int i = 1; i < 4; i++) {
			
			if (currPiece.coordinates[i][0] > lowestPoint) {
				lowestPoint = currPiece.coordinates[i][0];
			}
		}
		
		// check if there is space to fall
		if (lowestPoint == tiles.length - 1)
			return false;
		
		else {
			for (int i = 0; i < 4; i++) {
				
				int x = currPiece.coordinates[i][0];
				int y = currPiece.coordinates[i][1];
				
				if (!(tiles[x + 1][y].getBackground() == GRID_COLOR 
						|| tiles[x + 1][y].getPartOfCurrPiece()))
					return false;
			}
		}
		
		return true;
	}
	
	
	/**
	 * Clears the lines that are full.
	 */
	private void clearLines() {
		
		linesToClear.clear();
		
		
		for (int i = 18; i >= 0; i--) {
			
			boolean full = true, empty = true;
			
			for (int j = 0; j < 10; j++) {
				
				if (tiles[i][j].getBackground() == GRID_COLOR)
					full = false;
				else
					empty = false;
			}
			
			// if line is full, add current line to list of lines to clear
			if (full)
				linesToClear.add(i);
			
			// else if line is empty, break out of loop
			else if (empty)
				break;
		}
		
		// if there are lines that need clearing
		if (linesToClear.size() > 0) {
			
			// clear all the lines that are full
			for (int i = 0; i < linesToClear.size(); i++) {
				
				int x = linesToClear.get(i);
				
				for (int j = 0; j < 10; j++) {
					
					setToNoPiece(tiles[x][j]);
				}
			}
			
			int spacesToFall = 0;
			
			// allow floating pieces to fall
			for (int i = 18; i >= 0; i--) {
				
				if (spacesToFall < linesToClear.size() && i == linesToClear.get(spacesToFall)) {
					spacesToFall++;
					continue;
				}
				
				for (int j = 0; j < 10; j++) {
					
					tiles[i + spacesToFall][j].setBackground(tiles[i][j].getBackground());
					tiles[i + spacesToFall][j].setBorder(tiles[i][j].getBorder());
					
					// if the top has been reached
					if (i - spacesToFall < 0) {
						
						setToNoPiece(tiles[i][j]);
					}
				}
			}
			
			updateLines(linesToClear.size());
			updateScore(50 * linesToClear.size() * level);
		}
	}
	
	
	/**
	 * Sets up the next piece indicator panel to 
	 * show the next Tetrimino.
	 */
	private void setNextPieceIndicator() {
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				
				setToNoPiece(tilesNext[i][j]);
			}
		}
		
		// set up the panel based on which type of Tetrimino is next
		if (nextPiece instanceof IBlock) {
			
			for (int i = 1; i < 5; i++) {
				
				setToPiece(tilesNext[1][i], nextPiece, false);
			}
		}
		
		else if (nextPiece instanceof OBlock) {
			
			for (int i = 1; i < 3; i++) {
				for (int j = 2; j < 4; j++) {
					
					setToPiece(tilesNext[i][j], nextPiece, false);	
				}
			}
		}
		
		else if (nextPiece instanceof JBlock) {
			
			for (int i = 1; i < 4; i++) {
				
				setToPiece(tilesNext[1][i], nextPiece, false);
			}
			setToPiece(tilesNext[2][3], nextPiece, false);
		}
		
		else if (nextPiece instanceof LBlock) {
			
			for (int i = 1; i < 4; i++) {
				
				setToPiece(tilesNext[1][i], nextPiece, false);
			}
			setToPiece(tilesNext[2][1], nextPiece, false);
		}
		
		else if (nextPiece instanceof SBlock) {
			
			for (int i = 2; i < 4; i++) {
				
				setToPiece(tilesNext[1][i], nextPiece, false);
			}
			for (int i = 1; i < 3; i++) {
				
				setToPiece(tilesNext[2][i], nextPiece, false);
			}
		}
		
		else if (nextPiece instanceof ZBlock) {
			
			for (int i = 1; i < 3; i++) {
				
				setToPiece(tilesNext[1][i], nextPiece, false);
			}
			for (int i = 2; i < 4; i++) {
				
				setToPiece(tilesNext[2][i], nextPiece, false);
			}
		}
		
		else if (nextPiece instanceof TBlock) {
			
			for (int i = 1; i < 4; i++) {
				
				setToPiece(tilesNext[1][i], nextPiece, false);
			}
			setToPiece(tilesNext[2][2], nextPiece, false);
		}
	}
	
	
	/**
	 * Sets a tile to be part of the current 
	 * Tetrimino.
	 * @param t The tile to change
	 * @param piece The Tetrimino it is part of
	 * @param partOfCurrPiece Boolean which states 
	 * whether the tile is part of the current  
	 * Tetrimino or not
	 */
	private void setToPiece(Tile t, Tetrimino piece, boolean partOfCurrPiece) {
		
		t.setPartOfCurrPiece(partOfCurrPiece);
		t.setBackground(piece.COLOR);
		t.setBorder(TILE_BORDER);
	}
	
	
	/**
	 * Sets a tile to be not part of a Tetrimino 
	 * piece.
	 * @param t The tile to change
	 */
	private void setToNoPiece(Tile t) {
		
		t.setPartOfCurrPiece(false);
		t.setBackground(GRID_COLOR);
		t.setBorder(null);
	}
	
	
	/**
	 * Increments the lines field and updates the 
	 * lines indicator.
	 * @param increment The amount to increment 
	 * lines by
	 */
	private void updateLines(int increment) {
		
		lines += increment;
		linesIndicator.setText(Integer.toString(lines));
	}
	
	
	/**
	 * Increments the score and updates the score 
	 * indicator.
	 * @param increment The amount to increment the 
	 * score by
	 */
	private void updateScore(int increment) {
		
		score += increment;
		scoreIndicator.setText(Integer.toString(score));
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		// left arrow key
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			
			// check if the Tetrimino can move to the left
			int leftMostPoint = currPiece.coordinates[0][1];
			
			for (int i = 1; i < 4; i++) {
				
				if (currPiece.coordinates[i][1] < leftMostPoint) {
					leftMostPoint = currPiece.coordinates[i][1];
				}
			}
			
			boolean canMoveLeft = true;
			
			// check if there is space to move left
			if (leftMostPoint == 0)
				canMoveLeft = false;
			
			else {
				for (int i = 0; i < 4; i++) {
					
					int x = currPiece.coordinates[i][0];
					int y = currPiece.coordinates[i][1];
					
					if (!(tiles[x][y - 1].getBackground() == GRID_COLOR 
							|| tiles[x][y - 1].getPartOfCurrPiece()))
						canMoveLeft = false;
				}
			}
			
			// move to the left
			if (canMoveLeft) {
				
				// get new coordinates and remove old tiles
				for (int i = 0; i < 4; i++) {
					
					int x = currPiece.coordinates[i][0];
					int y = currPiece.coordinates[i][1];
					
					setToNoPiece(tiles[x][y]);
					
					currPiece.coordinates[i][1]--;
				}
				
				// set new tiles to Tetrimino
				for (int i = 0; i < 4; i++) {
					
					int x = currPiece.coordinates[i][0];
					int y = currPiece.coordinates[i][1];
					
					setToPiece(tiles[x][y], currPiece, true);
				}
			}
		}
		
		
		// right arrow key
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			// check if the Tetrimino can move to the right
			int rightMostPoint = currPiece.coordinates[0][1];
			
			for (int i = 1; i < 4; i++) {
				
				if (currPiece.coordinates[i][1] > rightMostPoint) {
					rightMostPoint = currPiece.coordinates[i][1];
				}
			}
			
			boolean canMoveRight = true;
			
			// check if there is space to move left
			if (rightMostPoint == 9)
				canMoveRight = false;
			
			else {
				for (int i = 0; i < 4; i++) {
					
					int x = currPiece.coordinates[i][0];
					int y = currPiece.coordinates[i][1];
					
					if (!(tiles[x][y + 1].getBackground() == GRID_COLOR 
							|| tiles[x][y + 1].getPartOfCurrPiece()))
						canMoveRight = false;
				}
			}
			
			// move to the right
			if (canMoveRight) {
				
				// get new coordinates and remove old tiles
				for (int i = 0; i < 4; i++) {
					
					int x = currPiece.coordinates[i][0];
					int y = currPiece.coordinates[i][1];
					
					setToNoPiece(tiles[x][y]);
					
					currPiece.coordinates[i][1]++;
				}
				
				// set new tiles to Tetrimino
				for (int i = 0; i < 4; i++) {
					
					int x = currPiece.coordinates[i][0];
					int y = currPiece.coordinates[i][1];
					
					setToPiece(tiles[x][y], currPiece, true);
				}
			}
		}
		
		
		// down arrow key
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			
			// let the current Tetrimino fall if it can
			if (pieceCanFall()) {
				
				// get new coordinates and remove old tiles
				for (int i = 0; i < 4; i++) {
					
					int x = currPiece.coordinates[i][0];
					int y = currPiece.coordinates[i][1];
					
					setToNoPiece(tiles[x][y]);
					
					currPiece.coordinates[i][0]++;
				}
				
				// set new tiles to Tetrimino
				for (int i = 0; i < 4; i++) {
					
					int x = currPiece.coordinates[i][0];
					int y = currPiece.coordinates[i][1];
					
					setToPiece(tiles[x][y], currPiece, true);
				}
			}
		}
		
		
		// x key (rotate)
		if (e.getKeyCode() == KeyEvent.VK_X) {
			
			if (!xIsPressed) {
				
				// check if Tetrimino can rotate
				int[][] coor = currPiece.getRotatedCoordinates();
				
				boolean canRotate = true;
				
				
				for (int i = 0; i < 4; i++) {
						
					if (!(coor[i][0] >= 0 && coor[i][0] < 19 && coor[i][1] >= 0 && coor[i][1] < 10 
							&& (tiles[coor[i][0]][coor[i][1]].getBackground() == GRID_COLOR || 
								tiles[coor[i][0]][coor[i][1]].getPartOfCurrPiece()))) {
						
						canRotate = false;
						break;
					}
				}
				
				// rotate the Tetrimino
				if (canRotate) {
					
					// remove old tiles
					for (int i = 0; i < 4; i++) {
						
						int x = currPiece.coordinates[i][0];
						int y = currPiece.coordinates[i][1];
						
						setToNoPiece(tiles[x][y]);
					}
					
					// set new coordinates
					currPiece.coordinates = coor;
					
					// set new tiles to Tetrimino
					for (int i = 0; i < 4; i++) {
						
						int x = currPiece.coordinates[i][0];
						int y = currPiece.coordinates[i][1];
						
						setToPiece(tiles[x][y], currPiece, true);
					}
				}
			}
			
			xIsPressed = true;
		}
	}

	
	@Override
	public void keyReleased(KeyEvent e) {
		
		// x key
		if (e.getKeyCode() == KeyEvent.VK_X)
			xIsPressed = false;
	}

	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// speed timer
		if (e.getSource() == speed) {
			
			// let the current Tetrimino fall if it can
			if (pieceCanFall()) {
				// get new coordinates and remove old tiles
				for (int i = 0; i < 4; i++) {
					
					int x = currPiece.coordinates[i][0];
					int y = currPiece.coordinates[i][1];
					
					setToNoPiece(tiles[x][y]);
					
					currPiece.coordinates[i][0]++;
				}
				
				// set new tiles to Tetrimino
				for (int i = 0; i < 4; i++) {
					
					int x = currPiece.coordinates[i][0];
					int y = currPiece.coordinates[i][1];
					
					setToPiece(tiles[x][y], currPiece, true);
				}
			}
			
			// else the piece can't fall
			else {
				
				for (int i = 0; i < 4; i++) {
					
					int x = currPiece.coordinates[i][0];
					int y = currPiece.coordinates[i][1];
					
					tiles[x][y].setPartOfCurrPiece(false);
				}
				
				updateScore(10 * level);
				clearLines();
				
				speed.stop();
				wait.restart();
				
				currPiece = null;
			}
		}
		
		// wait timer
		else if (e.getSource() == wait) {
			
			wait.stop();
			
			// set current Tetrimino to the Tetrimino in waiting
			currPiece = nextPiece;
			
			// generate next Tetrimino in waiting
			nextPiece = Tetrimino.randomTetrimino();
			
			addPiece();
		}
		
		// new game button
		else if (e.getSource() == newGame) {
			
			requestFocus();
			
			// unpause
			if (pauseCover1.isVisible()) {
				pauseCover1.setVisible(false);
				pauseCover2.setVisible(false);
				pause.setText("Pause");
			}
			
			wait.stop();
			speed.stop();
			resetGame();
		}
		
		// pause button
		else if (e.getSource() == pause) {
			
			if (pauseCover1.isVisible()) {
				
				requestFocus();
				
				pauseCover1.setVisible(false);
				pauseCover2.setVisible(false);
				pause.setText("Pause");
				speed.start();
			}
			else {
				
				pauseCover1.setVisible(true);
				gamePanel.setComponentZOrder(pauseCover1, 0);
				pauseCover2.setVisible(true);
				gamePanel.setComponentZOrder(pauseCover2, 0);
				pause.setText("Unpause");
				speed.stop();
				wait.stop();
			}
		}
	}

}
