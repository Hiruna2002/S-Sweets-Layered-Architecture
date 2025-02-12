package lk.ijse.gdse.s_sweets.bo.custom;

import lk.ijse.gdse.s_sweets.bo.SuperBO;
import lk.ijse.gdse.s_sweets.dto.FeedbackDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FeedbackBO extends SuperBO {
    ArrayList<FeedbackDTO> getAll() throws SQLException, ClassNotFoundException;
    boolean save(FeedbackDTO dto) throws SQLException, ClassNotFoundException;
    Object update(FeedbackDTO dto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    Object delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
}
