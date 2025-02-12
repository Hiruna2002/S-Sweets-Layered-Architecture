package lk.ijse.gdse.s_sweets.bo.custom.impl;

import lk.ijse.gdse.s_sweets.bo.custom.DeliveryBO;
import lk.ijse.gdse.s_sweets.dao.DAOFactory;
import lk.ijse.gdse.s_sweets.dao.custom.DeliveryDAO;
import lk.ijse.gdse.s_sweets.dto.DeliveryDTO;
import lk.ijse.gdse.s_sweets.entity.Delivery;

import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryBOImpl implements DeliveryBO {

    DeliveryDAO deliveryDAO = (DeliveryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DELIVERY);

    @Override
    public ArrayList<DeliveryDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Delivery> all = deliveryDAO.getAll();
        ArrayList<DeliveryDTO> deliveryDTOs = new ArrayList<>();
        for (Delivery delivery : all){
            deliveryDTOs.add(new DeliveryDTO(delivery.getDeliveryId(), delivery.getOrderId(), delivery.getDeliRyderName(), delivery.getDeliAddress(), delivery.getDeliDate(), delivery.getDeliAmount()));
        }
        return deliveryDTOs;
    }

    @Override
    public boolean save(DeliveryDTO dto) throws SQLException, ClassNotFoundException {
        return deliveryDAO.save(new Delivery(dto.getDeliveryId(), dto.getOrderId(),  dto.getDeliRyderName(), dto.getDeliAddress(), dto.getDeliDate(), dto.getDeliAmount()));
    }

    @Override
    public Object update(DeliveryDTO dto) throws SQLException, ClassNotFoundException {
        return deliveryDAO.update(new Delivery(dto.getDeliveryId(), dto.getOrderId(),  dto.getDeliRyderName(), dto.getDeliAddress(), dto.getDeliDate(), dto.getDeliAmount()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return deliveryDAO.exist(id);
    }

    @Override
    public Object delete(String id) throws SQLException, ClassNotFoundException {
        return deliveryDAO.delete(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return deliveryDAO.generateNewId();
    }
}
