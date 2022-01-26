package backend;

import java.io.PrintWriter;

public class Board {
	private final int dimension;
	private final BoardTile [][] arr;

	// Konstruktor. Vytvoří čtvercovou hrací desku zadaného rozměru, kde všechny dlaždice jsou prázdné, tedy BoardTile.EMPTY
	public Board(int dimension) {
		arr = new BoardTile[dimension][dimension];

		for(int i = 0; i < dimension; i++){
			for(int j = 0; j < dimension; j++){

				arr[i][j] = BoardTile.EMPTY;
			}
		}

		this.dimension = dimension;
	}

	// Rozměr hrací desky
	public int dimension() {
		return dimension;
	}

	// Vrací dlaždici na zvolené pozici.
	public BoardTile at(TilePos pos) {
		return arr[pos.i()][pos.j()];
	}

	// Vytváří novou hrací desku s novými dlaždicemi. Všechny ostatní dlaždice zůstávají stejné
	public Board withTiles(TileAt ...ats) {

		 Board board = new Board(this.dimension);

		 for(int i = 0; i < this.dimension; i++){
			 for(int j = 0; j < this.dimension; j++){
				 board.arr[i][j] = arr[i][j];
			 }
		 }


		for(int i = 0; i < ats.length; i++){
			int pos1 = ats[i].pos.i();
			int pos2 = ats[i].pos.j();
			board.arr[pos1][pos2] = ats[i].tile;
		}


		return board;
	}
	// Vytvoří instanci PositionFactory pro výrobu pozic na tomto hracím plánu
	public PositionFactory positionFactory() {
		return new PositionFactory(dimension) ;
	}



    public static class TileAt {

		public final BoardPos pos;
		public final BoardTile tile;

		public TileAt(BoardPos pos, BoardTile tile) {
			this.pos = pos;
			this.tile = tile;
		}
	}

	public void toJSON(PrintWriter printWriter) {
		int counter = 0;


		printWriter.printf("{");
		printWriter.printf("\"dimension\":" + dimension() + ",");
		printWriter.printf("\"tiles\":[");


		for (int j = 0; j<dimension; j++) {
			for(int i = 0; i<dimension; i++){
				counter++;

				arr[i][j].toJSON(printWriter);
				if(counter < dimension()*dimension())
					printWriter.printf(",");
			}
		}

		printWriter.printf("]}");
	}

}

