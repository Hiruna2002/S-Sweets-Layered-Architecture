package lk.ijse.gdse.s_sweets.dao.custom;

import lk.ijse.gdse.s_sweets.dao.CrudDAO;
import lk.ijse.gdse.s_sweets.entity.Order;

import java.sql.SQLException;
import java.util.List;


public interface OrderDAO extends CrudDAO<Order> {
   List<String> getAllOrderIds() throws SQLException, ClassNotFoundException;
}
