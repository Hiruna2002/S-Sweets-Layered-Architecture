package lk.ijse.gdse.s_sweets.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Supplier {
    private String supplierId;
    private String productId;
    private String supplierName;
    private String supplierDate;
    private int supplierAmount;
    private String phone;
}
