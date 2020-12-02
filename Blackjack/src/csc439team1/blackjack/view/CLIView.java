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
     * Logger for CLIVIEW class.
     */
    Logger logger = Logger.getLogger(CLIView.class.getName());

    /**
     * display the message arguments to the console
     */
    @Override
    public void output(String message) {
        logger.entering(getClass().getName(), "output method is called");
        logger.setLevel(Level.INFO);
        System.out.print(message);
        logger.info("Displaying message parameter to console");
        logger.entering(getClass().getName(), "output method is exiting");
    }

    /**
     * This method is taking input from user (console) and returning that user input when being called
     * @return userInput - the user input
     * @throws Exception if user entered "quit"
     */
    @Override
    public String input() throws Exception {
        logger.entering(getClass().getName(), "input method is called");
        logger.setLevel(Level.INFO);
        Scanner input = new Scanner(System.in);
        logger.info("Creating new scanner object, open input stream");
        String userInput = input.next();
        logger.info("Reading next input from input stream");
        if (userInput.toLowerCase().equals("quit") ) {
            logger.info("Player entered quit and exception being thrown to exit the game");
            throw new Exception();
        }
        logger.entering(getClass().getName(), "input method is exiting");
        return userInput;
    }

    /**
     * Creates a new scanner object and prompts the user for valid integer input. Will only except input that can be parsed as an Integer or the string "quit", which will end the game.
     * @return Integer Returns any valid integer. Determining if the integer is withing a specified range is handled in the calling method.
     * @throws Exception when quit is entered. Exception is caught in controller which then calls quit() to exit the game.
     */
    public Integer intInput () throws Exception {
        logger.entering(getClass().getName(), "intInput method is called");
        logger.setLevel(Level.INFO);
        Scanner input = new Scanner(System.in);
        logger.info("Creating new scanner object, open input stream");
        String userInput = input.next();
        logger.info("Reading next input from input stream");
        boolean isInt = false;
        while (!isInt) {
            logger.info("Starting while loop - condition: input is a type integer");
            try {
                if (userInput.toLowerCase().equals("quit") ) {
                    logger.info("Player entered quit and exception being thrown to exit the game");
                    throw new Exception();
                }
                Integer.parseInt(userInput);
                logger.info("Attempting to parse user input to integer");
                isInt = true;
            } catch (NumberFormatException e) {
                output("Entry must be an integer, try again: ");
                logger.info("Exception is being handled, asking user to enter the correct input");
                userInput = input.next();
                logger.info("Reading next input from input stream");
            }
        }
        logger.entering(getClass().getName(), "intInput method is exiting");
        return Integer.parseInt(userInput);
    }
}