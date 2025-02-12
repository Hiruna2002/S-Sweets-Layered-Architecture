package lk.ijse.gdse.s_sweets.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ProductTM {
    private String proId;
    private String catId;
    private String proName;
    private int price;
    private int qty;
}
