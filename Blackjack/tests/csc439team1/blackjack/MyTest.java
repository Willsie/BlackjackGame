package csc439team1.blackjack;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyTest {

    @Test
    public void player() {
        Shoe shoe = new Shoe(1);
        Player player = new Player(100);

        player.addCard(shoe.pick());
        player.addCard(shoe.pick());

        System.out.println(player.getHand().getCards().remove(0).toString());

        player.addCard(shoe.pick());

        Hand hand = player.getHand();
        for (Card card : hand.getCards()) {
            System.out.println(card.toString());

        }

        player.clearHand();
        assertTrue(player.getHand().size() == 0);



    }
}