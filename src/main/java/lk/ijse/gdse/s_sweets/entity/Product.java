package lk.ijse.gdse.s_sweets.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Product {
    private String proId;
    private String catId;
    private String proName;
    private int price;
    private int qty;
}
