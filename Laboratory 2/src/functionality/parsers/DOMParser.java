package parsers;

import Constants.Strings;
import models.Candies;
import models.Candy;
import models.Ingredient;
import models.Value;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMParser {
    public Candies parseXml(String xmlFilePath) throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(xmlFilePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);
        NodeList candiesNodeList = document.getElementsByTagName(Strings.CANDY);
        List<Candy> candies = new ArrayList<>();
        for(int i=0;i<candiesNodeList.getLength();i++){
            Node node = candiesNodeList.item(i);
            if(node.getNodeType()!=Node.ELEMENT_NODE)
                continue;
            Element element = (Element) node;
            candies.add(createCandy(element));
        }
        return new Candies(candies);
    }
    private Candy createCandy(Element element){
        int id = Integer.parseInt(element.getAttribute(Strings.ID));
        String name = getByTag(element,Strings.NAME);
        double energy = Double.parseDouble(getByTag(element,Strings.ENERGY));
        String type = getByTag(element,Strings.TYPE);
        String production = getByTag(element,Strings.PRODUCTION);
        Element valueElement = (Element) element.getElementsByTagName(Strings.VALUE).item(0);
        Element ingredientsElement = (Element) element.getElementsByTagName(Strings.INGREDIENTS).item(0);
        return new Candy(id,name,energy,type,createValue(valueElement),production,createIngredients(ingredientsElement));
    }
    private List<Ingredient> createIngredients(Element element){
        List<Ingredient> ingredients = new ArrayList<>();
        NodeList nodelist = element.getElementsByTagName(Strings.INGREDIENT);
        for(int i=0;i<nodelist.getLength();i++){
            Element ingredient_element = (Element) nodelist.item(i);
            String name = ingredient_element.getAttribute(Strings.INAME);
            double amount = Double.parseDouble(ingredient_element.getAttribute(Strings.AMOUNT));
            Ingredient ingredient = new Ingredient(name,amount);
            ingredients.add(ingredient);
        }
        return ingredients;
    }
    private Value createValue(Element element){
        double proteins = Double.parseDouble(getByTag(element,Strings.PROTEINS));
        double fats = Double.parseDouble(getByTag(element,Strings.FATS));
        double carbonHydrates = Double.parseDouble(getByTag(element,Strings.CARBONHYDRATES));

        return new Value(proteins,fats,carbonHydrates);
    }
    private String getByTag(Element element,String tag){
        return element.getElementsByTagName(tag).item(0).getTextContent();
    }
}
