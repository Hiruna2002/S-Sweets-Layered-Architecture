package lk.ijse.gdse.s_sweets.bo.custom.impl;

import lk.ijse.gdse.s_sweets.bo.custom.SupplierBO;
import lk.ijse.gdse.s_sweets.dao.DAOFactory;
import lk.ijse.gdse.s_sweets.dao.custom.SupplierDAO;
import lk.ijse.gdse.s_sweets.dto.SupplierDTO;
import lk.ijse.gdse.s_sweets.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIER);
    @Override
    public ArrayList<SupplierDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> all = supplierDAO.getAll();
        ArrayList<SupplierDTO>supplierDTOS = new ArrayList<>();
        for (Supplier supplier : all){
            supplierDTOS.add(new SupplierDTO(supplier.getSupplierId(), supplier.getProductId(),supplier.getSupplierName(),supplier.getSupplierDate(),supplier.getSupplierAmount(),supplier.getPhone()));
        }
        return supplierDTOS;
    }

    @Override
    public boolean save(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        System.out.println("12");
        return supplierDAO.save(new Supplier(dto.getSupplierId(), dto.getProductId(), dto.getSupplierName(), dto.getSupplierDate(), dto.getSupplierAmount(), dto.getPhone()));
    }

    @Override
    public Object update(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(dto.getSupplierId(), dto.getProductId(), dto.getSupplierName(), dto.getSupplierDate(), dto.getSupplierAmount(), dto.getPhone()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.exist(id);
    }

    @Override
    public Object delete(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNewId();
    }
}
