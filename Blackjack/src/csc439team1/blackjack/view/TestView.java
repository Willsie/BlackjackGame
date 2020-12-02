package csc439team1.blackjack.view;

import java.util.ArrayList;
import java.util.logging.Level;
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
    Logger logger = Logger.getLogger(TestView.class.getName());


    /**
     * ArrayList to store "user inputs" to be returned to the controller. Mimics the functionality of CLIView
     */
    private ArrayList<E> inputs = new ArrayList<>();


    /**
     * Default TestView() constructor
     */
    public TestView() {
        logger.entering(getClass().getName(), "TestView default constructor is called");
        logger.setLevel(Level.INFO);
        logger.info("TestView() is invoked");
        logger.entering(getClass().getName(), "TestView default constructor is exiting");
    }

    /**
     * Adds a value to the arraylist
     *
     * @param value will be an integer or the string "quit"
     */
    public void add(E value) {
        logger.entering(getClass().getName(), "add method is called");
        logger.setLevel(Level.INFO);
        this.inputs.add(value);
        logger.info("Adding value to inputs");
        logger.entering(getClass().getName(), "add method is exiting");
    }

    /**
     * Outputs a string message
     *
     * @param str message specified when controller calls this method.
     */
    @Override
    public void output(String str) {
        logger.entering(getClass().getName(), "output method is called");
        logger.setLevel(Level.INFO);
        System.out.print(str);
        logger.info("Displaying str to console");
        logger.entering(getClass().getName(), "output method is exiting");
    }

    /**
     * Unused at this point, but needed to be implemented from abstract view
     *
     * @return
     * @throws Exception
     */
    @Override
    public String input() throws Exception {
        logger.entering(getClass().getName(), "input method is called");
        logger.setLevel(Level.INFO);
        if (inputs.get(0).equals("quit")) {
            logger.info("Player entered quit - exiting the game");
            throw new Exception();
        } else {
            logger.info("Player did not enter quit - return player string");
            logger.entering(getClass().getName(), "output method is exiting");
            return (String) inputs.remove(0);
        }
    }

    /**
     * @return Integer to mimic integer input from user being returned to to controller.
     * @throws Exception mimics CLIviews detection of 'quit' being input to the user, which then throws an exception to the controller which then calls quit().
     */
    @Override
    public Integer intInput() throws Exception {
        logger.entering(getClass().getName(), "intInput method is called");
        logger.setLevel(Level.INFO);
        if (inputs.get(0).equals("quit")) {
            logger.info("Player entered quit - exiting the game");
            throw new Exception();
        } else {
            logger.info("Player enter valid integer input - return player integer input");
            logger.entering(getClass().getName(), "intInput method is exiting");
            return (Integer) inputs.remove(0);
        }
    }
}
