package labs.lab1.shapes;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;

// USAGE: see example in main-method below

public class ShapeFrame extends JFrame {

	List<Drawable> shapes;

	JPanel jPanel = new JPanel() {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (Drawable shape : shapes)
				shape.draw(g);
		}
	};

	public ShapeFrame(int w, int h, List<Drawable> shapes) {
		this.shapes = shapes;
		this.setSize(w, h);
		this.setTitle("Shapes");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.add(jPanel);
	}

	// test code
	public static void main(String[] args) {
		List<Drawable> shapes = new ArrayList<Drawable>();
		shapes.add(new DrawableCircle(100, 200, 50, Color.RED));
		shapes.add(new DrawableCircle(200, 100, 50, Color.BLUE));
		shapes.add(new DrawableRectangle(200,200,Color.black));
		ShapeFrame sf = new ShapeFrame(600, 600, shapes);
		sf.setVisible(true);
	}

}
