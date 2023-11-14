package parsers;

import lombok.Getter;
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
    @Getter
    private List<Candy> candiesList = new ArrayList<>();

    @Override
    public void startElement(String url, String name, String attributeName, Attributes attributes){
        switch (attributeName){
            case Strings.CANDY -> {
                int id = Integer.parseInt(attributes.getValue(Strings.ID));
                Candy candy = new Candy();
                candiesList.add(candy);
                getLastCandy().setId(id);
            }
            case Strings.VALUE -> {
                Value value = new Value();
                getLastCandy().setValue(value);
            }

            case Strings.INGREDIENT -> {
                Ingredient ingredient = new Ingredient();
                getLastCandy().addIngredient(ingredient);
                String ingredientName = attributes.getValue(Strings.INAME);
                double amount = Double.parseDouble(attributes.getValue(Strings.AMOUNT));
                getLastIngredient().setName(ingredientName);
                getLastIngredient().setAmount(amount);
            }
            case Strings.INGREDIENTS -> {
                getLastCandy().setIngredients(new ArrayList<>());
            }
        }
    }
    @Override
    public void endElement(String uri,String name,String attributeName){
        switch(attributeName){
//            case Strings.ID-> getLastCandy().setId(Integer.parseInt(valueOfElement));
            case Strings.NAME->getLastCandy().setName(valueOfElement);
            case Strings.ENERGY -> getLastCandy().setEnergy(Double.parseDouble(valueOfElement));
            case Strings.TYPE ->getLastCandy().setType(valueOfElement);
            case Strings.FATS -> getLastCandy().getValue().setFats(Double.parseDouble(valueOfElement));
            case Strings.PROTEINS -> getLastCandy().getValue().setProteins(Double.parseDouble(valueOfElement));
            case Strings.CARBONHYDRATES -> getLastCandy().getValue().setCarbonHydrates(Double.parseDouble(valueOfElement));
            case Strings.PRODUCTION -> getLastCandy().setProduction(valueOfElement);
            case Strings.INAME-> getLastIngredient().setName(valueOfElement);
            case Strings.AMOUNT -> getLastIngredient().setAmount(Double.parseDouble(valueOfElement));
        }

    }
    public void setField(String attributeName, String value, Map<String,String> attributes){
        this.valueOfElement = value;
        switch(attributeName){
            case Strings.CANDY -> {
                Candy candy = new Candy();
                int id = Integer.parseInt(attributes.get(Strings.ID));
                candiesList.add(candy);
                getLastCandy().setId(id);

            }
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
            case Strings.INGREDIENTS -> {
                getLastCandy().setIngredients(new ArrayList<>());
            }
            case Strings.INGREDIENT -> {
                Ingredient ingredient = new Ingredient();
                getLastCandy().addIngredient(ingredient);
                String name = attributes.get(Strings.INAME);
                double amount = Double.parseDouble(attributes.get(Strings.AMOUNT));
                getLastIngredient().setName(name);
                getLastIngredient().setAmount(amount);
            }
            case Strings.PRODUCTION -> getLastCandy().setProduction(valueOfElement);
        }
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
    private Ingredient getLastIngredient(){
        return getLastCandy().getIngredients().get(getLastCandy().getIngredients().size()-1);
    }

}
