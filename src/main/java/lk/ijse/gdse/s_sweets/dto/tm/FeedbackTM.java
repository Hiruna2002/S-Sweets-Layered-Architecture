package lk.ijse.gdse.s_sweets.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class FeedbackTM {
    private String feedbackId;
    private String productId;
    private String customerId;
    private String Description;
}
