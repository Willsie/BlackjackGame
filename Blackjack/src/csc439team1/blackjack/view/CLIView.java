package csc439team1.blackjack.view;

import csc439team1.blackjack.model.Dealer;
import csc439team1.blackjack.model.Player;

public class CLIView extends View
{
    @Override
    public void buyChip()
    {
        System.out.println("How much chips do you want to buy ? ($5,000 max");
    }

    @Override
    public String showHand(Player playerName)
    {
        return playerName.getPlayerHand().getCards().toString();
    }

    @Override
    public String showHand(Dealer playerName)
    {
        return playerName.getDealerHand().getCards().toString();
    }

    @Override
    public int showChips()
    {
        return 0;
    }

    @Override
    public double showMoney()
    {
        return 0;
    }

    @Override
    public int showValue()
    {
        return 0;
    }

    @Override
    public boolean hitOrStand()
    {
        return false;
    }

    @Override
    public String quit()
    {
        return null;
    }

    @Override
    public void gameStart()
    {

    }

    @Override
    public void jackpot()
    {

    }

    @Override
    public void busted()
    {

    }

    @Override
    public void lostBet()
    {

    }

    @Override
    public void winBet()
    {

    }

    @Override
    public void push()
    {

    }
}
