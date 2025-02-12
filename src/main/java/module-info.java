module lk.ijse.gdse.s_sweets {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;
    requires java.desktop;
//    requires jasperreports;


    opens lk.ijse.gdse.s_sweets.controller to javafx.fxml;
    opens lk.ijse.gdse.s_sweets.dto.tm to javafx.base;
    exports lk.ijse.gdse.s_sweets;
}