package lk.ijse.gdse.s_sweets.bo.custom;

import lk.ijse.gdse.s_sweets.bo.SuperBO;
import lk.ijse.gdse.s_sweets.dao.SuperDAO;
import lk.ijse.gdse.s_sweets.dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrderBO extends SuperBO {
    //    List<String> getAllOrderIds() throws SQLException, ClassNotFoundException;
    ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException;
    boolean save(OrderDTO dto) throws SQLException, ClassNotFoundException;
    Object update(OrderDTO dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    Object delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;

    List<String> getAllOrderIds() throws SQLException, ClassNotFoundException;
}
