package csc439team1.blackjack;

import csc439team1.blackjack.controller.Controller;
import csc439team1.blackjack.model.Card;
import csc439team1.blackjack.view.CLIView;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class contains main method to run the BlackJack game
 */
public class Blackjackgame {
    /**
     * Main starts the Blackjack game by instantiating a CLIview object and passing it to controller's constructor. Then the playBlackJack method is invoked.
     * This is in a try catch block because quit() throws an exception instead of calling System.exit(0) itself.
     * This was done to facilitate testing of the quit functionality in controller as well as provide a clean quit scenario to the user.
     *
     * @param args
     */
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("Main");
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.config("Entering Main");
        try {
            CLIView view = new CLIView();
            Controller controller = new Controller(view);
            controller.playBlackjack();
            logger.info("All methods fired successfully");
        } catch (IllegalStateException e) {
            logger.warning("Caught quit exceptions");
            System.exit(0);
        }
        logger.config("Exiting main method");
    }
}
