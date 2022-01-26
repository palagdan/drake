package backend;
import java.io.PrintWriter;

public enum  TroopFace {
    AVERS,REVERS;
    public void toJSON(PrintWriter printWriter) {
        printWriter.printf("\"" + this + "\"");
    }


}
