package game.entities;

import game.utils.physics.BoundingShape;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.pathfinding.Mover;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 10/4/2014
 * Proyecto
 */
public abstract class Entity implements Mover{
	/* Primitive variables for entity values */
	public float     health;
	public float     maxHealth;
	public float     x;
	public float     y;
	public int       height;
	/* Special variables assigned to entity */
	public Rectangle hitBox;
	public boolean   punching;
	public boolean   punched;
	int           width;
	Vector2f      vector2f;
	/* Movement boolean values*/
	boolean       moving;
	boolean       movedRight;
	boolean       moved;
	/**/
	/**/
	BoundingShape boundingShape;
	Animation     anime;
	Image         image;
	float verticalVelocity = 0;
	float maximumSpeed;
	float accelerationSpeed;
	float decelerationSpeed;
	private boolean onGround;
	private float horizontalVelocity = 0;
	private float maxFallRate        = .6f;

	Entity() {
		init();
	}

	protected abstract void init();

	public float getX() {
		return x;
	}

	public void setX( float f ) {
		x = f;
		updateBoundingShape();
	}

	public float getY() {
		return y;
	}

	public void setY( float f ) {
		y = f;
		updateBoundingShape();
	}

	public abstract void applyPowerUp( int powerUpType );

	public void moveRight( int delta ) {
		if ( horizontalVelocity < maximumSpeed ) {
			horizontalVelocity += accelerationSpeed * delta;
			if ( horizontalVelocity > maximumSpeed ) {
				horizontalVelocity = maximumSpeed;
			}
		}
		moved = true;
		movedRight = true;
	}

	public void moveLeft( int delta ) {
		if ( horizontalVelocity > -maximumSpeed ) {
			horizontalVelocity -= accelerationSpeed * delta;
			if ( horizontalVelocity < -maximumSpeed ) {
				horizontalVelocity = -maximumSpeed;
			}
		}
		moved = true;
		movedRight = false;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setOnGround( boolean onGround ) {
		this.onGround = onGround;
	}

	public boolean getIsOnGround() {
		return onGround;
	}

	public BoundingShape getBoundingShape() {
		return boundingShape;
	}

	void updateBoundingShape() {
		boundingShape.updatePosition( x, y );
	}

	public float getHorizontalVelocity() {
		return horizontalVelocity;
	}

	public void setHorizontalVelocity( float horizontalVelocity ) {
		this.horizontalVelocity = horizontalVelocity;
	}

	public float getVerticalVelocity() {
		return verticalVelocity;
	}

	public void setVerticalVelocity( float verticalVelocity ) {
		this.verticalVelocity = verticalVelocity;
	}

	public void applyGravity( float gravity ) {
		if ( verticalVelocity < maxFallRate ) {
			verticalVelocity += gravity;
			if ( verticalVelocity > maxFallRate ) {
				verticalVelocity = maxFallRate;
			}
		}
	}

	public void decelerate( int delta ) {
		if ( horizontalVelocity > 0 ) {
			horizontalVelocity -= decelerationSpeed * delta;
			if ( horizontalVelocity < 0 )
				horizontalVelocity = 0;
		}
		else if ( horizontalVelocity < 0 ) {
			horizontalVelocity += decelerationSpeed * delta;
			if ( horizontalVelocity > 0 )
				horizontalVelocity = 0;
		}
	}

	public void render( float x, float y ) {
		if ( anime != null )
			anime.draw( this.x - x, this.y - y, width, height );
		if ( image != null )
			image.draw( this.x - x, this.y - y );
	}

	public abstract void update( GameContainer gameContainer, int delta );
}
