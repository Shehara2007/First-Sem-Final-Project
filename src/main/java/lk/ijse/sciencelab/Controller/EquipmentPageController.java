package lk.ijse.sciencelab.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.sciencelab.Dto.EquipmentDto;
import lk.ijse.sciencelab.model.Equipmentmodel;

import java.sql.SQLException;
import java.util.ArrayList;

public class EquipmentPageController{
    private final Equipmentmodel Eqmodel = new Equipmentmodel();
    public Button btnSave;
    public Button btnDelete;
    public Button btnGenarateReport;
    public Button btnUpdate;
    public TableColumn SupIDclm;
    public TableColumn Typeclm;
    public TableColumn Quantityclm;
    public TableColumn EquipmentNameclm;
    public TableColumn EquipmentIDclm;
    public TableView tblEquipment;
    public TextField txtType;
    public TextField txtQuantity;
    public TextField txtEquipmentName;
    public Label lblEquipmentID;
    public ComboBox <String> ComboBoxSupplier;
    public Button btnReset;


    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        setnextID();
        ComboBoxSupplier.setItems(Eqmodel.getAllProjectID());
        loadtable();
    }

    private void setnextID() throws SQLException, ClassNotFoundException {
        String nextID = Equipmentmodel.getText();
        lblEquipmentID.setText(nextID);
    }

    private void setcellvaluefactory() {
        EquipmentIDclm.setCellValueFactory(new PropertyValueFactory<>("equipmentId"));
        EquipmentNameclm.setCellValueFactory(new PropertyValueFactory<>("equipmentName"));
        Quantityclm.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        Typeclm.setCellValueFactory(new PropertyValueFactory<>("type"));
        SupIDclm.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<EquipmentDto> equipment = Eqmodel.getAll();

        ObservableList<EquipmentDto> equipmentObservableList = FXCollections.observableArrayList();
        for (EquipmentDto Eq : equipment) {
            equipmentObservableList.add(Eq);
        }
        tblEquipment.setItems(equipmentObservableList);
    }

    public void clickOnAction (MouseEvent mouseEvent){
        EquipmentDto selectedItem = (EquipmentDto) tblEquipment.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblEquipmentID.setText(selectedItem.getEquipmentId());
            txtEquipmentName.setText(selectedItem.getEquipmentName());
            txtQuantity.setText(String.valueOf(selectedItem.getQuantity()));
            txtType.setText(selectedItem.getType());
            ComboBoxSupplier.setValue(selectedItem.getSupplierId());
            // save button disable
            btnSave.setDisable(true);
            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
    public void btnSaveOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String equipmentId = lblEquipmentID.getText();
        String equipmentName = txtEquipmentName.getText();
        String quantity = txtQuantity.getText();
        String type = txtType.getText();
        String supplierId = (String) ComboBoxSupplier.getValue();

        EquipmentDto equipment = new EquipmentDto(equipmentId, equipmentName, quantity, type, supplierId);
        boolean issave = Eqmodel.save(equipment);

        if (issave) {
            new Alert(Alert.AlertType.INFORMATION, "Equipment Saved", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Equipment NotSaved", ButtonType.OK).show();
        }

    }

    public void btnResetOnAction (ActionEvent actionEvent){
    }

    public void btnUpdateOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String equipmentId = lblEquipmentID.getText();
        String equipmentName = txtEquipmentName.getText();
        String quantity = txtQuantity.getText();
        String type = txtType.getText();
        String supplierId = (String) ComboBoxSupplier.getValue();

        EquipmentDto equipment = new EquipmentDto(equipmentId, equipmentName, quantity, type, supplierId);
        boolean isupdate = Eqmodel.update(equipment);

        if (isupdate) {
            new Alert(Alert.AlertType.INFORMATION, "Equipment Update", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Equipment NotUpdate", ButtonType.OK).show();
        }
    }

    public void btnDeleteOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String equipmentID = lblEquipmentID.getText();
        boolean isDelete = Eqmodel.DeleteEquipment(equipmentID);

        if (isDelete) {
            new Alert(Alert.AlertType.INFORMATION, "Equipment Deleted", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Equipment Not Deleted", ButtonType.OK).show();
        }

    }

    public void btnGenarateROnAction (ActionEvent actionEvent){
    }

}
