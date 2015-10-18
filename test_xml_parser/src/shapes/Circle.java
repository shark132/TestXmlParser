package shapes;

import java.util.ArrayList;

/**
 * Created by shark on 15.10.2015.
 */
public class Circle extends Shape {

    private float diameter;
    public Circle(ArrayList<Float> list, String color) {
        super(list,color);
        diameter = sides[0];
    }

    /**
     * Метод вычисляет площадь круга по диаметру
     * @return square
     */
    public float calculateSquare() {
        square = (float)(Math.PI * Math.pow(diameter, 2)) / 8 ;
        return square;
    }

}
