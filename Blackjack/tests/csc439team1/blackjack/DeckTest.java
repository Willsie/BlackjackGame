package csc439team1.blackjack;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest
{

    @Test
    public void pick()
    {
        /*Deck deck0 = new Deck();
        System.out.println(deck0.toString());*/
    }

    /**
     * Tests exception handling in pick()
     */
    @Test (expected = IllegalArgumentException.class)
    public void pick1() {
        Deck deck1 = new Deck();
        for(int i = 0; i <= 51; i++){
            deck1.pick();
        }
        deck1.pick();
    }

    /**
     * Tests testToString().
     */
    @Test
    public void testToString()
    {
        Deck deck2 = new Deck();
        String expected = "Ace of CLUB";
        assertEquals(deck2.toString().contains(expected), true);

        Card card = deck2.pick();
        String expected2 = card.toString();
        assertEquals(deck2.toString().contains(expected2), false);

    }

    @Test
    public void size()
    {
    }

    @Test
    public void isEmpty()
    {
    }
}