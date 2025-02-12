package lk.ijse.gdse.s_sweets.bo.custom;

import lk.ijse.gdse.s_sweets.bo.SuperBO;
import lk.ijse.gdse.s_sweets.dto.InventoryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InventoryBO extends SuperBO {
    ArrayList<InventoryDTO> getAll() throws SQLException, ClassNotFoundException;
    boolean save(InventoryDTO dto) throws SQLException, ClassNotFoundException;
    Object update(InventoryDTO dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    Object delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
}
