package lk.ijse.gdse.s_sweets.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class SupplierDTO {
    private String supplierId;
    private String productId;
    private String supplierName;
    private String supplierDate;
    private int supplierAmount;
    private String phone;

    public SupplierDTO(String supplierId, String productId, String supplierName, String supplierDate, int supplierAmount, String phone){

    }
}
