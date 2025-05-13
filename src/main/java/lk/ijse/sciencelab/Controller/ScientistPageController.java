package lk.ijse.sciencelab.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.sciencelab.Dto.ScientistDto;
import lk.ijse.sciencelab.model.Scientistmodel;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScientistPageController{
    private final Scientistmodel Scmodel = new Scientistmodel();
    public Button btnGenarateReport;
    public TextField txtContact;
    public TableColumn Contactclm;
    public TableColumn Specializaionclm;
    public TableColumn Employeeclm;
    public TableColumn ScientistNameclm;
    public TableColumn ScientistIDclm;
    public TableView tblScientist;
    public TextField txtEmployee;
    public Label lblScientistID;
    public Button btnReset;
    public Button btnUpdate;
    public ImageView btnDelete;
    public TextField txtScientistName;
    public Button btnSave;
    public TextField txtspecialization;
    public ComboBox ComboBoxEmployee;

    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        setnextID();
        loadtable();
    }

    private void setnextID() throws SQLException, ClassNotFoundException {
        String nextID = Scientistmodel.getText();
        lblScientistID.setText(nextID);
    }

    private void setcellvaluefactory() {
        ScientistIDclm.setCellValueFactory(new PropertyValueFactory<>("scientistId"));
        ScientistNameclm.setCellValueFactory(new PropertyValueFactory<>("scientistName"));
        Contactclm.setCellValueFactory(new PropertyValueFactory<>("contact"));
        Employeeclm.setCellValueFactory(new PropertyValueFactory<>("employee"));
        Specializaionclm.setCellValueFactory(new PropertyValueFactory<>("specialization"));
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<ScientistDto> scientist = Scmodel.getAll();

        ObservableList<ScientistDto> scientistDtoObservableList = FXCollections.observableArrayList();
        for (ScientistDto s : scientist) {
            scientistDtoObservableList.add(s);
        }
        tblScientist.setItems(scientistDtoObservableList);
    }


    public void clickOnAction (MouseEvent mouseEvent){
        ScientistDto selectedItem = (ScientistDto) tblScientist.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblScientistID.setText(selectedItem.getScientistId());
            txtScientistName.setText(selectedItem.getScientistName());
            txtContact.setText(selectedItem.getContact());
            txtEmployee.setText(selectedItem.getEmployee());
            txtspecialization.setText(selectedItem.getSpecialization());
            // save button disable
            btnSave.setDisable(true);
            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
    public void btnSaveOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String scientistID = lblScientistID.getText();
        String scientistName = txtScientistName.getText();
        String contact = txtContact.getText();
        String employee = txtEmployee.getText();
        String specialization = txtspecialization.getText();

        ScientistDto scientist = new ScientistDto(scientistID, scientistName, contact, employee, specialization);
        boolean issave = Scmodel.save(scientist);

        if (issave) {
            new Alert(Alert.AlertType.INFORMATION, "Scientist Saved", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Scientist NotSaved", ButtonType.OK).show();
        }

    }

    public void btnResetOnAction (ActionEvent actionEvent){
    }

    public void btnUpdateOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String scientistID = lblScientistID.getText();
        String scientistName = txtScientistName.getText();
        String contact = txtContact.getText();
        String employee = txtEmployee.getText();
        String specialization = txtspecialization.getText();

        ScientistDto scientist = new ScientistDto(scientistID, scientistName, contact, employee, specialization);
        boolean isupdate = Scmodel.update(scientist);

        if (isupdate) {
            new Alert(Alert.AlertType.INFORMATION, "Scientist Update", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Scientist NotUpdate", ButtonType.OK).show();
        }
    }

    public void btnDeleteOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String scientistID = lblScientistID.getText();
        boolean isDelete = Scmodel.DeleteScientist(scientistID);

        if (isDelete) {
            new Alert(Alert.AlertType.INFORMATION, "Scientist Deleted", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Scientist Not Deleted", ButtonType.OK).show();
        }
    }

    public void btnGenarateROnAction (ActionEvent actionEvent){
    }

}

