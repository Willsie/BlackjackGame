package csc439team1.blackjack.view;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * This is the test class for CLIView
 */
public class CLIViewTest {
    ByteArrayInputStream in = new ByteArrayInputStream("500".getBytes());
    ByteArrayInputStream in2 = new ByteArrayInputStream("quit".getBytes());

    /**
     * Tests input method to make sure the intInput method returns a valid value when an integer is entered
     */
    @Test
    public void intInput() {
        CLIView view = new CLIView();
        try{
            System.setIn(in);
            int number = view.intInput();
            assertEquals(number, 500);
        } catch (Exception e){

        }
    }

    /**
     * Test's that an exception is thrown by CLIView when 'quit' is entered
     * @throws Exception
     */
    @Test (expected = Exception.class)
    public void intInput1() throws Exception {
        CLIView view = new CLIView();
        System.setIn(in2);
        view.intInput();
    }
}