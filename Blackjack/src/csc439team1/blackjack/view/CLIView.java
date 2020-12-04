package csc439team1.blackjack.view;

import csc439team1.blackjack.model.Card;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * CLIView class extends View to get input and display output to console
 */
public class CLIView extends View
{
    /**
     * Logger for CLIView class.
     */
    private static final Logger logger = Logger.getLogger(Card.class.getName());

    /**
     * display the message arguments to the console
     */
    @Override
    public void output(String message) {
        logger.entering(getClass().getName(), "output");
        System.out.print(message);
        logger.info("message is " + message);
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
        Scanner input = new Scanner(System.in);
        String userInput = input.next();
        if (userInput.toLowerCase().equals("quit") ) {
            logger.info("input should equal quit:" + userInput);
            throw new Exception();
        }
        logger.info("userInput is: " + userInput);
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
        Scanner input = new Scanner(System.in);
        String userInput = input.next();
        logger.info("userInput is :" + userInput);
        boolean isInt = false;
        while (!isInt) {
            try {
                if (userInput.toLowerCase().equals("quit") ) {
                    logger.info("userInput is: " + userInput);
                    throw new Exception();
                }
                Integer.parseInt(userInput);
                isInt = true;
            } catch (NumberFormatException e) {
                output("Entry must be an integer, try again: ");
                logger.info("userInput should not be a number: " + userInput);
                userInput = input.next();
            }
        }
        logger.info("userInput should be a integer: " + userInput);
        logger.exiting(getClass().getName(), "intInput");
        return Integer.parseInt(userInput);
    }
}