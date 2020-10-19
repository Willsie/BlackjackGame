package csc439team1.blackjack;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * This is the test class for Hand class
 */
public class HandTest {

    /**
     * Test case for size, creates an empty hand and ensures size is 0.
     */
    @Test
    public void testSize(){
        Hand testHand = new Hand();
        assertTrue(testHand.size() == 0);
    }

    /**
     * testAddCard uses a shoe and has two hands pick from the shoe, with one being larger and tests that is actually
     * the case.
     */
    @Test
    public void testAddCard() {
        Shoe tesShoe = new Shoe(1);
        Hand testHand = new Hand();
        Hand testHand2 =new Hand();
        for (int a = 0; a < 5; a++) {
            testHand.addCard(tesShoe.pick());
        }
        for (int a = 0; a < 15; a++) {
            testHand2.addCard(tesShoe.pick());
        }
        assertTrue(testHand.size() < testHand2.size());
    }

    /**
     * testGetCards creates an Arraylist of cards and a hand, manually creates cards, and adds them to both objects
     * in the same order. The assert equals verifies that the cards returned by getCards method match the other Card
     * arraylist, therefore being the correct hand of cards.
     */
    @Test
    public void testGetCards(){
        ArrayList<Card> controlArray = new ArrayList<>();
        Hand testHand = new Hand();
        Card testCard = new Card(1, 0);
        Card testCard2 = new Card(1, 1);
        Card testCard3 = new Card(2, 2);
        testHand.addCard(testCard);
        testHand.addCard(testCard2);
        testHand.addCard(testCard3);
        controlArray.add(testCard);
        controlArray.add(testCard2);
        controlArray.add(testCard3);
        assertEquals(controlArray, testHand.getCards());
    }
}
