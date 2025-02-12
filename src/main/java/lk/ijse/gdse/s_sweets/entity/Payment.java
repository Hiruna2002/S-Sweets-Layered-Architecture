package lk.ijse.gdse.s_sweets.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Payment {
    private String payId;
    private String orderId;
    private String payDate;
    private String payMethod;
    private int amount;


}
