package csc439team1.blackjack.view;

/**
 * Abstract class View has output(), input(), intInput() that need to be implemented by CLIView
 */
public abstract class View {

    /**
     * Output method to display message to console
     *
     * @param str string message
     */
    public abstract void output(String str);

    /**
     * Input method takes string input from user
     *
     * @return user input string
     * @throws Exception if user input is "quit"
     */
    public abstract String input() throws Exception;

    /**
     * intInput method that takes integer input from user
     *
     * @return integer value of user input
     * @throws Exception if user input is "quit"
     */
    public abstract Integer intInput() throws Exception;
}


