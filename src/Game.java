import game.resources.Resources;
import game.states.Game_State;
import game.states.Lose_State;
import game.states.Menu_State;
import game.states.Win_State;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 12/2/2014
 * Juego
 */
class Game extends StateBasedGame {

	private Game() {
		super( "Proyecto POOA - Scotty doesn't Know..." );
	}

	public static void main( String[] args ) {
		try {
			AppGameContainer game = new AppGameContainer( new Game() );
			/* Check is screen is 16:9 in order to run full screen, otherwise calculate a smaller window size for 16:9 ratio */
			if ( game.getScreenWidth() / game.getScreenHeight() == 16 / 9 )
				game.setDisplayMode( game.getScreenWidth(), game.getScreenHeight(), true );
			else
				game.setDisplayMode( game.getScreenHeight() * 3 / 4 * 16 / 9, game.getScreenHeight() * 3 / 4, false );

			game.start();
		} catch ( SlickException e ) {
			e.printStackTrace();
		}
	}

	public void initStatesList( GameContainer gameContainer ) throws SlickException {
		gameContainer.setMaximumLogicUpdateInterval(240);
		gameContainer.setTargetFrameRate(240);
		gameContainer.setAlwaysRender(true);
		gameContainer.setShowFPS( false );

		new Resources();

		/* Add Menu, Game levels and Game Over/Victory Screen */
		this.addState(new Menu_State());
		this.addState( new Game_State() );
		this.addState( new Win_State() );
		this.addState( new Lose_State() );

	}
}