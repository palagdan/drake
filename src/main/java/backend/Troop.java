package backend;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.PrintWriter;

public class Troop {
    private final String name;
    private Offset2D aversPivot;
    private Offset2D reversPivot;
    private final List<TroopAction> aversActions;
    private final List<TroopAction> reversActions;

    public Troop(String name,Offset2D aversPivot, Offset2D reversPivot,List<TroopAction> aversActions,List<TroopAction> reversActions){
        this.name = name;
        this.aversPivot = aversPivot;
        this.reversPivot = reversPivot;
        this.aversActions = aversActions;
        this.reversActions = reversActions;

    }
    public Troop(String name, Offset2D pivot,List<TroopAction> aversActions,List<TroopAction> reversActions){
        this.name = name;
        Offset2D aversPivot = new Offset2D(pivot.x, pivot.y);
        Offset2D reversPivot = new Offset2D(pivot.x, pivot.y);
        this.aversPivot = aversPivot;
        this.reversPivot = reversPivot;
        this.aversActions = aversActions;
        this.reversActions = reversActions;
    }
    public Troop(String name,List<TroopAction> aversActions, List<TroopAction> reversActions){
        this.name = name;
        Offset2D aversPivot = new Offset2D(1,1);
        Offset2D reversPivot = new Offset2D(1,1);
        this.aversPivot = aversPivot;
        this.reversPivot = reversPivot;
        this.aversActions = aversActions;
        this.reversActions = reversActions;
    }
    public void toJSON(PrintWriter printWriter) {
       printWriter.printf("\"" + name() + "\"");
    }

    public List<TroopAction> actions(TroopFace face){
        if(face == TroopFace.AVERS) return aversActions;

        return reversActions;
    }
    public String name(){
        return this.name;
    }


    public Offset2D pivot(TroopFace face){
        if(face == TroopFace.AVERS) return aversPivot;
        else  return reversPivot;
    }
}

