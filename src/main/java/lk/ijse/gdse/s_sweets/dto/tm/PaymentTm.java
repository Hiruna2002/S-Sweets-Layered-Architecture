package lk.ijse.gdse.s_sweets.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class PaymentTm {
    private String payId;
    private String orderId;
    private String payDate;
    private String payMethod;
    private int payAmount;
}
