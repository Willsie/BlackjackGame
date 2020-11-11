package csc439team1.blackjack.controller;


import csc439team1.blackjack.model.Shoe;
import csc439team1.blackjack.view.TestView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        TestView TestView = new TestView();
        TestView.add(0);
        TestView.add(5001);
        TestView.add(50);
        Controller controller0 = new Controller(TestView);
        controller0.buyChips();
        assertEquals("Game is starting.....how much chips do you want to buy (between $10 to $5000): " +
                "Please enter a number between 10 and 5000!: " +
                "Please enter a number between 10 and 5000!: ", out.toString());
        assertEquals(controller0.player.getChips(), 50);

    }


    /**
     * Test buyChips by feeding it bad input twice, one outside the required range on the low end, and one on the high end,
     * then feeding it valid input, and checking to ensure the player's chips have been updated with correct value(50).
     * The value of 75 is added to TestViews arrayList as well, but it player's chips should remain at 50 as the loop in buyChips should exit at the first correct input.
     */
    @Test
    public void buyChips() {
        TestView TestView = new TestView();
        TestView.add(0);
        TestView.add(5001);
        TestView.add(50);
        TestView.add(75);
        Controller controller1 = new Controller(TestView);
        controller1.buyChips();
        assertEquals(controller1.player.getChips(), 50);
    }


    /**
     * Tests buyChips() ability to quit the game when "quit" is entered by the user.
     * The functionality of TestView mimics CLIView in that if 'quit' is detected, it throws an exception that is caught in the controller. This does not test controller's quit() method in any way.
     */
    @Test(expected = Exception.class)
    public void buyChipsQuit() {
        TestView TestView = new TestView();
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
        TestView TestView = new TestView();
        TestView.add(0);
        TestView.add(5001);
        TestView.add(400);
        TestView.add(401);
        TestView.add(5);
        TestView.add(25);
        Controller controller3 = new Controller(TestView);
        controller3.buyChips();
        assertEquals(controller3.player.getChips(), 400);

        controller3.askBet();
        assertEquals(controller3.player.getChips(), 375);

    }

    /**
     * This again tests askBet in controller, but verifies that bets over 500 will not accepted when the players chips are greater than 500.
     */

    public void askBet1() {
        TestView TestView = new TestView();
        TestView.add(0);
        TestView.add(5001);
        TestView.add(600);
        TestView.add(501);
        TestView.add(5);
        TestView.add(75);
        Controller controller4 = new Controller(TestView);
        controller4.buyChips();
        assertEquals(controller4.player.getChips(), 600);

        controller4.askBet();
        assertEquals(controller4.player.getChips(), 525);

    }

    /**
     * Tests that the correct messages are displayed to the console by controller via view when incorrect bet amounts are entered.
     */
    @Test
    public void askBetOutput() {
        TestView TestView = new TestView();
        TestView.add(500);
        TestView.add(0);
        TestView.add(501);
        TestView.add(25);
        Controller controller5 = new Controller(TestView);
        controller5.buyChips();
        controller5.askBet();
        assertEquals("Game is starting.....how much chips do you want to buy (between $10 to $5000): " +
                "How much do you want to bet (between $10 to $500): " +
                "Invalid bet amount! Bet needs to be between 10 & 500, and less than your current chips(500): " +
                "Invalid bet amount! Bet needs to be between 10 & 500, and less than your current chips(500): ", out.toString());

    }

    @Test(expected = Exception.class)
    public void askBetQuit() {
        TestView TestView = new TestView();
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
        TestView TestView = new TestView();
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
        TestView TestView = new TestView();
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
    @Test
    public void playBlackJack() {
        TestView TestView = new TestView();
        TestView.add(500);
        TestView.add(100);
        Controller controller8 = new Controller(TestView);
        controller8.playBlackjack();
        assertEquals(controller8.player.getChips(), 400);
        assertEquals(controller8.player.getHand().size(), 2);
        assertEquals(controller8.dealer.getHand().size(), 2);

    }
}