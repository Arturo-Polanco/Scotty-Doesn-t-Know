package game.resources;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import java.util.HashMap;
import java.util.Map;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 10/4/2014
 * Proyecto
 */
public class Resources {
	public static  Map<String, TiledMap>    maps;
	private static Map<String, Image>       images;


	public Resources() {
		images = new HashMap<>();
		maps = new HashMap<>();

		try {
			/* add tile maps to map hash map */
			TiledMap map = new TiledMap("res/maps/mapa3.tmx");
			maps.put( "LevelOne", map );
			map = new TiledMap("res/maps/mapa.tmx");
			maps.put( "LevelTwo", map );
			map = new TiledMap("res/maps/mapa2.tmx");
			maps.put( "LevelThree", map );

			/* Add power up images to hash map */
			images.put( "Background", loadImage( "res/maps/Imgur.png"));
			images.put( "Background2", loadImage( "res/maps/" + getTiledMap( "LevelOne" ).getMapProperty( "back", "grassy_mountains.png" ) ));
			images.put("FullHealthPowerUp", loadImage("res/sprites/powerup/fullhealth.png"));
			images.put("SpeedPowerUp", loadImage("res/sprites/powerup/speed.png"));
			images.put("StrengthPowerUp", loadImage("res/sprites/powerup/strength.png"));
			images.put("25HealthPowerUp", loadImage("res/sprites/powerup/25health.png"));
			images.put("SlowSpeedPowerUp", loadImage("res/sprites/powerup/slow.png"));
		}
		catch ( SlickException e ) {
			e.printStackTrace();
		}
	}

	public static Animation getAnimation(Image image, int spriteX, int spriteY, int spriteWidth, int spriteHeight) {
		Animation anime = new Animation(false);
		int c = 0;
		for ( int y = 0; y < spriteY; y++ )
			for ( int x = 0; x < spriteX; x++ ) {
				if ( c < 30 )
					anime.addFrame(image.getSubImage(x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight), 60);
				c++;
			}
		return anime;
	}

	public static Image loadImage(String path) throws SlickException {
		return new Image(path, false, Image.FILTER_NEAREST);
	}

	public static Image getImage(String getter) {
		return images.get(getter);
	}

	public static TiledMap getTiledMap(String map) {
		return maps.get( map );
	}
}
