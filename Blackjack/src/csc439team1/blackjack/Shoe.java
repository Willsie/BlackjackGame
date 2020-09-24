package csc439team1.blackjack;

import java.util.*;

public class Shoe
{
    private ArrayList<Deck> shoe;

    public Shoe(int numberOfDecks)
    {
        shoe = new ArrayList<Deck>();

        //loop n times, n is the numberOfDecks that was taken from user input
        for (int totalDecks = 0; totalDecks < numberOfDecks; totalDecks++)
        {
            shoe.add(new Deck());
        }
    }

    public int numDeck()
    {
        return shoe.size();
    }

    /*
    public Card pick()
    {
        int randomDeck = (Math.random() < 0.5 ? 0 : 1);
        if ((shoe.get(randomDeck)).size() == 0)
        {
            shoe.remove(randomDeck);
            return (shoe.size() != 0 ? (shoe.get(randomDeck)).pick() : null); //remove and return first card from shoe (main shoe not player shoe)
        }
    }
    */


        /*
        public int size ()
        {
            for (Deck deck )
            return shoe.size(); //return the size of the shoe
        }
        */

    public static void main(String[] args)
    {
        Shoe shoe1 = new Shoe(6);
        System.out.println(shoe1.toString());
        //System.out.println("The size of the shoe is: " + shoe1.size() + "\n");
        System.out.println(shoe1.toString());
    }

}
