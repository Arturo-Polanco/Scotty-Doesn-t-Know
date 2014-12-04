package game.entities;

import game.resources.Resources;
import game.utils.level.Level;
import game.utils.physics.AABoundingRect;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 10/4/2014
 * Proyecto
 */
public class Enemy extends Character{

	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		init();
	}

	/* Enemies should stand waiting for player to enter range of sight before pursuing attack */
	private void stand() {
		punched = punching = false;
		if ( moved ) {
			width = 44;
			height = 60;
			try {
				if ( movedRight )
					anime = Resources.getAnimation( Resources.loadImage( "res/sprites/enemy/New folder/enemy_wait_Right_strip24.png" ), 24, 1, width, height );
				else
					anime = Resources.getAnimation( Resources.loadImage( "res/sprites/enemy/New folder/enemy_wait_Left_strip24.png" ), 24, 1, width, height );
				moved = false;
				moving = false;
			} catch ( SlickException e ) {
				e.printStackTrace();
			}
		}
	}

	private void punch() {
		if ( punched && !punching )
			try {
				width = 64;
				height = 60;
				if ( movedRight )
				/* Punch Right */
					anime = Resources.getAnimation( Resources.loadImage( "res/sprites/enemy/New folder/enemy_punch_Right_64x60_strip24.png" ), 24, 1, width, height );
				else
				/* Punch Left */
					anime = Resources.getAnimation( Resources.loadImage( "res/sprites/enemy/New folder/enemy_punch_Left_64x60_strip24.png" ), 24, 1, width, height );
				punched = false;
				moved = true;
				moving = false;
				punching = true;
			} catch ( SlickException e ) {
				e.printStackTrace();
			}
	}

	public void init() {
		vector2f = new Vector2f( x, y );
		health = maxHealth = 40;
		width = 44;
		height = 60;
		movedRight = true;
		moving = false;
		moved = true;
		hitBox = new Rectangle( x, y, x + width, y + height );
		anime = new Animation( false );
		boundingShape = new AABoundingRect( x, y, width, height );
		maximumSpeed = .15f;
		accelerationSpeed = .003f;
		decelerationSpeed = .001f;
		try {
			anime = Resources.getAnimation( Resources.loadImage( "res/sprites/enemy/New folder/enemy_wait_Left_strip24.png" ), 24, 1, width, height );
		} catch ( SlickException e ) {
			e.printStackTrace();
		}
	}

	public void update( GameContainer gameContainer, int delta ) {
		if ( this.vector2f.distance( Level.entities.get( 0 ).vector2f ) < gameContainer.getWidth() * 7 / 10 ) {

			/* If close enough to hit punch*/
			if ( this.vector2f.distance( Level.entities.get( 0 ).vector2f ) < Level.entities.get( 0 ).width / 2 + this.width / 2 ) {
				punched = true;
				punch();
			}
			/* Otherwise move towards player */
			else {
				punched = punching = false;
				followPlayer( delta );
				if ( !moved ) {
					moving = true;
					try {
						width = 48;
						height = 68;
						if ( movedRight )
							this.anime = Resources.getAnimation( Resources.loadImage( "res/sprites/enemy/New folder/enemy_move_Right_48x68_strip32.png" ), 32, 1, width, height );
						else
							this.anime = Resources.getAnimation( Resources.loadImage( "res/sprites/enemy/New folder/enemy_move_Left_48x68_strip32.png" ), 32, 1, width, height );
					} catch ( SlickException e ) {
						e.printStackTrace();
					}
				}
			}
		}
		else
			stand();

	/* Animate sprite animation assigned to player's input */
		anime.update( delta + 10 );
	/* Hitbox bounds calculated based on players position and relative width, height */
		hitBox.setBounds( x, y, width, height );
		this.vector2f.set( this.hitBox.getCenterX(), this.hitBox.getCenterY() );
	}

	void followPlayer( int delta ) {
		/* Move X axis towards PLayer */
		if ( Level.entities.get( 0 ).hitBox.getCenterX() < this.hitBox.getCenterX() && Level.entities.get( 0 ).hitBox.getMaxX() < this.x ) {
			if ( movedRight )
				moving = true;
			this.moveLeft( delta );
		}
		else if ( Level.entities.get( 0 ).hitBox.getMinX() > this.x ) {
			if ( !movedRight )
				moving = true;
			this.moveRight( delta );
		}
	}

	@Override
	public void applyPowerUp( int powerUpType ) {
	}

	public void updateBoundingShape() {
		boundingShape.updatePosition( x, y );
	}
}
