package lk.ijse.gdse.s_sweets.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class DeliveryDTO {
    private String deliveryId;
    private String orderId;
    private String deliRyderName;
    private String deliAddress;
    private String deliDate;
    private int deliAmount;

    public DeliveryDTO(String deliId, String orderId, String deliRyderName, String deliAddress, String deliDate, int deliAmount) {
    }
}
