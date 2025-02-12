package lk.ijse.gdse.s_sweets.bo.custom;

import lk.ijse.gdse.s_sweets.bo.SuperBO;
import lk.ijse.gdse.s_sweets.dto.DeliveryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryBO extends SuperBO {
    ArrayList<DeliveryDTO> getAll() throws SQLException, ClassNotFoundException;
    boolean save(DeliveryDTO dto) throws SQLException, ClassNotFoundException;
    Object update(DeliveryDTO dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    Object delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
}
