package csc439team1.blackjack;

import java.util.*;

public class Deck
{

    private ArrayList<Card> deck;

    public Deck()
    {
        deck = new ArrayList<Card>();

        //loop 4 times, each loop will generate a deckValue ranges from 0 to 3
        for (int deckValue = 0; deckValue <= 3; deckValue++)
        {
            //loop 13 times, each loop will generate a cardValue ranges from 1 to 13
            for (int cardValue = 1; cardValue <= 13; cardValue++)
            {
                deck.add(new Card(cardValue, deckValue));
            }
        }
    }

    public Card deal()
    {
        return (deck.size() != 0 ? deck.remove(0) : null); //remove and return first card from deck (main deck not player deck)
    }

    public String toString()
    {
        String stringDeck = "";

        //loop for each card in current deck
        for (Card card : deck)
        {
            stringDeck += card.toString() + "\n"; //add current card.toString() to stringDeck
        }
        return stringDeck;
    }

    public int size()
    {
        return deck.size(); //return the size of the deck
    }

    public void shuffle()
    {
        Collections.shuffle(deck); //shuffle the current deck
    }

    public boolean isEmpty()
    {
        return (deck.size() == 0);  //returns true if no card in the deck; otherwise return false
    }

    public void reset()
    {
        //loop for each card in current deck
        for (Card card : deck)
        {
            deck.remove(card);  //remove current card
            deal();             //add new card (replacing the removed card)
        }
    }

    public static void main(String[] args)
    {
        Deck deck1 = new Deck();
        System.out.println(deck1.toString());
        System.out.println(deck1.size());
        deck1.shuffle();
        System.out.println(deck1.toString());
    }
}