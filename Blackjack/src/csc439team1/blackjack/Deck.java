package csc439team1.blackjack;

import java.util.*;

/**
 * Deck class is a collection of Card objects
 */
public class Deck
{
    /**
     * deck is an ArrayList<Card> (an ArrayList containing Card objects)
     */
    private ArrayList<Card> deck = new ArrayList<Card>(); //Initialize deck as ArrayList<Card>

    /**
     * Deck() is a constructor for Deck object that consists of 52 Card objects
     */
    public Deck()
    {
        //loop 4 times, each loop will generate a deckValue ranges from 0 to 3
        for (int deckValue = 0; deckValue <= 3; deckValue++)
        {
            //loop 13 times, each loop will generate a cardValue ranges from 1 to 13
            for (int cardValue = 1; cardValue <= 13; cardValue++)
            {
                deck.add(new Card(cardValue, deckValue));   //add a card to the deck
            }
        }
    }

    /**
     * pick() will remove and return one random card from current deck
     *
     * @return a single card removed from the current deck
     */
    public Card pick()
    {
        if (deck.size() <= 0) throw new IllegalArgumentException();
        else
        {
            int random = (int) (Math.random() * deck.size());    //generate random number from 0 to the current size of the deck
            return (deck.size() != 0 ? deck.remove(random) : null); //remove and return random from deck
        }
    }

    /**
     * toString() returns string representation of the current deck (ex: Ace of CLUB, 2 of SPADE,......)
     *
     * @return string representation of all cards available in the Deck
     */
    public String toString()
    {
        String stringDeck = ""; //initialize stringDeck

        //loop for each card in current deck
        for (Card card : deck)
        {
            stringDeck += card.toString() + "\n"; //add current card.toString() to stringDeck
        }
        return stringDeck;
    }

    /**
     * size() is the size of the current deck
     *
     * @return size of the amount of total cards left in the deck (int type)
     */
    public int size()
    {
        return deck.size(); //return the size of the deck
    }
}