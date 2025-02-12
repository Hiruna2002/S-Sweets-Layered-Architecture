package lk.ijse.gdse.s_sweets.dao.custom.impl;

import lk.ijse.gdse.s_sweets.dao.SQLUtil;
import lk.ijse.gdse.s_sweets.dao.custom.FeedbackDAO;
import lk.ijse.gdse.s_sweets.entity.Feedback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeedbackDAOImpl implements FeedbackDAO {
    public Object delete(String feedId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Feedback where Feedback_id=?",feedId);
    }

    public boolean save(Feedback feedbackDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into Feedback values(?,?,?,?)"
            , feedbackDTO.getFeedbackId()
            , feedbackDTO.getProductId()
            , feedbackDTO.getCustomerId()
            , feedbackDTO.getDescription());
    }

    public Object update(Feedback feedbackDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "update Feedback set Pro_id=?, Cus_id=?, Description=? where Feedback_id=?",
                feedbackDTO.getProductId(),
                feedbackDTO.getCustomerId(),
                feedbackDTO.getDescription(),
                feedbackDTO.getFeedbackId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst= SQLUtil.execute("SELECT * FROM Feedback WHERE Feedback_id=?",id);
        return rst.next();
    }

    public ArrayList<Feedback> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("select * from Feedback");

        ArrayList<Feedback> feedbackDTOS = new ArrayList<>();

        while (rst.next()){
            Feedback feedbackDTO = new Feedback(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            feedbackDTOS.add(feedbackDTO);
        }
        return feedbackDTOS;
    }

    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("select Feedback_id from Feedback order by Feedback_id desc limit 1");

        if (rst.next()){
            String lastId = rst.getString(1); // C002
            String substring = lastId.substring(4); // 002
            int i = Integer.parseInt(substring); // 2
            int newIdIndex = i+1; // 3
            return String.format("Feed%03d",newIdIndex);
        }
        return  "Feed001";
    }
}
