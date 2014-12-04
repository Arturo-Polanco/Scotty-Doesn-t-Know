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
public abstract class PlayerController {
	Player player;

	PlayerController( Player player ) {
		this.player = player;
	}

	public abstract void handleInput( Input input, int delta, Level level );
}
