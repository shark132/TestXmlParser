package shapes;

import java.util.ArrayList;

/**
 * Created by shark on 15.10.2015.
 */
public abstract class Shape {

    protected float square;
    protected int sidesCount; //  оличество сторон у фигуры
    protected float[] sides; // ћассив длин сторон фигуры (дл€ окружности - диаметр)
    protected String color;

    protected abstract float calculateSquare(); // ¬ычисление площади фигуры

    public String getColor() {
        return color;
    }

    /**
     * ћетод возвращает площадь фигуры
     * @return square
     */
    public float getSquare() {
        if (square == 0){
            square = calculateSquare();
        }

        return square;
    }

    public Shape (ArrayList<Float> list, String color) {
        super();

        if (list == null || color == null) {
            throw new NullPointerException();
        }
        else if (list.isEmpty() || color.isEmpty()){
            throw new IllegalArgumentException();
        }

        this.color = color;

        sides = new float[list.size()];
        sidesCount = sides.length;

        for (int i = 0; i < sidesCount; i++) {
            sides[i] = list.get(i);
        }
    }

    /**
     * ћетод возвращает количество сторон у фигуры
     * ” окружности за сторону принимать диаметр
     * @return sidesCount
     */
    public static int getSidesCount(Class<?> figure) {
        if (figure.equals(Triangle.class)) {
            return 3;
        }
        else if (figure.equals(Square.class)) {
            return 1;
        }
        else if (figure.equals(Rectangle.class)) {
            return 2;
        }
        if (figure.equals(Circle.class)) {
            return 1;
        }
        return 1;
    }

    public void display() {
        System.out.println(getColor() + " - " + getSquare());
    }
}


