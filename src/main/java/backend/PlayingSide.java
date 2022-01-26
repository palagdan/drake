package backend;
import java.io.PrintWriter;

public enum  PlayingSide {
    ORANGE,BLUE;

    public void toJSON(PrintWriter printWriter) {
        printWriter.printf("\"" + this + "\"");
    }

}
