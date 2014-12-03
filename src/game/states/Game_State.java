package game.states;

import game.entities.Entity;
import game.entities.Player;
import game.utils.input.KeyboardPlayerInput;
import game.utils.input.PlayerController;
import game.utils.level.Level;
import game.utils.physics.Physics;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 10/4/2014
 * Proyecto
 */
public class Game_State extends BasicGameState {
	public static float    SCALE;
	public static int      width;
	public static int      height;
	public static Level    level;
	public static StateBasedGame stateGame;
	public Physics physics;

	public static PlayerController playerController;

	public Player player;

	public int getID() {
		return States.GAME;
	}

	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		gameContainer.setAlwaysRender(true);
		stateGame = stateBasedGame;
		width = gameContainer.getWidth();
		height = gameContainer.getHeight();
		SCALE = width/1600;
		player = new Player();
		playerController = new KeyboardPlayerInput( player );

		physics = new Physics();
		level = new Level( "LevelOne", player );

	}

	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {

		level.render( gameContainer, g );

	}

	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		try {
			Input input = gameContainer.getInput();

			playerController.handleInput( input, delta, level );

			physics.handlePhysics( level, delta );

			for ( Entity entity : Level.entities )
				entity.update( gameContainer, delta );

			/* Go from game to menu screen */
			if ( input.isKeyPressed(Input.KEY_ESCAPE) )
				stateBasedGame.enterState(States.MENU);

			gameStatus();

		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	public void gameStatus(){
		if ( Level.enemies.isEmpty() )
			Game_State.stateGame.enterState( States.WIN );
		if ( Level.entities.get( 0 ).getY() > Game_State.height*Game_State.SCALE+32 || Level.entities.get( 0 ).health <= 0 )
			Game_State.stateGame.enterState( States.LOSE );
	}
}
