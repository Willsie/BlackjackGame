package csc439team1.blackjack;

public class Player {
    private int chips;
    private Hand hand;

    /**
     * Player constructor. Chips value must be greater than 0 and less than 5001.
     * @param chips Value of player's chips. Will be given by user at onset of game.
     */
    public Player(int chips) {
        if (chips <= 0 || chips > 5000){
            throw new IllegalArgumentException();
        } else {
            this.hand = new Hand();
            this.chips = chips;
        }

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
    public Hand getHand() {
        return this.hand;
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

