package csc439team1.blackjack.view;

import java.util.Scanner;

public class CLIView extends View
{

    /**
     * display the message arguments to the display
     */
    @Override
    public void output(String message) {
           System.out.print(message);
    }

    /**
     * creates new Scanner object called input as placeholder to return the input from user
     * @return String message
     */
    public String input () {
        Scanner input = new Scanner(System.in);
            return input.next();
    }
}

