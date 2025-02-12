package lk.ijse.gdse.s_sweets.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Ingredient {
    private String ingId;
    private String ingName;
    private String expDate;
    private String qty;
    private String unit;
}
