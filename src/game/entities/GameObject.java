package game.entities;

import org.newdawn.slick.GameContainer;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 12/4/2014
 * Juego
 */
public abstract class GameObject extends Entity {
	protected abstract void init();

	public abstract void update( GameContainer gameContainer, int delta );
}
