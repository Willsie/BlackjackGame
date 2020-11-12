package csc439team1.blackjack.controller;

import csc439team1.blackjack.model.Dealer;
import csc439team1.blackjack.model.Player;
import csc439team1.blackjack.model.Shoe;
import csc439team1.blackjack.view.View;

public class Controller {
    View view;
    Player player = new Player();
    Dealer dealer = new Dealer();

    public Controller(View view) {
        this.view = view;
    }

    public void playBlackjack() {
        Shoe shoe = new Shoe(1);
        buyChips();
        askBet();
        initialDeal(shoe);
    }

    /**
     * Prompt's the user for a chip amount between 10 and 5000. Loops until that criteria is met, and then updates the Player's chip count.
     * If the user enters quit during that loop, then game via call to quit method.
     */

    public void buyChips() {
        view.output("Game is starting.....how much chips do you want to buy (between $10 to $5000): ");
        boolean validInput = false;
        while (!validInput) {
            try {
                int input = view.intInput();
                if (input > 10 && input <= 5000) {
                    validInput = true;
                    player.setChips((input));
                } else {
                    view.output("Please enter a number between 10 and 5000!: ");
                }
            } catch (Exception e) {
                quit();
            }
        }
    }


    /**
     * Method for placing a bet, starts with using view to prompt user, then checks input before updating the value of Player's chip data member
     */
    public void askBet() {
        view.output("How much do you want to bet (between $10 to $500): ");
        boolean validInput = false;

        while (!validInput) {
            try {
                int input = view.intInput();
                if ((input >= 10) && (input <= 500) && (input <= player.getChips())) {
                    validInput = true;
                    player.setChips(player.getChips() - input);
                } else {
                    view.output("Invalid bet amount! Bet needs to be between 10 & 500, and less than your current chips(" + player.getChips() + "): ");
                }
            } catch (Exception e) {
                quit();
            }
        }
    }

    /**
     * InitialDeal is called at the start of every hand. It removes cards from the shoe provided the shoe has those cards available.
     * It then outputs the hands to the player, with one deal card remaining hidden.
     * @param shoe Takes the shoe object created at the start of the game.
     */
    public void initialDeal(Shoe shoe) {

        try {
            player.addCard(shoe.pick());
            player.addCard(shoe.pick());
            dealer.addCard(shoe.pick());
            dealer.addCard(shoe.pick());
        } catch (Exception e) {
            System.out.println("There was an error(s) of the following" + e);
        }
        view.output("\nDealer card: ");
        view.output(dealer.getHand().get(0).toString() + "\n\n");
        view.output("Your initial cards are: ");
        view.output(player.getHand().toString() + "\n");
    }

    /**
     * Quit() throws and exception up to main when the user enters quit.
     * This allows the program to quit in a clear manner.
     */
    public void quit() {
        view.output("Player has quit\n");
        throw new IllegalStateException("");
    }

}
