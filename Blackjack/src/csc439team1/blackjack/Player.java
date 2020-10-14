package csc439team1.blackjack;

public class Player {
    private int chips;
    private Hand hand;

    public Player(int chips) {
        this.hand = new Hand();
        this.chips = chips;
    }

    public int getChips() {
        return chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public Hand getHand() {
        return this.hand;
    }

    public void addCard(Card card){
        hand.addCard(card);
    }

    public void clearHand(){
        hand.getCards().clear();
    }
}
