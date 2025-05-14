package lk.ijse.sciencelab.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.sciencelab.Dto.ProjectDto;
import lk.ijse.sciencelab.model.Projectmodel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class ProjectPageController {
    private final Projectmodel Pmodel = new Projectmodel();
    public Label lblProjectID;
    public DatePicker txtStartDate;
    public DatePicker txtEndDate;
    public ImageView btnDelete;
    public ImageView btnUpdate;
    public Button btnReset;
    public Button btnSave;
    public Button btnGenarateReport;

    @FXML
    private TableColumn<?, ?> Descriptionclm;

    @FXML
    private TableColumn<?, ?> EndDateclm;

    @FXML
    private TableColumn<?, ?> FundingAmountclm;

    @FXML
    private TableColumn<?, ?> ProjectIDclm;

    @FXML
    private TableColumn<?, ?> StartDateclm;

    @FXML
    private TableColumn<?, ?> Titleclm;

    @FXML
    private TableView<ProjectDto> tblProject;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtFundingAmount;

    @FXML
    private TextField txtProjectID;

    @FXML
    private Label txtScientistID;

    @FXML
    private TextField txtTitle;

    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        setnextID();
        loadtable();
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<ProjectDto> project = Pmodel.getAll();

        ObservableList<ProjectDto> projectObservableList = FXCollections.observableArrayList();
        for (ProjectDto p : project) {
            projectObservableList.add(p);
        }

        tblProject.setItems(projectObservableList);
    }

    private void setnextID() throws SQLException, ClassNotFoundException {
        String nextID = Projectmodel.getText();
        lblProjectID.setText(nextID);

    }

    private void setcellvaluefactory() {
        ProjectIDclm.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        StartDateclm.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        EndDateclm.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        FundingAmountclm.setCellValueFactory(new PropertyValueFactory<>("fundingAmount"));
        Titleclm.setCellValueFactory(new PropertyValueFactory<>("title"));
        Descriptionclm.setCellValueFactory(new PropertyValueFactory<>("description"));

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String projectID = lblProjectID.getText();

        // Check if a project ID is selected
        if (projectID == null || projectID.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a project to delete.", ButtonType.OK).show();
            return;
        }

        // Confirmation alert before deletion
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Confirmation");
        confirmAlert.setHeaderText("Are you sure?");
        confirmAlert.setContentText("Do you really want to delete this project? This action cannot be undone.");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User confirmed deletion
            boolean isDelete = Pmodel.DeleteProject(projectID);

            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Project Deleted", ButtonType.OK).show();
                loadtable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Project Not Deleted", ButtonType.OK).show();
            }
        } else {
            // User cancelled deletion
            new Alert(Alert.AlertType.INFORMATION, "Deletion Cancelled", ButtonType.OK).show();
        }
    }


    @FXML
    void btnGenarateROnAction(ActionEvent event) {

    }

    @FXML
    void btnResetOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String projectID = lblProjectID.getText();
        String Title = txtTitle.getText();
        String description = txtDescription.getText();
        Double fundingAmount = Double.valueOf(txtFundingAmount.getText());
        String startDate = txtStartDate.getValue().toString();
        String endDate = txtEndDate.getValue().toString();

        ProjectDto project = new ProjectDto(projectID, startDate, endDate, fundingAmount, Title, description);
        boolean issave = Pmodel.save(project);

        if (issave) {
            new Alert(Alert.AlertType.INFORMATION, "Project Saved", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Project NotSaved", ButtonType.OK).show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String projectID = lblProjectID.getText();
        String Title = txtTitle.getText();
        String description = txtDescription.getText();
        Double fundingAmount = Double.valueOf(txtFundingAmount.getText());
        String startDate = txtStartDate.getValue().toString();
        String endDate = txtEndDate.getValue().toString();

        ProjectDto project = new ProjectDto(projectID, startDate, endDate, fundingAmount, Title, description);
        boolean isupdate = Pmodel.update(project);

        if (isupdate) {
            new Alert(Alert.AlertType.INFORMATION, "Project Update", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Project NotUpdate", ButtonType.OK).show();
        }

    }

    public void clickOnAction(MouseEvent mouseEvent) {
        ProjectDto selectedItem = tblProject.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblProjectID.setText(selectedItem.getProjectId());
            txtStartDate.setValue(LocalDate.parse(selectedItem.getStartDate()));
            txtEndDate.setValue(LocalDate.parse(selectedItem.getEndDate()));
            txtFundingAmount.setText(String.valueOf(selectedItem.getFundingAmount()));
            txtTitle.setText(selectedItem.getTitle());
            txtDescription.setText(selectedItem.getDescription());
            // save button disable
            btnSave.setDisable(true);
            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
