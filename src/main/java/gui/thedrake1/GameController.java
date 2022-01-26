package gui.thedrake1;

import backend.GameState;
import gui.thedrake1.mechanics.BoardView;
import gui.thedrake1.mechanics.ValidMoves;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class GameController  {
    @FXML
    private BoardView boardView;
    @FXML
    private Stack stackViewBlue;
    @FXML
    private Stack stackViewOrange;

    private GameState gameState;

    private ValidMoves validMoves;



}
