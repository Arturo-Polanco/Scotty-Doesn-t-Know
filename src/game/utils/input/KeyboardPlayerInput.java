package game.utils.input;

import game.entities.Player;
import game.utils.level.Level;
import org.newdawn.slick.Input;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 12/2/2014
 * Juego
 */
public class KeyboardPlayerInput extends PlayerController {
	public KeyboardPlayerInput( Player player ) {
		super( player );
	}

	private void handleKeyboardInput( Input input, int delta, Level level ) {

		/* Player Jump */
		if ( input.isKeyPressed( Input.KEY_SPACE ) )
			player.jump();

		/* Player Punch */
		if ( input.isKeyDown( Input.KEY_E ) ) {
			player.punched = player.punching = true;
			player.punch();
		}

		/* Player move Right */
		if ( input.isKeyDown( Input.KEY_RIGHT ) || input.isKeyDown( Input.KEY_D ) )
			player.moveRight( delta );

		/* Player move left */
		if ( input.isKeyDown( Input.KEY_LEFT ) || input.isKeyDown( Input.KEY_A ) )
			player.moveLeft( delta );

	}

	public void handleInput( Input input, int delta, Level level ) {
		handleKeyboardInput( input, delta, level );
	}
}
