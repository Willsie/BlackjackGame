package csc439team1.blackjack;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * This is test class for Card class
 * @author Trevor Sears
 * @author Will Sie
 * @author Conner Martin
 */
public class CardTest
{
    /**
     * Tests constructor's argument checking. Should catch IllegalArgumentException if cardNumber is greater than 13 or less than 1, or if cardSuit is less than 0 or greater than 3.
     */
    @Test(expected = IllegalArgumentException.class) // tests both arguments being illegal
    public void Card()
    {
        Card card0 = new Card(-1, -1);
    }

    @Test(expected = IllegalArgumentException.class) // cardSuit being illegal
    public void Card1()
    {
        Card card0 = new Card(5, 5);
    }

    @Test(expected = IllegalArgumentException.class) // cardNumber being illegal
    public void Card2()
    {
        Card card0 = new Card(-1, 2);
    }

    /**
     * Test constructor statement for assigning cardNumber to 13
     */
    @Test
    public void Card3()
    {
        Card card0 = new Card(13, 3); // cardNumber = 0 should create card with cardNumber being 13.
        assertEquals(13, card0.getNumber());
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
     * Test getSuitCLUB() by creating card2 with cardNumber 1 and cardSuit 0
     * expected "CLUB"
     */
    @Test
    public void getSuitCLUB()
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
     * Test getNumberString() by creating card3 with cardNumber 1 and cardSuit 0
     * expected "Ace"
     */
    @Test
    public void getNumberString()
    {
        Card card3 = new Card(1, 0);
        assertEquals("Ace", card3.getNumberString());
    }

    /**
     * Tests that entering 11 as Number produces Jack.
     */
    @Test
    public void getNumberString2()
    {
        Card testCard6 = new Card(11, 3);
        assertEquals("Jack", testCard6.getNumberString());
    }

    /**
     * Tests that entering 12 as Number produces Queen.
     */
    @Test
    public void getNumberString3()
    {
        Card testCard6 = new Card(12, 3);
        assertEquals("Queen", testCard6.getNumberString());
    }

    /**
     * Tests that entering 13 as Number produces King.
     */
    @Test
    public void getNumberString4()
    {
        Card testCard = new Card(13, 1);
        assertEquals("King", testCard.getNumberString());
    }

    /**
     * Tests that entering 3 as Number produces string of 3.
     */
    @Test
    public void getNumberString5()
    {
        Card testCard = new Card(3, 1);
        assertEquals("3", testCard.getNumberString());
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
}