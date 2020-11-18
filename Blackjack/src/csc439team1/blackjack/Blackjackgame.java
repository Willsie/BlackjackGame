package csc439team1.blackjack;

import csc439team1.blackjack.controller.Controller;
import csc439team1.blackjack.model.Dealer;
import csc439team1.blackjack.view.CLIView;

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
        try {
            CLIView view = new CLIView();
            Controller controller = new Controller(view);
            controller.playBlackjack();
        } catch (IllegalStateException e) {
            System.exit(0);
        }
    }
}
