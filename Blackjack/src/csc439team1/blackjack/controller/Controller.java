package csc439team1.blackjack.controller;

import csc439team1.blackjack.model.Dealer;
import csc439team1.blackjack.model.Player;
import csc439team1.blackjack.model.Shoe;
import csc439team1.blackjack.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Controller {
    View view;
    Player player = new Player();
    Dealer dealer = new Dealer();

    public Controller(View view) {
        this.view = view;
    }

    public void playBlackjack() {
        Shoe shoe = new Shoe(1);
        buyChips();
        askBet();
        initialDeal(shoe);
    }
    //example of changed method
    public void buyChips() {
        view.output("Game is starting.....how much chips do you want to buy (between $10 to $5000): ");
        String input = view.input();
        //call to method which checks for quit
        checkQuit(input);
        //call to method which does try catch via console to ensure converts to int
        input = checkInt(input);
        int amount = Integer.parseInt(input);
        if (amount > 5000 || amount < 10) {
            throw new IllegalArgumentException();
        }
        player.setChips(amount);
    }

    public void askBet() {
        view.output("How much do you want to bet (between $10 to $500): ");
        String input = view.input();
        checkQuit(input);
        input = checkInt(input);
        int bet = Integer.parseInt(input);
        if (bet > player.getChips() || bet > 500 || bet < 10) {
            throw new IllegalArgumentException();
        }
        player.setChips(player.getChips() - bet);
    }

    public void initialDeal(Shoe shoe) {
        player.addCard(shoe.pick());
        player.addCard(shoe.pick());
        dealer.addCard(shoe.pick());
        dealer.addCard(shoe.pick());
        view.output("\nDealer card: ");
        view.output(dealer.getHand().get(0).toString() + "\n\n");
        view.output("Your initial cards are: ");
        view.output(player.getHand().toString() + "\n");
    }

    public void quit() {
        view.output("Player has quit");
        System.exit(0);
    }
    //private method runs try catch while input cannot convert, and returns acceptable string
    //or lets player quit
    private String checkInt (String input)
    {
        boolean isInt = false;
        while(!isInt)
        {
            checkQuit(input);
            try{
                Integer.parseInt(input);
                isInt = true;
            }
            catch (Exception e)
            {
                view.output("Entry must be an integer, try again\n");
                input = view.input();

            }
        }
        return input;
    }
    //private method which calls quit if input string equals quit regardless of letter case
    private void checkQuit(String input){
        if (input.toLowerCase().equals("quit")){
            quit();
        }
    }
}
