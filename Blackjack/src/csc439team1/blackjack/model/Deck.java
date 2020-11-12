package csc439team1.blackjack.model;

import java.util.*;

/**
 * Deck class is a collection of Card objects
 *
 * @author Trevor Sears
 * @author Will Sie
 * @author Conner Martin
 */
public class Deck {
    /**
     * deck is an ArrayList<Card> (an ArrayList containing Card objects)
     */
    private ArrayList<Card> deck = new ArrayList<>(); //Initialize deck as ArrayList<Card>

    /**
     * Deck() is a constructor for Deck object that consists of 52 Card objects
     */
    public Deck() {
        //loop 4 times, each loop will generate a deckValue ranges from 0 to 3
        for (int deckValue = 0; deckValue <= 3; deckValue++) {
            //loop 13 times, each loop will generate a cardValue ranges from 1 to 13
            for (int cardValue = 1; cardValue <= 13; cardValue++) {
                deck.add(new Card(cardValue, deckValue));   //add a card to the deck
            }
        }
    }

    /**
     * pick() will remove and return one random card from current deck
     *
     * @return a single card removed from the current deck
     */
    public Card pick() throws IllegalStateException {
        int random = (int) (Math.random() * deck.size());   //generate random number from 0 to current size of the deck
        if (deck.size() < 1) throw new IllegalStateException();
        else {
            return deck.remove(random); //remove and return random card from deck
        }
    }

    /**
     * size() is the size of the current deck
     *
     * @return size of the amount of total cards left in the deck (int type)
     */
    public int size() {
        return deck.size(); //return the size of the deck
    }
}