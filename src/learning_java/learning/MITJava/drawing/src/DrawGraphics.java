import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class DrawGraphics {
    //  BouncingBox box;

    ArrayList<BouncingBox> alpha = new ArrayList<BouncingBox>();

    /**
     * Initializes this class for drawing.
     */
    public DrawGraphics() {

        alpha.add(new BouncingBox(250, 50, Color.red));
        alpha.add(new BouncingBox(200, 200, Color.green));
        alpha.add(new BouncingBox(100, 100, Color.PINK));
        alpha.add(new BouncingBox(150, 50, Color.blue));
        
        alpha.get(0).setMovementVector(1, 1);
        alpha.get(1).setMovementVector(0, 1);
        alpha.get(2).setMovementVector(1, 0);
        alpha.get(3).setMovementVector(0, 1);       

        //       box = new BouncingBox(200, 50, Color.RED);
        //       box.setMovementVector(1, 0);
    }

    /**
     * Draw the contents of the window on surface. Called 20 times per second.
     */
    public void draw(Graphics surface) {

        for (int i = 0; i < alpha.size(); i++) {
            alpha.get(i).draw(surface);
        }

//        box.draw(surface);

    }
}