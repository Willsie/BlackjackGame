package csc439team1.blackjack.model;

/**
 * This is a unique card that has 2 private variables: int suit and int number
 * @author Trevor Sears
 * @author Will Sie
 * @author Conner Martin
 */
public class Card
{
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
        if (cardSuit > 3 || cardSuit < 0)       //if cardSuit > 3 or cardSuit <0, throws IllegalArgumentException
            throw new IllegalArgumentException("Illegal card suit:" + cardSuit);
        else if (cardNumber > 13 || cardNumber < 1)    //if cardNumber > 13 or cardNumber < 1, throws IllegalArgumentException
            throw new IllegalArgumentException("Illegal card number:" + cardNumber);
        number = cardNumber;
        suit = cardSuit;
    }

    /**
     * get method that will return the suit of the card
     *
     * @return suit of the card (int type)
     */
    public int getSuit()
    {
        return this.suit;
    }

    /**
     * get method that will return the number of the card
     *
     * @return number of the card (int type)
     */
    public int getNumber()
    {
        return this.number;
    }

    /**
     * get method that will return the string representation of the card's suit
     *
     * @return a string representation of the card suit (string type)
     */
    public String getSuitString()
    {
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
        return getNumberString() + " of " + getSuitString();
    }
}