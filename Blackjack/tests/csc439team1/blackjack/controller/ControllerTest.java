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
        assertEquals(controller0.getPlayer().getChips(), 50, .0001);

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
        assertEquals(controller1.getPlayer().getChips(), 50, .0001);
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
        assertEquals(controller3.getPlayer().getChips(), 400, .0001);

        controller3.askBet();
        assertEquals(controller3.getPlayer().getChips(), 375, .0001);

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
        assertEquals(controller4.getPlayer().getChips(), 600, .0001);

        controller4.askBet();
        assertEquals(controller4.getPlayer().getChips(), 525, .0001);

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
            controller9.getPlayer().addCard(cards.get(i));
        }
        assertEquals(controller9.getTotalValue(controller9.getPlayer().getHand()), 6);
    }

    /**
     * Test getTotalValue on an empty hand. Should return 0.
     */
    @Test
    public void getTotalValue1() {
        TestView<Integer> TestView = new TestView<>();
        Controller controller10 = new Controller(TestView);
        assertEquals(controller10.getTotalValue(controller10.getPlayer().getHand()), 0);

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
            controller11.getPlayer().addCard(cards.get(i));
        }
        assertEquals(controller11.getTotalValue(controller11.getPlayer().getHand()), 15);

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
            controller12.getPlayer().addCard(cards.get(i));
        }
        assertEquals(controller12.getTotalValue(controller12.getPlayer().getHand()), 21);
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
            controller13.getPlayer().addCard(cards.get(i));
        }
        assertEquals(controller13.getTotalValue(controller13.getPlayer().getHand()), 12);
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
            controller14.getPlayer().addCard(cards.get(i));
        }
        assertEquals(controller14.getTotalValue(controller14.getPlayer().getHand()), 14);

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
            controller15.getPlayer().addCard(cards.get(i));
        }
        assertEquals(controller15.getTotalValue(controller15.getPlayer().getHand()), 16);
        controller15.getPlayer().addCard(new Card(11,0));
        assertEquals(controller15.getTotalValue(controller15.getPlayer().getHand()), 16);


    }

    /**
     * playerTotal just calls getTotalValue, so just a quick test to ensure that returns the correct value on a hand with 2 cards totaling 7
     */

    @Test
    public void getPlayerTotal() {
        TestView<Integer> TestView = new TestView<>();
        Controller controller16 = new Controller(TestView);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(2,1));
        cards.add(new Card(5,2));
        for(int i = 0; i <= 1 ; i ++){
            controller16.getPlayer().addCard(cards.get(i));
        }
        assertEquals(controller16.playerTotal(), 7);
    }


    /**
     * getDealer()Total just calls getTotalValue, so just test that return value is equal to 8 on a hand with 3 and 5.
     */
    @Test
    public void getDealerTotal() {
        TestView<Integer> TestView = new TestView<>();
        Controller controller17 = new Controller(TestView);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(3,1));
        cards.add(new Card(5,2));
        for(int i = 0; i <= 1 ; i ++){
            controller17.getDealer().addCard(cards.get(i));
        }
        assertEquals(controller17.dealerTotal(), 8);
    }


    /**
     * Test that the boolean blackJack is false when naturalBlackJack is called when neither the player or getDealer()'s total is 21
     */
    @Test
    public void naturalBlackJack() {
        TestView<Integer> TestView = new TestView<>();
        Controller controller18 = new Controller(TestView);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(3,1));
        cards.add(new Card(5,2));
        for(int i = 0; i <= 1 ; i ++){
            controller18.getDealer().addCard(cards.get(i));
            controller18.getPlayer().addCard(cards.get(i));
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
        controller19.getDealer().addCard(new Card(3,1));
        controller19.getDealer().addCard(new Card(5,2));
        controller19.getPlayer().addCard(new Card(1,0));
        controller19.getPlayer().addCard(new Card(11,0));

        assertTrue(controller19.naturalBlackJack());

    }

    /**
     * Test that naturalBlackJack returns true when getDealer() has blackJack and player does not.
     */
    @Test
    public void naturalBlackJack2() {
        TestView<Integer> TestView = new TestView<>();
        Controller controller20 = new Controller(TestView);
        controller20.naturalBlackJack();
        controller20.getDealer().addCard(new Card(1,1));
        controller20.getDealer().addCard(new Card(11,2));
        controller20.getPlayer().addCard(new Card(5,0));
        controller20.getPlayer().addCard(new Card(3,0));

        assertTrue(controller20.naturalBlackJack());

    }

    /**
     * Test that naturalBlackJack returns true when both have blackJack
     */
    @Test
    public void naturalBlackJack3() {
        TestView<Integer> TestView = new TestView<>();
        Controller controller21 = new Controller(TestView);
        controller21.getDealer().addCard(new Card(1,1));
        controller21.getDealer().addCard(new Card(11,2));
        controller21.getPlayer().addCard(new Card(1,0));
        controller21.getPlayer().addCard(new Card(12,0));

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
        controller22.getPlayer().addCard(new Card(11,1));
        controller22.getPlayer().addCard(new Card(2,0));
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
        controller23.getPlayer().addCard(new Card(5,1));
        controller23.getPlayer().addCard(new Card(3,0));
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
        controller25.getPlayer().addCard(new Card(5,1));
        controller25.getPlayer().addCard(new Card(4,0));
        assertTrue(controller25.ableToDouble());

    }


    /**
     * This test of ableToDouble ensures that the method returns false when a double would result in the players chips going negative.
     */
    @Test
    public void ableToDouble4() {
        TestView<Integer> TestView = new TestView<>();
        TestView.add(100);
        TestView.add(75);
        Controller controller = new Controller(TestView);
        controller.buyChips();
        controller.askBet();
        controller.getPlayer().addCard(new Card(5,1));
        controller.getPlayer().addCard(new Card(4,0));
        assertFalse(controller.ableToDouble());
    }

    /**
     * Test playerDouble. As there is no bet amount or cards in the player's hand, the call to ableToDouble in playerDouble should leave the boolean double value as false, thus causing playerDouble to return false as well.
     */
    @Test
    public void getPlayerDouble() {
        TestView<Integer> testView = new TestView<>();
        Controller controller26 = new Controller(testView);
        assertFalse(controller26.playerDouble());
    }

    /**
     * Verify that a call to playerDouble while ableToDouble is true but the user inputs "N" results in no change to the players hand, bet amount, or chip amount.
     */
    @Test
    public void getPlayerDouble1() {
        TestView testView = new TestView();
        testView.add(100);
        testView.add("N");
        Controller controller27 = new Controller(testView);
        controller27.getPlayer().addCard(new Card(5,0));
        controller27.getPlayer().addCard(new Card(4,0));
        controller27.getPlayer().setChips(400);
        controller27.askBet();
        controller27.playerDouble();
        assertEquals(controller27.getPlayer().getChips(), 300, .0001);
        assertEquals(controller27.getBet(), 100, .0001);
        assertEquals(controller27.getPlayer().getHand().size(), 2);
    }

    /**
     * Verify that a call to playerDouble while ableToDouble is true and the user inputs "Y" correctly updates the player's chip, the bet amount, and adds one card to the players hand.
     */
    @Test
    public void getPlayerDouble2() {
        TestView testView = new TestView();
        testView.add(100);
        testView.add("Y");
        Controller controller28 = new Controller(testView);
        controller28.setShoe(new Shoe(1));
        controller28.getPlayer().addCard(new Card(5,0));
        controller28.getPlayer().addCard(new Card(5, 1));
        controller28.getPlayer().setChips(400);
        controller28.askBet();
        assertTrue(controller28.playerDouble());
        assertEquals(controller28.getPlayer().getChips(), 200, .0001);
        assertEquals(controller28.getBet(), 200, .0001);
        assertEquals(controller28.getPlayer().getHand().size(), 3);

    }


    /**
     * Test playerInputActions by feeding the method two bad input's for the specified parameters, the two valid inputs.
     * The loop should exit when the first valid input is detected, so the return value should be the first valid input
     * and not the second.
     */
    @Test
    public void playerInputActions() {
        TestView<String> testView = new TestView<>();
        testView.add("i");
        testView.add("j");
        testView.add("Y");
        testView.add("N");
        Controller controller29 = new Controller(testView);
        assertEquals(controller29.playerInputActions("yes", "no"), "Y");
    }


    /**
     * Same test as above, but using hit and stand as the parameters passed to the method instead of yes and no. (yes and no are only used for
     * doubling)
     */
    @Test
    public void playerInputActions1() {
        TestView<String> testView = new TestView<>();
        testView.add("i");
        testView.add("j");
        testView.add("H");
        testView.add("S");
        Controller controller30 = new Controller(testView);
        assertEquals(controller30.playerInputActions("stand", "hit"), "H");
    }

    /**
     * Test to ensure that quit is being handled as a user input in playerInputActions
     */
    @Test(expected = IllegalStateException.class)
    public void playerInputActions2() {
        TestView<String> testView = new TestView<>();
        testView.add("i");
        testView.add("j");
        testView.add("quit");
        testView.add("S");
        Controller controller = new Controller(testView);
        controller.playerInputActions("stand", "hit");
    }



    /**
     * Test playerAction by calling the method when two cards totaling 20 exist in the players hand. This will test the control of the loop.
     * The testView array list is loaded with three 'hit' commands, but only one hit should occur as any card given to the player will put
     * the total over 20. The means that after the call to playerAction, the player's hand should only have 3 cards.
     */
    @Test
    public void getPlayerAction() {
        TestView<String> testView = new TestView<>();
        testView.add("h");
        testView.add("h");
        testView.add("h");
        Controller controller31 = new Controller(testView);
        controller31.setShoe(new Shoe(1));
        controller31.getPlayer().addCard(new Card(11,0));
        controller31.getPlayer().addCard(new Card(11,1));
        controller31.playerAction();
        assertEquals(controller31.getPlayer().getHand().size(), 3);
    }


    /**
     * This test also tests the loop control in playerAction. A single card of value 2 is added to the hand prior to calling the method, and
     * the test view is loaded with commands of (hit, stand, hit). The value of two means that any card dealt to the player will result in a value less than 21.
     * Then the stand command is given, meaning the loop should exit and the player's hand should only have two cards, as the final hit is never executed.
     */
    @Test
    public void getPlayerAction1() {
        TestView<String> testView =  new TestView<>();
        testView.add("h");
        testView.add("s");
        testView.add("h");
        Controller controller32 = new Controller(testView);
        controller32.setShoe(new Shoe(1));
        controller32.getPlayer().addCard(new Card(2,0));
        controller32.playerAction();
        assertEquals(controller32.getPlayer().getHand().size(), 2);
    }


    /**
     * Test postPlayerActions control flow by ensuring that the body of the first if statement executes when player's hand is greater than 21.
     */
    @Test
    public void postPlayerActions() {
        TestView<Integer> testView = new TestView<>();
        Controller controller33 = new Controller(testView);
        controller33.getPlayer().addCard(new Card(11,0));
        controller33.getPlayer().addCard(new Card(11,1));
        controller33.getPlayer().addCard(new Card(11,2));
        controller33.postPlayerActions();
        assertEquals("You have busted and lost the hand!\n", out.toString());
    }


    /**
     * Test postPlayerActions control flow by ensuring that only one card is added when the getDealer()'s total is at 16 prior to the call.
     */
    @Test
    public void postPlayerActions1() {
        TestView<Integer> testView = new TestView<>();
        Controller controller34 = new Controller(testView);
        controller34.getPlayer().addCard(new Card(11,0));
        controller34.setShoe(new Shoe(1));
        controller34.getDealer().addCard(new Card(11,0));
        controller34.getDealer().addCard(new Card(6,0));
        controller34.postPlayerActions();
        assertEquals(controller34.getDealer().getHand().size(), 3);

    }

    /**
     * This verifys that postPlayerActions correctly sets dlrBust to true when getDealer()'s hand is greater than 21.
     *
     */
    @Test
    public void postPlayerActions2() {
        TestView<Integer> testView = new TestView<>();
        Controller controller35 = new Controller(testView);
        controller35.getPlayer().addCard(new Card(2,0));
        controller35.getDealer().addCard(new Card(11,0));
        controller35.getDealer().addCard(new Card(12,0));
        controller35.getDealer().addCard(new Card(5,0));
        controller35.postPlayerActions();
        assertTrue(controller35.isDlrBust());
    }


    /**
     * Verify that postPlayerActions correctly sets plyBust to true when player's total is greater than 21
     *
     */
    @Test
    public void postPlayerActions3() {
        TestView<Integer> testView = new TestView<>();
        Controller controller36 = new Controller(testView);
        controller36.getPlayer().addCard(new Card(11,0));
        controller36.getPlayer().addCard(new Card(12,0));
        controller36.getPlayer().addCard(new Card(5,0));
        controller36.postPlayerActions();
        assertTrue(controller36.isPlyBust());
    }


    /**
     * Test's the various operations that occur in endHandFunctions. Player is given a hand that would result in a bust and postPlayerActions is call to ensure that 
     * plyBust is set to true. Also check to make sure the player's hand has been cleared correctly.
     */
    @Test
    public void endHandFunctions() {
        TestView<Integer> testView = new TestView<>();
        Controller controller37 = new Controller(testView);
        controller37.setShoe(new Shoe(1));
        controller37.getPlayer().addCard(new Card(11, 0));
        controller37.getPlayer().addCard(new Card(12, 0));
        controller37.getPlayer().addCard(new Card(13, 0));
        controller37.postPlayerActions();
        assertTrue(controller37.isPlyBust());
        controller37.endHandFunctions();
        assertFalse(controller37.isPlyBust());
        assertEquals(controller37.getPlayer().getHand().size(),0);

    }


    /**
     * Same test as above but instead with getDealer() having a busting hand, and then checking that the relevant values have been set correctly
     */
    @Test
    public void endHandFunctions1() {
        TestView<Integer> testView = new TestView<>();
        Controller controller38 = new Controller(testView);
        controller38.setShoe(new Shoe(1));
        controller38.getDealer().addCard(new Card(11, 0));
        controller38.getDealer().addCard(new Card(12, 0));
        controller38.getDealer().addCard(new Card(13, 0));
        controller38.postPlayerActions();
        assertTrue(controller38.isDlrBust());
        controller38.endHandFunctions();
        assertFalse(controller38.isDlrBust());
        assertEquals(controller38.getDealer().getHand().size(),0);

    }


    /**
     * Test that results updates the player's chip amount correctly in the case of a push.
     * Chip amount is verified to be decremented correctly after a call to askBet, and then verified to be returned
     * to the original amount after a call to results.
     */
    @Test
    public void results() {
        TestView<Integer> testView = new TestView<>();
        testView.add(100);
        testView.add(25);
        Controller controller39 = new Controller(testView);
        controller39.buyChips();
        controller39.askBet();
        assertEquals(controller39.getPlayer().getChips(), 75, .0001);
        controller39.getPlayer().addCard(new Card(11,0));
        controller39.getPlayer().addCard(new Card(8,0));
        controller39.getDealer().addCard(new Card(11,1));
        controller39.getDealer().addCard(new Card(8,1));
        controller39.results();
        assertEquals(controller39.getPlayer().getChips(), 100, .0001);

    }


    /**
     * Ensure that results correctly updates the player's chip amount when the dealer busts.
     */
    @Test
    public void results1() {
        TestView<Integer> testView = new TestView<>();
        testView.add(100);
        testView.add(25);
        Controller controller40 = new Controller(testView);
        controller40.buyChips();
        controller40.askBet();
        controller40.getDealer().addCard(new Card(11,1));
        controller40.getDealer().addCard(new Card(8,1));
        controller40.getDealer().addCard(new Card(9,1));
        controller40.postPlayerActions();    //set dlrBust to true

        controller40.results();
        assertEquals(controller40.getPlayer().getChips(),  125, .0001);
    }

    /**
     * Ensure that the player's chip amount has been correctly decremented after a call to results() when the player has busted
     */
    @Test
    public void results2() {
        TestView<Integer> testView = new TestView<>();
        testView.add(100);
        testView.add(25);
        Controller controller41 = new Controller(testView);
        controller41.buyChips();
        controller41.askBet();
        controller41.getPlayer().addCard(new Card(11,0));
        controller41.getPlayer().addCard(new Card(8,1));
        controller41.getPlayer().addCard(new Card(9,2));
        controller41.postPlayerActions(); //set plyBust to true
        controller41.results();
        assertEquals(controller41.getPlayer().getChips(), 75, .0001);

    }

    /**
     * Ensure that player's chips are incremented correctly by results when dlrBust is false and the player has a hand total greater than the dealer's total. 
     */
    @Test
    public void results3() {
        TestView<Integer> testView = new TestView<>();
        testView.add(100);
        testView.add(25);
        Controller controller42 = new Controller(testView);
        controller42.buyChips();
        controller42.askBet();
        controller42.getPlayer().addCard(new Card(11,0));
        controller42.getPlayer().addCard(new Card(8,1));
        controller42.getDealer().addCard(new Card(11,1));
        controller42.getDealer().addCard(new Card(7,2));
        controller42.results();
        assertEquals(controller42.getPlayer().getChips(), 125, .0001);
    }


    /**
     * Test that results correctly increments the player's chip count by 2.5 (1.5 * player's original bet in profit.) when the player has a natural blackjack.
     */
    @Test
    public void results4() {
        TestView<Integer> testView = new TestView<>();
        testView.add(100);
        testView.add(25);
        Controller controller43 = new Controller(testView);
        controller43.buyChips();
        controller43.askBet();
        controller43.getPlayer().addCard(new Card(11,0));
        controller43.getPlayer().addCard(new Card(1,1));
        controller43.getDealer().addCard(new Card(11,1));
        controller43.getDealer().addCard(new Card(7,2));
        controller43.naturalBlackJack();
        controller43.results();
        assertEquals(controller43.getPlayer().getChips(), 137.5, .0001);


    }

    /**
     * Ensure that results correctly reads a push when both player and dealer have a blackJack.
     */
    @Test
    public void results5() {
        TestView<Integer> testView = new TestView<>();
        testView.add(100);
        testView.add(25);
        Controller controller44 = new Controller(testView);
        controller44.buyChips();
        controller44.askBet();
        controller44.getPlayer().addCard(new Card(11,0));
        controller44.getPlayer().addCard(new Card(1,1));
        controller44.getDealer().addCard(new Card(11,1));
        controller44.getDealer().addCard(new Card(1,2));
        controller44.naturalBlackJack();
        controller44.results();
        assertEquals(controller44.getPlayer().getChips(), 100, .0001);

    }

    /**
     * Ensure that player's chips are correctly decremented when the dealer has a higher hand total than the player.
     */
    @Test
    public void results6() {
        TestView<Integer> testView = new TestView<>();
        testView.add(100);
        testView.add(25);
        Controller controller = new Controller(testView);
        controller.buyChips();
        controller.askBet();
        controller.getPlayer().addCard(new Card(4,0));
        controller.getPlayer().addCard(new Card(1,1));
        controller.getDealer().addCard(new Card(11,1));
        controller.getDealer().addCard(new Card(8,2));
        controller.results();
        assertEquals(controller.getPlayer().getChips(), 75, .0001);
    }


    /**
     * Ensure checkCut correctly repopulates the shoe when the amount of cards in the shoe drops below the cut level = Shoe.size() * .2
     */
    @Test
    public void checkCut() {
        TestView<Integer> testView =  new TestView<>();
        Controller controller45 = new Controller(testView);
        controller45.setShoe(new Shoe(1));
        controller45.setCut(controller45.getShoe().size() * .2);// cut determined the same way in playBlackJack()
        for(int i = 0; i <= 45; i++){
            controller45.getShoe().pick();
        }
        controller45.checkCut();
        assertEquals(controller45.getShoe().size(), 52);
    }

    /**
     * Ensure that checkCut does nothing when the amount of cards in the shoe is above the threshold for a cut.
     */
    @Test
    public void checkCut1() {
        TestView<Integer> testView =  new TestView<>();
        Controller controller46 = new Controller(testView);
        controller46.setShoe(new Shoe(1));
        controller46.setCut(controller46.getShoe().size() * .2); // cut determined the same way in playBlackJack()
        for(int i = 0; i < 20; i++){
            controller46.getShoe().pick();
        }
        controller46.checkCut();
        assertEquals(controller46.getShoe().size(), 32);
    }


    /**
     * Test that softTotal() correctly sets specialDouble to true when the player's hand has two cards with card numbers totaling between 9 and 11 inclusive.
     */
    @Test
    public void softTotal() {
        TestView testView = new TestView();
        testView.add(100);
        testView.add(25);
        testView.add("y");
        Controller controller47 = new Controller(testView);
        controller47.setShoe(new Shoe(1));
        controller47.buyChips();
        controller47.askBet();
        controller47.getPlayer().addCard(new Card(1, 1));
        controller47.getPlayer().addCard(new Card(8,1));
        controller47.softTotal();
        controller47.playerDouble();
        assertEquals(controller47.getBet(), 50, .0001);
        assertEquals(controller47.getPlayer().getHand().size(), 3);

    }


    /**
     * Test that soft total does not set specialDouble to true when card numbers of initial hand are outside of the range 9 and 11
     */
    @Test
    public void softTotal1() {
        TestView testView = new TestView();
        testView.add(100);
        testView.add(25);
        Controller controller48 = new Controller(testView);
        controller48.buyChips();
        controller48.askBet();
        controller48.getPlayer().addCard(new Card(1, 0));
        controller48.getPlayer().addCard(new Card(11, 0));
        controller48.softTotal();
        assertFalse(controller48.ableToDouble());

    }


    /**
     * Test that playBlackJack will correctly run through one play of the game and catch the illegal state exception thrown when user enters quit.
     */
    @Test (expected = IllegalStateException.class)
    public void playBlackJack() {
        TestView testView = new TestView();
        testView.add(100);
        testView.add(25);
        testView.add("n");
        testView.add("s");
        testView.add("quit");
        Controller controller49 = new Controller(testView);
        controller49.playBlackjack();
    }

    /**
     * Test that endGameActions correctly exits the game when user enters q
     */
    @Test (expected = IllegalStateException.class)
    public void endGameActions() {
        TestView testView = new TestView();
        testView.add("q");
        Controller controller50 = new Controller(testView);
        controller50.endGameActions();

    }


    /**
     * Test that when p is enter that a new game is began by ensuring that player's chip amount it updated to 100, then the next prompt from playBlackJack is requested and quit is enter, ending the game
     */
    @Test (expected = IllegalStateException.class)
    public void endGameActions1() {
        TestView testView = new TestView();
        testView.add("p");
        testView.add(100);
        testView.add("quit");
        Controller controller51 = new Controller(testView);
        controller51.endGameActions();
        assertEquals(controller51.getPlayer().getChips(), 100,.0001);


    }
}
