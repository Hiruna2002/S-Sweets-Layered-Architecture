package lk.ijse.gdse.s_sweets.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ProductSupplierDTO {
    private String supId;
    private String proId;
    private int amount;
}
