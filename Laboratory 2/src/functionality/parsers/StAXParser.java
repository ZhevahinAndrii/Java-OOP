package parsers;

import models.Candies;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StAXParser {
    public Candies parseXml(String xmlFilePath) throws XMLStreamException, FileNotFoundException {
        File xmlFile = new File(xmlFilePath);

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        CandyHandler candyHandler = new CandyHandler();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(xmlFile));

        while (reader.hasNext()) {
            XMLEvent nextXMLEvent = reader.nextEvent();
            if (nextXMLEvent.isStartElement()) {
                StartElement startElement = nextXMLEvent.asStartElement();

                String name = startElement.getName().getLocalPart();
                Map<String, String> attributeMap = new HashMap<>();
                Iterator<Attribute> attributes = startElement.getAttributes();
                while (attributes.hasNext()) {
                    Attribute attribute = attributes.next();
                    attributeMap.put(attribute.getName().getLocalPart(), attribute.getValue());
                }

                nextXMLEvent = reader.nextEvent();
                if (nextXMLEvent.isCharacters()) {
                    candyHandler.setField(name, nextXMLEvent.asCharacters().getData(), attributeMap);
                }
                else if(nextXMLEvent.isEndElement()){
                    candyHandler.setField(name,"",attributeMap);
                }
            }
        }
            return new Candies(candyHandler.getCandiesList());
    }
}
