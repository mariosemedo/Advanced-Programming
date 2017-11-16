package labs.lab1.shapes;

import java.awt.Color;
import java.awt.Graphics;


public class DrawableCircle extends Circle implements Drawable {
  Color c;

  public DrawableCircle(double x, double y, double r, Color c) {
    super(x,y,r);
    this.c = c;
  }

  public void draw(Graphics g) {
    g.setColor(c);
    g.fillOval( (int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r) );
  }
}