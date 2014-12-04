package game.entities;

import game.resources.Resources;
import game.utils.physics.AABoundingRect;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 12/4/2014
 * Juego
 */
public class Trash extends GameObject {
	public static int trashType;

	public Trash( int x, int y ) {
		this.x = x;
		this.y = y;
		init();
	}

	protected void init() {
		trashType = (int) ( Math.random() * 13 );
		vector2f = new Vector2f( x, y );
		width = 32;
		height = 32;
		movedRight = false;
		moving = false;
		moved = false;
		punching = punched = false;
		health = maxHealth = 1;
		hitBox = new Rectangle( x, y, x + width, y + height );
		boundingShape = new AABoundingRect( x, y, width, height );
		maximumSpeed = .15f;
		accelerationSpeed = .003f;
		decelerationSpeed = .001f;
		try {
			image = new Image( width, height );
			if ( trashType == 0 )
				image = Resources.getImage( "banana" );
			if ( trashType == 1 )
				image = Resources.getImage( "basurero" );
			if ( trashType == 2 )
				image = Resources.getImage( "bolaPapel" );
			if ( trashType == 3 )
				image = Resources.getImage( "bolsaRuffles" );
			if ( trashType == 4 )
				image = Resources.getImage( "bolsaSabritas" );
			if ( trashType == 5 )
				image = Resources.getImage( "botella" );
			if ( trashType == 6 )
				image = Resources.getImage( "cajaCarton" );
			if ( trashType == 7 )
				image = Resources.getImage( "cajaPizza" );
			if ( trashType == 8 )
				image = Resources.getImage( "cartonJuice" );
			if ( trashType == 9 )
				image = Resources.getImage( "cascaraHuevo" );
			if ( trashType == 10 )
				image = Resources.getImage( "lata" );
			if ( trashType == 11 )
				image = Resources.getImage( "llanta" );
			if ( trashType == 12 )
				image = Resources.getImage( "manzana" );

		} catch ( SlickException e ) {
			e.printStackTrace();
		}
	}

	public void update( GameContainer gameContainer, int delta ) {


		/* Hitbox bounds calculated based on position and relative width, height */
		hitBox.setBounds( x, y, width, height );
		this.vector2f.set( this.hitBox.getCenterX(), this.hitBox.getCenterY() );
	}

	public void applyPowerUp( int powerUpType ) {
	}

	public void updateBoundingShape() {
		boundingShape.updatePosition( x, y );
	}
}
