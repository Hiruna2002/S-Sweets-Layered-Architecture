package lk.ijse.gdse.s_sweets.bo.custom.impl;

import lk.ijse.gdse.s_sweets.bo.custom.EmployeeBO;
import lk.ijse.gdse.s_sweets.dao.DAOFactory;
import lk.ijse.gdse.s_sweets.dao.custom.EmployeeDAO;
import lk.ijse.gdse.s_sweets.dto.EmployeeDTO;
import lk.ijse.gdse.s_sweets.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);
    @Override
    public ArrayList<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> all = employeeDAO.getAll();
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : all){
            employeeDTOS.add(new EmployeeDTO(employee.getEmpId(), employee.getWareId(), employee.getEmpName(), employee.getPhone(), employee.getSalary()));
        }
        return employeeDTOS;
    }

    @Override
    public boolean save(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(dto.getEmpId(), dto.getWareId(), dto.getEmpName(), dto.getPhone(), dto.getSalary()));
    }

    @Override
    public Object update(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getEmpId(), dto.getWareId(), dto.getEmpName(), dto.getPhone(), dto.getSalary()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.exist(id);
    }

    @Override
    public Object delete(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateNewId();
    }
}
