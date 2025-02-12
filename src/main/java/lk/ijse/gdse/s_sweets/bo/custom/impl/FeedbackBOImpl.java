package lk.ijse.gdse.s_sweets.bo.custom.impl;

import lk.ijse.gdse.s_sweets.bo.custom.FeedbackBO;
import lk.ijse.gdse.s_sweets.dao.DAOFactory;
import lk.ijse.gdse.s_sweets.dao.custom.FeedbackDAO;
import lk.ijse.gdse.s_sweets.dto.FeedbackDTO;
import lk.ijse.gdse.s_sweets.entity.Feedback;

import java.sql.SQLException;
import java.util.ArrayList;

public class FeedbackBOImpl implements FeedbackBO {
    FeedbackDAO feedbackDAO = (FeedbackDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.FEEDBACK);
    @Override
    public ArrayList<FeedbackDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Feedback> all = feedbackDAO.getAll();
        ArrayList<FeedbackDTO>feedbackDTOS = new ArrayList<>();
        for (Feedback feedback : all){
            feedbackDTOS.add(new FeedbackDTO(feedback.getFeedbackId(), feedback.getProductId(), feedback.getCustomerId(), feedback.getDescription()));
        }
        return feedbackDTOS;
    }

    @Override
    public boolean save(FeedbackDTO dto) throws SQLException, ClassNotFoundException {
        return feedbackDAO.save(new Feedback(dto.getFeedbackId(), dto.getProductId(), dto.getCustomerId(),dto.getDescription()));
    }

    @Override
    public Object update(FeedbackDTO dto) throws SQLException, ClassNotFoundException {
        return feedbackDAO.update(new Feedback(dto.getFeedbackId(), dto.getProductId(), dto.getCustomerId(),dto.getDescription()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return feedbackDAO.exist(id);
    }

    @Override
    public Object delete(String id) throws SQLException, ClassNotFoundException {
        return feedbackDAO.delete(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return feedbackDAO.generateNewId();
    }
}
