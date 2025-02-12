package lk.ijse.gdse.s_sweets.bo.custom.impl;

import lk.ijse.gdse.s_sweets.bo.custom.LoginBO;
import lk.ijse.gdse.s_sweets.dao.DAOFactory;
import lk.ijse.gdse.s_sweets.dao.custom.LoginDAO;
import lk.ijse.gdse.s_sweets.dao.custom.impl.LoginDAOImpl;
import lk.ijse.gdse.s_sweets.dto.LoginDTO;
import lk.ijse.gdse.s_sweets.entity.Login;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoginBOImpl implements LoginBO {
    LoginDAO loginDAO = (LoginDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.LOGIN);
    @Override
    public boolean checkCorrectData(LoginDTO loginDTO) throws SQLException, ClassNotFoundException {
        return loginDAO.checkCorrectData(new Login(loginDTO.getUserName(),loginDTO.getPassword()));
    }

    @Override
    public ArrayList<LoginDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(LoginDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Object update(LoginDTO dto) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Object delete(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }
}
