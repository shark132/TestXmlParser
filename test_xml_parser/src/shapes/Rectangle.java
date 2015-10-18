package shapes;

import java.util.ArrayList;

/**
 * Created by shark on 15.10.2015.
 */
public class Rectangle extends Shape {

    public Rectangle(ArrayList<Float> list, String color) {
        super(list,color);
    }

    /**
     * Метод вычисляет площадь прямоугольника по двум сторонам
     * @return square
     */
    public float calculateSquare() {
        square = 1;
        for (int i = 0; i < getSidesCount(Rectangle.class); i++) {
            square *= sides[i];
        }
        return square;
    }
}
