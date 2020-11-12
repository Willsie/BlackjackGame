package csc439team1.blackjack.view;

public abstract class View {

    public abstract void output(String str);

    public abstract String input() throws Exception;

    public abstract Integer intInput() throws Exception;
}


