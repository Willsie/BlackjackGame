package csc439team1.blackjack.controller;

import csc439team1.blackjack.model.Dealer;
import csc439team1.blackjack.model.Player;
import csc439team1.blackjack.model.Shoe;
import csc439team1.blackjack.view.View;

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

    public void buyChips() {
        view.output("Game is starting.....how much chips do you want to buy (between $10 to $5000): ");
        String input = view.input();
        if (input.equals("quit")) quit();
        int amount = Integer.parseInt(input);
        if (amount > 5000 || amount < 10) {
            throw new IllegalArgumentException();
        }
        player.setChips(amount);
    }

    public void askBet() {
        view.output("How much do you want to bet (between $10 to $500): ");
        String input = view.input();
        if (input.equals("quit")) quit();
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
}
