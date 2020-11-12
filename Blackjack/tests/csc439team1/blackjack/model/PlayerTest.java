package csc439team1.blackjack.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    /**
     * Verify that a new player has the correct default values for it's data members
     */
    @Test
    public void player() {
        Player player0 = new Player();
        assertEquals(player0.getChips(), 0);
        assertEquals(player0.getHand().size(), 0);
    }

    /**
     * Verify that setChips sets the correct amount
     */
    @Test
    public void setChips() {
        Player player1 = new Player();
        player1.setChips(25);
        assertEquals(player1.getChips(), 25);
    }

    /**
     * Verify that a negative chip value throws an exception via checkChips()
     */
    @Test(expected = IllegalStateException.class)
    public void setChips1() {
        Player player2 = new Player();
        player2.setChips(-1);
    }

    /**
     * Verify that getChips returns the correct value
     */
    @Test
    public void getChips() {
        Player player3 = new Player();
        player3.setChips(50);
        assertEquals(player3.getChips(), 50);
    }


    /**
     * Test that addCard correctly adds a card to the players hand.
     */
    @Test
    public void addCard() {
        Player player4 = new Player();
        Shoe shoe = new Shoe(1);
        player4.addCard(shoe.pick());
        assertEquals(player4.getHand().size(), 1);
    }

    /**
     * Verify that addCard receives an exception from shoe when calling addcard with an empty shoe.
     */
    @Test(expected = Exception.class)
    public void addCard1() {
        Player player5 = new Player();
        Shoe shoe = new Shoe(1);
        for (int i = 0; i < 52; i++){
            shoe.pick();
        }
        player5.addCard(shoe.pick());
    }

    /**
     * Test that getHand returns an Arraylist of the correct size when called.
     */
    @Test
    public void getHand() {
        Player player6 = new Player();
        Shoe shoe = new Shoe(1);
        player6.addCard(shoe.pick());
        assertEquals(player6.getHand().size(), 1);
    }

    @Test (expected = Exception.class)
    public void testAddCardException() {
        Player player = new Player();
        Card card = new Card(100, 100);
        player.addCard(card);
    }
}