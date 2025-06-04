package lk.ijse.sciencelab.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sciencelab.Dto.ProjectDto;
import lk.ijse.sciencelab.model.Projectmodel;

import java.sql.SQLException;
import java.util.ArrayList;

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
    public ComboBox <String> ComboBoxItems;
    public AnchorPane ancItemUI;
    public TableColumn ItemIDclm;
    public TableColumn Nameclm;
    public TableColumn Quantityclm;
    public TableColumn Actionclm;

    @FXML
    private TableView<ProjectDto> tblProject;

    public void initialize() throws SQLException, ClassNotFoundException {
        setnextID();
        ComboBoxItems.setItems(FXCollections.observableArrayList("Equipment","Chemical"));
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
        }    }

    public void AddItemOnAction(ActionEvent actionEvent) {
    }

    public void PlaceOrderOnAction(ActionEvent actionEvent) {
    }

    public void clickOnAction(MouseEvent mouseEvent) {
    }
}
