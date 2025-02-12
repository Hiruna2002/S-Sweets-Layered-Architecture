package lk.ijse.gdse.s_sweets.dao.custom.impl;

import lk.ijse.gdse.s_sweets.dao.SQLUtil;
import lk.ijse.gdse.s_sweets.dao.custom.SupplierDAO;
import lk.ijse.gdse.s_sweets.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {

    public boolean save(Supplier supplierDTO) throws SQLException, ClassNotFoundException {
        System.out.println("13");
        return SQLUtil.execute("insert into Suppliers values(?,?,?,?,?,?)"
            , supplierDTO.getSupplierId()
            , supplierDTO.getProductId()
            , supplierDTO.getSupplierName()
            , supplierDTO.getSupplierDate()
            , supplierDTO.getSupplierAmount()
            , supplierDTO.getPhone());
    }

    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("select * from Suppliers");

        ArrayList<Supplier> supplierDTOS = new ArrayList<>();

        while (rst.next()){
            Supplier supplierDTO = new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getString(6)
            );
            supplierDTOS.add(supplierDTO);
        }
        return supplierDTOS;
    }

    public Object delete(String supId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from suppliers where SupId=?",supId);
    }

    public Object update(Supplier supplierDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "update suppliers set ProId=?, SupName=?, SupDate=?, SupAmount=?, SupContactNo=? where SupId=?",
                supplierDTO.getProductId(),
                supplierDTO.getSupplierName(),
                supplierDTO.getSupplierDate(),
                (Object)supplierDTO.getSupplierAmount(),
                supplierDTO.getPhone(),
                supplierDTO.getSupplierId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT SupId From suppliers whare SupId=?",id);
        return rst.next();
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("select SupId from suppliers order by SupId desc limit 1");

        if (rst.next()){
            String lastId = rst.getString(1); // C002
            String substring = lastId.substring(3); // 002
            int i = Integer.parseInt(substring); // 2
            int newIdIndex = i+1; // 3
            return String.format("Sup%03d",(Object) newIdIndex);
        }
        return  "Sup001";
    }

    public List<String> getAllIds() throws SQLException, ClassNotFoundException {
        List<String> supplierIds = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT SupId FROM suppliers");

        while (rst.next()) {
            supplierIds.add(rst.getString(1));
        }

        return supplierIds;
    }
}
