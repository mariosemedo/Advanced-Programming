package labs.lab1.shapes;

import java.awt.*;

/**
 * Created by mariooliveira on 11/10/2017.
 */
public class DrawableRectangle extends Rectangle implements Drawable{
    private Color c;

    DrawableRectangle(double x, double y, Color c) {
        super(x, y);this.c=c;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(c);
        g.fillRect((int)x+100,(int)y+100, (int)x, (int)y);
    }
}
