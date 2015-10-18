package xmlParser;

/**
 * Created by shark on 18.10.2015.
 */

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import shapes.Circle;
import shapes.Shape;
import shapes.Square;

import java.util.ArrayList;

public class XmlSaxParser extends DefaultHandler {

    private String figureName;
    private boolean figureElement = false;
    private int isSide;
    private int isDiameter;
    private double counter = 1;
    private String color;
    private ArrayList<Float> sidesList = new ArrayList<Float>();

    private int NOTVISITED = 0;
    private int VISITED = 1;
    private int LEFTOUT = 2;


    @Override
    public void startDocument() throws SAXException {
       // System.out.println("Start parse XML...");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {

        if ("square".equals(qName)) {
            figureName = qName;
            figureElement = true;
            sidesList.clear();
        }
        else if ("circle".equals(qName)) {
            figureName = qName;
            figureElement = true;
            sidesList.clear();
        }
        else if ("triangle".equals(qName)) {
            figureName = qName;
            figureElement = true;
            sidesList.clear();
        }
        else if ("rectangle".equals(qName)) {
            figureName = qName;
            figureElement = true;
            sidesList.clear();
        }
        else if ("side".equals(qName)) {
            isSide = VISITED;
        }
        else if ("diameter".equals(qName)) {
           // isDiameter = VISITED;
            isSide = VISITED;
        }
        else if ("color".equals(qName)) {
            color = qName;
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {

       if (figureElement && isSide == LEFTOUT || isDiameter == LEFTOUT) {
            //System.out.print(counter + " "+ figureName +" : ");
           System.out.format("%.0f: ", counter);
            XMLValidation.createShape(sidesList, figureName, color);
           figureElement = false;
           isDiameter = NOTVISITED;
           isSide = NOTVISITED;
           color = "";
           figureName = "";
            counter++;
        }

        else if (isSide == VISITED && figureElement) {
            isSide = LEFTOUT;
        }

        else if (isDiameter == VISITED && figureElement) {
            isDiameter = LEFTOUT;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (figureElement){
            if (isSide == VISITED) {
                //System.out.println(new String(ch, start, length) + " element");
                float side = Float.parseFloat(new String(ch,start,length));
                sidesList.add(side);
            }
        }

        if ("color".equals(color)) {
            color = new String(ch,start,length);
        }
    }
}


