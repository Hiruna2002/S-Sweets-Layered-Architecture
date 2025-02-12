package lk.ijse.gdse.s_sweets.dao.custom.impl;

import lk.ijse.gdse.s_sweets.dao.SQLUtil;
import lk.ijse.gdse.s_sweets.dao.custom.DeliveryDAO;
import lk.ijse.gdse.s_sweets.entity.Delivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryDAOImpl implements DeliveryDAO {

    public boolean save(Delivery deliveryDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "insert into Delivery values(?,?,?,?,?,?)",deliveryDTO.getDeliveryId(), deliveryDTO.getOrderId(), deliveryDTO.getDeliRyderName(), deliveryDTO.getDeliAddress(), deliveryDTO.getDeliDate(), (Object)deliveryDTO.getDeliAmount());

    }

    public Object delete(String delId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Delivery where DeliId=?",delId);
    }

    public ArrayList<Delivery> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("SELECT * from Delivery");

        ArrayList<Delivery> deliveryDTOS = new ArrayList<>();

        while (rst.next()){
            Delivery deliveryDTO = new Delivery(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6)
            );
            deliveryDTOS.add(deliveryDTO);
        }
        return deliveryDTOS;
    }

    public Object update(Delivery deliveryDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "update Delivery set OrderId=?, DeliRyderName=?, DeliAddress=?, DeliDate=?, DeliAmount=? where DeliId=?",
                deliveryDTO.getOrderId(),
                deliveryDTO.getDeliRyderName(),
                deliveryDTO.getDeliAddress(),
                deliveryDTO.getDeliDate(),
                (Object)deliveryDTO.getDeliAmount(),
                deliveryDTO.getDeliveryId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.execute("SELECT * FROM Delivery WHERE DeliId=?",id);
        return rst.next();
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("SELECT DeliId from Delivery order by DeliId desc limit 1");

        if (rst.next()){
            String lastId = rst.getString(1); // C002
            String substring = lastId.substring(1); // 002
            int i = Integer.parseInt(substring); // 2
            int newIdIndex = i+1; // 3
            return String.format("D%03d",(Object) newIdIndex);
        }
        return  "D001";
    }
}
