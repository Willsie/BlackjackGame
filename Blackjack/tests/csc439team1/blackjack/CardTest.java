package csc439team1.blackjack;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This is test class for Card class
 */
public class CardTest
{
    /**
     * Tests constructor's argument checking. Should catch IllegalArgumentException if cardValue is greater than 13 or less than 1, or if cardSuit is less than 0 or greater than 3.
     */
    @Test (expected = IllegalArgumentException.class) // tests both arguments being illegal
    public void Card() {
        Card card0 = new Card(-1,-1);
    }
    @Test (expected = IllegalArgumentException.class) // cardSuit being illegal
    public void Card1() {
        Card card0 = new Card(5,5);
    }
    @Test (expected = IllegalArgumentException.class) // cardValue being illegal
    public void Card2() {
        Card card0 = new Card(-1,2);
    }

    /**
     * Test constructor statement for assigning cardValue 13 to card obj. when 0 is given as cardValue
     */
    @Test
    public void Card3() {
        Card card0 = new Card(0, 3); // cardValue = 0 should create card with cardValue being 13.
        assertEquals(13, card0.getNumber());
    }

    /**
     * Test getSuit() by creating card0 with cardValue 1 and cardSuit 0
     * expected 0
     */
    @Test
    public void getSuit()
    {
        Card card0 = new Card(1, 0);
        assertEquals(0, card0.getSuit());
    }

    /**
     * Test getValue() by creating card1 with cardValue 1 and cardSuit 0
     * expected 1
     */
    @Test
    public void getValue()
    {
        Card card1 = new Card(1, 0);
        assertEquals(1, card1.getNumber());
    }

    /**
     * Test getSuitString() by creating card2 with cardValue 1 and cardSuit 0
     * expected "CLUB"
     */
    @Test
    public void getSuitString()
    {
        Card card2 = new Card(1, 0);
        assertEquals("CLUB", card2.getSuitString());
    }

    /**
     * Test getValueString() by creating card3 with cardValue 1 and cardSuit 0
     * expected "Ace"
     */
    @Test
    public void getValueString()
    {
        Card card3 = new Card(1, 0);
        assertEquals("Ace", card3.getNumberString());
    }

    /**
     * Test toString() by creating card4 with cardValue 1 and cardSuit 0
     * expected "Ace of CLUB"
     */
    @Test
    public void testToString()
    {
        Card card4 = new Card(1, 0);
        assertEquals("Ace of CLUB", card4.toString());
    }

    /**
     * Test equals() by creating card5 and card6 (both has cardValue 1 and cardSuit 0)
     * If both cards are equal, it should pass the test case
     */
    @Test
    public void testEquals()
    {
        Card card5 = new Card(1, 0);
        Card card6 = new Card(1, 0);
        assertTrue(card5.equals(card6));
    }
}