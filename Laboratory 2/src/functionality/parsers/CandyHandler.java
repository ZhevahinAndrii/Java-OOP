package parsers;

import models.Candy;
import models.Ingredient;
import models.Value;
import org.xml.sax.Attributes;

import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Constants.Strings;
public class CandyHandler extends DefaultHandler {
    private String valueOfElement;
    private List<Candy> candiesList = new ArrayList<>();
    @Override
    public void startElement(String url, String name, String attributeName, Attributes attributes){
        switch (attributeName){
            case Strings.CANDY -> {
                Candy candy = new Candy();
                candiesList.add(candy);
            }
            case Strings.VALUE -> {
                Value value = new Value();
                getLastCandy().setValue(value);
            }

        }
    }
    @Override
    public void endElement(String uri,String name,String attributeName){
        switch(attributeName){
            case Strings.ID-> getLastCandy().setId(Integer.parseInt(valueOfElement));
            case Strings.NAME->getLastCandy().setName(valueOfElement);
            case Strings.ENERGY -> getLastCandy().setEnergy(Double.parseDouble(valueOfElement));
            case Strings.TYPE ->getLastCandy().setType(valueOfElement);
            case Strings.FATS -> getLastCandy().getValue().setFats(Double.parseDouble(valueOfElement));
            case Strings.PROTEINS -> getLastCandy().getValue().setProteins(Double.parseDouble(valueOfElement));
            case Strings.CARBONHYDRATES -> getLastCandy().getValue().setCarbonHydrates(Double.parseDouble(valueOfElement));
            case Strings.PRODUCTION -> getLastCandy().setProduction(valueOfElement);
        }

    }
    public void setField(String attributeName, String value, Map<String,String> attributes){
        this.valueOfElement = value;
        switch(attributeName){
            case Strings.CANDY -> {
                Candy candy = new Candy();
                candiesList.add(candy);
            }
            case Strings.ID-> getLastCandy().setId(Integer.parseInt(valueOfElement));
            case Strings.NAME->getLastCandy().setName(valueOfElement);
            case Strings.ENERGY -> getLastCandy().setEnergy(Double.parseDouble(valueOfElement));
            case Strings.TYPE ->getLastCandy().setType(valueOfElement);
            case Strings.FATS -> getLastCandy().getValue().setFats(Double.parseDouble(valueOfElement));
            case Strings.PROTEINS -> getLastCandy().getValue().setProteins(Double.parseDouble(valueOfElement));
            case Strings.CARBONHYDRATES -> getLastCandy().getValue().setCarbonHydrates(Double.parseDouble(valueOfElement));
            case Strings.VALUE -> {
                Value Vvalue = new Value();
                getLastCandy().setValue(Vvalue);
            }
            case Strings.PRODUCTION -> getLastCandy().setProduction(valueOfElement);

        }
    }
    public List<Candy> getCandiesList(){
        return candiesList;
    }
    @Override
    public void startDocument(){
        candiesList = new ArrayList<>();
    }
    @Override
    public void characters(char[] ch,int start,int length){
        valueOfElement = new String(ch,start,length);
    }
    private Candy getLastCandy(){
        return candiesList.get(candiesList.size()-1);
    }

}
