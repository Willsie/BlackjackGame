package csc439team1.blackjack;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This is test class for Card class
 */
public class CardTest
{
    /**
     * Tests constructor's argument checking. Should catch IllegalArgumentException if cardNumber is greater than 13 or less than 1, or if cardSuit is less than 0 or greater than 3.
     */
    @Test (expected = IllegalArgumentException.class) // tests both arguments being illegal
    public void Card() {
        Card card0 = new Card(-1,-1);
    }
    @Test (expected = IllegalArgumentException.class) // cardSuit being illegal
    public void Card1() {
        Card card0 = new Card(5,5);
    }
    @Test (expected = IllegalArgumentException.class) // cardNumber being illegal
    public void Card2() {
        Card card0 = new Card(-1,2);
    }

    /**
     * Test constructor statement for assigning cardNumber 13 to card obj. when 0 is given as cardNumber
     */
    @Test
    public void Card3() {
        Card card0 = new Card(1, 3); // cardNumber = 0 should create card with cardNumber being 13.
        assertEquals(1, card0.getNumber());
    }

    /**
     * Test getSuit() by creating card0 with cardNumber 1 and cardSuit 0
     * expected 0
     */
    @Test
    public void getSuit()
    {
        Card card0 = new Card(1, 0);
        assertEquals(0, card0.getSuit());
    }

    /**
     * Test getNumber() by creating card1 with cardNumber 1 and cardSuit 0
     * expected 1
     */
    @Test
    public void getNumber()
    {
        Card card1 = new Card(1, 0);
        assertEquals(1, card1.getNumber());
    }

    /**
     * Test getSuitStringClub() by creating card2 with cardNumber 1 and cardSuit 0
     * expected "CLUB"
     */
    @Test
    public void getSuitStringClub()
    {
        Card card2 = new Card(1, 0);
        assertEquals("CLUB", card2.getSuitString());
    }

    /**
     * Test getSuitStringHeart() by creating card2 with cardNumber 1 and cardSuit 0
     * expected "HEARTH"
     */
    @Test
    public void getSuitStringHeart()
    {
        Card card2 = new Card(1, 1);
        assertEquals("HEART", card2.getSuitString());
    }

    /**
     * Test getSuitStringSpade() by creating card2 with cardNumber 1 and cardSuit 2
     * expected "DIAMOND"
     */
    @Test
    public void getSuitStringSpade()
    {
        Card card2 = new Card(1, 2);
        assertEquals("SPADE", card2.getSuitString());
    }

    /**
     * Test getSuitStringDiamond() by creating card2 with cardNumber 1 and cardSuit 2
     * expected "DIAMOND"
     */
    @Test
    public void getSuitStringDiamond()
    {
        Card card2 = new Card(1, 3);
        assertEquals("DIAMOND", card2.getSuitString());
    }

    /**
     * Test getNumberStringAce() by creating card3 with cardNumber 1 and cardSuit 0
     * expected "Ace"
     */
    @Test
    public void getNumberStringAce()
    {
        Card card3 = new Card(1, 0);
        assertEquals("Ace", card3.getNumberString());
    }

    /**
     * Test getNumberStringJack() by creating card3 with cardNumber 1 and cardSuit 11
     * expected "Jack"
     */
    @Test
    public void getNumberStringJack()
    {
        Card card3 = new Card(11, 0);
        assertEquals("Jack", card3.getNumberString());
    }

    /**
     * Test getNumberStringQueen() by creating card3 with cardNumber 1 and cardSuit 12
     * expected "Queen"
     */
    @Test
    public void getNumberStringQueen()
    {
        Card card3 = new Card(12, 0);
        assertEquals("Queen", card3.getNumberString());
    }

    /**
     * Test getNumberString() by creating card3 with cardNumber 1 and cardSuit 13
     * expected "King"
     */
    @Test
    public void getNumberStringKing()
    {
        Card card3 = new Card(13, 0);
        assertEquals("King", card3.getNumberString());
    }

     /**
     * Test getNumberStringTwo() by creating card3 with cardNumber 1 and cardSuit 13
     * expected "King"
     */
    @Test
    public void getNumberStringTwo()
    {
        Card card3 = new Card(2, 0);
        assertEquals("2", card3.getNumberString());
    }

    /**
     * Test toString() by creating card4 with cardNumber 1 and cardSuit 0
     * expected "Ace of CLUB"
     */
    @Test
    public void testToString()
    {
        Card card4 = new Card(1, 0);
        assertEquals("Ace of CLUB", card4.toString());
    }

    /**
     * Test equals() by creating card5 and card6 (both has cardNumber 1 and cardSuit 0)
     * If both cards are equal, it should pass the test case
     */
    @Test
    public void testEquals()
    {
        Card card5 = new Card(1, 0);
        Card card6 = new Card(1, 0);
        assertTrue(card5.equals(card6));
    }

    /**
     * Test equals() by creating card5 and card6 (both has cardNumber 1 and cardSuit 0)
     * If both cards are equal, it should pass the test case
     */
    @Test
    public void testEquals1()
    {
        Card card7 = new Card(1, 0);
        Card card8 = card7;
        assertTrue(card7.equals(card8));
    }
}