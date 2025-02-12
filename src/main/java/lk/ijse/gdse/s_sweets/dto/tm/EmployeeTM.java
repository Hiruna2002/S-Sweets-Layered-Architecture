package lk.ijse.gdse.s_sweets.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class EmployeeTM {
    private String empId;
    private String wareId;
    private String empName;
    private String phone;
    private int salary;

}
