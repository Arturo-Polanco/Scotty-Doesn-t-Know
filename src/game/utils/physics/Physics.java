package game.utils.physics;

import game.entities.Enemy;
import game.entities.Entity;
import game.entities.Player;
import game.entities.Trash;
import game.resources.Resources;
import game.states.Game_State;
import game.utils.level.Level;
import game.utils.tiles.Tile;

import java.util.ArrayList;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 12/2/2014
 * Juego
 */
public class Physics {
	private static final float GRAVITY = 0.0015f;

	private void handleEntities( Level level, int delta ) {
		for ( Entity obj : Level.entities ) {
			if ( !obj.isMoving() )
				obj.decelerate( delta );
			handleGameObject( obj, level, delta );
			hitDetection( obj );
		}
	}

	private void handleGameObject( Entity obj, Level level, int delta ) {
		float xMove = obj.getHorizontalVelocity() * delta;
		float yMove = obj.getVerticalVelocity() * delta;
		float xStep = 0;
		float yStep = 0;
		obj.setOnGround( isOnGround( obj, level.getTiles() ) );
		if ( !obj.getIsOnGround() )
			obj.applyGravity( GRAVITY * delta );
		if ( xMove != 0 ) {
			yStep = Math.abs( yMove ) / Math.abs( xMove );
			if ( yMove < 0 )
				yStep = -yStep;
			if ( xMove > 0 )
				xStep = 1;
			else
				xStep = -1;
			if ( ( yStep > 1 || yStep < -1 ) && yStep != 0 ) {
				xStep = Math.abs( xStep ) / Math.abs( yStep );
				if ( xMove < 0 )
					xStep = -xStep;
				if ( yMove < 0 )
					yStep = -1;
				else
					yStep = 1;
			}
		}
		else if ( yMove != 0 ) {
			if ( yMove > 0 )
				yStep = 1;
			else
				yStep = -1;
		}
		while ( xMove != 0 || yMove != 0 ) {
			if ( xMove != 0 ) {
				if ( ( xMove > 0 && xMove < xStep ) || ( xMove > xStep && xMove < 0 ) ) {
					xStep = xMove;
					xMove = 0;
				}
				else
					xMove -= xStep;
				obj.setX( obj.getX() + xStep );
				if ( checkCollision( obj, level.getTiles() ) ) {
					obj.setX( obj.getX() - xStep );
					obj.setHorizontalVelocity( 0 );
					xMove = 0;
				}
			}
			//same thing for the vertical
			if ( yMove != 0 ) {
				if ( ( yMove > 0 && yMove < yStep ) || ( yMove > yStep && yMove < 0 ) ) {
					yStep = yMove;
					yMove = 0;
				}
				else
					yMove -= yStep;
				obj.setY( obj.getY() + yStep );
				if ( checkCollision( obj, level.getTiles() ) ) {
					obj.setY( obj.getY() - yStep );
					obj.setVerticalVelocity( 0 );
					yMove = 0;
					break;
				}
			}
		}


	}

	private boolean isOnGround( Entity obj, Tile[][] mapTiles ) {
		ArrayList<Tile> tiles = obj.getBoundingShape().getGroundTiles( mapTiles );
		int yMovement = 1;
		obj.getBoundingShape().movePosition( 0, yMovement );
		for ( Tile t : tiles ) {
			if ( t.getBoundingShape() != null ) {
				if ( t.getBoundingShape().checkCollision( obj.getBoundingShape() ) ) {
					obj.getBoundingShape().movePosition( 0, -yMovement );
					return true;
				}
			}
		}
		obj.getBoundingShape().movePosition( 0, -yMovement );
		return false;
	}

	public void handlePhysics( Level level, int delta ) {
		handleEntities( level, delta );
		leftBehind();
	}

	boolean checkCollision( Entity obj, Tile[][] mapTiles ) {
		ArrayList<Tile> tiles = obj.getBoundingShape().getTilesOccupying( mapTiles );
		for(Tile t : tiles){
			if(t.getBoundingShape() != null){
				if ( t.getBoundingShape().checkCollision( obj.getBoundingShape() ) ) {
					return true;
				}
			}
		}
		return false;
	}

	/* Test if any enemies are left behind and removes any entities left behind */
	void leftBehind() {
		int i = Level.entities.size()-1;
		for ( int j = i; j > -1; j-- )
			/* If enemy is at left of screen constrict player movement within current screen */
			if ( ( Level.entities.get( j ) instanceof Enemy || Level.entities.get( j ) instanceof Trash ) && Level.entities.get( j ).y > Resources.maps.get( Level.levelMap ).getHeight() * 32 * Game_State.SCALE ) {
				Level.entities.remove(j);
				i--;
			}
	}

	/* Tests for Entity hit detection */
	boolean hitDetection( Entity obj ) {
		for ( Entity entity : Level.entities ) {
			if ( entity != obj && obj.hitBox.intersects( entity.hitBox ) ) {
				if ( obj instanceof Player && entity instanceof Trash ) {
					Level.entities.remove( entity );
				}
				if ( entity.punching ) {
					if ( obj.health > 0 ) {
						if ( obj instanceof Enemy )
							obj.health -= .05f;
						else if ( obj instanceof Player )
							obj.health -= .025f;
					}
					else
						obj.health = 0;
				}
				return true;
			}
		}
		return false;
	}
}
