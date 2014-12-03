package game.entities;

import game.resources.Resources;
import game.states.Game_State;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import java.util.Random;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 10/7/2014
 * Proyecto
 */
public class PowerUp extends Entity {
	private int powerUpType;

	public int getPowerUpType() {
		return powerUpType;
	}

	@Override
	public void applyPowerUp(int powerUpType) {

	}

	@Override
	public void init() {
		/* todo Power Up should appear after killing enemy at dead enemies x,y or random time intervals outside of screen */
		x = Game_State.width + 20;
		y = new Random().nextInt( Game_State.height * 4 / 5) + Game_State.height / 5;
		width = 32;
		height = 32;
		hitBox = new Rectangle(x, y, x + width, y + height);
		powerUpType = new Random().nextInt(5);
		try {
			image = new Image(width, height);
			if ( powerUpType == 0 )
				image = Resources.getImage("FullHealthPowerUp");
			if ( powerUpType == 1 )
				image = Resources.getImage("SpeedPowerUp");
			if ( powerUpType == 2 )
				image = Resources.getImage("StrengthPowerUp");
			if ( powerUpType == 3 )
				image = Resources.getImage("25HealthPowerUp");
			if ( powerUpType == 4 )
				image = Resources.getImage("SlowSpeedPowerUp");
			if ( powerUpType == 5 )
				image = null;
		}
		catch ( SlickException e ) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(GameContainer gameContainer, int delta) {
		hitBox.setBounds(x, y, width, height);
	}

/* todo Power up should disappear/fade after certain amount of time has passed */
}
