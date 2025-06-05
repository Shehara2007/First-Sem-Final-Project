package lk.ijse.sciencelab.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sciencelab.Dto.ProjectDto;
import lk.ijse.sciencelab.model.Projectmodel;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectPageController {
    private final Projectmodel Pmodel = new Projectmodel();
    @FXML
    private Label lblProjectID;

    @FXML
    private TextField txtTitle;

    @FXML
    private DatePicker txtStartDate;

    @FXML
    private TextField txtFundingAmount;

    @FXML
    private Label txtScientistID;

    @FXML
    private DatePicker txtEndDate;

    @FXML
    private TextField txtDescription;

    @FXML
    private ComboBox<?> ComboBoxItems;

    @FXML
    private AnchorPane ancItemUI;

    @FXML
    private TableColumn<?, ?> ItemIDclm;

    @FXML
    private TableColumn<?, ?> Nameclm;

    @FXML
    private TableColumn<?, ?> Quantityclm;

    @FXML
    private TableColumn<?, ?> Actionclm;

    @FXML
    private TableView<ProjectDto> tblProject;

    public void initialize() throws SQLException, ClassNotFoundException {
        setnextID();
        ComboBoxItems.setItems(FXCollections.observableArrayList("Equipment","Chemical"));
        loadtable();
        setCellValueFactory();
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


    private void nevigateTo(String s) {
        try {
            ancItemUI.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(s));

            pane.prefWidthProperty().bind(ancItemUI.widthProperty());
            pane.prefHeightProperty().bind(ancItemUI.heightProperty());

            ancItemUI.getChildren().add(pane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page Not Found!").show();
            e.printStackTrace();

        }
    }

    public void ItemSearchOnAction(ActionEvent actionEvent) {
        if(ComboBoxItems.getSelectionModel().getSelectedItem().equals("Equipment")) {
            nevigateTo("/view/EquipmentCartPage.fxml");
        } else if (ComboBoxItems.getSelectionModel().getSelectedItem().equals( "Chemical")) {
            nevigateTo("/view/ChemicalCartPage.fxml");
        }
    }

    private void setCellValueFactory() {
        ItemIDclm.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("factoryEmployeeNumber"));

    }

    public void AddItemOnAction(ActionEvent actionEvent) {
    }

    public void PlaceOrderOnAction(ActionEvent actionEvent) {
    }

    public void clickOnAction(MouseEvent mouseEvent) {
    }
}
