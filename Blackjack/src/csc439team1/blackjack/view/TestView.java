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
    private static final Logger logger = Logger.getLogger(TestView.class.getName());

    /**
     * ArrayList to store "user inputs" to be returned to the controller. Mimics the functionality of CLIView
     */
    private ArrayList<E> inputs = new ArrayList<>();

    /**
     * Default TestView() constructor
     */
    public TestView() {
        logger.entering(getClass().getName(), "TestView");
        logger.setLevel(Level.WARNING);
        logger.exiting(getClass().getName(), "TestView");
    }

    /**
     * Adds a value to the arraylist
     *
     * @param value will be an integer or the string "quit"
     */
    public void add(E value) {
        logger.entering(getClass().getName(), "add");
        logger.setLevel(Level.WARNING);
        this.inputs.add(value);
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
        logger.setLevel(Level.WARNING);
        System.out.print(str);
        logger.exiting(getClass().getName(), "output");
    }

    /**
     * Unused at this point, but needed to be implemented from abstract view
     *
     * @return
     * @throws Exception
     */
    @Override
    public String input() throws Exception {
        logger.entering(getClass().getName(), "input");
        logger.setLevel(Level.WARNING);
        if (inputs.get(0).equals("quit")) {
            logger.warning("Player entered quit to exit the game");
            throw new Exception();
        } else {
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
        logger.setLevel(Level.WARNING);
        if (inputs.get(0).equals("quit")) {
            logger.warning("Player entered quit to exit the game");
            throw new Exception();
        } else {
            logger.exiting(getClass().getName(), "intInput");
            return (Integer) inputs.remove(0);
        }
    }
}
