package game.utils.physics;

import game.utils.tiles.Tile;

import java.util.ArrayList;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 12/2/2014
 * Juego
 */
public abstract class BoundingShape {

	public boolean checkCollision(BoundingShape bs){
		if(bs instanceof AABoundingRect)
			return checkCollision( (AABoundingRect ) bs);
		return false;
	}

	public abstract boolean checkCollision(AABoundingRect box);

	public abstract void updatePosition(float newX, float newY);

	public abstract void movePosition(float x, float y);

	public abstract ArrayList<Tile> getTilesOccupying(Tile[][] tiles);

	public abstract ArrayList<Tile> getGroundTiles(Tile[][] tiles);
}
