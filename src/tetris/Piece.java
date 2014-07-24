package tetris;

import java.awt.Graphics;

public abstract class Piece {
	protected int x;
	protected int y;
	public Piece() {
		this.x = 0;
		this.y = 0;
	}
	public Piece(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return this.x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return this.y;
	}
	public void setY(int y) {
		this.y = y;
	}
	abstract void draw();
	// abstract void rotate();
	abstract Pair[] getPattern();
}
