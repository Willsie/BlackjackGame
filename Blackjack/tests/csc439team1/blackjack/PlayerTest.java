package csc439team1.blackjack;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {


    /**
     * Tests Player constructors argument checking. Should throw exception if 0 >= chips > 5000
     */
    @Test (expected = IllegalArgumentException.class)
    public void Player() {
        Player player0 = new Player(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void Player1() {
        Player player0 = new Player(5001);
    }

    /**
     * Test getChips method.
     */
    @Test
    public void getChips() {
        Player player2 = new Player(50);
        assertEquals(player2.getChips(), 50);
    }


    /**
     * Test set chips method
     */
    @Test
    public void setChips() {
        Player player3 = new Player(50);
        player3.setChips(60);
        assertEquals(player3.getChips(), 60);
    }

    /**
     * Test player's add card method.
     */
    @Test
    public void addCard() {
        Shoe shoe = new Shoe(1);
        Player player4 = new Player(50);
        player4.addCard(shoe.pick());

        assertEquals(player4.getHand().size(), 1);
    }

    /**
     * Test player's getHand method by adding the same 3 cards to the player's hand and to a controlArray and comparing them
     */
    @Test
    public void getHand() {
        ArrayList<Card> controlArray = new ArrayList<>();
        Player player5 = new Player(50);
        Card testCard = new Card(1, 0);
        Card testCard2 = new Card(1, 1);
        Card testCard3 = new Card(2, 2);
        player5.addCard(testCard);
        player5.addCard(testCard2);
        player5.addCard(testCard3);
        controlArray.add(testCard);
        controlArray.add(testCard2);
        controlArray.add(testCard3);
        assertEquals(controlArray, player5.getHand().getCards());
    }


    /**
     * Test player's ability to clear the hand.
     */
    @Test
    public void clearHand() {
        Shoe shoe = new Shoe(1);
        Player player6 = new Player(50);
        player6.addCard(shoe.pick());
        assertEquals(player6.getHand().size(), 1);
        player6.clearHand();

        assertEquals(player6.getHand().size(), 0);
    }
}