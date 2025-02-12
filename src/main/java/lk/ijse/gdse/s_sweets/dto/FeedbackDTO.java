package lk.ijse.gdse.s_sweets.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class FeedbackDTO {
    private String feedbackId;
    private String productId;
    private String customerId;
    private String Description;
}
