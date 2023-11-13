package parsers;

import models.Candies;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXDocumentParser {
    public Candies parseXml(String xmlFilePath) throws SAXException,IOException, ParserConfigurationException{
        File xmlFile = new File(xmlFilePath);

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();

        CandyHandler candyHandler = new CandyHandler();
        saxParser.parse(xmlFile,candyHandler);

        return new Candies(candyHandler.getCandiesList());

    }
}
