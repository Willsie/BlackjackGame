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
     * double variable cut that holds amount for cut of shoe, so that shoe may be repopulated during gameplay; and
     * double variable bet which stores the amount of the player's bet.
     */
    double cut, bet = 0;

    /**
     * Shoe object to be used during game play, and hold at current hard coded number of decks.
     */
    Shoe shoe;
    /**
     * Boolean values for doubling (doubled), plyBust (player bust), dlrBust (dealer bust), and blackJack (for natural
     * blackjack); which are used by various methods to judge winners/losers, or if the hand skips to end due to a blackjack
     * on the initial deal.
     */
    private boolean doubled, plyBust, dlrBust, blackJack;
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
        //cut variable is 1/5 of original shoe size, and is used by checkCut() to see if shoe needs repopulating
        cut = shoe.size() * .2;
        buyChips();
        //While loop which operates as long as the player's chips are at minimum bet amount (10)
        while(player.getChips() >= 10) {
            askBet();
            initialDeal(shoe);
            naturalBlackJack();
            //Conditional clause that is only true if there has not been an natural blackjack, else goes straight to endHandFunctions
            if(!blackJack){
                playerDouble();
                playerAction();
                postPlayerActions();
            }
            endHandFunctions();
        }
        endGameActions();
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
                    //Changed to player.getChips + input to hold any leftover chips if game is restarted by endGameActions
                    player.setChips(player.getChips() + (input));
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
        view.output(dealer.getHand().get(0).toString() + "\n");
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
     * Method for calculating the total of a hand. First runs through entire hand and calculates all cards total except
     * aces, acesCounter tracks the number of aces in a hand and is used by the final if clause for calculation. The
     * final if clause subtracts 1 from the number of aces (provided there are any aces) and adds it 11 (one ace counted
     * as 11, will never have more than 1 ace count as 11 as that would equal 22), then compares to see if it exceeds
     * 21, should that exceed 21 all aces are counted as one (the aceCounter) and added to the total.
     * @param hand Param is an Arraylist of cards, ie a hand.
     * @return Returns an int that is the total value of the cards in a hand.
     */
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

    /**
     * Method which returns player hand total by calling getTotalValue method, used to save on typing out
     * getTotalValue() repeatedly.
     * @return returns int of total card values for the player hand.
     */
    public int playerTotal() {
        return getTotalValue(player.getHand());
    }

    /**
     * Method which returns dealer hand total by calling getTotalValue method, used to save on typing out
     * getTotalValue() repeatedly.
     * @return returns int of total card values for the dealer hand.
     */
    public int dealerTotal()
    {
        return getTotalValue(dealer.getHand());
    }

    /**
     * Method that looks for natural blackjack in either player or dealer, and sets blackJack boolean to true; with the
     * effect that the game goes straight to endGameFunc, where if both player & dealer have blackjack will produce a push
     * or declare winner if only one has blackjack.
     * @return boolean value blackJack
     */
    public boolean naturalBlackJack(){
        if (playerTotal() == 21 || dealerTotal() == 21){
            blackJack = true;
            view.output("Blackjack\n");
            return blackJack;
        }
        return blackJack;
    }

    /**
     * Method which checks if the player is able to double by checking both total and seeing if player.setChips will throw
     * an IllegalStateException due to making number of chips go below 0. Should the conditional clause not execute or the
     * exception gets thrown, method will return false signifying to playerDouble method that player is ineligible to double.
     * @return Returns an boolean value representing whether the player can double or not.
     */
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

    /**
     * playerDouble executes the prompt asking if the player wishes to double, provided ableToDouble returns true. Should
     * the player respond yes their chips are decremented an additional amount of the initial bet (making the bet double
     * in size, setting the bet variable to twice its original size, and adding an additional card to simulate live
     * doubling. Additionally, boolean variable doubled is set to true, as it is used by playerAction method to determine
     * if the player can hit or stand.
     */
    public void playerDouble() {
        String input = "";
        if (ableToDouble()) {
            view.output("Do you wish to double, y for yes or n for no");
            //call to player input actions to verify user input is acceptable. TS
            input = playerInputActions("yes", "no");
            if (input.toLowerCase().equals("y")) {
                player.setChips(player.getChips() - bet);
                bet = bet * 2;
                player.addCard(shoe.pick());
                doubled = true;
            }
        }
    }

    /**
     * printHand is a private method that other methods within Controller class can call, pass a char ('p' will print the
     * player's hand, while all other chars will execute dealers hand to print).
     * @param a is String parameter filled by other method(s) inside of Controller class.
     */
    private void printHand(String a){
        if (a == "p"){
            view.output("Your cards are: ");
            view.output(player.getHand().toString() + "\n");
        }
        else{
            view.output("Dealer cards are: ");
            view.output(dealer.getHand().toString() + "\n");
        }
    }

    /**
     * playerInputActions method takes string input, which it uses to prompt the user for desired entries. The entries
     * all checked in a try catch block in a while loop to certify that the entered data is correct, with the ability of
     * the user to quit at anytime (same methodology as askBet and buyChips. Method also uses the first letter of each
     * string parameter to output to the user what they are expected to answer, ie y for yes or h for hit.
     * @param a is a string which corresponds to either yes or hit
     * @param b is a sting entered by other methods which corresponds to no or stand.
     * @return Returns a string of which will be used by other methods to determine user intent of y for yes, h for hit, ect.
     */
    public String playerInputActions(String a, String b) {
        boolean validInput = false;
        String action = "", c = a.substring(0, 1), d = b.substring(0, 1);
        while (!validInput) {
            try {
                action = view.input();
                if (action.toLowerCase().equals(c) || action.toLowerCase().equals(d) ) {
                    validInput = true;
                }
                else {
                    view.output("That is not a valid entry, " + c + " for " + a + "or " + d + " for " + b);
                }
            }
            catch (Exception e) {
                quit();
            }
        }
        return action;
    }

    /**
     * playerAction method determines if the player is able to hit or stand, and what input function they wish to pursue,
     * operates under a while block (executes if player did not double) that loops provided the player total is below 21
     * and has not indicated a desire to stand. Calls to playerInputActions to verify input of user data, and give user
     * opportunity to quit game.
     */
    public void playerAction() {
        String action = "";
        boolean stand = false;
        if (!doubled){
            while (playerTotal() < 21 && !stand) {
                view.output("Would you like to hit or stand, h for hit or s for stand");
                action = playerInputActions("hit", "stand");
                if (action.toLowerCase().equals("h")) {
                    player.addCard(shoe.pick());
                    printHand("p");
                }
                else {
                    stand = true;
                }
            }
        }
    }

    /**
     * postPlayerActions handles what transpires after the player has initiated a stand or gone bust. The if clause checks
     * if the player has gone over 21 and went bust, with printing a statement to the player stating such, then heading
     * to endGameFunctions method. Should the if clause not be true, then the else clause will execute and have the dealer
     * hit while the dealer total is below 17; there is nested if clause within the else that checks if the dealer has gone
     * bust, and is used by results method to algorithms to ease complications of conditional clauses.
     */
    public void postPlayerActions() {
        if (playerTotal() > 21){
            view.output("You have gone bust, and lost the hand\n");
            plyBust = true;
        }
        else
        {
            while (dealerTotal() < 17) {
                dealer.addCard(shoe.pick());
            }
            if (dealerTotal() > 21){
                dlrBust = true;
            }
        }
    }

    /**
     * This method performs functions for the end of the hand cycle, with statements telling the player the hand has ended,
     * a call to results method (which prints both hands, player and dealer, and determines a winner or if push occurs),
     * then prints the dealers remaining chips. Finally, endHandFunctions clears both hands, calls checkCut to see if shoe
     * must be repopulated, and resets all boolean values to false for the next hand.
     */
    public void endHandFunctions(){
        view.output("End of hand\n");
        results();
        view.output("Your remaining chips are :" + player.getChips() + "\n\n");
        player.getHand().clear();
        dealer.getHand().clear();
        checkCut();
        dlrBust = false;
        plyBust = false;
        blackJack = false;
    }

    /**
     * results method calculates if there is a draw/push or declares a winner. if the plyBust boolean is true no action is
     * taken after printing the totals for dealer and player; should plyBust be false the totals are compared, if equal
     * a push occurs, should dlrBust be true or player total is greater the player is declared winner since the player is
     * closer to 21 or the dealer exceeded 21. Should neither of the preceding conditions be true the dealer is declared
     * winner since they have not gone bust and have a greater total than the player.
     */
    public void results(){
        printHand("d");
        printHand("p");
        view.output("Dealer total: " + dealerTotal() + "\n");
        view.output("Your total:" + playerTotal() + "\n");
        if (!plyBust)
        {
            if (playerTotal() == dealerTotal()){
                view.output("Draw, therefore push\n");
                player.setChips(player.getChips() + bet);
            }
            else if (dlrBust || playerTotal() > dealerTotal()){
                view.output("You have won\n");
                player.setChips(player.getChips() + (1.5 * bet));
            }
            else
            {
                view.output("You have lost, the Dealer has won\n");
            }
        }

    }

    /**
     * checkCut compares the number of cards in a shoe against the cut variable, and if the shoe is less than the cut
     * amount the deck is repopulated.
     */
    public void checkCut(){
        if (shoe.size() < cut) {
            shoe = new Shoe(shoeDecks);
        }
    }

    /**
     * Method that determines what route to take for the player once they have either fallen below minimum bet amount or
     * have 0 chips. They are prompted to either purchase chips, which calls playBlackjack to restart game, or type q for
     * quit (a reply of quit will also quit the game). The conditional clause only checks for a desire to quit, no additional
     * checks are needed since the only other option is to continue/restart with more chips.
     */
    public void endGameActions(){
        view.output("You have either run out of chips or fell below minimum bet amount, enter either p for purchase" +
                "or q for quit");
        String action = playerInputActions("p", "q");
        if (action.equals("q")){
            quit();
        }
        playBlackjack();
    }
}
