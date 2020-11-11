package csc439team1.blackjack;

import csc439team1.blackjack.controller.Controller;
import csc439team1.blackjack.model.Dealer;
import csc439team1.blackjack.view.CLIView;

public class Blackjackgame {
    public static void main (String[] args){
        try{
            CLIView view = new CLIView();
            Controller controller = new Controller(view);
            controller.playBlackjack();
        } catch (IllegalStateException e) {
            System.exit(0);
        }

    }
}
