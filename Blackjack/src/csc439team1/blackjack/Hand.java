package csc439team1.blackjack;

import java.util.ArrayList;
import java.util.Scanner;

public class Hand extends Deck
{
    private static ArrayList<Card> cards;

    public Hand()
    {
        cards = new ArrayList<>();
    }

    public void addCard(Card anotherCard)
    {
        cards.add(anotherCard);
    }

    public static boolean hasBlackjack()
    {
        return getTotalValue() == 21;
    }

    public static boolean isBusted()
    {
        return getTotalValue() > 21;
    }


    public static int countAces()
    {
        int totalAces = 0;
        for (Card card : cards)
        {
            if (card.getSuitString().equals("ACE")) totalAces++;
        }
        return totalAces;
    }


    public static int getSoftTotal()
    {
        int total = 0;
        for (Card card : cards)
        {
            if (card.getNumber() != 1 && card.getNumber() < 11)
            {
                total += card.getNumber();
            } else if ((card.getNumber() == 1) && (total < 11))
            {
                total += 11;
            } else if (card.getNumber() == 1 && total > 10)
            {
                total += 1;
            } else
            {
                total += 10;
            }
        }
        return total;
    }

    public static int getTotalValue()
    {
        int nonAces = 0;
        int acesCounter = 0;
        int total = 0;
        for (Card card : cards)
        {
            if (card.getNumber() > 1 && card.getNumber() < 11 && acesCounter > 2)
            {
                nonAces += card.getNumber();
                total = nonAces + 11 + acesCounter;
            } else if (card.getNumber() > 1 && card.getNumber() < 11 && acesCounter == 0)
            {
                nonAces += card.getNumber();
                total = nonAces;
            } else if (card.getNumber() > 1 && card.getNumber() < 11 && acesCounter == 1)
            {
                nonAces += card.getNumber();
                total = nonAces > 10 ? nonAces + 1 : nonAces + 11;
            } else if (card.getNumber() > 1 && card.getNumber() < 11 && acesCounter == 2)
            {
                nonAces += card.getNumber();
                total = nonAces > 9 ? nonAces + 2 : nonAces + 12;
            } else if (card.getNumber() == 1)
            {
                acesCounter++;
                if (acesCounter > 1) total++;
                else if (acesCounter == 1 && nonAces <= 10) total += 11;
                else total++;
            } else
            {
                nonAces += 10;
                total += 10;
            }
        }
        return total;
    }

    //The getCards method should return the collection of cards currently in the hand.
    //create getCards() methods here.....to be completed

    //The size method should return the number of cards currently in the hand.
    //create getTotalCards() methods here.....to be completed

    @SuppressWarnings("static-access")
    public static void main(String[] args)
    {
        String hit = "";    //initialize hit that will later takes a user input (expecting either "h" or "s")
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the total deck of cards for the shoe: ");
        int numberOfDecks = input.nextInt();
        Shoe shoe = new Shoe(numberOfDecks);         //creates a Deck object named deck
        Hand playerHand = new Hand();         //creates a Hand object named playerHand
        Hand houseHand = new Hand();
        System.out.println("Blackjack round is starting...dealing 2 cards, good luck! \n");
        playerHand.addCard(shoe.pick());  //add one card to the player's hand (initial card)
        //houseHand.addCard(shoe.pick());  //add first card to the dealer's hand (initial card)
        //houseHand.addCard(shoe.pick());  //add second card to the dealer's hand (initial card)

        //do...while loop that will let the user choose hit or stand
        //the loop will exit and displays messages when: player hit blackjack or busted (total is more than 21), or player enter "s" to stand
        //displays the current cards, current total, final cards, and the final total count
        do
        {
            playerHand.addCard(shoe.pick()); //add one card to the player's deck (after first initial card or each time the player hit)
            if (isBusted())
            {
                System.out.println("Your cards are: " + cards.toString());
                System.out.println("Total: " + playerHand.getTotalValue());
                System.out.println("BUSTED!  YOU LOSE!");
                break;
            }
            if (hasBlackjack())
            {
                System.out.println("Your cards are: " + cards.toString());
                System.out.println("BLACKJACK !!! ... CONGRATULATIONS, YOU WON!");
                break;
            }
            System.out.println("Your cards are: " + cards.toString());
            System.out.println("Total: " + playerHand.getTotalValue());
            System.out.print("Enter \"h\" to hit or \"s\" to stand: ");
            hit = input.next();
        } while (shoe.size() > 0 && hit.equals("h"));

        if (hit.equals("s") && !isBusted())
        {
            if (playerHand.getTotalValue() < houseHand.getTotalValue())
            {
                System.out.printf("%s%s\n", "Your final cards are      :", cards.toString());
                //System.out.printf("%s%s\n", "The dealer final cards are:", cards.toString());
                System.out.printf("%s%2d\n", "Your total is  : ", playerHand.getTotalValue());
                //System.out.printf("%s%2d\n", "Dealer total is: ", houseHand.getTotalValue());
                System.out.printf("%s\n", "YOU LOST !!!");
            } else if (playerHand.getTotalValue() > houseHand.getTotalValue())
            {
                System.out.printf("%s%s\n", "Your final cards are      :", cards.toString());
                //System.out.printf("%s%s\n", "The dealer final cards are:", cards.toString());
                System.out.printf("%s%2d\n", "Your total is  : ", playerHand.getTotalValue());
                //System.out.printf("%s%2d\n", "Dealer total is: ", houseHand.getTotalValue());
                System.out.printf("%s\n", "YOU WON !!!");
            } else if (playerHand.getTotalValue() == houseHand.getTotalValue())
            {
                System.out.printf("%s%s\n", "Your final cards are      :", cards.toString());
                //System.out.printf("%s%s\n", "The dealer final cards are:", cards.toString());
                System.out.printf("%s%2d\n", "Your total is  : ", playerHand.getTotalValue());
                //System.out.printf("%s%2d\n", "Dealer total is: ", houseHand.getTotalValue());
                System.out.printf("%s\n", "DRAW !!!");
            } else
            {
                System.out.printf("%s%s\n", "Your final cards are: ", cards.toString());
                System.out.printf("%s%s\n", "You total from this round is: ", getTotalValue());
            }
        }
    }
}
