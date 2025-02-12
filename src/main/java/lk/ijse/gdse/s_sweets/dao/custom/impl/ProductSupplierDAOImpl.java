package lk.ijse.gdse.s_sweets.dao.custom.impl;

import lk.ijse.gdse.s_sweets.dao.SQLUtil;
import lk.ijse.gdse.s_sweets.dao.custom.ProductSupplierDAO;
import lk.ijse.gdse.s_sweets.dto.ProductSupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductSupplierDAOImpl implements ProductSupplierDAO {
    @Override
    public ArrayList<ProductSupplierDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(ProductSupplierDTO productSupplierDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into Product_Supplier values(?,?,?)"
                , productSupplierDTO.getSupId()
                , productSupplierDTO.getProId()
                , productSupplierDTO.getAmount());
    }


    public Object delete(String supId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Product_Supplier where Sup_id=?",supId);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    public Object update(ProductSupplierDTO productSupplierDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "update Product_Supplier set Pro_id=?, Amount=? where Sup_id=?",
                productSupplierDTO.getProId(),
                productSupplierDTO.getAmount(),
                productSupplierDTO.getSupId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
