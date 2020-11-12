package csc439team1.blackjack.view;

import java.util.Scanner;

public class CLIView extends View
{

    /**
     * display the message arguments to the console
     */
    @Override
    public void output(String message) {
           System.out.print(message);
    }

    @Override
    public String input() throws Exception {
        return null;
    }

    /**
     * Creates a new scanner object and prompts the user for valid integer input. Will only except input that can be parsed as an Integer or the string "quit", which will end the game.
     * @return Integer Returns any valid integer. Determining if the integer is withing a specified range is handled in the calling method.
     * @throws Exception when quit is entered. Exception is caught in controller which then calls quit() to exit the game.
     */
    public Integer intInput () throws Exception {
        Scanner input = new Scanner(System.in);
        String userInput = input.next();

        boolean isInt = false;
        while (!isInt) {
            try {
                if (userInput.toLowerCase().equals("quit") ) {
                    throw new Exception();
                }
                Integer.parseInt(userInput);
                isInt = true;
            } catch (NumberFormatException e) {
                output("Entry must be an integer, try again: ");
                userInput = input.next();

            }
        }
        return Integer.parseInt(userInput);
    }
}

