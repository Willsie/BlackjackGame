package csc439team1.blackjack.model;

import java.util.ArrayList;

/**
 * Player class has 2 private variables int chips and Hand hand
 */
public class Player {
    /**
     * this is the current player's chips
     */
    private int chips = 0;

    /**
     * this is the current player's hand
     */
    private Hand hand = new Hand();

    /**
     * default no args constructor for Player()
     */
    public Player() {
    }

    /**
     * Getter method for chips.
     *
     * @return player's chip value
     * @throws IllegalStateException via checkChips method.
     */
    public int getChips() {
        //checkChips verifies that there are chips in player, throws IllegalStateException if there are no chips.TS
        checkChips(chips);
        return chips;
    }

    /**
     * Setter method for chips.
     *
     * @param chips set a new value for the player's chips. Will be done at the end of hand to reflect win/loss of bet.
     * @throws IllegalStateException via checkChips method.
     */
    public void setChips(int chips) {
        //checkChips verifies that there are chips in player, throws IllegalStateException if there are no chips. TS
        checkChips(chips);
        this.chips = chips;
    }

    /**
     * Getter method for Player's hand
     *
     * @return player's hand.
     */
    public ArrayList<Card> getHand() {
        return this.hand.getCards();
    }

    /**
     * Adds a card to the players hand.
     *
     * @param card Card returned from shoe.pick() will be added to player's hand.
     */
    public void addCard(Card card) {
        hand.addCard(card);
    }

    /**
     * Helper method for input validation used by other methods within the player object that deal with chip amounts.
     * Checks that there are chips for the player object, and throws IllegalStateException if no chips are present.
     *
     * @param chips current total chips
     */
    private void checkChips(int chips) {
        //checkChips looks for chips being less than 1, and throws IllegalStateException.TS
        if (chips < 0) {
            throw new IllegalStateException();
        }
    }
}