package game.utils.physics;

import game.entities.Enemy;
import game.entities.Entity;
import game.entities.Player;
import game.utils.level.Level;
import game.utils.tiles.Tile;

import java.util.ArrayList;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 12/2/2014
 * Juego
 */
public class AABoundingRect extends BoundingShape {
	private float x;
	private float y;
	private float width;
	private float height;

	public AABoundingRect( float x, float y, float width, float height ) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean checkTileCollision( AABoundingRect rect ) {
		return !( rect.x > this.x + width || rect.x + rect.width < this.x || rect.y > this.y + height || rect.y + rect.height < this.y );
	}

	public boolean checkEntityCollision( Entity entity ) {
		for ( Entity entity1 : Level.entities ) {
			if ( entity != entity1 )
				if ( entity.hitBox.intersects( entity1.hitBox ) && entity1 instanceof Player || entity1 instanceof Enemy )
					return true;
		}
		return false;
	}

	public void updatePosition( float newX, float newY ) {
		this.x = newX;
		this.y = newY;
	}

	public void movePosition( float x, float y ) {
		this.x += x;
		this.y += y;
	}

	public ArrayList<Tile> getTilesOccupying( Tile[][] tiles ) {
		ArrayList<Tile> tilesOccupied = new ArrayList<>();
		for ( int i = (int) x; i <= x + width + ( 32 - width % 32 ); i++ ) {
			for ( int j = (int) y; j <= y + height + ( 32 - height % 32 ); j += 32 ) {
				try {
					tilesOccupied.add( tiles[i / 32][j / 32] );
				} catch ( Exception e ) {
					return tilesOccupied;
				}
			}
		}
		return tilesOccupied;
	}

	public ArrayList<Tile> getGroundTiles( Tile[][] tiles ) {
		ArrayList<Tile> tilesUnderneath = new ArrayList<>();
		int j = ( (int) ( y + height + 1 ) );
		for ( int i = ( (int) x ); i <= x + width + ( 32 - width % 32 ); i++ )
			try {
				tilesUnderneath.add( tiles[i / 32][j / 32] );
			} catch ( Exception e ) {
				return tilesUnderneath;
			}
		return tilesUnderneath;
	}
}
