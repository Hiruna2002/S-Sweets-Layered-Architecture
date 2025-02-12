package lk.ijse.gdse.s_sweets.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class PaymentDTO {
    private String payId;
    private String orderId;
    private String payDate;
    private String payMethod;
    private int amount;


}
