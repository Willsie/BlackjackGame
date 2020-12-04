package csc439team1.blackjack.model;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Dealer class contains a private variable type Hand
 */
public class Dealer {
    /**
     * Logger for Dealer class.
     */
    private static final Logger logger = Logger.getLogger(Card.class.getName());
    /**
     * this is the collection of cards on the current Dealer hand
     */
    private Hand dealerHand = new Hand();

    /**
     * default no args constructor for Dealer()
     */
    public Dealer() {
        logger.entering(getClass().getName(), "Dealer");
        logger.severe("Issue related to Dealer constructor");
        logger.exiting(getClass().getName(), "Dealer");
    }


    /**
     * Getter method for Dealer's hand
     * @return dealer's hand.
     */
    public ArrayList<Card> getHand() {
        logger.entering(getClass().getName(), "getHand");
        logger.info("getHand is: " + dealerHand.getCards());
        logger.exiting(getClass().getName(), "getHand");
        return dealerHand.getCards();
    }

    /**
     * Adds a card to the dealer's hand.
     * @param card Card returned from shoe.pick() will be added to dealer's hand.
     */
    public void addCard(Card card){
        logger.entering(getClass().getName(), "addCard");
        dealerHand.addCard(card);
        logger.info("addCard " + card.toString());
        logger.exiting(getClass().getName(), "addCard");
    }
}