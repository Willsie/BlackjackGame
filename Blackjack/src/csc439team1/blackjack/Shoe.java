package csc439team1.blackjack;

import java.util.*;

public class Shoe
{

    private ArrayList<Card> shoe;

    public Shoe(int numberOfDecks)
    {
        shoe = new ArrayList<Card>();

        //loop 4 times, each loop will generate a shoeValue ranges from 0 to 3
        for (int shoeValue = 0; shoeValue <= 3; shoeValue++)
        {
            //loop n times (n is the number of deck(s) inserted by player
            //each loop will generate a cardValue ranges from 1 to 13
            for (int cardValue = 1; cardValue <= numberOfDecks * 13; cardValue++)
            {
                shoe.add(new Card(cardValue % 13, shoeValue));
            }
        }
    }

    public Card deal()
    {
        return (shoe.size() != 0 ? shoe.remove(0) : null); //remove and return first card from shoe (main shoe not player shoe)
    }

    public String toString()
    {
        String stringshoe = "";

        //loop for each card in current shoe
        for (Card card : shoe)
        {
            stringshoe += card.toString() + "\n"; //add current card.toString() to stringshoe
        }
        return stringshoe;
    }

    public int size()
    {
        return shoe.size(); //return the size of the shoe
    }

    public void shuffle()
    {
        Collections.shuffle(shoe); //shuffle the current shoe
    }

    public boolean isEmpty()
    {
        return (shoe.size() == 0);  //returns true if no card in the shoe; otherwise return false
    }

    public void reset()
    {
        //loop for each card in current shoe
        for (Card card : shoe)
        {
            shoe.remove(card);  //remove current card
            deal();             //add new card (replacing the removed card)
        }
    }

    public static void main(String[] args)
    {
        Shoe shoe1 = new Shoe(6);
        System.out.println(shoe1.toString());
        System.out.println("The size of the shoe is: " + shoe1.size() + "\n");
        shoe1.shuffle();
        System.out.println(shoe1.toString());
    }
}