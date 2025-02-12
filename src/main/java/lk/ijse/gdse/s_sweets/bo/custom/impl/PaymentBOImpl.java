package lk.ijse.gdse.s_sweets.bo.custom.impl;

import lk.ijse.gdse.s_sweets.bo.custom.PaymentBO;
import lk.ijse.gdse.s_sweets.dao.DAOFactory;
import lk.ijse.gdse.s_sweets.dao.custom.PaymentDAO;
import lk.ijse.gdse.s_sweets.dto.PaymentDTO;
import lk.ijse.gdse.s_sweets.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    @Override
    public ArrayList<PaymentDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> all = paymentDAO.getAll();
        ArrayList<PaymentDTO>paymentDTOS = new ArrayList<>();
        for (Payment payment : all){
            paymentDTOS.add(new PaymentDTO(payment.getPayId(), payment.getOrderId(),payment.getPayDate(),payment.getPayMethod(),payment.getAmount()));
        }
        return paymentDTOS;
    }

    @Override
    public boolean save(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(dto.getPayId(), dto.getOrderId(), dto.getPayDate(), dto.getPayMethod(), dto.getAmount()));
    }

    @Override
    public Object update(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(dto.getPayId(), dto.getOrderId(), dto.getPayDate(), dto.getPayMethod(), dto.getAmount()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.exist(id);
    }

    @Override
    public Object delete(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return paymentDAO.generateNewId();
    }
}
