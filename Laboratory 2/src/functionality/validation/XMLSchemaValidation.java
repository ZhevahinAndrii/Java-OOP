package validation;

import org.xml.sax.SAXException;

import javax.security.sasl.SaslException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.File;

public class XMLSchemaValidation {
    public static boolean apply(String xmlFilePath,String xsdFilePath){
        try{
            validateXML(xmlFilePath,xsdFilePath);
        }
        catch(IOException| SAXException e){
            throw new IllegalStateException("Invalid XML",e);
        }
        return true;
    }
    private static void validateXML(String xmlFilePath,String xsdFilePath) throws SAXException,IOException{
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(xsdFilePath));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new File(xmlFilePath)));
    }
}
