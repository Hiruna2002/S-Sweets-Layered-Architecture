package lk.ijse.gdse.s_sweets.bo.custom.impl;

import lk.ijse.gdse.s_sweets.bo.custom.InventoryBO;
import lk.ijse.gdse.s_sweets.dao.DAOFactory;
import lk.ijse.gdse.s_sweets.dao.custom.InventoryDAO;
import lk.ijse.gdse.s_sweets.dto.InventoryDTO;
import lk.ijse.gdse.s_sweets.entity.Inventory;

import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryBOImpl implements InventoryBO {

    InventoryDAO inventoryDAO = (InventoryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.INVENTORY);
    @Override
    public ArrayList<InventoryDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Inventory> all = inventoryDAO.getAll();
        ArrayList<InventoryDTO>inventoryDTOS = new ArrayList<>();
        for (Inventory inventory : all){
            inventoryDTOS.add(new InventoryDTO(inventory.getInvenId(), inventory.getIngId(), inventory.getWareId()));
        }
        return inventoryDTOS;
    }

    @Override
    public boolean save(InventoryDTO dto) throws SQLException, ClassNotFoundException {
        return inventoryDAO.save(new Inventory(dto.getInvenId(), dto.getIngId(), dto.getWareId()));
    }

    @Override
    public Object update(InventoryDTO dto) throws SQLException, ClassNotFoundException {
        return inventoryDAO.update(new Inventory(dto.getInvenId(), dto.getIngId(), dto.getWareId()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return inventoryDAO.exist(id);
    }

    @Override
    public Object delete(String id) throws SQLException, ClassNotFoundException {
        return inventoryDAO.delete(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return inventoryDAO.generateNewId();
    }
}
