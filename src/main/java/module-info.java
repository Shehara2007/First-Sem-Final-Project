module lk.ijse.sciencelab {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;


    opens lk.ijse.sciencelab to javafx.fxml;
    exports lk.ijse.sciencelab;

   exports lk.ijse.sciencelab.Controller;
    opens lk.ijse.sciencelab.Controller to javafx.fxml;
    opens lk.ijse.sciencelab.Dto;
    exports lk.ijse.sciencelab.Dto;
}