package lk.ijse.gdse.s_sweets.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Feedback {
    private String feedbackId;
    private String productId;
    private String customerId;
    private String Description;
}
