package lk.ijse.gdse.s_sweets.dao.custom.impl;

import lk.ijse.gdse.s_sweets.dao.SQLUtil;
import lk.ijse.gdse.s_sweets.dao.custom.PaymentDAO;
import lk.ijse.gdse.s_sweets.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    public boolean save(Payment paymentDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into Payment values(?,?,?,?,?)"
            , paymentDTO.getPayId()
            , paymentDTO.getOrderId()
            , paymentDTO.getPayDate()
            , paymentDTO.getPayMethod()
            , paymentDTO.getAmount());
    }

    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("select * from Payment");

        ArrayList<Payment> paymentDTOS = new ArrayList<>();

        while (rst.next()){
            Payment paymentDTO = new Payment(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5)
            );
            paymentDTOS.add(paymentDTO);
        }
        return paymentDTOS;
    }

    public Object delete(String payId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Payment where PayId=?",payId);
    }

    public Object update(Payment paymentDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "update Payment set OrderId=?, PayDate=?, payMethod=?, PayAmount=? where PayId=?",
                paymentDTO.getOrderId(),
                paymentDTO.getPayDate(),
                paymentDTO.getPayMethod(),
                paymentDTO.getAmount(),
                paymentDTO.getPayId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst= SQLUtil.execute("SELECT * FROM Payment WHERE PayId=?",id);
        return rst.next();
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("select PayId from Payment order by PayId desc limit 1");

        if (rst.next()){
            String lastId = rst.getString(1); // C002
            String substring = lastId.substring(1); // 002
            int i = Integer.parseInt(substring); // 2
            int newIdIndex = i+1; // 3
            return String.format("P%03d",newIdIndex);
        }
        return  "P001";
    }
}
