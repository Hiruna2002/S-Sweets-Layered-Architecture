package lk.ijse.gdse.s_sweets.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SupplierTM {
    private String supplierId;
    private String productId;
    private String supplierName;
    private String supplierDate;
    private int supplierAmount;
    private String phone;
}
