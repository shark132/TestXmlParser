package xmlParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;
import shapes.*;

public class XMLValidation {

    public static void main(String[] args) {
        //String xsdPath = "test_xml_parser\\src\\xmlParser\\shapes.xsd";
        //String xmlPath = "C:\\Job\\test\\test.xml";
        String xsdPath = "shapes.xsd";
        String xmlPath = args[0];
        SAXParser parser;
        //Проверка файла .xml на валидность заданной схеме
        boolean validationSuccess = validateXMLSchema(xsdPath, xmlPath);

        if (validationSuccess) {
            //getFigures(xmlPath); // получение элементов из файла .xml

        }
        else {
            System.out.println("Error. Check your file name or destination");
        }


        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            parser = factory.newSAXParser();
            XmlSaxParser saxp = new XmlSaxParser();

            parser.parse(new File("C:\\Job\\test\\test.xml"), saxp);
        }
        catch (SAXException | ParserConfigurationException | IOException e) {
            System.out.print(e.getMessage());
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
     * Метод создает объеты(фигуры)
     * @return figure
     */
    public static Shape createShape(ArrayList<Float> list, String name, String color) {

        Shape figure = null;
        if ("square".equals(name)) {
            figure = new Square(list, color);
            figure.display();
        }
        else if ("rectangle".equals(name)) {
            figure = new Rectangle(list,color);
            figure.display();

        }
        else if ("triangle".equals(name)) {
            figure = new Triangle(list,color);
            figure.display();
        }

        //figureName == circle, согласно заданной схеме иных названий быть не может
        else {
            figure = new Circle(list,color);
            figure.display();
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