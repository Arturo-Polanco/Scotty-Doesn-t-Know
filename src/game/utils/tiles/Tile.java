package game.utils.tiles;

import game.utils.physics.BoundingShape;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 12/2/2014
 * Juego
 */
public class Tile {
	protected BoundingShape boundingShape;
	protected int           x;
	protected int           y;

	public Tile( int x, int y ) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public BoundingShape getBoundingShape() {
		return boundingShape;
	}
}
