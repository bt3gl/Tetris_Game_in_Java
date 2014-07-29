/* The Board Class for the Awesome Tetris
 *  
 *  Purpose of this class: 
 *  	-> The Game Board and Timer
 *
 * @author Mari & Samer
 * @version 1.0 Jul/14
 * 
 * TO DO:
 * 	-> customize to decide size board and game's speed
 *  -> ask to play again after game over
 *  -> understand java's graphics class better
 * 
 */

package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * The listener interface for receiving action events. 
 * The class that is interested in processing an action event implements this 
 * interface, and the object created with that class is registered with a component,
 * using the component's addActionListener method. When the action event occurs, 
 * that object's actionPerformed method is invoked.
 */

public class Board extends JPanel implements ActionListener {

   
    // Set the size of the pieces in relation to the board
	// smaller values = bigger pieces
    final int boardWid = 10;
    final int boardHei = 18;

    // Counts the number of lines removed
    int numLinesRemoved = 0;
    // Counts the number of points
    int points = 0;
    int level = 0;
    
    // Determines the actual position of the 
    // falling tetris shape
    int posX = 0;
    int posY = 0;
    
    // Timer starts action events after some delay, calling
    // the method actionPerformed() for each 300s (for example).
    // i.e. smaller speed = faster game
    int gameSpeed = 300;
    
    // Controls whether the piece is in the bottom yet
    boolean isFallingDone = false;
    
    // Paused (p)
    boolean isPaused = false;
    
    // Started after paused (p)
    boolean isStarted = false;
    
    // Constructors
    Timer timer;
    ImageIcon image;
    JLabel statusbar;
    JLabel picture;
    
    Shape currentPiece   = new Shape();
    Shape previewPiece   = new Shape();
    Shape.Pieces[] board = new Shape.Pieces[boardWid * boardHei];
    

    public Board(Tetris p) {
        // Sets the focusable state of this Component to the specified value.
        setFocusable(true);
        this.statusbar =  p.getStatusBar();       
     
        // Start timer
        this.timer = new Timer(this.gameSpeed, this);
        this.timer.start(); 
        
        // Add the background pictures
        this.image = new ImageIcon(this.getClass().getResource("hs1.png"));
        this.picture = new JLabel(new ImageIcon(this.image.getImage()));
        add(this.picture);
        
        // Start Listener
        addKeyListener(new getKeys());
        
        clearBoard();  

     }


    

    /*
     * Here we control the input from the keyboard
     * via KeyAdapter, which is an inner 
     * class that overrides the keyPressed() method.
     * 
     * KeyAdapter is an abstract adapter class for 
     * receiving keyboard events. The methods in this 
     * class are empty. This class exists as convenience 
     * for creating listener objects.
     * 
     * KeyEvent is an event which indicates that a 
     * keystroke occurred in a component.
     */

    class getKeys extends KeyAdapter {
    	
         public void keyPressed(KeyEvent key) {


             //System.out.println(currentPiece.getShape());
        	 // dealing with exceptions
             if (!isStarted) {  
                 return;
             }

             // get the keyboard key
             int keyCode = key.getKeyCode();

             // pause needs to be a separated event
             if (keyCode == 's' || keyCode == 'S') {
                 pause();
                 return;
             }

             // continues after paused 
             if (keyCode == 'c' || keyCode == 'C') {
                 restart();
                 return;
             }
             
             // don't do anything if it is paused
             if (isPaused)
                 return;

             // if not paused, these are the options:
             switch (keyCode) {
	             case KeyEvent.VK_LEFT:
	            	 tryToMove(currentPiece, posX - 1, posY);
	                 break;
	             case KeyEvent.VK_RIGHT:
	            	 tryToMove(currentPiece, posX + 1, posY);
	                 break;
	             case KeyEvent.VK_DOWN:
	            	 tryToMove(currentPiece.rotate(true), posX, posY);
	                 break;
                     case KeyEvent.VK_UP:
                         tryToMove(currentPiece.rotate(false), posX, posY);
                         break;
	             case KeyEvent.VK_SPACE:
	            	 dropPieceDown();
	                 break;
                     case 'F': // fallthrough
	             case 'f':
	            	 moveDownOneLine();
	                 break;
                     case 'Q': // fallthrough
	             case 'q':
                         System.out.println("Score: " + points);
	            	 System.exit(0);
                         break;
             }

         }
     }

    
    
    /*
     * This method moves the block until reaching
     * the bottom.
     */
    public void actionPerformed(ActionEvent e) {
    	// actionPerformed() method checks if the falling has finished. 
        if (isFallingDone) {
        	// If so, a new piece is created.
        	isFallingDone = false;
            newPiece();
        } 
        else {
        	// If not, the falling tetris piece goes one line down.
            moveDownOneLine();
        }
    }


    /*
     * Controls the begin and pauses of the game.
     */
    public void start()
    { 
        isStarted = true;
        isFallingDone = false;
        numLinesRemoved = 0;
        points = 0;
        level = 0;
        clearBoard();
        newPiece();
        timer.start();
    }

    private void pause()
    {
        timer.stop();
        statusbar.setText("Game Paused. Type c to continue.");
        isPaused = true;
    }
        	
    private void restart()
    {
         timer.start();
         statusbar.setText(String.valueOf(points));
         isPaused = false;
     }



	/*
	 * This method fills the board with 0 coordinates.
	 */
	private void clearBoard()
	{
		// the board is just made of empty pieces
	    for (int i = 0; i < this.boardHei * this.boardWid; ++i)
	    	this.board[i] = Shape.Pieces.NoShape;
	}
	
	
	
	/* 
	 * Check if there are full rows in all the rows in the board.
	 * Then increases the points and destroy the full line. 
	 */
	private void deleteLines()
	{
	    int countFullLines = 0;
	
	    // starts in the bottom and decreases
	    for (int i = this.boardHei - 1; i >= 0; --i) {
	    	
	    	//System.out.println(i);
	        boolean lineIsFull = true;
	
	        // starts in the left and increases  
	        for (int j = 0; j < this.boardWid; ++j) {
	        	// if it find at least one space filled with 0s...
	            if (getShapeHere(j, i) == Shape.Pieces.NoShape) {
	            	// the line should not be removed
	                lineIsFull = false;
	                break;
	            }
	        }
	
	        if (lineIsFull) {
	            ++countFullLines;
	            // destroy line
	            for (int k = i; k < this.boardHei - 1; ++k) {
	                for (int j = 0; j < this.boardWid; ++j)
	                	// replaces by the shape above the removed line
	                	this.board[(k * this.boardWid) + j] = getShapeHere(j, k + 1);
	            }
	        }
	    }
	    
	    // increase points if we had removed lines
	    if (countFullLines > 0) {
                // Score formula from: http://harddrop.com/wiki/Scoring
                int multiplier = 0;
                switch (countFullLines) {
                    case 1:
                        multiplier = 40;
                        break;
                    case 2:
                        multiplier = 100;
                        break;
                    case 3:
                        multiplier = 300;
                        break;
                    case 4:
                        multiplier = 1200;
                        break;
                }
                this.points += multiplier * (level + 1);
	        this.numLinesRemoved += countFullLines;
	        
	        // The points are giving by the number of lines and the width of the board
	        this.statusbar.setText(String.valueOf(this.points));
	        // time to release a new piece
	        this.isFallingDone = true;
	    }
	 }

	
	/* Tries to move/start the tetris piece. 
	 * The method returns false, if it has 
	 * reached the board boundaries or it is adjacent 
	 * to the already fallen tetris pieces.
	 */
	private boolean tryToMove(Shape newPiece, int dX, int dY)
	{
		// Check if the x,y of the new piece collide
		// with the current top of dropped pieces
	    for (int i = 0; i < 4; ++i) {
	        int x = dX + newPiece.getX(i);
	        int y = dY - newPiece.getY(i);
	        // if goes off the limits
	        if (x < 0 || x >= this.boardWid || y < 0 || y >= this.boardHei)
	            return false;
	        // if not a empty space
	        if (getShapeHere(x, y) != Shape.Pieces.NoShape)
	            return false;
	    }
	
	    // Not game over, so updates the piece
	    this.currentPiece = newPiece;
	    this.posX = dX;
	    this.posY = dY;
	    // from graphics 
	    repaint();
	    return true;
	}
	
	
	/*
	 * The piece gets a new random shape. 
	 * If we cannot move to the initial x,y, the game is over.
	 */
	private void newPiece()
	{
            if (this.currentPiece.getShape() == Shape.Pieces.NoShape ||
                this.previewPiece.getShape() == Shape.Pieces.NoShape) {
                // get a random new piece
                this.currentPiece.setRandomShape();
                this.previewPiece.setRandomShape();
            } else {
                Shape.setRandomShape(currentPiece, previewPiece);
            }
            // set it to start in the initial position
	    this.posX = this.boardWid / 2 + 1;
	    this.posY = this.boardHei - 1 + this.currentPiece.minY();
	
	    // if it cannot start, because there are pieces there,
	    // this is game over.
	    if (!tryToMove(this.currentPiece, this.posX, this.posY)) {
	        timer.stop();
	        isStarted = false;
	        statusbar.setText("Game over!");
	    }
	}
	
	
	/*
	 * When the piece reach the bottom it is saved in an array.
	 * (the board holds all squares of the pieces that has fall). 
	 * 
	 * Every time the piece reaches the bottom it checks if lines
	 * should be removed.
	 */
	private void pieceDropped()
	{
	    for (int i = 0; i < 4; ++i) {
	    	// get the values of the dropped piece
	        int x = this.posX + this.currentPiece.getX(i);
	        int y = this.posY - this.currentPiece.getY(i);
	        // the board directions is given in one-dim array,
	        // so this calculates the position
	        this.board[(y * this.boardWid) + x] = this.currentPiece.getShape();
	    }
	
	    deleteLines();
	    newPiece();
	}
	
	
	/* 
	 * This method moves the piece one line down
	 */
	private void moveDownOneLine()
	{
	   if (!tryToMove(this.currentPiece, this.posX, this.posY - 1))
		   // check if it is the end
	       pieceDropped();
	}
	
	
	/*
	 * Move the piece directly to the bottom
	 */
	private void dropPieceDown()
	{
	    int newY = this.posY;
	    while (newY > 0) {
	        if (!tryToMove(this.currentPiece, this.posX, newY - 1))
	            break;
	        --newY;
	    }
	    pieceDropped();
	}
	
	
	
	/*
	 * These methods set the sizes/shapes of the squares
	 * It implements methods from java's graphic class
	 */
	int squareWidth() 
	{ 
		return (int) getSize().getWidth() / this.boardWid; 
	}
	
	int squareHeight() 
	{ 
		return (int) getSize().getHeight() / this.boardHei; 
	}
	
	
	/*
	 * Get the information of how the board is filled at the moment
	 */
	Shape.Pieces getShapeHere(int x, int y) 
	{ 
		return this.board[(y * this.boardWid) + x]; 
	}
	

	
	/* 
	 * This is where we draw things.
	 * First, it paints all the shapes that have been dropped.
	 * Second, it paints the falling piece.
	 */
	public void paint(Graphics g)
	{ 
	    // we are going to use the methods from the superclass
		super.paint(g);
	
	    Dimension size = getSize();
	    //System.out.println(size);
	    
	    
	    int boardTop = (int) size.getHeight() - this.boardHei * squareHeight();
	
	    // paint already dropped
	    for (int i = 0; i < this.boardHei; ++i) {
	        for (int j = 0; j < this.boardWid; ++j) {
	        	Shape.Pieces shape = getShapeHere(j, this.boardHei - i - 1);
	        	// if not filled with 0s, paint it
	            if (shape != Shape.Pieces.NoShape)
	            	paintColors(g, 0 + j * squareWidth(), boardTop + i * squareHeight(), shape);
	        }
	    }
	    
	    // paint falling piece
	    if (currentPiece.getShape() != Shape.Pieces.NoShape) {
	        for (int i = 0; i < 4; ++i) {
	            int x = posX + currentPiece.getX(i);
	            int y = posY - currentPiece.getY(i);
	            paintColors(g, 0 + x * squareWidth(),
	                       boardTop + (this.boardHei - y - 1) * squareHeight(),
	                       this.currentPiece.getShape());
	        }
	    }
	}
	
	

	/*
	 * Every tetris piece has four squares, this method
	 * paint these squares
	 */
	private void paintColors(Graphics g, int x, int y, Shape.Pieces pieceHere)
	{
		//  new Color(red, green, blue)
	    Color colors[] = { new Color(0,0,0), new Color(0,20,0), 
	        new Color(10,40,0), new Color(10,60,0), 
	        new Color(10,90,0), new Color(10,120,0), 
	        new Color(10,150,0), new Color(10,180,0)
	    };
	    
	    // for each of the enum pieces, 
	    // uses the methods from Graphics
	    Color color = colors[pieceHere.ordinal()];
	    g.setColor(color);
	    g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);
	
	}
}





    
