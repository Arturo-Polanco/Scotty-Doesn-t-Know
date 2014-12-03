package game.states;

import game.entities.Player;
import game.utils.input.KeyboardPlayerInput;
import game.utils.level.Level;
import org.newdawn.slick.*;
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
		g.setBackground( Color.gray );
		g.setColor( Color.black );
		g.drawString( "Win State", 150, 150 );
	}

	public void update( GameContainer gameContainer, StateBasedGame stateBasedGame, int i ) throws SlickException {
		Input input = gameContainer.getInput();
		if ( input.isKeyPressed( Input.KEY_ENTER ) ) {
			Game_State.player = new Player();
			Game_State.playerController = new KeyboardPlayerInput( Game_State.player );
			Game_State.level = new Level( "LevelTwo", Game_State.player );
			stateBasedGame.enterState( States.GAME );
		}
		
		if ( input.isKeyPressed(Input.KEY_ESCAPE) || input.isControlPressed(7) )
			gameContainer.exit();
	}
}
