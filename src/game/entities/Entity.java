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
	public int       width;
	public int       height;
	public Vector2f  vector2f;
	/* Special variables assigned to entity */
	public Rectangle hitBox;
	/* Movement boolean values*/
	boolean moving;
	boolean movedRight;
	boolean moved;
	public boolean punching;
	public boolean punched;
	boolean       onGround;
	/**/
	/**/
	BoundingShape boundingShape;
	Animation     anime;
	Image         image;
	float horizontalVelocity = 0;
	float verticalVelocity   = 0;
	float maxFallRate        = .6f;
	protected float maximumSpeed;
	protected float accelerationSpeed;
	protected float decelerationSpeed;

	public void setX( float f ) {
		x = f;
		updateBoundingShape();
	}

	public float getX() {
		return x;
	}

	public void setY( float f ) {
		y = f;
		updateBoundingShape();
	}

	public float getY() {
		return y;
	}

	Entity() {
		init();
	}

	public abstract void applyPowerUp( int powerUpType );

	protected abstract void init();

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

	public void updateBoundingShape() {
		boundingShape.updatePosition( x, y );
	}

	public float getHorizontalVelocity() {
		return horizontalVelocity;
	}

	public float getVerticalVelocity() {
		return verticalVelocity;
	}

	public void setHorizontalVelocity( float horizontalVelocity ) {
		this.horizontalVelocity = horizontalVelocity;
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
			anime.draw( this.x - x, y, width, height );
		if ( image != null )
			image.draw( this.x - x, y );
	}

	public abstract void update( GameContainer gameContainer, int delta );
}
