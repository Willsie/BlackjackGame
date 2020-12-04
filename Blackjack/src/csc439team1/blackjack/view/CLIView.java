package csc439team1.blackjack.view;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CLIView class extends View to get input and display output to console
 */
public class CLIView extends View
{
    /**
     * Logger for CLIView class.
     */
    private static final Logger logger = Logger.getLogger(CLIView.class.getName());

    /**
     * display the message arguments to the console
     */
    @Override
    public void output(String message) {
        logger.entering(getClass().getName(), "output");
        logger.setLevel(Level.WARNING);
        System.out.print(message);
        logger.exiting(getClass().getName(), "output");
    }

    /**
     * This method is taking input from user (console) and returning that user input when being called
     * @return userInput - the user input
     * @throws Exception if user entered "quit"
     */
    @Override
    public String input() throws Exception {
        logger.entering(getClass().getName(), "input");
        logger.setLevel(Level.INFO);
        Scanner input = new Scanner(System.in);
        String userInput = input.next();
        if (userInput.toLowerCase().equals("quit") ) {
            logger.warning("Player entered quit to exit the game");
            throw new Exception();
        }
        logger.exiting(getClass().getName(), "input");
        return userInput;
    }

    /**
     * Creates a new scanner object and prompts the user for valid integer input. Will only except input that can be parsed as an Integer or the string "quit", which will end the game.
     * @return Integer Returns any valid integer. Determining if the integer is withing a specified range is handled in the calling method.
     * @throws Exception when quit is entered. Exception is caught in controller which then calls quit() to exit the game.
     */
    public Integer intInput () throws Exception {
        logger.entering(getClass().getName(), "intInput");
        logger.setLevel(Level.WARNING);
        Scanner input = new Scanner(System.in);
        String userInput = input.next();
        boolean isInt = false;
        while (!isInt) {
            try {
                if (userInput.toLowerCase().equals("quit") ) {
                    logger.warning("Player entered quit to exit the game");
                    throw new Exception();
                }
                Integer.parseInt(userInput);
                isInt = true;
            } catch (NumberFormatException e) {
                output("Entry must be an integer, try again: ");
                userInput = input.next();
                logger.warning("Reading next input from input stream");
            }
        }
        logger.exiting(getClass().getName(), "intInput");
        return Integer.parseInt(userInput);
    }
}