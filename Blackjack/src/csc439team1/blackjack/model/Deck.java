package csc439team1.blackjack.model;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Deck class is a collection of Card objects
 *
 * @author Trevor Sears
 * @author Will Sie
 * @author Conner Martin
 */
public class Deck {
    /**
     * Logger for Deck class.
     */
    Logger logger = Logger.getLogger(Card.class.getName());
    /**
     * deck is an ArrayList<Card> (an ArrayList containing Card objects)
     */
    private ArrayList<Card> deck = new ArrayList<>(); //Initialize deck as ArrayList<Card>

    /**
     * Deck() is a constructor for Deck object that consists of 52 Card objects
     */
    public Deck() {
        logger.entering(getClass().getName(), "Deck");
        logger.setLevel(Level.INFO);
        //loop 4 times, each loop will generate a deckValue ranges from 0 to 3
        for (int deckValue = 0; deckValue <= 3; deckValue++) {
            logger.info("Beginning inner for loop deck value is " + deckValue);
            //loop 13 times, each loop will generate a cardValue ranges from 1 to 13
            for (int cardValue = 1; cardValue <= 13; cardValue++) {
                logger.info("Inside inner for loop deck value is " + deckValue + ". card value is " + cardValue);
                deck.add(new Card(cardValue, deckValue));   //add a card to the deck
            }
        }
        logger.exiting(getClass().getName(), "Deck");
    }

    /**
     * pick() will remove and return one random card from current deck
     *
     * @return a single card removed from the current deck
     */
    public Card pick() throws IllegalStateException {
        logger.entering(getClass().getName(), "pick");
        logger.setLevel(Level.INFO);
        int random = (int) (Math.random() * deck.size());   //generate random number from 0 to current size of the deck
        logger.info("Random int is " + random);
        if (deck.size() < 1){
            logger.info("Thrown exception due to deck size being less than 1");
            throw new IllegalStateException();
        }
        else {
            logger.info("Removing this card from the deck " + deck.get(random).toString());
            logger.exiting(getClass().getName(), "pick");
            return deck.remove(random); //remove and return random card from deck
        }
    }

    /**
     * size() is the size of the current deck
     *
     * @return size of the amount of total cards left in the deck (int type)
     */
    public int size() {
        logger.entering(getClass().getName(), "size");
        logger.setLevel(Level.INFO);
        logger.info("Something is wrong with size of deck" + deck.size());
        logger.exiting(getClass().getName(), "size");
        return deck.size(); //return the size of the deck
    }
}