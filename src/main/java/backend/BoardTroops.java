package backend;

import java.util.*;
import java.io.PrintWriter;


public class BoardTroops {
	private final PlayingSide playingSide;
	private final Map<BoardPos, TroopTile> troopMap;
	private final TilePos leaderPosition;
	private final int guards;
	
	public BoardTroops(PlayingSide playingSide) { 
		this.playingSide = playingSide;
		leaderPosition = TilePos.OFF_BOARD;
		guards = 0;
		troopMap = Collections.emptyMap();
	}
	
	public BoardTroops(
			PlayingSide playingSide,
			Map<BoardPos, TroopTile> troopMap,
			TilePos leaderPosition, 
			int guards) {
		this.playingSide = playingSide;
		this.troopMap = troopMap;
		this.leaderPosition = leaderPosition;
		this.guards = guards;
	}

	public boolean MapControl(TilePos target){
		for(Map.Entry<BoardPos,TroopTile> entry : troopMap.entrySet()){
			if(target.isNextTo(entry.getKey())) return true;
		}
		return false;
	}

	public Optional<TroopTile> at(TilePos pos) {
		 if(troopMap.containsKey(pos)) {
			 return Optional.of(troopMap.get(pos));
		 }
		 return Optional.empty();
	}

	public PlayingSide playingSide() {
		return this.playingSide;
	}
	
	public TilePos leaderPosition() {
		if(leaderPosition != TilePos.OFF_BOARD) {
			return leaderPosition;
		}
		return TilePos.OFF_BOARD;
	}

	public int guards() {
		return this.guards;
	}
	
	public boolean isLeaderPlaced() {
		if(leaderPosition == TilePos.OFF_BOARD) return false;

		return true;
	}
	
	public boolean isPlacingGuards() {
		return isLeaderPlaced() && guards < 2;
	}	
	
	public Set<BoardPos> troopPositions() {
		return troopMap.keySet() ;
	}

	public BoardTroops placeTroop(Troop troop, BoardPos target) {
		if((at(target).isPresent())) throw new IllegalArgumentException();

		Map<BoardPos, TroopTile> newTroops1 = new HashMap<>(troopMap);

		TroopTile tile = new TroopTile(troop,playingSide,TroopFace.AVERS);


		if(leaderPosition == TilePos.OFF_BOARD){
			newTroops1.put(target, tile);
			return  new BoardTroops(playingSide,newTroops1,target,guards);
		}
		if(isLeaderPlaced() && guards < 2){
			newTroops1.put(target, tile);
			return new BoardTroops(playingSide,newTroops1,leaderPosition,guards+1);
		}

		newTroops1.put(target, tile);
		return new BoardTroops(playingSide,newTroops1,leaderPosition,guards);

	}
	
	public BoardTroops troopStep(BoardPos origin, BoardPos target) {
		if(!isLeaderPlaced()) {
			throw new IllegalStateException(
					"Cannot move troops before the leader is placed.");
		}

		if(isPlacingGuards()) {
			throw new IllegalStateException(
					"Cannot move troops before guards are placed.");
		}
		if(at(origin).isEmpty()) throw new IllegalArgumentException();
		if(at(target).isPresent()) throw new IllegalArgumentException();

		Map<BoardPos, TroopTile> newPosition = new HashMap<>(troopMap);


		TroopTile tile = newPosition.remove(origin);
		newPosition.put(target, tile.flipped());


		if(origin.i() == leaderPosition.i() && origin.j() == leaderPosition.j()){
			return new BoardTroops(playingSide,newPosition,target,guards);
		}



		return new BoardTroops(playingSide,newPosition,leaderPosition,guards);
	}
	
	public BoardTroops troopFlip(BoardPos origin) {
		if(!isLeaderPlaced()) {
			throw new IllegalStateException(
					"Cannot move troops before the leader is placed.");			
		}
		
		if(isPlacingGuards()) {
			throw new IllegalStateException(
					"Cannot move troops before guards are placed.");			
		}
		
		if(!at(origin).isPresent())
			throw new IllegalArgumentException();
		
		Map<BoardPos, TroopTile> newTroops = new HashMap<>(troopMap);
		TroopTile tile = newTroops.remove(origin);
		newTroops.put(origin, tile.flipped());

		return new BoardTroops(playingSide(), newTroops, leaderPosition, guards);
	}
	
	public BoardTroops removeTroop(BoardPos target) {

		if(!isLeaderPlaced()) throw new IllegalStateException();
		if(isPlacingGuards()) throw new IllegalStateException();
		if(!at(target).isPresent()) throw new IllegalArgumentException();

		Map<BoardPos, TroopTile> RemoveTroops = new HashMap<>(troopMap);

		if(target.i() == leaderPosition.i() && target.j() == leaderPosition.j()){
			RemoveTroops.remove(target);
			return new BoardTroops(playingSide,RemoveTroops,TilePos.OFF_BOARD,guards);
		}

		RemoveTroops.remove(target);

		return new BoardTroops(playingSide,RemoveTroops,leaderPosition, guards);
	}
		public void toJSON(PrintWriter printWriter){
			//printWriter.printf("\"boardTroops\":{");
			printWriter.printf("{");
			printWriter.printf("\"side\":");
			playingSide.toJSON(printWriter);
			printWriter.printf(",");

			printWriter.printf("\"leaderPosition\":");
			leaderPosition.toJSON(printWriter);
			printWriter.printf(",");

			printWriter.printf("\"guards\":" + guards() + ",");
			armyMapJSON(printWriter);
			printWriter.printf("}");

		}

	public void armyMapJSON(PrintWriter printWriter){
		Map<BoardPos, TroopTile> armyMap = new TreeMap<>(troopMap);
		printWriter.printf("\"troopMap\":{");
		int count = 0;
		for(BoardPos pos : armyMap.keySet()) {
			count++;
			pos.toJSON(printWriter);
			printWriter.printf(":");




			troopMap.get(pos).toJSON(printWriter);


			if(count < troopMap.size())
				printWriter.printf(",");
		}

		printWriter.printf("}");
	}


}
