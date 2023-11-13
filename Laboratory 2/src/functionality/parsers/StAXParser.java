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

public class StAXParser {
    public Candies parseXml(String xmlFilePath) throws XMLStreamException, FileNotFoundException{
        File xmlFile = new File(xmlFilePath);

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        CandyHandler candyHandler = new CandyHandler();
        XMLEventReader reader  = xmlInputFactory.createXMLEventReader(new FileInputStream(xmlFile));

        while(reader.hasNext()){
            XMLEvent nextXMLEvent = reader.nextEvent();
            if(nextXMLEvent.isStartElement()){
                StartElement startElement = nextXMLEvent.asStartElement();
                nextXMLEvent = reader.nextEvent();
                String name = startElement.getName().getLocalPart();
                if(nextXMLEvent.isCharacters()){
                    var attributesList = new ArrayList<Attribute>();
                    var iter = startElement.getAttributes();
                    while(iter.hasNext()){
                        attributesList.add(iter.next());
                    }
                    var attributeMap = new HashMap<String,String>();

                    for(var attribute:attributesList){
                        attributeMap.put(attribute.getName().getLocalPart(),attribute.getValue());
                    }
                    candyHandler.setField(name,nextXMLEvent.asCharacters().getData(),attributeMap);
                }
            }
        }
        return new Candies(candyHandler.getCandiesList());
    }
}
