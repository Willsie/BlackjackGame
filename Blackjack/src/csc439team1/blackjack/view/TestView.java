package csc439team1.blackjack.view;

import csc439team1.blackjack.model.Card;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Class created to testing Controller class
 *
 * @param <E> generic type E
 */
public class TestView<E> extends View {
    /**
     * Logger for TestView<E> class.
     */
    private static final Logger logger = Logger.getLogger(Card.class.getName());

    /**
     * ArrayList to store "user inputs" to be returned to the controller. Mimics the functionality of CLIView
     */
    private ArrayList<E> inputs = new ArrayList<>();

    /**
     * Default TestView() constructor
     */
    public TestView() {
        logger.entering(getClass().getName(), "TestView");
        logger.info("TestView() is invoked");
        logger.exiting(getClass().getName(), "TestView");
    }

    /**
     * Adds a value to the arraylist
     *
     * @param value will be an integer or the string "quit"
     */
    public void add(E value) {
        logger.entering(getClass().getName(), "add");
        this.inputs.add(value);
        logger.info("Adding value to inputs");
        logger.exiting(getClass().getName(), "add");
    }

    /**
     * Outputs a string message
     *
     * @param str message specified when controller calls this method.
     */
    @Override
    public void output(String str) {
        logger.entering(getClass().getName(), "output");
        System.out.print(str);
        logger.info("Displaying str to console");
        logger.exiting(getClass().getName(), "output");
    }

    /**
     * String input method for receiving data from player and which also allows player to quit.
     * @throws Exception when player has quit the game.
     */
    @Override
    public String input() throws Exception {
        logger.entering(getClass().getName(), "input");
        if (inputs.get(0).equals("quit")) {
            logger.info("Player entered quit - exiting the game");
            throw new Exception();
        } else {
            logger.info("Player did not enter quit - return player string");
            logger.exiting(getClass().getName(), "output");
            return (String) inputs.remove(0);
        }
    }

    /**
     * @return Integer to mimic integer input from user being returned to to controller.
     * @throws Exception mimics CLIviews detection of 'quit' being input to the user, which then throws an exception to the controller which then calls quit().
     */
    @Override
    public Integer intInput() throws Exception {
        logger.entering(getClass().getName(), "intInput");
        if (inputs.get(0).equals("quit")) {
            logger.info("Player entered quit - exiting the game");
            throw new Exception();
        } else {
            logger.info("Player enter valid integer input - return player integer input");
            logger.exiting(getClass().getName(), "intInput");
            return (Integer) inputs.remove(0);
        }
    }
}
