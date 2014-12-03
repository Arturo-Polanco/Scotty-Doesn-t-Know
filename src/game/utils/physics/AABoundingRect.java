package game.utils.physics;

import game.utils.tiles.Tile;

import java.util.ArrayList;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 12/2/2014
 * Juego
 */
public class AABoundingRect extends BoundingShape{
	public float x;
	public float y;
	public float width;
	public float height;

	public AABoundingRect( float x, float y, float width, float height ) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void updatePosition(float newX, float newY){
		this.x = newX;
		this.y = newY;
	}

	public void movePosition(float x, float y){
		this.x += x;
		this.y += y;
	}

	public boolean checkCollision( AABoundingRect rect ) {
		return !( rect.x > this.x+width || rect.x+rect.width < this.x || rect.y > this.y+height || rect.y+rect.height < this.y);
	}

	public ArrayList<Tile> getTilesOccupying( Tile[][] tiles ) {
		ArrayList<Tile> tilesOccupied = new ArrayList<>();

		for(int i = (int)x; i <= x+width+(32-width%32);i++ ){
			for(int j = (int) y; j <= y+height+(32-height%32); j+=32){
				try {
					tilesOccupied.add( tiles[i / 32][j / 32] );
				}catch ( Exception e ){
					return tilesOccupied;
				}
			}
		}
		return tilesOccupied;
	}

	public ArrayList<Tile> getGroundTiles( Tile[][] tiles ) {
		ArrayList<Tile> tilesUnderneath = new ArrayList<>();
		int j = ( (int) ( y + height + 1 ) );

		for(int i = ( (int) x ); i <= x+width+(32-width%32); i++)
			try {
				tilesUnderneath.add( tiles[i / 32][j / 32] );
			}catch(Exception e){
				return tilesUnderneath;
			}
		return tilesUnderneath;
	}
}
