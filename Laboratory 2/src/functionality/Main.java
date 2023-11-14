import models.Candies;
import parsers.DOMParser;
import parsers.SAXDocumentParser;
import parsers.StAXParser;
import validation.XMLSchemaValidation;
public class Main {
    public static void main(String[] args) throws Exception{
        XMLSchemaValidation.apply("C:\\OOP Java\\Laboratory 2\\src\\resources\\xml\\data.xml","C:\\OOP Java\\Laboratory 2\\src\\resources\\xml\\schema.xsd");
        var domParser = new DOMParser();
        Candies candies =domParser.parseXml("C:\\OOP Java\\Laboratory 2\\src\\resources\\xml\\data.xml");
        System.out.println(candies);

        StAXParser stAXParser = new StAXParser();
        Candies candies2 = stAXParser.parseXml("C:\\OOP Java\\Laboratory 2\\src\\resources\\xml\\data.xml");
        System.out.println(candies2);

        SAXDocumentParser saxDocumentParser = new SAXDocumentParser();
        Candies candies3 =saxDocumentParser.parseXml("C:\\OOP Java\\Laboratory 2\\src\\resources\\xml\\data.xml");
        System.out.println(candies3);

    }
}
