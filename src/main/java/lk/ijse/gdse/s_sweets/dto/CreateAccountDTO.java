package lk.ijse.gdse.s_sweets.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class CreateAccountDTO {
    private String userName;
    private String password;
    private String confirmPassword;
    private String email;
}
