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
     * double variable that holds amount for cut of shoe, so that shoe may be repopulated during gameplay.
     */
    double cut, bet = 0;

    /**
     * Shoe object to be used during game play, and hold at current hard coded number of decks.
     */
    Shoe shoe;
    /**
     * Boolean hasQuit is used for looping gameplay mechanics after purchasing chips and setting cut amount
     */
    private boolean hasQuit, doubled, plyBust, dlrBust;
    /**
     * int variable shoeSize holds the number of decks to be used in the shoe, currently that number is hardcoded
     * versus asking for user input.
     */
    private int shoeDecks = 1;
    /**
     * Constructor, for Controller object, accepts a View parameter. View object is required as Controller passes messages
     * and requirements to user via View and CLIView.
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
        shoe = new Shoe(shoeDecks);
        cut = shoe.size() * .2;
        System.out.println(cut);
        buyChips();
        while(!hasQuit) {
            askBet();
            initialDeal(shoe);
            playerDouble();
            playerAction();
            postPlayerAction();
            dealerActions();
            endGameFunc();
        }
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
                if (input >= 10 && input <= 5000) {
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
                double input = view.intInput();
                if ((input >= 10) && (input <= 500) && (input <= player.getChips())) {
                    validInput = true;
                    bet = input;
                    player.setChips(player.getChips() - bet);
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
        //Added has quit boolean in order to perform loop beyond operations of buy chips in playBlackjack method. TS
        hasQuit = true;
        view.output("Player has quit\n");
        throw new IllegalStateException("");
    }

    /**
     * Method which repopulates shoe if it is less than cut variable (1/5 of original shoe size).
     */
    public void rePopulateShoe() {
        if (shoe.size() < cut) {
            shoe = new Shoe(2);
        }
    }
    public int getTotalValue(ArrayList<Card> hand) {
        Iterator handIterator = hand.iterator();
        int acesCounter = 0, total = 0;
        while (handIterator.hasNext()) {
            Card currentCard = (Card) handIterator.next();
            if (currentCard.getNumber() < 2) {
                acesCounter++;
            }
            else {
                if (currentCard.getNumber() < 10) {
                    total = total + currentCard.getNumber();
                }
                else {
                    total = total + 10;
                }
            }
        }
        if (acesCounter > 0) {
            if ((acesCounter - 1 + 11) + total <= 21) {
                total = total + (acesCounter -1 + 11);
            }
            else {
                total = total + acesCounter;
            }
        }
        return total;
    }
    public int playerTotal() {
        return getTotalValue(player.getHand());
    }
    public int dealerTotal()
    {
        return getTotalValue(dealer.getHand());
    }
    public boolean ableToDouble() {
        doubled = false;
        double chips = player.getChips();
        if (playerTotal() > 8 && playerTotal() < 12) {
            try {
                player.setChips(player.getChips() - bet);
            }
            catch (IllegalStateException e)
            {
                player.setChips(chips);
                return false;
            }
            player.setChips(chips);
            return true;
        }
       return false;
    }
    public void playerDouble() {
        boolean validInput = false;
        String input = "";
        if (ableToDouble())
        {
            view.output("Do you wish to double, y for yes or n for no");
            while (!validInput) {
                try {
                    input = view.input();
                    if (input.toLowerCase().equals("y") || input.toLowerCase().equals("n")) {
                        validInput = true;
                    }
                    else {
                        view.output("That is not a valid entry, y for yes or n for no");
                    }

                } catch (Exception e)
                {
                    quit();
                }
            }
            if (input.toLowerCase().equals("y")) {
                player.setChips(player.getChips() - bet);
                bet = bet * 2;
                player.addCard(shoe.pick());
                doubled = true;
            }
        }
    }
    public String playerInputActions() {
        boolean validInput = false;
        String action = "";
        while (!validInput) {
            try {
                action = view.input();
                if (action.toLowerCase().equals("h") || action.toLowerCase().equals("s") ) {
                    validInput = true;
                }
                else {
                    view.output("That is not a valid entry, h for hit or s for stand");
                }
            }
            catch (Exception e) {
                quit();
            }
        }
        return action;
    }
    public void playerAction() {
        String action = "";
        boolean stand = false;
        if (!doubled){
            while (playerTotal() < 21 && !stand) {
                view.output("Would you like to hit or stand, h for hit or s for stand");
                action = playerInputActions();
                if (action.toLowerCase().equals("h")) {
                    player.addCard(shoe.pick());
                    view.output("\nYour cards are: ");
                    view.output(player.getHand().toString() + "\n\n");
                }
                else {
                    stand = true;
                }
            }
        }
    }
    public void postPlayerAction() {
        if (playerTotal() > 21){
            view.output("\nYou have gone bust, and lost the hand\n");
            plyBust = true;
        }
        else if(playerTotal() == 21){
            view.output("\nBlackjack\n");
        }
    }
    public void dealerActions(){
        if(!doubled){
            while (dealerTotal() < 17) {
                dealer.addCard(shoe.pick());
                if (dealerTotal() > 21){
                    dlrBust = true;
                }
            }
        }
    }
    public void results(){
        view.output("Dealer total: " + dealerTotal() + "\n");
        view.output("Your total:" + playerTotal() + "\n");
        if (!plyBust)
        {
            if (playerTotal() == dealerTotal()){
                view.output("Draw, therefore push\n");
                player.setChips(player.getChips() + bet);
            }
            else if (!dlrBust){
                if (playerTotal() > dealerTotal())
                {
                    view.output("You have won\n");
                    player.setChips(player.getChips() + (1.5 * bet));
                }
                else {
                    view.output("You have lost\n");
                }
            }
            else {
                view.output("You have won\n");
                player.setChips(player.getChips() + (1.5 * bet));
            }
        }

    }
    public void endGameFunc(){
        results();
        view.output("\nDealer cards are: ");
        view.output(dealer.getHand().toString() + "\n\n");
        view.output("Your cards are: ");
        view.output(player.getHand().toString() + "\n\n");
        view.output("End of hand\n");
        view.output("Your remaining chips are :" + player.getChips() + "\n\n");
        player.getHand().clear();
        dealer.getHand().clear();
        checkCut();
        dlrBust = false;
        plyBust = false;
    }
    public void checkCut(){
        if (shoe.size() < cut) {
            shoe = new Shoe(shoeDecks);
        }
    }
    /**
     * This method will check if the current shoe is less than 1/5 of the initial shoe when first created
     * if it is less, then the new shoe will be generated
     *
     * @param shoe current shoe
     * @return new shoe if the size of the current shoe is less than 1/5 of the initial shoe
     */
   /* public Shoe cut(Shoe shoe) {
        if(shoe.size() < 11) shoe = new Shoe(1);
        return shoe;
    }*/

    /*
    //To Do - finish and insert JavaDoc
    public void double(Hand hand){
        if (9 <= getTotalScore(hand) <= 11 ){
            view.output('Would you like to double, hit or stand ?');
        }
    }
    */

}
