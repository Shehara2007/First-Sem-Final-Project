package lk.ijse.sciencelab.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.sciencelab.Dto.ChemicalDto;
import lk.ijse.sciencelab.Dto.GroupDto;
import lk.ijse.sciencelab.Dto.ProjectDto;
import lk.ijse.sciencelab.model.Chemicalmodel;
import lk.ijse.sciencelab.model.Projectmodel;

import java.sql.SQLException;
import java.util.ArrayList;

public class ChemicalPageController {
    private final Chemicalmodel Cmodel = new Chemicalmodel();
    public TextField txtChemicalName;
    public TextField txtQuantity;
    public TextField txtConcentration;
    public TextField txtSupID;
    public TableView tblChemical;
    public Label lblChemicalID;
    public TableColumn ChemicalIDclm;
    public TableColumn Quantityclm;
    public TableColumn ChemicalNameclm;
    public TableColumn Concentrationclm;
    public TableColumn SupIDclm;
    public ComboBox ComboBoxSupplier;
    public ImageView btnSave;
    public Button btnReset;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnGenarateReport;

    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        setnextID();
        ComboBoxSupplier.setItems(Cmodel.getAllSupplier());
        loadtable();
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<ChemicalDto> chemical = Cmodel.getAll();

        ObservableList<ChemicalDto> chemicalObservableList = FXCollections.observableArrayList();
        for (ChemicalDto p : chemical) {
            chemicalObservableList.add(p);
        }
        tblChemical.setItems(chemicalObservableList);
    }

    private void setnextID() throws SQLException, ClassNotFoundException {
        String nextID = Chemicalmodel.getText();
        lblChemicalID.setText(nextID);
    }

    private void setcellvaluefactory() {
        ChemicalIDclm.setCellValueFactory(new PropertyValueFactory<>("ChemicalID"));
        ChemicalNameclm.setCellValueFactory(new PropertyValueFactory<>("ChemicalName"));
        Quantityclm.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        Concentrationclm.setCellValueFactory(new PropertyValueFactory<>("Concentration"));
        SupIDclm.setCellValueFactory(new PropertyValueFactory<>("SupID"));
    }


    public void clickOnAction (MouseEvent mouseEvent){
        ChemicalDto selectedItem = (ChemicalDto) tblChemical.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblChemicalID.setText(selectedItem.getChemicalId());
            txtChemicalName.setText(selectedItem.getChemicalName());
            txtQuantity.setText(selectedItem.getQuantity());
            txtConcentration.setText(selectedItem.getConcentration());
            txtSupID.setText(selectedItem.getSupplierId());

            // save button disable
            btnSave.setDisable(true);
            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
    public void btnSaveOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String chemicalId = lblChemicalID.getText();
        String chemicalName = txtChemicalName.getText();
        String quantity =(txtQuantity.getText());
        String concentration = txtConcentration.getText();
        String supplierId = txtSupID.getText();

        ChemicalDto chemical = new ChemicalDto(chemicalId, chemicalName, quantity, concentration, supplierId);
        boolean issave = Cmodel.save(chemical);

        if (issave) {
            new Alert(Alert.AlertType.INFORMATION, "Chemical Saved", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Chemical NotSaved", ButtonType.OK).show();
        }

    }


    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String ChemicalID = lblChemicalID.getText();
        boolean isDelete = Cmodel.DeleteChemical(ChemicalID);

        if (isDelete) {
            new Alert(Alert.AlertType.INFORMATION, "Chemical Deleted", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Chemical Not Deleted", ButtonType.OK).show();
        }
    }

    public void btnResetOnAction (ActionEvent actionEvent){
    }

    public void btnUpdateOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String chemicalId = lblChemicalID.getText();
        String chemicalName = txtChemicalName.getText();
        String quantity = (txtQuantity.getText());
        String concentration = txtConcentration.getText();
        String supplierId = txtSupID.getText();

        ChemicalDto chemical = new ChemicalDto(chemicalId, chemicalName, quantity, concentration, supplierId);
        boolean isupdate = Cmodel.update(chemical);

        if (isupdate) {
            new Alert(Alert.AlertType.INFORMATION, "Chemical Update", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Chemical NotUpdate", ButtonType.OK).show();
        }
    }


    public void btnGenarateROnAction (ActionEvent actionEvent){
    }

}
