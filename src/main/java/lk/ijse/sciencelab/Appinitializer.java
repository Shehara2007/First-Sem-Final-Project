package lk.ijse.sciencelab;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Appinitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(lk.ijse.sciencelab.Appinitializer.class.getResource("/view/DashBoard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ScienceLab!");
        stage.setScene(scene);
        stage.show();
    }
}
