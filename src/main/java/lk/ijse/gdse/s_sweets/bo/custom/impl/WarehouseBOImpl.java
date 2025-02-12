package lk.ijse.gdse.s_sweets.bo.custom.impl;

import lk.ijse.gdse.s_sweets.bo.custom.WarehouseBO;
import lk.ijse.gdse.s_sweets.dao.DAOFactory;
import lk.ijse.gdse.s_sweets.dao.custom.WarehouseDAO;
import lk.ijse.gdse.s_sweets.dto.WarehouseDTO;
import lk.ijse.gdse.s_sweets.entity.Warehouse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseBOImpl implements WarehouseBO {

    WarehouseDAO warehouseDAO = (WarehouseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.WAREHOUSE);

    @Override
    public ArrayList<WarehouseDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Warehouse> all = warehouseDAO.getAll();
        ArrayList<WarehouseDTO> warehouseDTOS = new ArrayList<>();
        for (Warehouse warehouse : all){
            warehouseDTOS.add(new WarehouseDTO(warehouse.getWareId(), warehouse.getLocation()));
        }
        return warehouseDTOS;
    }

    @Override
    public boolean save(WarehouseDTO dto) throws SQLException, ClassNotFoundException {
        return warehouseDAO.save(new Warehouse(dto.getWareId(), dto.getLocation()));
    }

    @Override
    public Object update(WarehouseDTO dto) throws SQLException, ClassNotFoundException {
        return warehouseDAO.update(new Warehouse(dto.getWareId(), dto.getLocation()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return warehouseDAO.exist(id);
    }

    @Override
    public Object delete(String id) throws SQLException, ClassNotFoundException {
        return warehouseDAO.delete(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return warehouseDAO.generateNewId();
    }

    @Override
    public List<String> getAllWarehouseIds() throws SQLException, ClassNotFoundException {
        return warehouseDAO.getAllWarehouseIds();
    }

    @Override
    public List<String> getAllEmployeeIds() throws SQLException, ClassNotFoundException {
        return warehouseDAO.getAllEmployeeIds();
    }

}
