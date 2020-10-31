package csc439team1.blackjack.view;

import csc439team1.blackjack.model.Dealer;
import csc439team1.blackjack.model.Player;

public abstract class View
{
    /**
     * asks player the amount of chips to buy
     *
     */
    public abstract void buyChip();

     /**
      * displays collection of cards on current Hand
      *
      * @return collection of cards on current Hand
      */
    public abstract String showHand(Player playerName);


    /**
     * displays collection of cards on current Hand
     *
     * @return collection of cards on current Hand
     */
    public abstract String showHand(Dealer dealerName);

    /**
     * displays total amount of chips
     *
     * @return total amount of chips
     */
    public abstract int showChips();

    /**
     * displays the total amount of money
     *
     * @return total amount of money
     */
    public abstract double showMoney();

    /**
     * displays the total value of Hand
     *
     * @return total value of Hand
     */
    public abstract int showValue();

    /**
     * asks the player to hit (h) or stand (s)
     *
     * @return true if player input "hit" or "h"
     */
    public abstract boolean hitOrStand();

    /**
     * displays notification that player choose to quit
     *
     * @return a string to confirm that a player has entered "quit" to exit the game
     */
    public abstract String quit();

    /**
     * display the notification "Blackjack game is starting"
     */
    public abstract void gameStart();

    /**
     * display the notification "Jackpot, you win 1.5 times!"
     */
    public abstract void jackpot();

    /**
     * display the notification "Busted, you lose!"
     */
    public abstract void busted();

    /**
     * display the notification "You lost the bet!"
     */
    public abstract void lostBet();

    /**
     * display the notification "You win the bet!"
     */
    public abstract void winBet();

    /**
     * display the notification "Draw, it is a push !!!"
     */
    public abstract void push();
}
