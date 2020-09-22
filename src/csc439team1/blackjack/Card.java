package csc439team1.blackjack;

/**
 * Card class is a blueprint for individual unique card that contains 2 private variables: suit and value.
 *
 * @author Conner Martin, Trevor Sears, Wie Lie Sie
 */
public class Card
{
    /**
     * The card's suit(CLUBS, HEARTHS, SPADES, DIAMONDS)
     */
    private final int suit;

    /**
     * The card's value ranges from 1 to 13 (for example: 1 is ACE and 13 is KING; however ACE can also be 11 depending on the play)
     */
    private final int value;

    /**
     * constructor for Card object that takes 2 parameters: int cardValue, and int cardSuit
     *
     * @param cardValue the value of the card ranges from 1 to 13 (1 is ACE, 11 is JACK, 12 is QUEEN, 13 is KING)
     * @param cardSuit  the suit of the card (0 is CLUBS, 1 is HEARTS, 2 is SPADES, 3 is DIAMONDS)
     * @throws IllegalArgumentException if the parameter cardSuit is smaller than 0 or greater than 3 or
     *                                  if the parameter cardValue is smaller than 1 or greater than 13
     */
    public Card(int cardValue, int cardSuit)
    {
        if (cardValue == 0) cardValue = 13;    //if cardValue is 0, set cardValue to 13
        if (cardSuit > 3 || cardSuit < 0)       //if cardSuit > 3 or cardSuit <0, throws IllegalArgumentException
            throw new IllegalArgumentException("Illegal card suit:" + cardSuit);
        else if (cardValue > 13 || cardValue < 1)    //if cardValue > 13 or cardValue < 1, throws IllegalArgumentException
            throw new IllegalArgumentException("Illegal card value:" + cardValue);
        value = cardValue;
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
     * get method that will return the value of the card
     *
     * @return value of the card (int type)
     */
    public int getValue()
    {
        return this.value;
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
            case 0:
                return "CLUB";
            case 1:
                return "HEART";
            case 2:
                return "SPADE";
            case 3:
                return "DIAMOND";
            default:
                return "";
        }
    }

    /**
     * get method that will return the string representation of the card's value
     *
     * @return a string representation of the card value such as "Ace", "2", "3", ...,"10", "Jack", "Queen", and "King"
     */
    public String getValueString()
    {
        switch (value)
        {
            case 1:
                return "Ace";   //return "Ace" if the card's value is 1
            case 11:
                return "Jack";  //return "Jack" if the card's value is 11
            case 12:
                return "Queen"; //return "Queen" if the card's value is 12
            case 13:
                return "King";  //return "King" if the card's value is 13
            default:
                return String.valueOf(value); //else return the value of the card
        }
    }

    /**
     * Overriding toString() method and will return the value of the card + " of " + the suit of the card (ex: Ace of SPADE)
     *
     * @return string representation of the card
     */
    @Override
    public String toString()
    {
        return getValueString() + " of " + getSuitString();
    }

    /**
     * equals(Card other) is the a method that compares 2 card objects for their properties
     *
     * @param otherCard is the card that we are comparing with the original card
     * @return true if both cards has the same values and suits; otherwise, return false
     */
    public boolean equals(Card otherCard)
    {
        // If the object is compared with itself then return true (has the same reference)
        if (otherCard == this)
        {
            return true;
        }

        /* Check if other is an instance of Card or not
          "null instanceof [type]" also returns false */
        if (!(otherCard instanceof Card))
        {
            return false;
        }

        // typecast otherCard to Card so that we can compare data members
        Card originalCard = (Card) otherCard;

        // Compare the data members (suit and value) and return accordingly
        return Integer.compare(this.suit, otherCard.suit) == 0 && Integer.compare(this.value, otherCard.value) == 0;
    }
}