package csc439team1.blackjack;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hand class is a collection of cards who are being played after being picked from the shoe / deck
 *
 * has one private variable ArrayList<Card> cards
 */
public class Hand
{
    /**
     * an array list of card objects called "cards"
     */
    private final ArrayList<Card> cardsOnHand;

    /**
     * Hand object default constructor
     */
    public Hand()
    {
        this.cardsOnHand = new ArrayList<>();
    }   //constructor for Hand method

    /**
     * this is a method to add card to the current Hand
     *
     * @param anotherCard is a card that is being added to the current Hand
     */
    public void addCard(Card anotherCard)
    {
        cardsOnHand.add(anotherCard);
    }

    /**
     * this is the size of the Hand / total cards in the current Hand
     *
     * @return the total cards in the current Hand
     */
    public int size()
    {
        return cardsOnHand.size();
    }

    /**
     * this is a method to calculate the total value of all cards in the current Hand
     * the ACEs value will be adjusted according to the play
     *
     * @return the total value of all cards in the current Hand
     */
    public int getTotalValue()
    {
        int acesCounter = 0, total = 0;
        for (Card currentCard: cardsOnHand)
        {
            if (currentCard.getNumber() < 2)
            {
                acesCounter++;
            }
            else
            {
                if (currentCard.getNumber() < 10)
                {
                    total = total + currentCard.getNumber();
                }
                else
                {
                    total = total + 10;
                }
            }
        }
        if (acesCounter > 0)
        {
            if ((acesCounter - 1 + 11) + total <= 21)
            {
                total = total + (acesCounter -1 + 11);
            }
            else
            {
                total = total + acesCounter;
            }
        }
        return total;
    }

    /**
     * the getCards method returns the combination of all cards in the current Hand.
     *
     * @return an array list of card objects in the current Hand
     */

    public ArrayList<Card> getCards()
    {
       return cardsOnHand;
    }

}
