package csc439team1.blackjack;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShoeTest {
    /**
     * Test case which checks to make sure that a card is returned by the pick method.
     */
    @Test
    public void testPick() {
        Shoe testShoe = new Shoe(3);
        Card testCard = testShoe.pick();
        assertTrue(testCard instanceof Card);

    }

    /**
     *This test checks that decks are removed via the pick method, by starting with 2
     * decks and removing all cards except results in a lower number of decks within the shoe.
     */
    @Test
    public void testPick2() {
        Shoe testShoe = new Shoe(2);
        for (int a = 0; a < 103; a++)
        {
            testShoe.pick();
        }
        assertEquals(1,testShoe.numDeck());
    }

    /**
     * Test for ensure that numDeck method correctly returns the number of decks within a shoe.
     */
    @Test
    public void testNumberDecks() {
        Shoe testShoe = new Shoe(15);
        assertEquals(15, testShoe.numDeck());
    }

    /**
     * testSize checks to see if size method is correctly returning the number of cards
     * in the shoe, by creating a shoe with 1 deck and checking that the total is 52 cards.
     */
    @Test
    public void testSize() {
        Shoe testShoe = new Shoe(1);
        assertEquals(52, testShoe.size());
    }
}