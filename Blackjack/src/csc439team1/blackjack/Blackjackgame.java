package csc439team1.blackjack;

import csc439team1.blackjack.controller.Controller;
import csc439team1.blackjack.model.Card;
import csc439team1.blackjack.view.CLIView;
import java.util.logging.Logger;

/**
 * This class contains main method to run the BlackJack game
 */
public class Blackjackgame {
    /**
     * Logger for Blackjackgame class.
     */
    private static final Logger logger = Logger.getLogger(Blackjackgame.class.getName());
    /**
     * Main starts the Blackjack game by instantiating a CLIview object and passing it to controller's constructor. Then the playBlackJack method is invoked.
     * This is in a try catch block because quit() throws an exception instead of calling System.exit(0) itself.
     * This was done to facilitate testing of the quit functionality in controller as well as provide a clean quit scenario to the user.
     *
     * @param args
     */
    public static void main(String[] args) {
        logger.entering("Blackjackgame", "main");
        try {
            CLIView view = new CLIView();
            Controller controller = new Controller(view);
            controller.playBlackjack();

        } catch (IllegalStateException e) {
            logger.warning("Caught quit exceptions");
            System.exit(0);
        }
        logger.exiting("Blackjackgame", "main");
    }
}
