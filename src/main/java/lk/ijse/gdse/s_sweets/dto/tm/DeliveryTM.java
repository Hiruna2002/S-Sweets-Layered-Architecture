package lk.ijse.gdse.s_sweets.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class DeliveryTM {
    private String deliveryId;
    private String orderId;
    private String deliRyderName;
    private String deliAddress;
    private String deliDate;
    private int deliAmount;
}
