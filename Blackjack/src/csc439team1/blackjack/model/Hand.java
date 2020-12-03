package csc439team1.blackjack.model;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Hand class is a collection of cards who are being played after being picked from the shoe / deck
 *
 * @author Trevor Sears
 * @author Will Sie
 * @author Conner Martin
 */
public class Hand
{
    /**
     * Logger for Hand class.
     */
    private static final Logger logger = Logger.getLogger(Card.class.getName());
    /**
     * an array list of card objects called "cards"
     */
    public final ArrayList<Card> cardsOnHand;

    /**
     * Hand object default constructor
     */
    public Hand()
    {
        logger.entering(getClass().getName(), "Hand");
        this.cardsOnHand = new ArrayList<>(); //constructor for Hand method
        logger.info("Creation of empty arraylist for card contained in hand");
        logger.info("cardsOnHand should contain no elements" + cardsOnHand);
        logger.exiting(getClass().getName(), "Hand");
    }

    /**
     * this is a method to add card to the current Hand
     *
     * @param anotherCard is a card that is being added to the current Hand
     */
    public void addCard(Card anotherCard)
    {
        logger.entering(getClass().getName(), "addCard");
        logger.info("Card being added is " + anotherCard);
        cardsOnHand.add(anotherCard);
        logger.exiting(getClass().getName(), "addCard");
    }

    /**
     * this is the size of the Hand / total cards in the current Hand
     *
     * @return the total cards in the current Hand
     */
    public int size()
    {
        logger.entering(getClass().getName(), "size");
        logger.info("cardsOnHand size is " + cardsOnHand.size());
        logger.exiting(getClass().getName(), "size");
        return cardsOnHand.size();
    }

    /**
     * the getCards method returns the combination of all cards in the current Hand.
     *
     * @return an array list of card objects in the current Hand
     */
    public ArrayList<Card> getCards()
    {
        logger.entering(getClass().getName(), "getCards");
        logger.info("getCards: " + cardsOnHand);
        logger.exiting(getClass().getName(), "getCards");
        return cardsOnHand;
    }
}
