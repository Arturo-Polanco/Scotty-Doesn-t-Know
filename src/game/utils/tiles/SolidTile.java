package game.utils.tiles;

import game.utils.physics.AABoundingRect;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 12/2/2014
 * Juego
 */
public class SolidTile extends Tile {
	public SolidTile( int x, int y ) {
		super( x, y );
		boundingShape = new AABoundingRect( x * 32, y * 32, 32, 32 );
	}
}