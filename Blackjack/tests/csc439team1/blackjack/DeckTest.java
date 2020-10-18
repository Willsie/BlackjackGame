package csc439team1.blackjack;

import org.junit.Test;
import static org.junit.Assert.*;

public class DeckTest
{
    /**
     * testPick checks that the object returned by pick method from a deck is indeed a card versus other object.
     */
    @Test
    public void testPick() {
        Deck testDeck = new Deck();
        assertTrue(testDeck.pick() instanceof Card);
    }

    /**
     * The purpose of this test is check that the same card is not returned twice, and while not truly verifying
     * randomness of pick it does ensure the same object is removed and not called twice.
     */
    @Test
    public void testPick2(){
        Deck testDeck = new Deck();
        Card testCard = testDeck.pick();
        Card testCard2 = testDeck.pick();
        assertTrue(!testCard.toString().equals(testCard2.toString()));
    }

    /**
     * testSize checks correctness of size method by creating a deck, picking/removing 1 card and verifying
     * total is 51.
     */
    @Test
    public void testSize() {
        Deck testDeck = new Deck();
        testDeck.pick();
        assertEquals(51, testDeck.size());
    }
}