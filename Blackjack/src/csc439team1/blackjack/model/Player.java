package csc439team1.blackjack.model;

import java.util.ArrayList;

public class Player {
    private int chips;
    private Hand hand = new Hand();

    /**
     * default no args constructor for Player()
     */
    public Player() {
    }

    /**
     * Getter method for chips.
     * @return player's chip value
     */
    public int getChips() {
        return chips;
    }

    /**
     * Setter method for chips.
     * @param chips set a new value for the player's chips. Will be done at the end of hand to reflect win/loss of bet.
     */
    public void setChips(int chips) {
        this.chips = chips;
    }

    /**
     * Getter method for Player's hand
     * @return player's hand.
     */
    public ArrayList<Card> getHand() {
        return this.hand.getCards();
    }

    /**
     * Adds a card to the players hand.
     * @param card Card returned from shoe.pick() will be added to player's hand.
     */
    public void addCard(Card card){
        hand.addCard(card);
    }

    /**
     * Clear's the hand when hand is over.
     */
    public void clearHand(){
        hand.getCards().clear();
    }
}