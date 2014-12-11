package game.entities;

import game.resources.Resources;
import game.states.Game_State;
import game.utils.physics.AABoundingRect;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import java.io.Serializable;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 10/4/2014
 * Proyecto
 */
public class Player extends Character implements Serializable{
	public int score;

	public void jump() {
		if ( getIsOnGround() )
			verticalVelocity = -.8f;
		moved = true;
	}

	void stand() {
		punching = false;
		if ( moved ) {
			width = (int) ( Game_State.SCALE * 40 );
			try {
				if ( movedRight )
					anime = Resources.getAnimation( Resources.loadImage( "res/sprites/scott/spr_scott_wait_strip32.png" ), 32, 1, width, height );
				else
					anime = Resources.getAnimation( Resources.loadImage( "res/sprites/scott/scott_wait_Left_40x60_strip32.png" ), 32, 1, width, height );
				moved = false;
			} catch ( SlickException e ) {
				e.printStackTrace();
			}
		}
	}

	/* fixme punch method does not take collision into account and should invalidate move()*/
	public void punch() {
		punching = true;
		if ( punched )
			try {
				width = (int) ( Game_State.SCALE * 70 );
				if ( movedRight )
				/* Punch Right */
					anime = Resources.getAnimation( Resources.loadImage( "res/sprites/scott/punch_Right.png" ), 16, 1, width, height );
				else
				/* Punch Left */
					anime = Resources.getAnimation( Resources.loadImage( "res/sprites/scott/punch_Left.png" ), 16, 1, width, height );
				Resources.getAudio( "punch" ).playAsSoundEffect( 0.9f, 0.9f, false );
				punched = false;
				moved = true;
			} catch ( SlickException e ) {
				e.printStackTrace();
			}
	}

	public void init() {
		width = (int) ( Game_State.SCALE * 40 );
		height = (int) ( Game_State.SCALE * 60 );
		strength = .5f;
		health = maxHealth = 100;
		x = 200;
		y = Game_State.height - height * 2;
		vector2f = new Vector2f( x, y );
		boundingShape = new AABoundingRect( x, y, width, height );
		maximumSpeed = .35f;
		accelerationSpeed = .003f;
		decelerationSpeed = .001f;


		/* Boolean variables defining player states*/
		movedRight = true;
		punching = punched = false;

		/* Special variables assigned to player */
		hitBox = new Rectangle( x, y, x + width, y + height );
		anime = new Animation( false );
		try {
			anime = Resources.getAnimation( Resources.loadImage( "res/sprites/scott/spr_scott_wait_strip32.png" ), 32, 1, width, height );
		} catch ( SlickException e ) {
			e.printStackTrace();
		}
	}

	/* todo Player input should poll controller and keyboard */
	public void update( GameContainer gameContainer, int delta ) {
		Input input = gameContainer.getInput();
		try {

		/* If player is not moving or attacking, show waiting animation */
			if ( !input.isKeyDown( Input.KEY_LEFT ) && !input.isKeyDown( Input.KEY_RIGHT ) && !input.isKeyDown( Input.KEY_SPACE ) && !input.isKeyDown( Input.KEY_A ) && !input.isKeyDown( Input.KEY_D ) && !input.isKeyDown( Input.KEY_E ) )
				stand();

		/* If player inputs left animate left movement */
			if ( input.isKeyPressed( Input.KEY_LEFT ) || input.isKeyPressed( Input.KEY_A ) ) {
				/* modify player width to match animation - should be 52 for 1:1 scale */
				width = (int) ( Game_State.SCALE * 52 );
				anime = Resources.getAnimation( Resources.loadImage( "res/sprites/scott/scott_walk_left_strip32.png" ), 32, 1, width, height );
			}

		/* If player inputs right animate right movement */
			if ( input.isKeyPressed( Input.KEY_RIGHT ) || input.isKeyPressed( Input.KEY_D ) ) {
				moved = true;
				movedRight = true;
				/* modify player width to match animation - should be 52 for 1:1 scale */
				width = (int) ( Game_State.SCALE * 52 );
				anime = Resources.getAnimation( Resources.loadImage( "res/sprites/scott/scott_walk_right_strip32.png" ), 32, 1, width, height );
			}

		/* If player is moving up or down show animation for last x axis direction moved */
			if ( input.isKeyPressed( Input.KEY_UP ) || input.isKeyPressed( Input.KEY_SPACE ) ) {
				moved = true;
				/* modify player width to match animation - should be 52 for 1:1 scale */
				width = (int) ( Game_State.SCALE * 52 );
				if ( movedRight )
					anime = Resources.getAnimation( Resources.loadImage( "res/sprites/scott/scott_walk_right_strip32.png" ), 32, 1, width, height );
				else
					anime = Resources.getAnimation( Resources.loadImage( "res/sprites/scott/scott_walk_left_strip32.png" ), 32, 1, width, height );
			}
		} catch ( SlickException e ) {
			e.printStackTrace();
		}

		/* Animate sprite animation assigned to player's input */
		anime.update( delta + 10 );
		/* Hitbox bounds calculated based on players position and relative width, height */
		hitBox.setBounds( x, y, width, height );
		this.vector2f.set( this.hitBox.getCenterX(), this.hitBox.getCenterY() );
	}

	public void applyPowerUp( int powerUpType ) {

		/* Max Health type Power Up */
		if ( powerUpType == 0 ) {
			if ( health == maxHealth && maxHealth <= 250 )
				maxHealth += 25;
			health = maxHealth;
		}
		/* Speed Power Up */
		if ( powerUpType == 1 ) {
			maximumSpeed *= 1.5f;
			/* todo Set timer to void speed power up effects */
		}
		/* Strength Power Up */
		if ( powerUpType == 2 )
			strength *= 1.1f;
		/* +25 Health Power up */
		if ( powerUpType == 3 ) {
			/* Apply health increase if +25 to health is still within maxHealth, otherwise just set to maxHealth */
			if ( health < maxHealth - 25 )
				health += 25;
			else
				health = maxHealth;
		}
		/* Slow Speed Power Up */
		if ( powerUpType == 4 )
			maximumSpeed *= 0.5f;
	}

	public void updateBoundingShape() {
		boundingShape.updatePosition( x, y );
	}
}