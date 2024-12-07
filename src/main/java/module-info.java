module com.example.trspolab4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.trspolab4 to javafx.fxml;
    opens com.example.trspolab4.classes to javafx.base;
    exports com.example.trspolab4;
}