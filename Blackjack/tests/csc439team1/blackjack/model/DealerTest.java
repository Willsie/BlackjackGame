package csc439team1.blackjack.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DealerTest {

    /**
     * Test that dealer's constructor gives correct values to data member.
     */
    @Test
    public void dealer() {
        Dealer dealer0 = new Dealer();
        assertEquals(dealer0.getHand().size(), 0);
    }

    /**
     * Verify that add card correctly adds a card to dealers hand
     */
    public void addCard() {
        Dealer dealer = new Dealer();
        Shoe shoe = new Shoe(1);
        dealer.addCard(shoe.pick());
        assertEquals(dealer.getHand().size(), 1);
    }

    /**
     * Test that an exception is thrown when attempting to add a card to dealer's hand from an empty shoe.
     */
    @Test(expected = Exception.class)
    public void addCard1() {
        Dealer dealer1 = new Dealer();
        Shoe shoe = new Shoe(1);
        for (int i = 0; i < 52; i++){
            shoe.pick();
        }
        dealer1.addCard(shoe.pick());
    }

    /**
     * Test that get hand returns an ArrayList of the correct size.
     */

    public void getHand() {
        Dealer dealer2 = new Dealer();
        Shoe shoe = new Shoe(1);
        dealer2.addCard(shoe.pick());
        assertEquals(dealer2.getHand().size(), 1);
    }
}