package game.states;

import game.entities.Player;
import game.resources.Resources;
import game.utils.input.KeyboardPlayerInput;
import game.utils.level.Level;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 12/3/2014
 * Juego
 */
public class Win_State extends BasicGameState {
	private static int i = 0;

	public int getID() {
		return States.WIN;
	}

	public void init( GameContainer gameContainer, StateBasedGame stateBasedGame ) throws SlickException {
	}

	public void render( GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g ) throws SlickException {
		Resources.getImage( "win" ).draw( 0, 0, Game_State.width, Game_State.height );
		g.drawString( "Your Score: " + Game_State.player.score ,Game_State.width / 2, Game_State.height / 5 );
	}

	public void update( GameContainer gameContainer, StateBasedGame stateBasedGame, int i ) throws SlickException {
		Input input = gameContainer.getInput();
		Score.leer();
		if ( !Resources.getAudio( "song2" ).isPlaying() ) {
			Resources.getAudio( "song" ).stop();
			Resources.getAudio( "song3" ).stop();
			Resources.getAudio( "song4" ).stop();
			Resources.getAudio( "song2" ).playAsMusic( 1.0f, 1.0f, true );
		}
		if ( input.isKeyPressed( Input.KEY_ENTER ) ) {
			Game_State.player = new Player();
			Game_State.playerController = new KeyboardPlayerInput( Game_State.player );
			if ( i == 0 )
				Game_State.level = new Level( "LevelTwo", Game_State.player );
			else if ( i == 1 )
				Game_State.level = new Level( "LevelTwo", Game_State.player );
			else if ( i == 2 )
				Game_State.level = new Level( "LevelThree", Game_State.player );
			else
				gameContainer.reinit();
			i++;
			Resources.getAudio( "song2" ).stop();
			Resources.getAudio( "song" ).playAsMusic( 1.f, 1.f, true );
			stateBasedGame.enterState( States.GAME );
		}
		if ( input.isKeyPressed( Input.KEY_ESCAPE ) || input.isControlPressed( 7 ) )
			gameContainer.exit();
	}
}
