package game.utils.input;

import game.entities.Player;
import org.newdawn.slick.Input;
import game.utils.level.*;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 12/2/2014
 * Juego
 */
public abstract class PlayerController {
	protected Player player;

	public PlayerController(Player player){
		this.player = player;
	}

	public abstract void handleInput(Input input,int delta, Level level );
}
