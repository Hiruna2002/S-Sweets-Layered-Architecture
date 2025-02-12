package lk.ijse.gdse.s_sweets.dao.custom.impl;

import lk.ijse.gdse.s_sweets.dao.SQLUtil;
import lk.ijse.gdse.s_sweets.dao.custom.InventoryDAO;
import lk.ijse.gdse.s_sweets.entity.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryDAOImpl implements InventoryDAO {

    public Object delete(String invenId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Inventory where Inven_id=?",invenId);
    }

    public boolean save(Inventory inventoryDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into Inventory values(?,?,?)"
                , inventoryDTO.getInvenId()
                , inventoryDTO.getIngId()
                , inventoryDTO.getWareId());
    }

    public ArrayList<Inventory> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("select * from Inventory");

        ArrayList<Inventory> inventoryDTOS = new ArrayList<>();

        while (rst.next()){
            Inventory inventoryDTO = new Inventory(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
            inventoryDTOS.add(inventoryDTO);
        }
        return inventoryDTOS;
    }

    public Object update(Inventory inventoryDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "update Inventory set Ing_id=?, Ware_id=? where Inven_id=?",
                inventoryDTO.getIngId(),
                inventoryDTO.getWareId(),
                inventoryDTO.getInvenId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst= SQLUtil.execute("SELECT * FROM Inventory WHERE Inven_id=?",id);
        return rst.next();
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("select Inven_id from Inventory order by Inven_id desc limit 1");

        if (rst.next()){
            String lastId = rst.getString(1); // C002
            String substring = lastId.substring(5); // 002
            int i = Integer.parseInt(substring); // 2
            int newIdIndex = i+1; // 3
            return String.format("Inven%03d",newIdIndex);
        }
        return  "Inven001";
    }
}
