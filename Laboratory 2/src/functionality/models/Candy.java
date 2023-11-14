package models;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Candy {
    private int id;
    private String name;
    private double energy;
    private String type;
    private Value value;
    private String production;
    private List<Ingredient> ingredients;
    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

}
