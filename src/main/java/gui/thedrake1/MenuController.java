package gui.thedrake1;

import gui.thedrake1.mechanics.BoardView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuController {


    @FXML
    Button singlePlayer;

    @FXML
    Button twoPlayers;

    @FXML
    Button onlineGame;

    @FXML
    Button quit;


    @FXML
    private void setTwoPlayers(ActionEvent ae) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameController.fxml"));


        Stage stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root, 1000,800);
        stage.setScene(scene);
       // scene.getStylesheets().add(getClass().getResource("mainMenu.css").toExternalForm());
        stage.show();

    }

    @FXML
    private void quit(ActionEvent ae){
        Platform.exit();
    }


}