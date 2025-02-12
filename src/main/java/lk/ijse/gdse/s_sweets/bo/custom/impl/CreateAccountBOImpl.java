package lk.ijse.gdse.s_sweets.bo.custom.impl;

import lk.ijse.gdse.s_sweets.bo.custom.CreateAccountBO;
import lk.ijse.gdse.s_sweets.dao.DAOFactory;
import lk.ijse.gdse.s_sweets.dao.custom.CreateAccountDAO;
import lk.ijse.gdse.s_sweets.dto.CreateAccountDTO;
import lk.ijse.gdse.s_sweets.entity.CreateAccount;

import java.sql.SQLException;
import java.util.ArrayList;

public class CreateAccountBOImpl implements CreateAccountBO {
    CreateAccountDAO createAccountDAO = (CreateAccountDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CREATEACCOUNT);
    @Override
    public ArrayList<CreateAccountDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(CreateAccountDTO dto) throws SQLException, ClassNotFoundException {
        return createAccountDAO.save(new CreateAccount(dto.getUserName(), dto.getPassword(), dto.getConfirmPassword(), dto.getEmail()));
    }

    @Override
    public Object update(CreateAccountDTO dto) throws SQLException, ClassNotFoundException {
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
