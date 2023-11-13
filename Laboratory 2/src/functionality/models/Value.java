package models;
import lombok.*;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Value {
    private double proteins;
    private double fats;
    private double carbonHydrates;



}
