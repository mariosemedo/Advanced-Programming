package labs.lab1.shapes;

import java.util.ArrayList;
import java.util.List;

public abstract class Shape {
	public double x;
	public double y;

	public abstract double area();

	public abstract double perimeter();

	public String toString() {
		return "Shape[x=" + x + ",y=" + y + ",area=" + area() + ",perimeter="
				+ perimeter() + "]";
	}

	// test code
	public static void main(String[] args) {
		testShapes();
	}

	public static void testShapes() {
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(new Circle(100, 200, 50));
		shapes.add(new Circle(210, 100, 50));
		shapes.add(new Rectangle(200, 100));

		Shape rect = new Rectangle(200,100);

		System.out.println(rect.perimeter());
		shapes.forEach(System.out::println); 
	}
}
