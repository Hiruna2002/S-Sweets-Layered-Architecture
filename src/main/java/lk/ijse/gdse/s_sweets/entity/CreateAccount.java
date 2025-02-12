package lk.ijse.gdse.s_sweets.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class CreateAccount {
    private String userName;
    private String password;
    private String confirmPassword;
    private String email;
}
