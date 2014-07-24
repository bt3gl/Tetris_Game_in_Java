package tetris;

import java.awt.Graphics;

public class SquarePiece extends Piece {
	public SquarePiece() {
		super();
	}
	public SquarePiece(int x, int y) {
		super(x, y);
	}

	public Pair[] getPattern() {
		return new Pair[] { new Pair(this.x, this.y), new Pair(this.x+1, this.y),
				new Pair(this.x, this.y+1), new Pair(this.x+1, this.y+1)};
	}
	
	public void draw(Graphics surface) {
		Pair[] p = this.getPattern();
		
		
	}
}


