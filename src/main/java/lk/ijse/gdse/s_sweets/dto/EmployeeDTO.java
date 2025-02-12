package lk.ijse.gdse.s_sweets.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class EmployeeDTO {
    private String empId;
    private String wareId;
    private String empName;
    private String phone;
    private int salary;

}
