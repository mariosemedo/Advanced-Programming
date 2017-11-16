package labs.lab1.shapes;

public class Circle extends Shape {
  public double r;

  public Circle(double x, double y, double r) {
    this.x = x;  this.y = y;  this.r = r;
  }

  public Circle() {
	}

	public double area() {
    return Math.PI * r * r;
  }

  public double perimeter() {
    return Math.PI * 2 * r;
  }

  public String toString(){
  	return "Circle(" + x + "," + y + "," + r + ")"; 
  }

}