package labs.lab1.shapes;

/**
 * Created by mariooliveira on 11/10/2017.
 */
public class Rectangle extends Shape {


    Rectangle(double x, double y){
        this.x = x; this.y=y;
    }

    public double area() {
        return x*y;
    }


    public double perimeter() {
        return 2*(x+y);
    }

    public String toString() {
        return "Rectangle("+x+","+y+")";
    }
}
