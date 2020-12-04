package csc439team1.blackjack;

import csc439team1.blackjack.controller.Controller;
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
        String loggerName = "com.something";
        Logger log = Logger.getLogger(loggerName);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        log.addHandler(handler);
        log.setLevel(Level.ALL);
        log.info("MAIN METHOD starting");
        try {
            CLIView view = new CLIView();
            Controller controller = new Controller(view);
            controller.playBlackjack();

        } catch (IllegalStateException e) {
            System.exit(0);
        }

    }
}
