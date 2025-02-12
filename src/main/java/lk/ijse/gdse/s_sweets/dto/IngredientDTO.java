package lk.ijse.gdse.s_sweets.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class IngredientDTO {
    private String ingId;
    private String ingName;
    private String expDate;
    private String qty;
    private String unit;
}
