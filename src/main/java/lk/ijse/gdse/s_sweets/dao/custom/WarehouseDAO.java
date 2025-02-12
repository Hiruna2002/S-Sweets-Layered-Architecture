package lk.ijse.gdse.s_sweets.dao.custom;

import lk.ijse.gdse.s_sweets.dao.CrudDAO;
import lk.ijse.gdse.s_sweets.entity.Warehouse;


import java.sql.SQLException;
import java.util.List;

public interface WarehouseDAO extends CrudDAO<Warehouse> {
     List<String> getAllWarehouseIds() throws SQLException, ClassNotFoundException;
     List<String> getAllEmployeeIds() throws SQLException, ClassNotFoundException;
}
