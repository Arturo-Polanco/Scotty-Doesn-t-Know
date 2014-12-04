package game.states;

import game.entities.Enemy;
import game.entities.Entity;
import game.entities.Player;
import game.resources.Resources;
import game.utils.input.KeyboardPlayerInput;
import game.utils.input.PlayerController;
import game.utils.level.Level;
import game.utils.physics.Physics;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 10/4/2014
 * Proyecto
 */
public class Game_State extends BasicGameState {
	public static  float            SCALE;
	public static  int              width;
	public static  int              height;
	public static  Level            level;
	public static  PlayerController playerController;
	public static  Player           player;
	private static StateBasedGame   stateGame;
	private static Physics          physics;

	public int getID() {
		return States.GAME;
	}

	public void init( GameContainer gameContainer, StateBasedGame stateBasedGame ) throws SlickException {
		stateGame = stateBasedGame;
		width = gameContainer.getWidth();
		height = gameContainer.getHeight();
		SCALE = width / 1600;
		player = new Player();
		playerController = new KeyboardPlayerInput( player );
		physics = new Physics();
		level = new Level( Resources.getLevel( 0 ), player );
		SoundStore.get().setMusicVolume( 0.6f );
	}

	public void render( GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g ) throws SlickException {
		level.render( gameContainer, g );
	}

	public void update( GameContainer gameContainer, StateBasedGame stateBasedGame, int delta ) throws SlickException {
		try {
			Input input = gameContainer.getInput();
			playerController.handleInput( input, delta, level );
			physics.handlePhysics( level, delta );
			for ( Entity entity : Level.entities ) {
				entity.update( gameContainer, delta );
				deadEntity( entity );
			}

			/* Go from game to menu screen */
			if ( input.isKeyPressed( Input.KEY_ESCAPE ) )
				stateBasedGame.enterState( States.MENU );

			/* Test Win / Loss of Game */
			gameStatus();

			/* Sound*/
			SoundStore.get().poll( delta );
			if ( !Resources.getAudio( "song" ).isPlaying() ) {
				Resources.getAudio( "song2" ).stop();
				Resources.getAudio( "song3" ).stop();
				Resources.getAudio( "song4" ).stop();
				Resources.getAudio( "song" ).playAsMusic( 1.0f, 1.0f, true );
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	void deadEntity( Entity entity ) {
		if ( entity.health <= 0 && entity instanceof Enemy )
			Level.entities.remove( entity );
	}

	void gameStatus() {
		if ( Level.entities.size() == 1 && Level.entities.get( 0 ) instanceof Player )
			Game_State.stateGame.enterState( States.WIN );
		if ( player.getY() > Resources.maps.get( Level.levelMap ).getHeight() * 32 * Game_State.SCALE || player.health <= 0 )
			Game_State.stateGame.enterState( States.LOSE );
	}
}
