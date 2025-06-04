package lk.ijse.sciencelab.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.sciencelab.Dto.FunderDto;
import lk.ijse.sciencelab.model.Fundermodel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class FunderPageController {
    private final Fundermodel Fmodel = new Fundermodel();
    public Button btnSave;
    public ImageView btnReset;
    public Button btnDelete;
    public Button btnGenarateReport;
    public TableView tblFunder;
    public TableColumn FunderIDclm;
    public TableColumn FunderNameclm;
    public TableColumn Projectclm;
    public TableColumn Amountclm;
    public TableColumn Organizationclm;
    public TextField txtOrganization;
    public TextField txtAmount;
    public TextField txtFunderName;
    public Label lblFunderID;
    public Button btnUpdate;
    public ComboBox<String> ComboBoxProject;
    public ComboBox  <String> ComboBoxOrganization;

    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        setnextID();
            ComboBoxOrganization.setItems(FXCollections.observableArrayList( "National Science Laboratory","Advanced Research Institute","Global Science Collaborative","Center for Scientific Excellence","Institute of Experimental Science","ChemX Research Center (Chemistry)"));
        loadtable();
    }

    private void setnextID() throws SQLException, ClassNotFoundException {
        String nextID = Fundermodel.getText();
        lblFunderID.setText(nextID);
    }

    private void setcellvaluefactory() {
        FunderIDclm.setCellValueFactory(new PropertyValueFactory<>("funderId"));
        FunderNameclm.setCellValueFactory(new PropertyValueFactory<>("funderName"));
        Amountclm.setCellValueFactory(new PropertyValueFactory<>("amount"));
        Projectclm.setCellValueFactory(new PropertyValueFactory<>("project"));
        Organizationclm.setCellValueFactory(new PropertyValueFactory<>("organization"));
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<FunderDto> funder = Fmodel.getAll();

        ObservableList<FunderDto> funderObservableList = FXCollections.observableArrayList();
        for (FunderDto F : funder) {
            funderObservableList.add(F);
        }
        tblFunder.setItems(funderObservableList);
    }

    public void clickOnAction (MouseEvent mouseEvent){
        FunderDto selectedItem = (FunderDto) tblFunder.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblFunderID.setText(selectedItem.getFunderId());
            txtFunderName.setText(selectedItem.getFunderName());
            txtAmount.setText(String.valueOf(selectedItem.getAmount()));
            ComboBoxProject.setValue(selectedItem.getProject());
            ComboBoxOrganization.setValue(selectedItem.getOrganization());
            // save button disable
            btnSave.setDisable(true);
            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
    public void btnSaveOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String funderId = lblFunderID.getText();
        String funderName = txtFunderName.getText();
        Double amount = Double. valueOf(txtAmount.getText());
        String project = (String) ComboBoxProject.getValue();
        String organization = ComboBoxOrganization.getValue();

        FunderDto funder = new FunderDto(funderId, funderName, amount, project, organization);
        boolean issave = Fmodel.save(funder);

        if (issave) {
            new Alert(Alert.AlertType.INFORMATION, "Funder Saved", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Funder NotSaved", ButtonType.OK).show();
        }

    }

    public void btnResetOnAction (ActionEvent actionEvent){
    }

    public void btnUpdateOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String funderId = lblFunderID.getText();
        String funderName = txtFunderName.getText();
        Double amount = Double. valueOf(txtAmount.getText());
        String project = (String) ComboBoxProject.getValue();
        String organization = ComboBoxOrganization.getValue();

        FunderDto funder = new FunderDto(funderId, funderName, amount, project, organization);
        boolean isupdate = Fmodel.update(funder);

        if (isupdate) {
            new Alert(Alert.AlertType.INFORMATION, "Funder Update", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Funder NotUpdate", ButtonType.OK).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String funderID = lblFunderID.getText();

        // Check if a funder is selected
        if (funderID == null || funderID.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a funder to delete.", ButtonType.OK).show();
            return;
        }

        // Show confirmation alert
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Confirmation");
        confirmAlert.setHeaderText("Are you sure?");
        confirmAlert.setContentText("Do you really want to delete this funder? This action cannot be undone.");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // If confirmed, delete
            boolean isDelete = Fmodel.DeleteFunder(funderID);

            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Funder Deleted", ButtonType.OK).show();
                loadtable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Funder Not Deleted", ButtonType.OK).show();
            }
        } else {
            // If cancelled
            new Alert(Alert.AlertType.INFORMATION, "Deletion Cancelled", ButtonType.OK).show();
        }
    }


    public void btnGenarateROnAction (ActionEvent actionEvent){
    }

}
