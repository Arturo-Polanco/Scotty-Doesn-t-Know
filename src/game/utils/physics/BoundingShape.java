package game.utils.physics;

import game.entities.Entity;
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
			return checkTileCollision( (AABoundingRect) bs );
		return false;
	}

	public abstract boolean checkTileCollision( AABoundingRect box );

	public abstract boolean checkEntityCollision( Entity entity );

	public abstract void updatePosition(float newX, float newY);

	public abstract void movePosition(float x, float y);

	public abstract ArrayList<Tile> getTilesOccupying(Tile[][] tiles);

	public abstract ArrayList<Tile> getGroundTiles(Tile[][] tiles);
}
