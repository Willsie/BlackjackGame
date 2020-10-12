package csc439team1.blackjack;

import org.junit.Test;

import static org.junit.Assert.*;

public class HandTest {
    /**
     * Tests various functions of hand class. All functions are just calls to methods of underlying ArrayList frame work, so tests are minimal.
     */
    @Test
    public void handTests(){
        Shoe shoe = new Shoe(1);
        Hand hand = new Hand();

        hand.addCard(shoe.pick());
        assertEquals(hand.size(), 1);

        for(int i = 0; i < 5; i++){
            hand.addCard(shoe.pick());
        }
        assertEquals(hand.size(), 6);

        assertEquals(hand.getCards().size(), hand.size());
    }


}