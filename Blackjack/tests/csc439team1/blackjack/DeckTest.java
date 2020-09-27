package csc439team1.blackjack;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest
{

    @Test
    public void pick()
    {
        Deck deck0 = new Deck();
        System.out.println(deck0.toString());
    }

    @Test (expected = IllegalArgumentException.class)
    public void pick1() {
        Deck deck1 = new Deck();
        for(int i = 0; i <= 51; i++){
            deck1.pick();
        }
        deck1.pick();
    }


    @Test
    public void testToString()
    {
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