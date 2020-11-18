package csc439team1.blackjack.controller;

import csc439team1.blackjack.model.*;
import csc439team1.blackjack.view.View;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Controller class that control all game logic for BlackJack game
 */
public class Controller {
    /**
     * View type object  used by Controller to receive input and display output
     */
    View view;

    /**
     * Player type object to store current player information
     */
    Player player = new Player();

    /**
     * Dealer type object to store current dealer information
     */
    Dealer dealer = new Dealer();

    /**
     * Constructor, for Controller object, accepts a View parameter. View object is required as Controller passes messages
     * and requirements to user via View and CLIView.
     *
     * @param view accepts View object as parameter.
     */
    public Controller(View view) {
        this.view = view;
    }

    /**
     * playBlackjack method initiates gameplay for the first level of stories (using hard coded shoe of 1 deck, calls to
     * buyChips, askBet, and initialDeal) to show start of game, purchasing chips, betting, and first deal of cards to both
     * player and dealer. Accepts no parameters at current since shoe is hardcoded.
     */
    public void playBlackjack() {
        Shoe shoe = new Shoe(1);
        buyChips();
        askBet();
        cut(shoe);
        initialDeal(shoe);
        //playerActions(player);
        cut(shoe);

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
     *
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

    /**
     * This method will check if the current shoe is less than 1/5 of the initial shoe when first created
     * if it is less, then the new shoe will be generated
     *
     * @param shoe current shoe
     * @return new shoe if the size of the current shoe is less than 1/5 of the initial shoe
     */
    public Shoe cut(Shoe shoe) {
        if (shoe.size() < 11) shoe = new Shoe(1);
        return shoe;
    }


    /**
     * This method will ask for player actions (hit, stand or double)
     * If current player's total score is between 9 and 11, offers player to double the bet, hit or stand
     * If current player's total score is less than 22 (not busted), offers player to hit or stand
     * Otherwise will display busted notification to console
     *
     * @param player current player
     */
    /* TODO - STILL NOT WORKING, PLEASE FINISH
    public String playerActions(Player player) {
        try {
            int score = getTotalValue(player.getHand());
            String input;

            if (9 <= score && score <= 11) {
                view.output("Would you like to double, hit or stand ?");
                input = view.input();
            } else if (score > 21) {
                view.output("Busted...you lose !!! Your total score is " + score);
            } else {
                view.output("Would you like to hit or stand ?");
                input = view.input();
            }

        }catch (Exception e) {
            quit();
        }
        return input;
    }
    */

        /**
         * This method counts the total value / score of the current hand
         *
         * @param hand is current hand
         * @return total value / score of the current hand
         */
        public int getTotalValue(ArrayList<Card> hand)
        {
            Iterator handIterator = hand.iterator();
            int acesCounter = 0, total = 0;
            while (handIterator.hasNext()) {
                Card currentCard = (Card) handIterator.next();
                if (currentCard.getNumber() < 2) {
                    acesCounter++;
                } else {
                    if (currentCard.getNumber() < 10) {
                        total = total + currentCard.getNumber();
                    } else {
                        total = total + 10;
                    }
                }
            }
            if (acesCounter > 0) {
                if ((acesCounter - 1 + 11) + total <= 21) {
                    total = total + (acesCounter - 1 + 11);
                } else {
                    total = total + acesCounter;
                }
            }
            return total;
        }
    }
