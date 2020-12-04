package csc439team1.blackjack.model;
import java.util.logging.Logger;
/**
 * This is a unique card that has 2 private variables: int suit and int number
 * @author Trevor Sears
 * @author Will Sie
 * @author Conner Martin
 */
public class Card
{
    /**
     * Logger for Card class.
     */
    private static final Logger logger = Logger.getLogger(Card.class.getName());
    /**
     * The card's suit(CLUBS, HEARTHS, SPADES, DIAMONDS)
     */
    private final int suit;

    /**
     * The card's number ranges from 1 to 13 (for example: 1 is ACE and 13 is KING; however ACE can also be 11 depending on the play)
     */
    private final int number;

    /**
     * constructor for Card object that takes 2 parameters: int cardNumber, and int cardSuit
     *
     * @param cardNumber the number of the card ranges from 1 to 13 (1 is ACE, 11 is JACK, 12 is QUEEN, 13 is KING)
     * @param cardSuit   the suit of the card (0 is CLUBS, 1 is HEARTS, 2 is SPADES, 3 is DIAMONDS)
     * @throws IllegalArgumentException if the parameter cardSuit is smaller than 0 or greater than 3 or
     *                                  if the parameter cardNumber is smaller than 1 or greater than 13
     */
    public Card(int cardNumber, int cardSuit)
    {
        logger.entering(getClass().getName(), "Card");
        if (cardSuit > 3 || cardSuit < 0) {     //if cardSuit > 3 or cardSuit <0, throws IllegalArgumentException
            logger.warning("Suit value of either below 0 or greater than 3 entered, " + cardSuit);
            throw new IllegalArgumentException("Illegal card suit:" + cardSuit);
        }
        else if (cardNumber > 13 || cardNumber < 1) {    //if cardNumber > 13 or cardNumber < 1, throws IllegalArgumentException
            logger.warning("Card number of below 1 or greater than 13 entered, " + cardNumber);
            throw new IllegalArgumentException("Illegal card number:" + cardNumber);
        }
        logger.config("Card Suit is " + cardSuit + ". Card Number is " + cardNumber);
        number = cardNumber;
        suit = cardSuit;
        logger.exiting(getClass().getName(), "Card");
    }

    /**
     * get method that will return the suit of the card
     *
     * @return suit of the card (int type)
     */
    public int getSuit()
    {
        logger.entering(getClass().getName(), "getSuit");
        logger.severe("Unexpected Error with getSuit method");
        logger.exiting(getClass().getName(), "getSuit");
        return this.suit;

    }

    /**
     * get method that will return the number of the card
     *
     * @return number of the card (int type)
     */
    public int getNumber()
    {
        logger.entering(getClass().getName(), "getNumber");
        logger.severe("Unexpected error related to suit, " + number);
        logger.exiting(getClass().getName(), "getNumber");
        return this.number;
    }

    /**
     * get method that will return the string representation of the card's suit
     *
     * @return a string representation of the card suit (string type)
     */
    public String getSuitString()
    {
        logger.entering(getClass().getName(), "getSuitString");
        logger.severe("Issue related to Suit or switch in method, " + suit);
        logger.exiting(getClass().getName(), "getSuitString");
        switch (suit)
        {
            case 1:
                return "HEART";
            case 2:
                return "SPADE";
            case 3:
                return "DIAMOND";
            default:
                return "CLUB";
        }
    }

    /**
     * get method that will return the string representation of the card's number
     *
     * @return a string representation of the card number such as "Ace", "2", "3", ...,"10", "Jack", "Queen", and "King"
     */
    public String getNumberString()
    {
        logger.entering(getClass().getName(), "getNumberString");
        logger.severe("Related to card number or switch in getNumberString, " + number);
        logger.exiting(getClass().getName(), "getNumberString");
        switch (number)
        {
            case 1:
                return "Ace";   //return "Ace" if the card's number is 1
            case 11:
                return "Jack";  //return "Jack" if the card's number is 11
            case 12:
                return "Queen"; //return "Queen" if the card's number is 12
            case 13:
                return "King";  //return "King" if the card's number is 13
            default:
                return String.valueOf(number); //else return the number of the card
        }
    }

    /**
     * Overriding toString() method and will return the number of the card + " of " + the suit of the card (ex: Ace of SPADE)
     *
     * @return string representation of the card
     */
    @Override
    public String toString()
    {
        logger.entering(getClass().getName(), "toString");
        logger.severe("Related to toString override with either number string or suit string, " + getNumberString() + " or " + getSuitString());
        logger.exiting(getClass().getName(), "toString");
        return getNumberString() + " of " + getSuitString();
    }
}