package lk.ijse.gdse.s_sweets.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter

public class OrderDetailDTO {
    private String orderId;
    private String proId;
    private String cusId;
    private int amount;
}
