package backend;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TroopTile implements Tile {
    private TroopAction troopAction;

    private PlayingSide side;
    private Troop troop;
    private TroopFace face;

    public TroopTile(Troop troop, PlayingSide side, TroopFace face) {
        this.troop = troop;
        this.face = face;
        this.side = side;
    }

    public PlayingSide side() {
        if (this.side == PlayingSide.BLUE) return PlayingSide.BLUE;
        else return PlayingSide.ORANGE;
    }

    public TroopFace face() {
        if (this.face == TroopFace.REVERS) return TroopFace.REVERS;
        else return TroopFace.AVERS;
    }

    public Troop troop() {
        return this.troop;
    }

    public boolean canStepOn() {
        return false;
    }

    public boolean hasTroop() {
        return true;
    }

    @Override
    public void toJSON(PrintWriter printWriter) {
        printWriter.printf("{");
        printWriter.printf("\"troop\":");
        troop.toJSON(printWriter);

        printWriter.printf(",");
        printWriter.printf("\"side\":");
        side.toJSON(printWriter);

        printWriter.printf(",");
        printWriter.printf("\"face\":");
        face.toJSON(printWriter);
        printWriter.printf("}");
    }

    public TroopTile flipped() {
        if (this.face == TroopFace.REVERS)
            return new TroopTile(this.troop, this.side, TroopFace.AVERS);
        else return new TroopTile(this.troop, this.side, TroopFace.REVERS);
    }


    public List<Move> movesFrom(BoardPos pos, GameState state) {
        List<Move> result = new ArrayList<>();
        for (TroopAction action : troop.actions(face)) {
            result.addAll(action.movesFrom(pos, side, state));
        }

        return result;
    }
}
