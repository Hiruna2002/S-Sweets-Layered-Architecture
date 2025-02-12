package lk.ijse.gdse.s_sweets.bo.custom;

import lk.ijse.gdse.s_sweets.bo.SuperBO;
import lk.ijse.gdse.s_sweets.dto.WarehouseDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface WarehouseBO extends SuperBO {
    ArrayList<WarehouseDTO> getAll() throws SQLException, ClassNotFoundException;
    boolean save(WarehouseDTO dto) throws SQLException, ClassNotFoundException;
    Object update(WarehouseDTO dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    Object delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    List<String> getAllWarehouseIds() throws SQLException, ClassNotFoundException;
    List<String> getAllEmployeeIds() throws SQLException, ClassNotFoundException;

}
