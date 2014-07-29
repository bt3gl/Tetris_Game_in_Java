/* The Tetris Class for the Awesome Tetris
 * 
 *  -> We use a Timer class to create a game cycle
 *  -> The pieces are drawn
 *  -> The shapes move on squares (not pixels0
 *  -> a board is a list of numbers
 *  -> the score is the number of lines
 *  
 *  Purpose of this class: 
 *  	-> set up the game
 * 		-> create a board to play
 * 		-> create statusbar
 *
 * @author Mari & Samer
 * @version 1.0 Jul/14
 * 
 * TO DO:
 *  --> implement acceleration/ different speeds?
 *  --> implement board size options
 * 
 */



package tetris;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import tetris.Tetris.STATE;


/* 
 * JFrame  is an extended version of java.awt.Frame that adds support for the 
 * JFC/Swing component architecture. Like all other JFC/Swing top-level containers, 
 * a JFrame contains a JRootPane as its only child. The content pane provided 
 * by the root pane should, as a rule, contain all the non-menu components 
 * displayed by the JFrame. This is different from the AWT Frame case. 
 * 
 * A Frame is a top-level window with a title and a border.
 * The size of the frame includes any area designated for the border. 
 * The dimensions of the border area may be obtained using the getInsets method.
 * The default layout for a frame is BorderLayout.
 */

public class Tetris extends JFrame {

	// Dimensions of the Board
	final int xBoard = 350;
    final int yBoard = 600;
	
    // Text in title
    final String titleBoard = "Awesome Tetris!";
    
    // Constructor for the texts in the board
    JLabel statusbar;
    JLabel instructbar;

    
 // Create states for menu
    public static enum STATE{
      MENU,
      GAME,
      AI
    };

    // create state with the modifier STATE to check which
    // state we are in
    private Menu menu ;

    public Tetris() {
    	
        setSize(xBoard, yBoard);
        setTitle(titleBoard);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
        setState( STATE.MENU);
       
        
        
    }

    
   // Set points every time the piece drops
   public JLabel getStatusBar() {
       return statusbar;
   }

   
   /*
    *  Instance to start new game
    *  Remember: methods from JFrame
    */
  
   public static void main(String[] args) {
        Tetris game = new Tetris();
        /*
         * Sets the location of the window relative to the 
         * specified component. If the component is null, 
         * the window is placed in the center of the screen. 
         */
        game.setLocationRelativeTo(null);
        // Shows or hides this component.
        game.setVisible(true);

   }


public void setState(STATE game) {
	Container c = getContentPane();
	//c.removeAll();
    if(game == STATE.MENU){
    	Menu menu = new Menu(this);
        c.add(menu);
    }
	
	if(game == STATE.GAME){
        
    	// starts with 0 points
        statusbar = new JLabel("   0");
        statusbar.setFont (statusbar.getFont ().deriveFont (30.0f));
        c.add(statusbar, BorderLayout.NORTH);
        
        // instructions
        instructbar = new JLabel("s:stop, f:faster, "
        		+ "space:drop, down:rotate, q:quit");
        instructbar.setFont (instructbar.getFont ().deriveFont (10.0f));
        c.add(instructbar, BorderLayout.SOUTH);
        
        // constructor for our class board
        Board board = new Board(this);
        c.add(board);
        
        // HERE WE START THE GAME!!!!
        board.start();
	}
    if(game == STATE.AI){
    	// ADD L
    }
	
} 
}