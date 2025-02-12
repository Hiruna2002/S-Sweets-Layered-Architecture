package lk.ijse.gdse.s_sweets.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter

public class Order {
    private String orderId;
    private String cusId;
    private String proId;
    private String item;
    private int qty;
    private String orderDate;
    private String dueDate;
    private int amount;
}
