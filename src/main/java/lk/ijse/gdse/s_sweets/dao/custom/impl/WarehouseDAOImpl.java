package lk.ijse.gdse.s_sweets.dao.custom.impl;

import lk.ijse.gdse.s_sweets.dao.SQLUtil;
import lk.ijse.gdse.s_sweets.dao.custom.WarehouseDAO;
import lk.ijse.gdse.s_sweets.entity.Warehouse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDAOImpl implements WarehouseDAO {
    public Object delete(String wareId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Warehouse where Ware_Id=?",wareId);
    }

    public boolean save(Warehouse warehouseDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into Warehouse values(?,?)"
            , warehouseDTO.getWareId()
            , warehouseDTO.getLocation());

    }

    public ArrayList<Warehouse> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("select * from Warehouse");

        ArrayList<Warehouse> warehouseDTOS = new ArrayList<>();

        while (rst.next()){
            Warehouse warehouseDTO = new Warehouse(
                    rst.getString(1),
                    rst.getString(2)
            );
            warehouseDTOS.add(warehouseDTO);
        }
        return warehouseDTOS;
    }

    public Object update(Warehouse warehouseDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "update Warehouse set Location=? where Ware_Id=?",
                warehouseDTO.getLocation(),
                warehouseDTO.getWareId()

        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst= SQLUtil.execute("SELECT * FROM Warehouse WHERE Ware_Id=?",id);
        return rst.next();
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("select Ware_Id from Warehouse order by Ware_Id desc limit 1");

        if (rst.next()){
            String lastId = rst.getString(1); // C002
            String substring = lastId.substring(4); // 002
            int i = Integer.parseInt(substring); // 2
            int newIdIndex = i+1; // 3
            return String.format("Were%03d",newIdIndex);
        }
        return  "Were001";
    }

    public List<String> getAllWarehouseIds() throws SQLException, ClassNotFoundException {
        List<String> productIds = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT Ware_Id FROM Warehouse");

        while (rst.next()) {
            productIds.add(rst.getString(1));
        }

        return productIds;
    }

    public List<String> getAllEmployeeIds() throws SQLException, ClassNotFoundException {
        List<String> productIds = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT Ware_id FROM Warehouse");

        while (rst.next()) {
            productIds.add(rst.getString(1));
        }

        return productIds;
    }
}
