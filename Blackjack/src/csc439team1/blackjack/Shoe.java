package csc439team1.blackjack;

import java.util.*;

/**
 * Shoe class contains the collections of deck objects
 *
 */
public class Shoe
{
    /**
     * an array list of deck called "shoe"
     */
    private ArrayList<Deck> shoe;

    /**
     * This is Shoe constructor that takes number of deck(s). Will throw IllegalArgumentException if a
     * number below 1 is entered.
     *
     * @param numberOfDecks the number of deck(s) taken from user input
     */
    public Shoe(int numberOfDecks)  //Shoe constructor that takes number of deck(s)
    {
        if (numberOfDecks < 1){
            throw new IllegalArgumentException();
        }
        shoe = new ArrayList<>();   //initialize shoe as new array list

        //loop n times, n is the numberOfDecks that was taken from user input
        for (int totalDecks = 0; totalDecks < numberOfDecks; totalDecks++)
        {
            shoe.add(new Deck());   //add new deck to the shoe
        }
    }

    /**
     * This is the method that will count the number of available deck(s) in the shoe
     *
     * @return the total number of deck(s) in the shoe
     */
    public int numDeck()
    {
        //Call to size method from Arraylist returns the number of decks within the shoe.
        return shoe.size();
    }

    /**
     * This is the method that will pick one random card from the current shoe, and removes
     * empty decks from shoe. Method will also throw IllegalStateException should the shoe be
     * allowed to allowed to run below 1 deck.
     *
     * @return a random card by calling Deck.pick()
     */
    public Card pick()
    {
        //Throws IllegalStatementException should game play neglect addressing an empty shoe.
        if (shoe.size() < 1)
        {
            throw new IllegalStateException();
        }
        /*Overall logic of pick method is creating an random int to access an deck within the shoe, create a card from
        what is returned by call to Deck class's pick method. Then checking if that was the last card for that deck,
        and removing the deck from shoe provided the condition is true. Using this method will remove the possibility
        pick method landing on an empty deck, as empty decks are removed within game play.*/

        //Creation of random int from 0 to number of decks within shoe.
        int randomDeck = (int) (Math.random() * numDeck());
        //Card generated by using pick method from Deck class.
        Card pickCard = (shoe.get(randomDeck)).pick();
        //if clause that will execute if no more cards are within the deck corresponding to randomDeck variable,
        //and remove that deck from the shoe.
        if ((shoe.get(randomDeck)).size() == 0)
        {
            shoe.remove(randomDeck);
        }
        return pickCard;
    }

    /**
     * This is the method that will count the total card(s) in the shoe
     *
     * @return the total card(s) in the shoe
     */
    public int size()
    {
        int totalCards = 0;
        for (Deck deck : shoe)
        {
            totalCards += deck.size();
        }
        return totalCards; //return the total card(s) in the shoe
    }
}
