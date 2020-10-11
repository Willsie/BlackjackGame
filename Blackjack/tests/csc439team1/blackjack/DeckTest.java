package csc439team1.blackjack;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {
    /**
     * Tests deck's pick() and size method. Pick 5 cards and verify that size of deck is of size 47. Tests size method as well.
     */
    @Test
    public void pick() {
        Deck deck0 = new Deck();
        for (int i = 0; i <= 4; i++) {
            deck0.pick();
        }
        assertEquals(deck0.size(), 47);
    }

    /**
     * Tests exception handling in pick(). Remove all cards from deck with pick() method, then call pick() again which should throw an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void pick1() {
        Deck deck1 = new Deck();
        for (int i = 0; i <= 51; i++) {
            deck1.pick();
        }
        deck1.pick();
    }
}

