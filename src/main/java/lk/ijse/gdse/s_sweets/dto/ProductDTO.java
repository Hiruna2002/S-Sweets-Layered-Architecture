package lk.ijse.gdse.s_sweets.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ProductDTO {
    private String proId;
    private String catId;
    private String proName;
    private int price;
    private int qty;
}
