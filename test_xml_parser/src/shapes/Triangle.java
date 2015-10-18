package shapes;

import java.util.ArrayList;

/**
 * Created by shark on 13.10.2015.
 */
public class Triangle extends Shape {

    private static int sidesCount = 3;

    public Triangle(ArrayList<Float> list, String color) {
        super(list, color);
    }

    public float calculateSquare() {
        float squaredSquare;
        float semiPerimeter = 0;

        for (int i = 0; i < getSidesCount(Triangle.class); i++) {
            semiPerimeter += sides[i];
        }

        semiPerimeter = semiPerimeter / 2;
        squaredSquare = semiPerimeter; // ����� ��������� ��� ���������� ������� ������������

        for (int i = 0; i < getSidesCount(); i++) {
            // p*(p -a)*(p-b)*(p-c), ��� p - ������������, a,b,c - �������
            squaredSquare *= (semiPerimeter - sides[i]);
        }

        square = (float) Math.sqrt(squaredSquare);

        return square;
    }

    /**
     * ����� ���������� ���������� ������ ������������
     * @return square
     */
    public static int getSidesCount() {
        return sidesCount;
    }

}
