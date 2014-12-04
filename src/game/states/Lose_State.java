package game.states;

import game.resources.Resources;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 12/3/2014
 * Juego
 */
public class Lose_State extends BasicGameState {
	public int getID() {
		return States.LOSE;
	}

	public void init( GameContainer gameContainer, StateBasedGame stateBasedGame ) throws SlickException {
	}

	public void render( GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g ) throws SlickException {
		g.setBackground( Color.gray );
		g.setColor( Color.black );
		g.drawString( "Lose State", 150, 150 );
		Resources.getImage( "Loss" ).draw( 0, 0, Game_State.width, Game_State.height );
	}

	public void update( GameContainer gameContainer, StateBasedGame stateBasedGame, int i ) throws SlickException {
		Input input = gameContainer.getInput();
		if ( !Resources.getAudio( "song4" ).isPlaying() ) {
			Resources.getAudio( "song" ).stop();
			Resources.getAudio( "song2" ).stop();
			Resources.getAudio( "song3" ).stop();
			Resources.getAudio( "song4" ).playAsMusic( 1.0f, 1.0f, true );
		}

		if ( input.isKeyPressed( Input.KEY_ENTER ) ) {
			gameContainer.reinit();
			stateBasedGame.enterState( States.GAME );
		}
		if ( input.isKeyPressed( Input.KEY_ESCAPE ) || input.isControlPressed( 7 ) )
			gameContainer.exit();
	}
}
