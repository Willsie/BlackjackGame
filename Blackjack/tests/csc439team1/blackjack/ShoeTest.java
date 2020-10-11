package csc439team1.blackjack;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShoeTest {


    /**
     * Test Shoe's exception handling in constructor.
     */
    @Test (expected = IllegalArgumentException.class)
    public void shoe() {
        Shoe shoe0 = new Shoe(0);
    }
    @Test (expected = IllegalArgumentException.class)
    public void shoe1() {
        Shoe shoe0 = new Shoe(-1);
    }


    /**
     * Test Shoe's pick method. Should be null after 104 picks on a Shoe with 2 decks
     */
    @Test
    public void pick() {
        Shoe shoe2 = new Shoe(2);
        for(int i = 0; i <= 103; i++){
            shoe2.pick();
        }
        assertEquals(shoe2.pick(), null);
    }

    /**
     * Test numDecks method in shoe. numDecks should remove a deck from the shoe when that deck is empty.
     * After 103 picks, there should only be 1 of the original 2 decks left in the shoe. After another pick,
     * the shoe should be empty.
     */

    @Test
    public void numDecks() {
        Shoe shoe3 = new Shoe(2);
        for(int i = 0; i <= 102; i++){
            shoe3.pick();
        }
        assertEquals(shoe3.numDeck(), 1);
        shoe3.pick();
        assertEquals(shoe3.numDeck(), 0);
    }

    /**
     * Test size method in Shoe.
     */
    @Test
    public void size() {
        Shoe shoe4 = new Shoe(2);
        shoe4.pick();
        assertEquals(shoe4.size(), 103);
        for(int i = 0; i <= 50; i++){
            shoe4.pick();
        }
        assertEquals(shoe4.size(), 52);
    }

}