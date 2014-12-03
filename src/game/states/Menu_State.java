package game.states;

import game.resources.Resources;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 10/4/2014
 * Proyecto
 */
public class Menu_State extends BasicGameState {
	boolean up    = false;
	boolean down  = false;
	boolean left  = false;
	boolean right = false;
	Input input;

	public int getID() {
		return States.MENU;
	}

	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
	}

	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
		g.scale( Game_State.SCALE, Game_State.SCALE );
		g.setBackground(Color.gray);
		g.setColor(Color.black);
		g.drawString("Menu State", 150, 150);
		if ( up )
			g.drawString("Player inputs UP", 200, 200);
		if ( down )
			g.drawString("Player inputs DOWN", 200, 220);
		if ( left )
			g.drawString("Player inputs LEFT", 200, 240);
		if ( right )
			g.drawString("Player inputs RIGHT", 200, 260);
	}

	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		input = gameContainer.getInput();

		/* testing controller input */
		up = input.isControllerUp(0);
		down = input.isControllerDown(0);
		right = input.isControllerRight(0);
		left = input.isControllerLeft(0);
		if ( Resources.getAudio( "song" ).isPlaying() ) {
			Resources.getAudio( "song" ).stop();
			Resources.getAudio( "song2" ).playAsMusic( 1.0f, 1.0f, true );
		}

		if ( input.isKeyPressed(Input.KEY_ENTER) || input.isControlPressed(8) )
			stateBasedGame.enterState(States.GAME);
		if ( input.isKeyPressed(Input.KEY_ESCAPE) || input.isControlPressed(7) )
			gameContainer.exit();
	}

	/* todo Menu state needs to be created in order to allow access to game options*/
}
