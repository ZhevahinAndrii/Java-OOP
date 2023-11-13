package models;
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
//    private List<Ingredient> ingredients;
    private Value value;
    private String production;


}
