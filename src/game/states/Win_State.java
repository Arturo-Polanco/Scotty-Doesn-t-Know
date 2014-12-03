package game.states;

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
	public int getID() {
		return States.WIN;
	}

	public void init( GameContainer gameContainer, StateBasedGame stateBasedGame ) throws SlickException {
	}

	public void render( GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics ) throws SlickException {
	}

	public void update( GameContainer gameContainer, StateBasedGame stateBasedGame, int i ) throws SlickException {
		Input input = gameContainer.getInput();

		if ( input.isKeyPressed( Input.KEY_ENTER ) )
			stateBasedGame.initStatesList( gameContainer );
		
		if ( input.isKeyPressed(Input.KEY_ESCAPE) || input.isControlPressed(7) )
			gameContainer.exit();
	}
}
