package lk.ijse.sciencelab.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.sciencelab.Dto.ProgressDto;
import lk.ijse.sciencelab.model.Progressmodel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProgressPageController{
    private final Progressmodel Pmodel = new Progressmodel();
    public Button btnSave;
    public ImageView btnReset;
    public Button btnDelete;
    public Button btnGenarateReport;
    public TableColumn LastUpdatedDateclm;
    public TableColumn Statusclm;
    public TableColumn ProjectIDclm;
    public TableColumn ProgressIDclm;
    public Button btnUpdate;
    public DatePicker DPLastUpdatedDate;
    public Label lblProgressID;
    public ComboBox ComboBoxProject;
    public TableView tblProgress;
    public TextField txtstatus;


    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        setnextID();
        ComboBoxProject.setItems(Pmodel.getAllProjectID());
        loadtable();
    }

    private void setnextID() throws SQLException, ClassNotFoundException {
        String nextID = Progressmodel.getText();
        lblProgressID.setText(nextID);
    }

    private void setcellvaluefactory() {
        ProgressIDclm.setCellValueFactory(new PropertyValueFactory<>("progressId"));
        ProjectIDclm.setCellValueFactory(new PropertyValueFactory<>("progressName"));
        Statusclm.setCellValueFactory(new PropertyValueFactory<>("status"));
        LastUpdatedDateclm.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedDate"));
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<ProgressDto> progress = Pmodel.getAll();

        ObservableList<ProgressDto> ProgressObservableList = FXCollections.observableArrayList();
        for (ProgressDto Pr : progress) {
            ProgressObservableList.add(Pr);
        }
        tblProgress.setItems(ProgressObservableList);
    }

    public void clickOnAction (MouseEvent mouseEvent){
        ProgressDto selectedItem = (ProgressDto) tblProgress.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblProgressID.setText(selectedItem.getProgressId());
            ComboBoxProject.setValue(selectedItem.getProjectId());
            txtstatus.setText(selectedItem.getStatus());
            DPLastUpdatedDate.setValue(LocalDate.parse(selectedItem.getLastUpdatedDate()));
            // save button disable
            btnSave.setDisable(true);
            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
    public void btnSaveOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String progressID = lblProgressID.getText();
        String projectID = (String) ComboBoxProject.getValue();
        String status = (txtstatus.getText());
        String lastUpdatedDate = String.valueOf(DPLastUpdatedDate.getValue());

        ProgressDto progress = new ProgressDto(progressID, projectID, status, lastUpdatedDate);
        boolean issave = Pmodel.save(progress);

        if (issave) {
            new Alert(Alert.AlertType.INFORMATION, " Progress Saved", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, " Progress NotSaved", ButtonType.OK).show();
        }

    }

    public void btnResetOnAction (ActionEvent actionEvent){
    }

    public void btnUpdateOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String progressID = lblProgressID.getText();
        String projectID = (String) ComboBoxProject.getValue();
        String status =(txtstatus.getText());
        String lastUpdatedDate = String.valueOf(DPLastUpdatedDate.getValue());

        ProgressDto progress = new ProgressDto(progressID, projectID, status, lastUpdatedDate);
        boolean isupdate = Pmodel.update(progress);

        if (isupdate) {
            new Alert(Alert.AlertType.INFORMATION, " Progress Update", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, " Progress NotUpdate", ButtonType.OK).show();
        }
    }

    public void btnDeleteOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String progressID = lblProgressID.getText();
        boolean isDelete = Pmodel.DeleteProgress(progressID);

        if (isDelete) {
            new Alert(Alert.AlertType.INFORMATION, " Progress Deleted", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, " Progress Not Deleted", ButtonType.OK).show();
        }

    }

    public void btnGenarateROnAction (ActionEvent actionEvent){
    }

}
