package csc439team1.blackjack.controller;


import csc439team1.blackjack.model.Card;
import csc439team1.blackjack.model.Shoe;
import csc439team1.blackjack.view.TestView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ControllerTest {

    /**
     * Necessary to test that methods are outputting the correct messages to console on invalid input
     */

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    private final PrintStream originalOut = System.out;

    /**
     * Necessary to test that methods are outputting the correct messages to console on invalid input
     */

    @Before
    public void setStreams() {
        System.setOut(new PrintStream(out));

    }

    /**
     * Necessary to test that methods are outputting the correct messages to console on invalid input
     */

    @After
    public void restoreInitialStreams() {
        System.setOut(originalOut);

    }


    /**
     * This tests the output sent from buyChips() to the controller's view object to ensure that the correct output is getting displayed when integers outside the specified range are being entered.
     */
    @Test
    public void buyChipsOutput() {
        TestView<Integer> TestView = new TestView<>();
        TestView.add(0);
        TestView.add(5001);
        TestView.add(50);
        Controller controller0 = new Controller(TestView);
        controller0.buyChips();
        assertEquals("Game is starting.....how much chips do you want to buy (between $10 to $5000): " +
                "Please enter a number between 10 and 5000!: " +
                "Please enter a number between 10 and 5000!: ", out.toString());
        assertEquals(controller0.player.getChips(), 50, .0001);

    }


    /**
     * Test buyChips by feeding it bad input twice, one outside the required range on the low end, and one on the high end,
     * then feeding it valid input, and checking to ensure the player's chips have been updated with correct value(50).
     * The value of 75 is added to TestViews arrayList as well, but it player's chips should remain at 50 as the loop in buyChips should exit at the first correct input.
     */
    @Test
    public void buyChips() {
        TestView<Integer> TestView = new TestView<>();
        TestView.add(0);
        TestView.add(5001);
        TestView.add(50);
        TestView.add(75);
        Controller controller1 = new Controller(TestView);
        controller1.buyChips();
        assertEquals(controller1.player.getChips(), 50, .0001);
    }


    /**
     * Tests buyChips() ability to quit the game when "quit" is entered by the user.
     * The functionality of TestView mimics CLIView in that if 'quit' is detected, it throws an exception that is caught in the controller. This does not test controller's quit() method in any way.
     */
    @Test(expected = Exception.class)
    public void buyChipsQuit() {
        TestView<String> TestView = new TestView<>();
        TestView.add("quit");
        Controller controller2 = new Controller(TestView);
        controller2.buyChips();
    }

    /**
     * Test askBet() by first prompting TestView for player chips amount, again feeding a couple of bad inputs before providing a valid one.
     * Test to make sure player's chips have the correct value before placing a bet. Then call askBet, again feeding input outside the acceptable range first by inputting
     * a value greater than the players chip count, then the low end of the acceptable range (less than 10). Finally place a
     * valid bet(25) and ensure that the player's chip amount has been decremented correctly. This method does not test if a bet > 500 is entered as it's impossible to test both that condition and
     * the condition that a bet is less than the players chip amount at the same time.
     */
    @Test
    public void askBet() {
        TestView<Integer> TestView = new TestView<>();
        TestView.add(0);
        TestView.add(5001);
        TestView.add(400);
        TestView.add(401);
        TestView.add(5);
        TestView.add(25);
        Controller controller3 = new Controller(TestView);
        controller3.buyChips();
        assertEquals(controller3.player.getChips(), 400, .0001);

        controller3.askBet();
        assertEquals(controller3.player.getChips(), 375, .0001);

    }

    /**
     * This again tests askBet in controller, but verifies that bets over 500 will not accepted when the players chips are greater than 500.
     */
    @Test
    public void askBet1() {
        TestView<Integer> TestView = new TestView<>();
        TestView.add(0);
        TestView.add(5001);
        TestView.add(600);
        TestView.add(501);
        TestView.add(5);
        TestView.add(75);
        Controller controller4 = new Controller(TestView);
        controller4.buyChips();
        assertEquals(controller4.player.getChips(), 600, .0001);

        controller4.askBet();
        assertEquals(controller4.player.getChips(), 525, .0001);

    }

    /**
     * Tests that the correct messages are displayed to the console by controller via view when incorrect bet amounts are entered.
     */
    @Test
    public void askBetOutput() {
        TestView<Integer> TestView = new TestView<>();
        TestView.add(500);
        TestView.add(0);
        TestView.add(501);
        TestView.add(25);
        Controller controller5 = new Controller(TestView);
        controller5.buyChips();
        controller5.askBet();
        assertEquals("Game is starting.....how much chips do you want to buy (between $10 to $5000): " +
                "How much do you want to bet (between $10 to $500): " +
                "Invalid bet amount! Bet needs to be between 10 & 500, and less than your current chips(500.0): " +
                "Invalid bet amount! Bet needs to be between 10 & 500, and less than your current chips(500.0): ", out.toString());

    }

    @Test(expected = Exception.class)
    public void askBetQuit() {
        TestView TestView = new TestView<>();
        TestView.add(500);
        Controller controller = new Controller(TestView);
        controller.buyChips();
        TestView.add("quit");
        controller.askBet();
    }

    /**
     * Test initialDeal() by insuring that after method call, the size of the controller's shoe is decremented by 4.
     */
    @Test
    public void initialDeal() {
        TestView<Integer> TestView = new TestView<>();
        Shoe shoe = new Shoe(1);
        TestView.add(500);
        TestView.add(50);
        Controller controller6 = new Controller(TestView);
        controller6.buyChips();
        controller6.askBet();
        controller6.initialDeal(shoe);
        assertEquals(shoe.size(), 48);


    }

    /**
     * Test initialDeals ability to throw an exception when the shoe has been depleted of cards.
     */
    @Test(expected = Exception.class)
    public void initialDeal1() {
        TestView<Integer> TestView = new TestView<>();
        Shoe shoe = new Shoe(1);
        TestView.add(500);
        TestView.add(50);
        Controller controller7 = new Controller(TestView);
        controller7.buyChips();
        controller7.askBet();
        for (int i = 0; i < 51; i++) {
            shoe.pick();
        }
        controller7.initialDeal(shoe);

    }

    /**
     * Test playBlackJack() in controller. After successful run of all methods in controller,
     * Player's initial chip count should have been decremented by the bet amount, and both the player and dealers hands should be of size 2.
     */
   /* @Test
    public void playBlackJack() {
        TestView<Integer> TestView = new TestView<>();
        TestView.add(500);
        TestView.add(100);
        Controller controller8 = new Controller(TestView);
        controller8.playBlackjack();
        assertEquals(controller8.player.getChips(), 400);
        assertEquals(controller8.player.getHand().size(), 2);
        assertEquals(controller8.dealer.getHand().size(), 2);

    }*/

    /**
     * Test getTotalValue with hand having no aces. With three two's the expected value should be 6.
     */
    @Test
    public void getTotalValue0() {
        TestView<Integer> TestView = new TestView<>();
        Controller controller9 = new Controller(TestView);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(2,1));
        cards.add(new Card(2, 2));
        cards.add(new Card(2,3));
        for(int i = 0; i <=2 ; i ++){
            controller9.player.addCard(cards.get(i));
        }
        assertEquals(controller9.getTotalValue(controller9.player.getHand()), 6);
    }

    /**
     * Test getTotalValue on an empty hand. Should return 0.
     */
    @Test
    public void getTotalValue1() {
        TestView<Integer> TestView = new TestView<>();
        Controller controller10 = new Controller(TestView);
        assertEquals(controller10.getTotalValue(controller10.player.getHand()), 0);

    }

    /**
     * Test get total value with a hand having no aces, one face card, and one 5. Total should be 15
     */

    @Test
    public void getTotalValue2() {
        TestView<Integer> TestView = new TestView<>();
        Controller controller11 = new Controller(TestView);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(11,1));
        cards.add(new Card(5, 2));
        for(int i = 0; i <=1 ; i ++){
            controller11.player.addCard(cards.get(i));
        }
        assertEquals(controller11.getTotalValue(controller11.player.getHand()), 15);

    }


    /**
     * Test getTotalValue with one ace and one face card, total should equal 21.
     */
    @Test
    public void getTotalValue3() {
        TestView<Integer> TestView = new TestView<>();
        Controller controller12 = new Controller(TestView);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(1,1));
        cards.add(new Card(11, 2));
        for(int i = 0; i <=1 ; i ++){
            controller12.player.addCard(cards.get(i));
        }
        assertEquals(controller12.getTotalValue(controller12.player.getHand()), 21);
    }
    /**
     * Test getTotalValue with two aces and one face card, total should equal 12.
     */
    @Test
    public void getTotalValue4() {
        TestView<Integer> TestView = new TestView<>();
        Controller controller13 = new Controller(TestView);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(1,1));
        cards.add(new Card(1,2));
        cards.add(new Card(11, 2));
        for(int i = 0; i <= 2 ; i ++){
            controller13.player.addCard(cards.get(i));
        }
        assertEquals(controller13.getTotalValue(controller13.player.getHand()), 12);
    }

    /**
     * Test get card value on a hand with 4 aces, should equal 14
     */
    @Test
    public void getTotalValue5() {
        TestView<Integer> TestView = new TestView<>();
        Controller controller14 = new Controller(TestView);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(1,1));
        cards.add(new Card(1,2));
        cards.add(new Card(1, 3));
        cards.add(new Card(1,0));
        for(int i = 0; i <= 3 ; i ++){
            controller14.player.addCard(cards.get(i));
        }
        assertEquals(controller14.getTotalValue(controller14.player.getHand()), 14);

    }

    /**
     * Test getTotalValue by added an ace and a 5 to the hand and checking that it equals 16, then add a face card to the hand
     * make sure that it still equals 16.
     */
    @Test
    public void getTotalValue6() {
        TestView<Integer> TestView = new TestView<>();
        Controller controller15 = new Controller(TestView);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(1,1));
        cards.add(new Card(5,2));
        for(int i = 0; i <= 1 ; i ++){
            controller15.player.addCard(cards.get(i));
        }
        assertEquals(controller15.getTotalValue(controller15.player.getHand()), 16);
        controller15.player.addCard(new Card(11,0));
        assertEquals(controller15.getTotalValue(controller15.player.getHand()), 16);


    }

    /**
     * playerTotal just calls getTotalValue, so just a quick test to ensure that returns the correct value on a hand with 2 cards totaling 7
     */

    @Test
    public void playerTotal() {
        TestView<Integer> TestView = new TestView<>();
        Controller controller16 = new Controller(TestView);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(2,1));
        cards.add(new Card(5,2));
        for(int i = 0; i <= 1 ; i ++){
            controller16.player.addCard(cards.get(i));
        }
        assertEquals(controller16.playerTotal(), 7);
    }


    /**
     * dealerTotal just calls getTotalValue, so just test that return value is equal to 8 on a hand with 3 and 5.
     */
    @Test
    public void dealerTotal() {
        TestView<Integer> TestView = new TestView<>();
        Controller controller17 = new Controller(TestView);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(3,1));
        cards.add(new Card(5,2));
        for(int i = 0; i <= 1 ; i ++){
            controller17.dealer.addCard(cards.get(i));
        }
        assertEquals(controller17.dealerTotal(), 8);
    }


    /**
     * Test that the boolean blackJack is false when naturalBlackJack is called when neither the player or dealer's total is 21
     */
    @Test
    public void naturalBlackJack() {
        TestView<Integer> TestView = new TestView<>();
        Controller controller18 = new Controller(TestView);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(3,1));
        cards.add(new Card(5,2));
        for(int i = 0; i <= 1 ; i ++){
            controller18.dealer.addCard(cards.get(i));
            controller18.player.addCard(cards.get(i));
        }
        assertFalse(controller18.naturalBlackJack());

    }
    /**
     * Test that the boolean blackJack is true when naturalBlackJack is called when just the player has blackJack and the deal does not
     */
    @Test
    public void naturalBlackJack1() {
        TestView<Integer> TestView = new TestView<>();
        Controller controller19 = new Controller(TestView);
        controller19.dealer.addCard(new Card(3,1));
        controller19.dealer.addCard(new Card(5,2));
        controller19.player.addCard(new Card(1,0));
        controller19.player.addCard(new Card(11,0));

        assertTrue(controller19.naturalBlackJack());

    }

    /**
     * Test that naturalBlackJack returns true when dealer has blackJack and player does not.
     */
    @Test
    public void naturalBlackJack2() {
        TestView<Integer> TestView = new TestView<>();
        Controller controller20 = new Controller(TestView);
        controller20.naturalBlackJack();
        controller20.dealer.addCard(new Card(1,1));
        controller20.dealer.addCard(new Card(11,2));
        controller20.player.addCard(new Card(5,0));
        controller20.player.addCard(new Card(3,0));

        assertTrue(controller20.naturalBlackJack());

    }

    /**
     * Test that naturalBlackJack returns true when both have blackJack
     */
    @Test
    public void naturalBlackJack3() {
        TestView<Integer> TestView = new TestView<>();
        Controller controller21 = new Controller(TestView);
        controller21.dealer.addCard(new Card(1,1));
        controller21.dealer.addCard(new Card(11,2));
        controller21.player.addCard(new Card(1,0));
        controller21.player.addCard(new Card(12,0));

        assertTrue(controller21.naturalBlackJack());

 }

    /**
     * Test ableToDouble. In this test, player should have a chip amount that would allow a double,
     * but would not have the correct range of card values to be able to double (i.e. 12), so ableToDouble should return false.
     */
    @Test
    public void ableToDouble() {
        TestView<Integer> TestView = new TestView<>();
        TestView.add(500);
        TestView.add(100);
        Controller controller22 = new Controller(TestView);
        controller22.buyChips();
        controller22.askBet();
        controller22.player.addCard(new Card(11,1));
        controller22.player.addCard(new Card(2,0));
        assertFalse(controller22.ableToDouble());

    }

    /**
     * Test ableToDouble. In this test, player should have a chip amount that would allow a double,
     * but would not have the correct range of card values to be able to double (i.e. 8), so ableToDouble should return false.
     */
    @Test
    public void ableToDouble1() {
        TestView<Integer> TestView = new TestView<>();
        TestView.add(500);
        TestView.add(100);
        Controller controller23 = new Controller(TestView);
        controller23.buyChips();
        controller23.askBet();
        controller23.player.addCard(new Card(5,1));
        controller23.player.addCard(new Card(3,0));
        assertFalse(controller23.ableToDouble());

    }

    /**
     * Test ableToDouble, in this test, player should have the card total to allow a double (testing on the lower bound of 9),
     * but should not have a chip amount that would allow a double (i.e. the player doubling his bet would bring him to a negative chip value.)
     */
    @Test
    public void ableToDouble2() {
        TestView<Integer> TestView = new TestView<>();
        TestView.add(20);
        TestView.add(15);
        Controller controller24 = new Controller(TestView);
        controller24.buyChips();
        controller24.askBet();

        assertFalse(controller24.ableToDouble());
    }

    /**
     * This test of ableToDouble should return true as the player's card total is within in the acceptable range and his chip value allows a double.
     */
    @Test
    public void ableToDouble3() {
        TestView<Integer> TestView = new TestView<>();
        TestView.add(200);
        TestView.add(15);
        Controller controller25 = new Controller(TestView);
        controller25.buyChips();
        controller25.askBet();
        controller25.player.addCard(new Card(5,1));
        controller25.player.addCard(new Card(4,0));
        assertTrue(controller25.ableToDouble());

    }

    /**
     * Test playerDouble. As there is no bet amount or cards in the player's hand, the call to ableToDouble in playerDouble should leave the boolean double value as false, thus causing playerDouble to return false as well.
     */
    @Test
    public void playerDouble() {
        TestView testView = new TestView();
        Controller controller26 = new Controller(testView);
        assertFalse(controller26.playerDouble());
    }

    /**
     * Verify that a call to playerDouble while ableToDouble is true but the user inputs "N" results in no change to the players hand, bet amount, or chip amount.
     */
    @Test
    public void playerDouble1() {
        TestView testView = new TestView();
        testView.add(100);
        testView.add("N");
        Controller controller27 = new Controller(testView);
        controller27.player.addCard(new Card(5,0));
        controller27.player.addCard(new Card(4,0));
        controller27.player.setChips(400);
        controller27.askBet();
        controller27.playerDouble();
        assertEquals(controller27.player.getChips(), 300, .0001);
        assertEquals(controller27.bet, 100, .0001);
        assertEquals(controller27.player.getHand().size(), 2);
    }

    @Test
    public void playerDouble2() {
        TestView testView = new TestView();
        testView.add(100);
        testView.add("Y");
        Controller controller28 = new Controller(testView);
        controller28.shoe = new Shoe(1);
        controller28.player.addCard(new Card(5,0));
        controller28.player.addCard(new Card(5, 1));
        controller28.player.setChips(400);
        controller28.askBet();
        controller28.playerDouble();
        assertEquals(controller28.player.getChips(), 200, .0001);
        assertEquals(controller28.bet, 200, .0001);
        assertEquals(controller28.player.getHand().size(), 3);

    }
}
