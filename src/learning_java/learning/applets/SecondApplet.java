import java.awt.Graphics;
import java.awt.Color;

public class SecondApplet extends java.applet.Applet
{

    public void paint(Graphics g)
    {
        int width = 200;
        int height = 200;
        g.drawRect(0, 0, width, height);
        g.drawLine(0, 0, width, height);
        g.drawLine(width, 0, 0, height);
    }
}
