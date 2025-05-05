package lk.ijse.sciencelab.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.sciencelab.Dto.ChemicalDto;
import lk.ijse.sciencelab.Dto.ProjectDto;
import lk.ijse.sciencelab.model.Chemicalmodel;
import lk.ijse.sciencelab.model.Projectmodel;

import java.sql.SQLException;
import java.util.ArrayList;

public class ChemicalPageController {
    private final Chemicalmodel Cmodel = new Chemicalmodel();
    public TextField txtChemicalName;
    public TextField txtID;
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

    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        setnextID();
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

    public void btnGenarateROnAction(ActionEvent actionEvent) {
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

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }
}