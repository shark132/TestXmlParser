package shapes;

import java.util.ArrayList;

/**
 * Created by shark on 15.10.2015.
 */
public class Square extends Shape {

    public Square(ArrayList<Float> list, String color){
        super(list,color);
    }

    /**
     * Метод вычисляет площадь квадрата по одной заданной стороне
     * @return square
     */
    protected float calculateSquare() {
        square = sides[0] * sides[0];

        return square;
    }
}
