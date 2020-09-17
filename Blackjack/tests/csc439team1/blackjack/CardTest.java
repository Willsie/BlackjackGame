package csc439team1.blackjack;

import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest
{
    @Test
    public void getSuit()
    {
        Card card0 = new Card(1, 0);
        assertEquals(0, card0.getSuit());
    }

    @Test
    public void getValue()
    {
        Card card1 = new Card(1, 0);
        assertEquals(1, card1.getValue());
    }

    @Test
    public void getSuitString()
    {
        Card card2 = new Card(1, 0);
        assertEquals("CLUB", card2.getSuitString());
    }

    @Test
    public void getValueString()
    {
        Card card3 = new Card(1, 0);
        assertEquals("Ace", card3.getValueString());
    }

    @Test
    public void testToString()
    {
        Card card4 = new Card(1, 0);
        assertEquals("Ace of CLUB", card4.toString());
    }

    @Test
    public void testEquals()
    {
        Card card5 = new Card(1, 0);
        Card card6 = new Card(1, 0);
        assertTrue(card5.equals(card6));
    }
}