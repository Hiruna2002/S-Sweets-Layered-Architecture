package lk.ijse.gdse.s_sweets.dao.custom.impl;

import lk.ijse.gdse.s_sweets.dao.SQLUtil;
import lk.ijse.gdse.s_sweets.dao.custom.CreateAccountDAO;
import lk.ijse.gdse.s_sweets.entity.CreateAccount;

import java.sql.SQLException;
import java.util.ArrayList;

public class CreateAccountDAOImpl implements CreateAccountDAO {
    @Override
    public ArrayList<CreateAccount> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(CreateAccount createAccountDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into Admin values(?,?,?,?)"
            , createAccountDTO.getUserName()
            , createAccountDTO.getPassword()
            , createAccountDTO.getConfirmPassword()
            , createAccountDTO.getEmail());
    }

    @Override
    public Object update(CreateAccount dto) throws SQLException, ClassNotFoundException {
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
