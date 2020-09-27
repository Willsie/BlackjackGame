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
     * Test constructor statement for assigning cardValue 13 to card obj. when 0 is given as cardValue. Changed as
     * card being 0 has been removed.TS
     */
    @Test
    public void Card3() {
        Card card0 = new Card(13, 3); // cardValue = 0 should create card with cardValue being 13.
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
     * Test which ensures cardSuit of 2 produces SPADE
     */
    @Test
    public void testSuitSPADE()
    {
        Card testCard1 = new Card(2, 2);
        assertEquals("SPADE", testCard1.getSuitString());
    }
    /**
     * Test which ensures cardSuit of 1 produces HEART
     */
    @Test
    public void testSuitHEART()
    {
        Card testCard3 = new Card(1, 1);
        assertEquals("HEART", testCard3.getSuitString());
    }
    /**
     * Test which ensures cardSuit of 3 produces DIAMOND
     */
    @Test
    public void testSuitDiamond()
    {
        Card testCard4 = new Card(1, 3);
        assertEquals("DIAMOND", testCard4.getSuitString());
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
     * Tests that entering 11 as value produces Jack.
     */
    @Test
    public void getValueString2()
    {
        Card testCard6 = new Card(11, 3);
        assertEquals("Jack", testCard6.getNumberString());
    }
    /**
     * Tests that entering 12 as value produces Queen.
     */
    @Test
    public void getValueString3()
    {
        Card testCard6 = new Card(12, 3);
        assertEquals("Queen", testCard6.getNumberString());
    }
    /**
     * Tests that entering 13 as value produces King.
     */
    @Test
    public void getValueString4()
    {
        Card testCard = new Card(13, 1);
        assertEquals("King", testCard.getNumberString());
    }
    /**
     * Tests that entering 3 as value produces string of 3.
     */
    @Test
    public void getValueString5()
    {
        Card testCard = new Card(3, 1);
        assertEquals("3", testCard.getNumberString());
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

    /**
     * Test to verify override of equals if clause that compares
     * object to self.
     */
    @Test
    public void testIsSelf()
    {
        Card self = new Card(1, 3);
        assertTrue(self.equals(self));
    }
//Empty test, merge with master, then delete. Should force project to adopt-openjdk14 and solve issues.
    @Test
    public void shellTest()
    {

    }
}