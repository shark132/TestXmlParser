package xmlParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import shapes.*;

public class XMLValidation {

    public static void main(String[] args) {
        String xsdPath = "test_xml_parser\\src\\xmlParser\\shapes.xsd";
        String xmlPath = "C:\\Job\\test\\test.xml";

        //Проверка файла .xml на валидность заданной схеме
        boolean validationSuccess = validateXMLSchema(xsdPath, xmlPath);

        if (validationSuccess) {
            getFigures(xmlPath); // получение элементов из файла .xml
        }
        else {
            System.out.println("Error. Check your file name or destination");
        }


    }

    /**
     * Метод проверяет заданный xml файл на соотвествие схеме .xsd.
     * @return true or false
     */
    public static boolean validateXMLSchema(String xsdPath, String xmlPath){

        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        }
        catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Метод получает фигуры из xml файла
     * @return void
     */
    public static void getFigures(String path) {
        File xmlFile = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document = null;

        try {
           builder = factory.newDocumentBuilder();
           document = builder.parse(xmlFile);
           document.normalize();
        }
        catch (javax.xml.parsers.ParserConfigurationException | org.xml.sax.SAXException |IOException e){
            System.out.println(e.getMessage());
        }

        Element root = document.getDocumentElement(); // получение корневого элемента
        NodeList nodeList = root.getChildNodes(); // список дочерних узлов

        int figuresNumber = nodeList.getLength();
        int figureCounter = 0;

        for (int i = 0; i < figuresNumber; i++) {
            Node node = nodeList.item(i); // получение текущего узла
            String figureName = node.getNodeName();

            if (node.getNodeType() == Node.ELEMENT_NODE) { //избавление от текстовых узлов(пробелы и тд).
                figureCounter++;
                Shape figure = createShape(figureName, node);

                // Вывод на экран в виде <i>: <color> - <area>\n
                System.out.print(figureCounter + ": ");
                figure.display();
            }
        }
    }

    /**
     * Метод создает объеты(фигуры)
     * @return figure
     */
    public static Shape createShape(String name, Node node) {

        Shape figure = null;
        if ("square".equals(name)) {
            int sideCount = Square.getSidesCount(Square.class);
            figure = new Square(getSides(sideCount,node), getColor(node));


        }
        else if ("rectangle".equals(name)) {
            int sideCount = Rectangle.getSidesCount(Rectangle.class);
            figure = new Rectangle(getSides(sideCount,node),getColor(node));

        }
        else if ("triangle".equals(name)) {
            int sideCount = Triangle.getSidesCount(Triangle.class);
            figure = new Triangle(getSides(sideCount,node),getColor(node));
        }

        //figureName == circle, согласно заданной схеме иных названий быть не может
        else {
            int sideCount = Circle.getSidesCount(Circle.class);
            figure = new Circle(getSides(sideCount,node),getColor(node));
        }

        return figure;
    }

    /**
     * Метод получает длины сторон текущей фигуры
     * @return list
     */
    public static ArrayList<Float> getSides(int sidesCount, Node node) {
        ArrayList<Float> list = new ArrayList<Float>();
        Element element = (Element) node;
        String strSide;

        for (int i = 0; i < sidesCount; i++) {

            if (!"circle".equals(node.getNodeName())) {
                strSide = element.getElementsByTagName("side").item(i).getTextContent();
            }
            else {
                strSide = element.getElementsByTagName("diameter").item(i).getTextContent();
            }

            float side = Float.parseFloat(strSide);
            list.add(side);
        }
        return list ;
    }

    /**
     * Метод получает цвет фигуры из xml файла
     * @return void
     */
    public static String getColor(Node node) {
        Element element = (Element) node;
        String color = element.getElementsByTagName("color").item(0).getTextContent();

        return color ;
    }
}