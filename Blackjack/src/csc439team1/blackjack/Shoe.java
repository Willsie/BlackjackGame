package csc439team1.blackjack;

import java.util.*;

/**
 * Shoe class contains the collections of deck objects
 * <p>
 * has a private variable ArrayList<Deck> shoe
 */
public class Shoe {
    /**
     * an array list of deck called "shoe"
     */
    private ArrayList<Deck> shoe;

    /**
     * This is Shoe constructor that takes number of deck(s)
     *
     * @param numberOfDecks the number of deck(s) taken from user input
     */
    public Shoe(int numberOfDecks) throws IllegalArgumentException  //Shoe constructor that takes number of deck(s)
    {
        if (numberOfDecks <= 0) {
            throw new IllegalArgumentException("Number of decks must be positive.");
        } else {
            shoe = new ArrayList<>();   //initialize shoe as new array list

            //loop n times, n is the numberOfDecks that was taken from user input
            for (int totalDecks = 0; totalDecks < numberOfDecks; totalDecks++) {
                shoe.add(new Deck());   //add new deck to the shoe
            }

        }

    }

    /**
     * This is the method that will count the number of available deck(s) in the shoe. If a deck in the shoe is empty, it is removed by the iterator.
     *
     * @return the total number of deck(s) in the shoe
     */
    public int numDeck() {
        int totalDeck = 0;  //Initialize total deck to 0
        Iterator<Deck> iter = shoe.iterator();
        while(iter.hasNext()){
            Deck deck = iter.next();
            if (deck.size() == 0) {
                iter.remove();
            } else {
                totalDeck++;
            }
        }
        return totalDeck;   //return the total number of deck(s) in the shoe
    }

    /**
     * This is the method that will pick one random card from the current shoe
     *
     * @return a random card by calling Deck.pick()
     */
    public Card pick() {
        int randomDeck = (int) (Math.random() * numDeck()); //initialize a random number between 0 to the total number of deck(s) available in the shoe
        return (shoe.size() != 0 ? (shoe.get(randomDeck)).pick() : null); //return a random card by calling the Deck.pick()
    }

    /**
     * This is the method that will count the total card(s) in the shoe
     *
     * @return the total card(s) in the shoe
     */
    public int size() {
        int totalCards = 0;
        for (Deck deck : shoe) {
            totalCards += deck.size();
        }
        return totalCards; //return the total card(s) in the shoe
    }

}
