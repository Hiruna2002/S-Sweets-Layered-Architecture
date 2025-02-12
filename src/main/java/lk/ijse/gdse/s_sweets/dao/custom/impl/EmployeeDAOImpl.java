package lk.ijse.gdse.s_sweets.dao.custom.impl;

import lk.ijse.gdse.s_sweets.dao.SQLUtil;
import lk.ijse.gdse.s_sweets.dao.custom.EmployeeDAO;
import lk.ijse.gdse.s_sweets.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    public Object delete(String empId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Employee where Emp_Id=?",empId);
    }

    public boolean save(Employee employeeDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into Employee values(?,?,?,?,?)"
                , employeeDTO.getEmpId()
                , employeeDTO.getWareId()
                , employeeDTO.getEmpName()
                , employeeDTO.getPhone()
                , employeeDTO.getSalary());

    }

    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("select * from Employee");

        ArrayList<Employee> employeeDTOS = new ArrayList<>();

        while (rst.next()){
            Employee employeeDTO = new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5)
            );
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    public Object update(Employee employeeDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "update Employee set Ware_id=?, Emp_name=?, PhoneNo=?, Salary=? where Emp_id=?",
                employeeDTO.getWareId(),
                employeeDTO.getEmpName(),
                employeeDTO.getPhone(),
                employeeDTO.getSalary(),
                employeeDTO.getEmpId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst= SQLUtil.execute("SELECT * FROM Employee WHERE Emp_id=?",id);
        return rst.next();
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("select Emp_id from Employee order by Emp_id desc limit 1");

        if (rst.next()){
            String lastId = rst.getString(1); // C002
            String substring = lastId.substring(3); // 002
            int i = Integer.parseInt(substring); // 2
            int newIdIndex = i+1; // 3
            return String.format("Emp%03d",newIdIndex);
        }
        return  "Emp001";
    }
}
