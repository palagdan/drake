package backend;

import java.util.ArrayList;
import java.util.List;

public class SlideAction extends TroopAction {

    public SlideAction(Offset2D offset) {super(offset);}

    public SlideAction(int offsetX, int offsetY){
        super(offsetX,offsetY);
    }

    @Override
    public List<Move> movesFrom(BoardPos origin, PlayingSide side, GameState state) {
        TilePos tar = origin.stepByPlayingSide(offset(), side);
        List<Move> result = new ArrayList<>();
        while (!tar.toString().equals("off-board")) {
            if (state.canStep(origin, tar)) {
                result.add(new StepOnly(origin, (BoardPos) tar));
            } else if (state.canCapture(origin, tar)) {
                result.add(new StepAndCapture(origin, (BoardPos) tar));
                break;
            }
            tar = tar.stepByPlayingSide(offset(), side);
        }

        return result;
    }

}
