package csc439team1.blackjack.model;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Player class has 2 private variables int chips and Hand hand
 */
public class Player {
    /**
     * Logger for Player class.
     */
    private static final Logger logger = Logger.getLogger(Card.class.getName());
    /**
     * this is the current player's chips
     */
    private double chips = 0;

    /**
     * this is the current player's hand
     */
    private Hand hand = new Hand();

    /**
     * default no args constructor for Player()
     */
    public Player() {
        logger.entering(getClass().getName(), "Player");
        logger.severe("Issue related to Player constructor");
        logger.exiting(getClass().getName(), "Player");
    }

    /**
     * Getter method for chips.
     * @return player's chip value
     * @throws IllegalStateException via checkChips method.
     */
    public double getChips() {
        logger.entering(getClass().getName(), "getChips");
        //checkChips verifies that there are chips in player, throws IllegalStateException if there are no chips.TS
        logger.info("Checking correct value of chips via call to checkChips, chips value is " +chips);
        checkChips(chips);
        logger.exiting(getClass().getName(), "getChips");
        return chips;
    }

    /**
     * Setter method for chips.
     * @param chips set a new value for the player's chips. Will be done at the end of hand to reflect win/loss of bet.
     * @throws IllegalStateException via checkChips method.
     */
    //Changed to double for last phase of assignment. TS
    public void setChips(double chips) {
        logger.entering(getClass().getName(), "setChips");
        //checkChips verifies that there are chips in player, throws IllegalStateException if there are no chips. TS
        logger.info("Checking correct value of chips via call to checkChips, chips value is " + chips);
        checkChips(chips);
        this.chips = chips;
        logger.exiting(getClass().getName(), "setChips");
    }

    /**
     * Getter method for Player's hand
     * @return player's hand.
     * */
    public ArrayList<Card> getHand() {
        logger.entering(getClass().getName(), "getHand");
        logger.info("getHand method of Player class, getHand is: " + hand.getCards());
        logger.exiting(getClass().getName(), "getHand");
        return this.hand.getCards();
    }

    /**
     * Adds a card to the players hand.
     * @param card Card returned from shoe.pick() will be added to player's hand.
     */
    public void addCard(Card card){
        logger.entering(getClass().getName(), "addCard");
        logger.info("addCard method of Player class, card is: " + card);
        hand.addCard(card);
        logger.exiting(getClass().getName(), "addCard");
    }

    /**
     * Helper method for input validation used by other methods within the player object that deal with chip amounts.
     * Checks that there are chips for the player object, and throws IllegalStateException if no chips are present.
     * @param chips is total "chip" amount that in possession of the player.
     */
    //Changed to double for last phase of assignment. TS
    private void checkChips(double chips)
    {
        logger.entering(getClass().getName(), "checkChips");
        //checkChips looks for chips being less than 1, and throws IllegalStateException.TS
        if (chips < 0)
        {
            logger.warning("Exception thrown due to chips being below 0, chips are: " + chips);
            throw new IllegalStateException();
        }
        logger.exiting(getClass().getName(), "checkChips");
    }
}