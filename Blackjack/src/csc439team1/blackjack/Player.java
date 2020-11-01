package csc439team1.blackjack;

public class Player {
    protected int money;
    protected int chips;
    protected Hand playerHand;

    public Player(int money){
        if (money < 1){
            throw new IllegalArgumentException();
        }
        this.money = money;
    }
    public void addChips(int addChips){
        if (addChips < 1)
        {
            throw new IllegalArgumentException();
        }
        chips = chips + addChips;
    }
    public void spendMoney(int moneySpend){
        if (moneySpend < 1){
            throw new IllegalArgumentException();
        }
        money = money - moneySpend;
    }
}
