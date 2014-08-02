/* The Shape Class for the Awesome Tetris
 *  
 *  Purpose of this class: 
 *  	-> create the pieces's seven shape types
 *  	-> The way we define the pieces is by their coordinates
 *  				inside a coordinates Table.
 *
 * @author Mari & Samer
 * @version 1.0 Jul/14
 * 
 * TO DO:
 * 
 */

package tetris;

import java.util.Random;
import java.lang.Math;


public class Shape {

	/*
	 * Here we define all the possible piece shapes.
	 * 
	 * An enum type is a data type that enables 
	 * for a variable to be a set of predefined constants. 
	 * 
	 * Unlike in C, enum is a full class
         *
         * In a specific ordering in order to get the same
         * distribution for random numbers as the Game Boy game. See
         * setRandomShape(Shape,Shape) for details.
	 * 
	 */
    enum Pieces { 		NoShape,
                                LShape,
                                MirrorLShape,
                                LineShape,
                                SquareShape,
                                ZShape, 
                                SShape, 
                                TShape };

    private Pieces pieceShape;
    private int coords[][];
    private int[][][] coordsTable;

    

    public Shape() {

    	/*
    	 * Define the dimensions for coords
         * Since all the pieces have a limit in 4 spaces in X
         * and 2 in Y, it fills the piece's coordinates properly.
         */
        this.coords = new int[4][2];
        
        
        /*
         * Here we define the coordinate table that includes all the possible
         * shapes for each piece. It gets a value x and select from the coordsTable
         * 
         * Z: { -1, 1 },  { 0, 1 },   { 0, 0 },  { 0, 1 }
         *                       |
         *                       2
         *                       |
         *                    X  X
         *                       |
         *               --2--1--X--X--2----
         *                       |
         *                       1
         *                       |
         *                       2
         *                       
         *                       
         *  S: { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 }                                   |
         *                       2
         *                       |
         *                       1  X
         *                       |
         *               --2--1--X--X--2----
         *                       |
         *                       X
         *                       |
         *                       2
         * 
         * Line: { 0, -2 },  { 0, -1 },   { 0, 0 },   { 0, 1 }
         *                       2
         *                       |
         *                       X  
         *                       |
         *               --2--1--X--1--2----
         *                       |
         *                       X
         *                       |
         *                       X
         * 
         * T: { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, -1 } }
         *                       2
         *                       |
         *                       1  
         *                       |
         *               --2--X--X--X--2----
         *                       |
         *                       X
         *                       |
         *                       2
         *  
         * Square: { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } }
         *                       2
         *                       |
         *                       X  X
         *                       |
         *               --2--1--X--X--2----
         *                       |
         *                       1
         *                       |
         *                       2
         * 
         *  L: { { 0, 1 }, { 0, 0 },  { 0, -1 },   { 1, -1 } }
         *                       2
         *                       |
         *                       X   
         *                       |
         *               --2--1--X--1--2----
         *                       |
         *                       X  X
         *                       |
         *                       2
         *  
         *  Mirror L: { { 0, 1 }, { 0, 0 },  { 0, -1 },   { -1, -1 } }  
         *                       2
         *                       |
         *                       X  
         *                       |
         *               --2--1--X--1--2----
         *                       |
         *                    X  X
         *                       |
         *                       2
         * 
         */
        this.coordsTable = new int[][][] {
                { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } }, // no shape
                { { 0, 1 },   { 0, 0 },  { 0, -1 },   { 1, -1 } },  // L
                { { 0, 1 },   { 0, 0 },  { 0, -1 },   { -1, -1 }},  // mirror L
                { { 0, -2 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }, // line
                { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } }, // square
                { { -1, 0 },  { 0, 0 },   { 0, 1 },   { 1, 1 } }, // z
                { { -1, 1 },  { 0, 1 },   { 0, 0 },   { 1, 0 } }, // s
                { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, -1 } } // T
            };
        
    	// NoShape has no coordinates (initialize)
        this.setShape(Pieces.NoShape);

    }

    

    /*
     * Set Methods: coordinates and shape
     */
    
    public void setShape(Pieces shape) {      
        /*
         * The java.lang.Enum.ordinal() method returns the ordinal 
         * of this enumeration constant (its position in its enum declaration, 
         * where the initial constant is assigned an ordinal of zero).
         */
        for (int c = 0; c < 4 ; c++) {
            for (int xy = 0; xy < 2; ++xy) {
            	// i for each of the four {}, j for the x,y inside {}
                this.coords[c][xy] = this.coordsTable[shape.ordinal()][c][xy];
                //System.out.println(coords[j]); --> objects, doesnt work
            }
        }
        
        // We have a shape!!!!
        this.pieceShape = shape;

    }

    private void setX(int idx, int x) 
    { 
    	this.coords[idx][0] = x; 
    }
    
    private void setY(int idx, int y) 
    { 
    	this.coords[idx][1] = y; 
    }
    
    
    
    /*
     * Get methods: coordinates and shapes
     */
    
    public Pieces getShape()  
    { 
    	return this.pieceShape; 
    }
    
    public int getX(int idx) 
    { 
    	return this.coords[idx][0]; 
    }
    
    public int getY(int idx) 
    { 
    	return this.coords[idx][1]; 
    }
    
    
    
    /*
     *  We use a pseudo-random function to select the first piece
     */
    public void setRandomShape()
    {
    	/*
    	 * We create a pseudo-random object and from this we generate
    	 * num as a int from 1 to 7 (possible shapes).
    	 * 
    	 * An instance of random class is used to generate a stream 
    	 * of pseudorandom numbers. The class uses a 48-bit seed.
    	 * 
    	 * nextInt() returns the next pseudorandom, uniformly distributed 
    	 * int value from this random number generator's sequence.
    	 */
        Random ran = new Random();
        //System.out.println("Testing random... " + ran);
        int num = Math.abs(ran.nextInt()) % 7 + 1;
        //System.out.println(ran.nextInt());
        //System.out.println( Math.abs(ran.nextInt()) % 7 + 1);
        //System.out.println(num);
        
        // instance a type pieces and set shape to it
        Pieces[] valueHere = Pieces.values(); 
        //System.out.println(valueHere); // whats Ltetris.Shape$Pieces?
        setShape(valueHere[num]);
        
    }
    
    /*
     *  Uses the randomizer algorithm from the Tetris Game Boy game
     *  to pick the next piece. Modifies both of its arguments.
     *
     *  The randomizer takes the current shape and the preview shape,
     *  and picks it if the bitwise OR of the current shape, the
     *  preview shape, and the pick do not equal current shape. If
     *  this OR'd result is equal to the current num, pick a new
     *  number and try again. If the OR'd result is equal again, 
     *  pick a random number and simply take that.
     *
     *  When a shape is "picked", "curr" is set to "preview" and
     *  "preview" is set to the picked shape.
     *
     *  http://harddrop.com/wiki/Tetris_(Game_Boy)#Randomizer
     * 
     *  "curr" is the locking piece from the algorithm.
     *
     *  TODO: This is kind of confusing because
     *  setRandomShape(Shape, Shape) is a static function, where
     *  setRandomShape() is a member function. We should probably
     *  get these to follow a consistent API.
     */
    public static void setRandomShape(Shape curr, Shape preview)
    {
        Random ran = new Random();
        // To stay true to the Game Boy randomizer, we offset all of
        // our int values by -1, and then add one before we set the
        // shapes at the end.
        int currNum = curr.getShape().ordinal() - 1;
        int previewNum = preview.getShape().ordinal() - 1;
        int pick = 0;
        for (int i = 0; i < 2; i++) {
            pick = Math.abs(ran.nextInt()) % 7;
            if (currNum != (currNum | previewNum | pick)) {
                break;
            }
        }
        Pieces[] valueHere = Pieces.values();
        // Offset the indexes by +1 to compensate for our -1 offset
        // above.
        curr.setShape(valueHere[previewNum+1]);
        preview.setShape(valueHere[pick+1]);
    }

    
    /*
     * These are the minimum x, y coordinates for the piece.
     * Without this, the piece would go beyond the wall and
     * would not stop at the bottom
     */

    // TODO: this method is never called.
    // He is lonely and wasting resources.
    public int minX()
    {
      int lim = this.coords[0][0]; // [0] is all the x coordinates
      for (int i = 0; i < 4; i++) {
    	  lim = Math.min(lim, this.coords[i][0]); // [0] is all the x coordinates
      }
      return lim;
    }


    public int minY() 
    {
      int lim = this.coords[0][1];	// [1] is all the y coordinates
      for (int i = 0; i < 4; i++) {
    	  lim = Math.min(lim, this.coords[i][1]); // [1] is all the y coordinates
      }
      return lim;
    }

    
    /*
     * These methods to rotate the pieces.
     * 
     *  Suggestion from Samer:
     *  Every time you move the piece, you are actually creating
     *  a new piece in the new coordinates
     *  
     *  Rotating is the same as inverting all X to be Y and vice versa
     *  
     *  For example, if we have a L:
     *    L: { { 0, 1 }, { 0, 0 },  { 0, -1 },   { 1, -1 } }
     *                       2
     *                       |
     *                       X   				X
     *                       |
     *               --2--1--X--1--2----  or    X  
     *                       |		
     *                       X  X				X  X
     *                       |
     *                       2
     *                       
     *  In coord array [i][j], i defines the four {x,y} and j the values for x or y   
     *  If we rotate once, for each idx from 1 to 4:
     *     setX(idx, - x) --> coords[idx][0] = x
     *         where x is getY(idx) --> coords[idx][1]
     *  (and same for setY but without -).
     *   
     *  So the new coordinates become:
     *         { -1, 0 }, { 0, 0 },  { 1, 0 },   { 1, 1 }
     *                       2
     *                       |
     *                       1  X   
     *                       |						  X
     *               --2--X--X--X--2----   or   X  X  X 
     *                       |
     *                       1  
     *                       |
     *                       2               
     * 
     *  Rotating once more:
     *         { 0, -1 }, { 0, 0 },  { 0, 1 },   { -1, 1 }
     *                       2
     *                       |
     *                    X  X   
     *                       |				   X  X
     *               --2--1--X--1--2----   or     X 
     *                       |					  X	
     *                       X  
     *                       |
     *                       2    
     *                           
     *  Rotating once more:
     *         { 1, 0 }, { 0, 0 },  { -1, 0 },   { -1, -1 }
     *                       2
     *                       |
     *                       1   
     *                       |				   
     *               --2--X--X--X--2----   or     X X X
     *                       |					  X	
     *                    X  1  
     *                       |
     *                       2  
     *                       
     *  Rotating once more we return to the origin:
     *         { 0, 1 }, { 0, 0 },  { 0, -1 },   { 1, -1 }
     *                       2
     *                       |
     *                       X   
     *                       |				      X
     *               --2--1--X--1--2----   or     X  
     *                       |					  X X    	
     *                       X  X
     *                       |
     *                       2    
     *                       
     *   what would happen if we did not inverted with -?
     *   
     *  Using the same example for L, we would have:
     *         { 1, 0 }, { 0, 0 },  { -1, 0 },   { -1, 1 }
     *                       2
     *                       |
     *                    X  1   
     *                       |				     X 
     *               --2--X--X--X--2----   or    X X X   
     *                       |					     	
     *                       1  
     *                       |
     *                       2   
     *                       
     *    We would invert the PARITY!!!!
     * 
     *  To reverse in the opposite direction, the transformation is
     *  (x, y) -> (y, -x).
     */

    // TODO: I'm not sure if it makes sense to have reverse be a
    // boolean.
    public Shape rotate(boolean reverse) 
    {
    	// square pieces don't rotate
        if (this.pieceShape == Pieces.SquareShape){
            return this;
        }

        // create new piece
        Shape result = new Shape();
        result.pieceShape = this.pieceShape;

        // set new coordinates to this new piece
        for (int idx = 0; idx < 4; ++idx) {
            if (reverse) {
                result.setX(idx, this.getY(idx));
                result.setY(idx, -this.getX(idx));
            } else {
                result.setX(idx, -this.getY(idx));
                result.setY(idx, this.getX(idx)); 
            }
        }
        
        return result;
    }
}
