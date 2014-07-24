package tetris;

public class Engine {
	public int[][] board;
	public int boardSize = 10;
	public Piece currentPiece;
	
	public Engine() {
		this.currentPiece = nextPiece();
		this.board = new int[this.boardSize][this.boardSize];
		for (int i = 0; i < this.boardSize; i++) {
			for (int j = 0; j < this.boardSize; j++) {
				if (j == this.boardSize - 1) {
					this.board[i][j] = 1;
				} else {
					this.board[i][j] = 0;
				}
			}
		}
	}
	
	public Piece nextPiece() {
		// picked randomly :O
		return new SquarePiece(this.boardSize/2, 0);
	}
	
	public void setPiece(Piece p) {
		Pair[] pairs = p.getPattern();
		for (int i = 0; i < pairs.length; i++) {
			this.board[pairs[i].x][pairs[i].y] = 1;
		}
	}
	
	public Boolean noCollisions(Piece p) {
		Pair[] pairs = p.getPattern();
		for (int i = 0; i < pairs.length; i++) {
			if (this.board[pairs[i].x][pairs[i].y] == 1) {
				return false;
			}
		}
		return true;
	}
	
	public void tick() {
		// deal with time somehow
		Piece newPiece = new SquarePiece(currentPiece.getX(), currentPiece.getY() + 1);
		if (noCollisions(newPiece)) {
			currentPiece = newPiece;
			return;
		}
		setPiece(currentPiece);
	}
	
}
