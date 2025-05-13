package lk.ijse.sciencelab.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.sciencelab.Dto.GroupDto;
import lk.ijse.sciencelab.model.Groupmodel;

import java.sql.SQLException;
import java.util.ArrayList;

public class GroupPageController{
    private final Groupmodel Gmodel = new Groupmodel();
    public Button btnSave;
    public ImageView btnReset;
    public Button btnDelete;
    public Button btnGenarateReport;
    public Button btnUpdate;
    public TableColumn ScientistIDclm;
    public TableColumn ResearchOfProgressclm;
    public TableColumn Memberclm;
    public TableColumn Progressclm;
    public TableColumn GroupNameclm;
    public TableColumn GroupIDclm;
    public TableView tblGroup;
    public TextField txtResearchOfProgress;
    public TextField txtMember;
    public TextField txtProgress;
    public TextField txtGroupName;
    public Label lblGroupID;
    public ComboBox ComboBoxScientist;



    public void initialize() throws SQLException, ClassNotFoundException {
        setcellvaluefactory();
        setnextID();
ComboBoxScientist.setItems(Gmodel.getAllProjectID());
        loadtable();
    }

    private void setnextID() throws SQLException, ClassNotFoundException {
        String nextID = Groupmodel.getText();
        lblGroupID.setText(nextID);
    }

    private void setcellvaluefactory() {
        GroupIDclm.setCellValueFactory(new PropertyValueFactory<>("groupId"));
        GroupNameclm.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        Progressclm.setCellValueFactory(new PropertyValueFactory<>("progress"));
        Memberclm.setCellValueFactory(new PropertyValueFactory<>("member"));
        ResearchOfProgressclm.setCellValueFactory(new PropertyValueFactory<>("researchOfProgress"));
        ScientistIDclm.setCellValueFactory(new PropertyValueFactory<>("scientistID"));

    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<GroupDto> group = (ArrayList<GroupDto>) Gmodel.getAll();

        ObservableList<GroupDto> groupObservableList = FXCollections.observableArrayList();
        for (GroupDto E : group) {
            groupObservableList.add(E);
        }
        tblGroup.setItems(groupObservableList);
    }

    public void clickOnAction (MouseEvent mouseEvent){
        GroupDto selectedItem = (GroupDto) tblGroup.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblGroupID.setText(selectedItem.getGroupId());
            txtGroupName.setText(selectedItem.getGroupName());
            txtProgress.setText(selectedItem.getProgress());
            txtMember.setText(selectedItem.getMember());
            txtResearchOfProgress.setText(selectedItem.getResearchProgress());
            ComboBoxScientist.setValue(selectedItem.getScientistId());

            // save button disable
            btnSave.setDisable(true);
            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
    public void btnSaveOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String groupID = lblGroupID.getText();
        String groupName = txtGroupName.getText();
        String progress = txtProgress.getText();
        String member = txtMember.getText();
        String researchOfProgress = txtResearchOfProgress.getText();
        String scientistID = (String) ComboBoxScientist.getValue();


        GroupDto group = new GroupDto(groupID, groupName, progress, member, researchOfProgress, scientistID);
        boolean issave = Gmodel.save(group);

        if (issave) {
            new Alert(Alert.AlertType.INFORMATION, "Group Saved", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Group NotSaved", ButtonType.OK).show();
        }

    }

    public void btnResetOnAction (ActionEvent actionEvent){
    }

    public void btnUpdateOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String groupID = lblGroupID.getText();
        String groupName = txtGroupName.getText();
        String progress = txtProgress.getText();
        String member = txtMember.getText();
        String researchOfProgress = txtResearchOfProgress.getText();
        String scientistID = (String) ComboBoxScientist.getValue();

        GroupDto group = new GroupDto(groupID, groupName, progress, member, researchOfProgress, scientistID);
        boolean isupdate = Gmodel.update(group);

        if (isupdate) {
            new Alert(Alert.AlertType.INFORMATION, "Group Update", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Group NotUpdate", ButtonType.OK).show();
        }
    }

    public void btnDeleteOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String groupID = lblGroupID.getText();
        boolean isDelete = Gmodel.DeleteGroup(groupID);

        if (isDelete) {
            new Alert(Alert.AlertType.INFORMATION, "Group Deleted", ButtonType.OK).show();
            loadtable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Group Not Deleted", ButtonType.OK).show();
        }

    }

    public void btnGenarateROnAction (ActionEvent actionEvent){
    }

}
