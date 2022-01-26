package gui.thedrake1;

import backend.*;

import gui.thedrake1.mechanics.BoardView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
public class TheDrakeApp extends Application {



    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
       menu(primaryStage);
    }

    public void menu(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TheDrakeApp.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        stage.getIcons().add(new Image(Objects.requireNonNull(TheDrakeApp.class.getResourceAsStream("images/icon.png")))) ;
        scene.getStylesheets().add(TheDrakeApp.class.getResource("mainMenu.css").toExternalForm());
        stage.setTitle("The Drake");
        stage.setScene(scene);
        stage.show();

    }



         


}