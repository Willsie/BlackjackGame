package csc439team1.blackjack.view;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class created to testing Controller class
 *
 * @param <E> generic type E
 */
public class TestView<E> extends View {

    /**
     * ArrayList to store "user inputs" to be returned to the controller. Mimics the functionality of CLIView
     */
    private ArrayList<E> inputs = new ArrayList<>();

    /**
     * Default TestView() constructor
     */
    public TestView() {
    }

    /**
     * Adds a value to the arraylist
     *
     * @param value will be an integer or the string "quit"
     */
    public void add(E value) {
        this.inputs.add(value);
    }

    /**
     * Outputs a string message
     *
     * @param str message specified when controller calls this method.
     */
    @Override
    public void output(String str) {
        System.out.print(str);
    }

    /**
     * Unused at this point, but needed to be implemented from abstract view
     *
     * @return
     * @throws Exception
     */
    @Override
    public String input() throws Exception {
        if (inputs.get(0).equals("quit")) {
            throw new Exception();
        } else {
            return (String) inputs.remove(0);
        }

    }

    /**
     * @return Integer to mimic integer input from user being returned to to controller.
     * @throws Exception mimics CLIviews detection of 'quit' being input to the user, which then throws an exception to the controller which then calls quit().
     */
    @Override
    public Integer intInput() throws Exception {
        if (inputs.get(0).equals("quit")) {
            throw new Exception();
        } else {
            return (Integer) inputs.remove(0);
        }
    }
}
