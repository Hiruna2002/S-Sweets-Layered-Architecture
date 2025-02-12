package lk.ijse.gdse.s_sweets.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SecurityController {

    @FXML
    private AnchorPane ancSecurity;

    @FXML
    private Button btnSubmit;

    @FXML
    private PasswordField psdPassword;

    private String originalPassword = "Hiruna@123";

    @FXML
    void btnSubmitOnNavigateTo(ActionEvent event) throws IOException {
        if(psdPassword.getText().equals(originalPassword)){
            ancSecurity.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/CreateAccount.fxml"));
            ancSecurity.getChildren().add(load);
        }else {
            new Alert(Alert.AlertType.ERROR, "Passwords do not match").show();
            psdPassword.clear();
        }

    }

}
