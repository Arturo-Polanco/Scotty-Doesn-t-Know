package game.utils.physics;

import game.entities.Enemy;
import game.entities.Entity;
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
	public static final float GRAVITY = 0.0015f;

	public void handlePhysics(Level level, int delta){
		handleEntities(level,delta);
		leftBehind();
	}

	private void handleEntities( Level level, int delta ) {

		for ( Entity obj : Level.entities ) {
			if ( !obj.isMoving() )
				obj.decelerate( delta );

			handleGameObject( obj, level, delta );
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

		if ( xMove != 0 ){
			yStep = Math.abs(yMove)/Math.abs(xMove);
			if(yMove < 0)
				yStep = -yStep;

			if(xMove > 0)
				xStep = 1;
			else
				xStep = -1;

			if((yStep > 1 || yStep < -1) && yStep != 0){
				xStep = Math.abs(xStep)/Math.abs(yStep);
				if(xMove < 0)
					xStep = -xStep;
				if(yMove < 0)
					yStep = -1;
				else
					yStep = 1;
			}
		}else if(yMove != 0){

			if(yMove > 0)
				yStep = 1;
			else
				yStep = -1;
		}

		while(xMove != 0 || yMove != 0){


			if(xMove != 0){

				if((xMove > 0 && xMove < xStep) || (xMove > xStep  && xMove < 0)){
					xStep = xMove;
					xMove = 0;
				}else
					xMove -= xStep;


				obj.setX(obj.getX()+xStep);


				if(checkCollision(obj,level.getTiles())){


					obj.setX(obj.getX()-xStep);
					obj.setHorizontalVelocity( 0 );
					xMove = 0;
				}

			}
			//same thing for the vertical
			if(yMove != 0){
				if((yMove > 0 && yMove < yStep) || (yMove > yStep  && yMove < 0)){
					yStep = yMove;
					yMove = 0;
				}else
					yMove -= yStep;

				obj.setY(obj.getY()+yStep);

				if(checkCollision(obj,level.getTiles())){
					obj.setY(obj.getY()-yStep);
					obj.setVerticalVelocity( 0 );
					yMove = 0;
					break;
				}
			}
		}


	}

	public boolean checkCollision( Entity obj, Tile[][] mapTiles ){

		ArrayList<Tile> tiles = obj.getBoundingShape().getTilesOccupying(mapTiles);
		for(Tile t : tiles){

			if(t.getBoundingShape() != null){
				if(t.getBoundingShape().checkCollision(obj.getBoundingShape())){
					return true;
				}
			}
		}
		return false;
	}

	private boolean isOnGround(Entity obj, Tile[][] mapTiles){
		ArrayList<Tile> tiles = obj.getBoundingShape().getGroundTiles(mapTiles);

		int yMovement = 1;

		obj.getBoundingShape().movePosition( 0, yMovement);

		for(Tile t : tiles){
			if(t.getBoundingShape() != null){
				if( t.getBoundingShape().checkCollision( obj.getBoundingShape() ) ){
					obj.getBoundingShape().movePosition( 0, -yMovement);
					return true;
				}
			}
		}

		obj.getBoundingShape().movePosition( 0, -yMovement);

		return false;
	}

	/* Test if any enemies are left behind and removes any entities left behind */
	void leftBehind() {
		int i = Level.entities.size()-1;
		for ( int j = i; j > 0; j-- )
			/* If enemy is at left of screen constrict player movement within current screen */
			if ( Level.entities.get(j) instanceof Enemy && Level.entities.get(j).y > Game_State.height * Game_State.SCALE + Level.entities.get( j ).height ) {
				Level.entities.remove(j);
				i--;
			}
	}

}
