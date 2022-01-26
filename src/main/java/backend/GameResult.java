package backend;

import java.io.PrintWriter;

public enum GameResult {
	VICTORY, DRAW, IN_PLAY;

    public void toJSON(PrintWriter printWriter) {
        printWriter.printf("\"" + this + "\"");
    }

}
