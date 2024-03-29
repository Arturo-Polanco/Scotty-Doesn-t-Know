package game.resources;

import org.newdawn.slick.*;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 10/4/2014
 * Proyecto
 */
public class Resources {
	public static  Map<String, TiledMap> maps;
	private static Map<String, Image>    images;
	private static Map<String, Sound>    sounds;
	private static Map<String, Music>    musics;
	private static Map<String, Audio>    audio;
	private static String[]              levels;

	public Resources() {
		levels = new String[3];
		levels[0] = "LevelOne";
		levels[1] = "LevelTwo";
		levels[2] = "LevelThree";
		images = new HashMap<>();
		maps = new HashMap<>();
		sounds = new HashMap<>();
		musics = new HashMap<>();
		audio = new HashMap<>();
		try {
			/* add tile maps to map hash map */
			TiledMap map = new TiledMap( "res/maps/mapa7.tmx" );
			maps.put( "LevelOne", map );
			map = new TiledMap( "res/maps/mapa.tmx" );
			maps.put( "LevelTwo", map );
			map = new TiledMap( "res/maps/mapa2.tmx" );
			maps.put( "LevelThree", map );

			/* Add images to hash map */
			images.put( "Loss", loadImage( "res/backgrounds/gameover.png" ) );
			images.put( "win", loadImage( "res/backgrounds/win.png" ) );
			images.put( "Start", loadImage( "res/backgrounds/inicio.png" ) );
			images.put( "Background", loadImage( "res/backgrounds/back.png" ) );
			images.put( "Background2", loadImage( "res/maps/" + getTiledMap( "LevelOne" ).getMapProperty( "back", "grassy_mountains.png" ) ) );
			images.put( "Background3", loadImage( "res/backgrounds/back.png" ) );
			/* PowerUps */
			images.put( "FullHealthPowerUp", loadImage( "res/sprites/powerup/fullhealth.png" ) );
			images.put( "SpeedPowerUp", loadImage( "res/sprites/powerup/speed.png" ) );
			images.put( "StrengthPowerUp", loadImage( "res/sprites/powerup/strength.png" ) );
			images.put( "25HealthPowerUp", loadImage( "res/sprites/powerup/25health.png" ) );
			images.put( "SlowSpeedPowerUp", loadImage( "res/sprites/powerup/slow.png" ) );
			/* Trash */
			images.put( "banana", loadImage( "res/sprites/assets/banana.png" ) );
			images.put( "basurero", loadImage( "res/sprites/assets/basurero.png" ) );
			images.put( "bolaPapel", loadImage( "res/sprites/assets/bolaPapel.png" ) );
			images.put( "bolsaRuffles", loadImage( "res/sprites/assets/bolsa_rufles.png" ) );
			images.put( "bolsaSabritas", loadImage( "res/sprites/assets/bolsa_sabritas.png" ) );
			images.put( "botella", loadImage( "res/sprites/assets/botella.png" ) );
			images.put( "cajaCarton", loadImage( "res/sprites/assets/caja_carton.png" ) );
			images.put( "cajaPizza", loadImage( "res/sprites/assets/caja_pizza.png " ) );
			images.put( "cartonJuice", loadImage( "res/sprites/assets/carton_apple_juice.png" ) );
			images.put( "cascaraHuevo", loadImage( "res/sprites/assets/cascara_huevo.png" ) );
			images.put( "lata", loadImage( "res/sprites/assets/lata.png" ) );
			images.put( "llanta", loadImage( "res/sprites/assets/llanta.png" ) );
			images.put( "manzana", loadImage( "res/sprites/assets/manzana.png" ) );


			/* add Sounds */
			sounds.put( "punch", loadSound( "res/sounds/punch.wav" ) );
			/* add Music */
			musics.put( "music", loadMusic( "res/sounds/BloodyTears.ogg" ) );
			/* add Audio */
			audio.put( "song", loadOggAudio( "res/sounds/BloodyTears.ogg" ) );
			audio.put( "song2", loadOggAudio( "res/sounds/AerisPianoByTannerHelland.ogg" ) );
			audio.put( "song3", loadOggAudio( "res/sounds/Guile_Theme.ogg" ) );
			audio.put( "song4", loadOggAudio( "res/sounds/GameOver.ogg" ) );
			audio.put( "ring", loadWavAudio( "res/sounds/ring.wav" ) );
			audio.put( "punch", loadWavAudio( "res/sounds/punch.wav" ) );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	public static Animation getAnimation( Image image, int spriteX, int spriteY, int spriteWidth, int spriteHeight ) {
		Animation anime = new Animation( false );
		int c = 0;
		for ( int y = 0; y < spriteY; y++ )
			for ( int x = 0; x < spriteX; x++ ) {
				if ( c < 30 )
					anime.addFrame( image.getSubImage( x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight ), 60 );
				c++;
			}
		return anime;
	}

	public static Image loadImage( String path ) throws SlickException {
		return new Image( path, false, Image.FILTER_NEAREST );
	}

	public static Image getImage( String getter ) {
		return images.get( getter );
	}

	public static TiledMap getTiledMap( String map ) {
		return maps.get( map );
	}

	public static String getLevel( int i ) {
		return levels[i];
	}

	public static Music getMusic( String music ) {
		return musics.get( music );
	}

	public static Sound getSound( String sound ) {
		return sounds.get( sound );
	}

	private static Sound loadSound( String path ) throws SlickException {
		return new Sound( path );
	}

	private static Music loadMusic( String path ) throws SlickException {
		return new Music( path );
	}

	private static Audio loadOggAudio( String path ) throws SlickException, IOException {
		return AudioLoader.getAudio( "OGG", ResourceLoader.getResourceAsStream( path ) );
	}

	private static Audio loadWavAudio( String path ) throws SlickException, IOException {
		return AudioLoader.getAudio( "WAV", ResourceLoader.getResourceAsStream( path ) );
	}

	public static Audio getAudio( String sound ) {
		return audio.get( sound );
	}
}
