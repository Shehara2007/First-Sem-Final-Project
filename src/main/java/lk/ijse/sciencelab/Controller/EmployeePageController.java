package lk.ijse.sciencelab.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.sciencelab.Dto.EmployeeDto;
import lk.ijse.sciencelab.model.Employeemodel;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeePageController {
    private final Employeemodel Emodel = new Employeemodel();
    public Button btnSave;
    public ImageView btnReset;
    public Button btnDelete;
    public Button btnGenarateReport;
    public Button btnUpdate;
    public TableColumn Emailclm;
    public TableColumn Groupidclm;
    public TableColumn Roleclm;
    public TableColumn Contactclm;
    public TableColumn EmployeeNameclm;
    public TableColumn Employeeidclm;
    public TableView tblEmployee;
    public TextField txtEmail;
    public TextField txtRole;
    public TextField txtContact;
    public TextField txtEmployeeName;
    public Label lblEmployeeID;
    public ComboBox ComboBoxGroupID;


    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        setnextID();
        ComboBoxGroupID.setItems(Emodel.getAll());
        loadtable();
    }

    private void setnextID() throws SQLException, ClassNotFoundException {
        String nextID = Employeemodel.getText();
        lblEmployeeID.setText(nextID);
    }

    private void setcellvaluefactory() {
        Employeeidclm.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        Roleclm.setCellValueFactory(new PropertyValueFactory<>("role"));
        EmployeeNameclm.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        Contactclm.setCellValueFactory(new PropertyValueFactory<>("contact"));
        Groupidclm.setCellValueFactory(new PropertyValueFactory<>("groupId"));
        Emailclm.setCellValueFactory(new PropertyValueFactory<>("email"));

    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDto> employee = (ArrayList<EmployeeDto>) Emodel.getAll();

        ObservableList<EmployeeDto> employeeObservableList = FXCollections.observableArrayList();
        for (EmployeeDto E : employee) {
            employeeObservableList.add(E);
        }
        tblEmployee.setItems(employeeObservableList);
    }

    public void clickOnAction (MouseEvent mouseEvent){
        EmployeeDto selectedItem = (EmployeeDto) tblEmployee.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblEmployeeID.setText(selectedItem.getEmployeeId());
            txtRole.setText(selectedItem.getRole());
            txtEmployeeName.setText(String.valueOf(selectedItem.getEmployeeName()));
            txtContact.setText(selectedItem.getContact());
            ComboBoxGroupID.setValue(selectedItem.getGroupId());
            // save button disable
            btnSave.setDisable(true);
            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
    public void btnSaveOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String employeeID = lblEmployeeID.getText();
        String role = txtRole.getText();
        String employeeName = txtEmployeeName.getText();
        String contact = txtContact.getText();
        String groupID = (String) ComboBoxGroupID.getValue();
        String email = txtEmail.getText();


        EmployeeDto employee = new EmployeeDto(employeeID, role, employeeName, contact, groupID, email);
        boolean issave = Emodel.save(employee);

        if (issave) {
            new Alert(Alert.AlertType.INFORMATION, "Employee Saved", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Employee NotSaved", ButtonType.OK).show();
        }

    }

    public void btnResetOnAction (ActionEvent actionEvent){
    }

    public void btnUpdateOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String employeeID = lblEmployeeID.getText();
        String role = txtRole.getText();
        String employeeName = txtEmployeeName.getText();
        String contact = txtContact.getText();
        String groupID = (String) ComboBoxGroupID.getValue();
        String email = txtEmail.getText();

        EmployeeDto employee = new EmployeeDto(employeeID, role, employeeName, contact, groupID, email);
        boolean isupdate = Emodel.update(employee);

        if (isupdate) {
            new Alert(Alert.AlertType.INFORMATION, "Employee Update", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Employee NotUpdate", ButtonType.OK).show();
        }
    }

    public void btnDeleteOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String employeeID = lblEmployeeID.getText();
        boolean isDelete = Emodel.DeleteEmployee(employeeID);

        if (isDelete) {
            new Alert(Alert.AlertType.INFORMATION, "Employee Deleted", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Employee Not Deleted", ButtonType.OK).show();
        }

    }

    public void btnGenarateROnAction (ActionEvent actionEvent){
    }

}
