package csc439team1.blackjack.model;

/**
 * Player class containing player object with 2 private variables: chips and Hand playerHand
 */
public class Player
{
    private double money;
    private int chips;
    private Hand playerHand;

    public Player(Hand playerHand){
        this.playerHand = playerHand;
    }

    public void setChips(int amount){
        this.chips = amount;
    }

    public void setMoney(double dollar){
        this.money = dollar;
    }

    public Hand getPlayerHand(){
        return this.playerHand;
    }
}
