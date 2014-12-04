package game.utils.level;

import game.entities.Enemy;
import game.entities.Entity;
import game.entities.Player;
import game.resources.Resources;
import game.states.Game_State;
import game.utils.tiles.AirTile;
import game.utils.tiles.SolidTile;
import game.utils.tiles.Tile;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 12/2/2014
 * Juego
 */
public class Level {
	public static ArrayList<Entity> entities;
	public static ArrayList<Entity> enemies;
	public        String            levelMap;
	public        Tile[][]          tiles;
	public        Player            player;

	public Level( String level, Player player ) throws SlickException {
		entities = new ArrayList<>();
		enemies = new ArrayList<>();
		this.player = player;
		levelMap = level;
		entities.add( player );
		loadTileMap();
		loadObjects();
	}

	private void loadTileMap() {
		int mapWidth = Resources.maps.get( levelMap ).getWidth();
		int mapHeight = Resources.maps.get( levelMap ).getHeight();
		tiles = new Tile[mapWidth][mapHeight];
		int layerIndex = Resources.maps.get( levelMap ).getLayerIndex( "Floor" );
		for ( int x = 0; x < mapWidth; x++ ) {
			for ( int y = 0; y < mapHeight; y++ ) {
				int tileID = Resources.maps.get( levelMap ).getTileId( x, y, layerIndex );
				Tile tile = null;
				switch ( Resources.maps.get( levelMap ).getTileProperty( tileID, "tileType", "solid" ) ) {
					case "air":
						tile = new AirTile( x, y );
						break;
					default:
						tile = new SolidTile( x, y );
						break;
				}
				tiles[x][y] = tile;
			}
		}
	}

	private void renderBackground() {
		Image background = Resources.getImage( "Background" );
		float backgroundXScrollValue = ( background.getWidth() - Game_State.width / Game_State.SCALE );
		float backgroundYScrollValue = ( background.getHeight() - Game_State.height / Game_State.SCALE );
		float mapXScrollValue = ( (float) Resources.maps.get( levelMap ).getWidth() * 32 - Game_State.width / Game_State.SCALE );
		float mapYScrollValue = ( (float) Resources.maps.get( levelMap ).getHeight() * 32 - Game_State.height / Game_State.SCALE );
		float scrollXFactor = backgroundXScrollValue / mapXScrollValue * -1;
		float scrollYFactor = backgroundYScrollValue / mapYScrollValue * -1;
		background.draw( this.getXOffset() * scrollXFactor, this.getYOffset() * scrollYFactor );
	}

	private void loadObjects() throws SlickException {
		TiledMap map = Resources.getTiledMap( levelMap );
		int objectAmount = map.getObjectCount( 0 );
		for ( int i = 0; i < objectAmount; i++ ) {
			switch ( map.getObjectName( 0, i ) ) {
				case "Enemy":
					Entity entity = new Enemy( map.getObjectX( 0, i ), map.getObjectY( 0, i ) );
					entities.add( entity );
					enemies.add( entity );
					break;
				default:
					break;
			}
		}
	}

	public int getXOffset() {
		int offset_x;
		int half_width = (int) ( Game_State.width / Game_State.SCALE / 2 );
		int maxX = Resources.maps.get( levelMap ).getWidth() * 32 - half_width;
		if ( player.getX() < half_width ) {
			offset_x = 0;
		}
		else if ( player.getX() > maxX ) {
			offset_x = maxX - half_width;
		}
		else {
			offset_x = (int) ( player.getX() - half_width );
		}
		return offset_x;
	}

	public int getYOffset() {
		int offset_y;
		int half_width = (int) ( Game_State.height / Game_State.SCALE / 2 );
		int maxX = Resources.maps.get( levelMap ).getHeight() * 32 - half_width;
		if ( player.getY() < half_width ) {
			offset_y = 0;
		}
		else if ( player.getY() > maxX ) {
			offset_y = maxX - half_width;
		}
		else {
			offset_y = (int) ( player.getY() - half_width );
		}
		return offset_y;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void render( GameContainer gameContainer, Graphics g ) throws SlickException {
		int offset_x = getXOffset();
		g.scale( Game_State.SCALE, Game_State.SCALE );

		renderBackground();
		Resources.maps.get( levelMap ).render( -( offset_x % 32 ), 0, offset_x / 32, 0, Game_State.width / 32, Game_State.height );

		/* Draw Health Bars*/
		g.setColor( Color.black );
		g.drawRect( 15, 15, entities.get( 0 ).maxHealth * 4, 20 );
		g.setColor( Color.green );
		g.fillRect( 15, 15, entities.get( 0 ).health * 4, 20 );
		g.setColor( Color.red );
		g.fillRect( 15 + entities.get( 0 ).health * 4, 15, entities.get( 0 ).maxHealth * 4 - entities.get( 0 ).health * 4, 20 );
		for ( int j = 1; j <= entities.size() - 1; j++ ) {
			if ( entities.get( j ) instanceof game.entities.Character ) {
				g.setColor( Color.black );
				g.drawRect( entities.get( j ).x - offset_x, entities.get( j ).y - 10, entities.get( j ).maxHealth, 10 );
				g.setColor( Color.red );
				g.fillRect( entities.get( j ).x - offset_x, entities.get( j ).y - 10, entities.get( j ).health, 10 );
			}
		}

	/* Draw Characters to screen */
		g.setColor( Color.white );
		for ( Entity entity : entities )
			entity.render( offset_x, entity.getY() );
	}
}