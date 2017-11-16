package labs.lab1;

import java.util.function.DoubleUnaryOperator;

/**
 * Created by mariooliveira on 11/10/2017.
 */
public class Maths {

    public static double integrate(DoubleUnaryOperator u, double a, double b, int n){

        double h = (b - a)/n;
        double x = a + h/2;
        double sum= 0;
        for(int i = 0; i < n; i++){
            sum += u.applyAsDouble(x);
            x+= h;
        }

        return h * sum;
    }

    public static void main(String[] args) {
        DoubleUnaryOperator square = (x) -> x*x;
        System.out.println(integrate(square,0.0,1.0,1));
        System.out.println(integrate(square,0.0,1.0,2));
        System.out.println(integrate(square,0.0,1.0,1000));

    }
}
