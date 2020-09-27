package csc439team1.blackjack;

import java.util.*;

public class Shoe
{
    private ArrayList<Deck> shoe;   //declare an array list of deck called "shoe"

    /**
     * This is Shoe constructor that takes number of deck(s)
     *
     * @param numberOfDecks the number of deck(s) taken from user input
     */
    public Shoe(int numberOfDecks)  //Shoe constructor that takes number of deck(s)
    {
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
        int totalDeck = 0;  //Initialize total deck to 0
        for(Deck deck : shoe){  //For each loop that will iterate every deck in the shoe
            if(deck.size() == 0) shoe.remove(deck); //if the current deck size is 0, remove current deck from shoe
            else totalDeck ++;  //increase the total deck by one
        }
        return totalDeck;   //return the total number of deck(s) in the shoe
    }

    /**
     * This is the method that will pick one random card from the current shoe
     *
     * @return a random card by calling Deck.pick()
     */
    public Card pick()
    {
        int randomDeck = (int) (Math.random() * numDeck()); //initialize a random number between 0 to the total number of deck(s) available in the shoe
        return (shoe.size() != 0 ? (shoe.get(randomDeck)).pick() : null); //return a random card by calling the Deck.pick()
    }

    /**
     * This is the method that will count the total card(s) in the shoe
     *
     * @return the total card(s) in the shoe
     */
    public int size()
    {
        int totalCards = 0;
        for(Deck deck: shoe){
            totalCards += deck.size();
        }
        return totalCards; //return the total card(s) in the shoe
    }

    /**
     * This is the method that will return string representation of the current shoe (ex: Ace of CLUB, 2 of SPADE,......)
     *
     * @return tring representation of all cards available in the current shoe
     */
    public String toString()
    {
        String stringShoe = ""; //initialize stringShoe

        //loop for each deck in current shoe
        for (Deck deck : shoe)
        {
            stringShoe += deck.toString(); //add current deck.toString() to stringShoe
        }
        return stringShoe;
    }
}
