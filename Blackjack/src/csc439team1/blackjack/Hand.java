/*package csc439team1.blackjack;

import java.util.ArrayList;
import java.util.Scanner;

public class Hand extends Deck
{
    private static ArrayList<Card> cards;

    public Hand()
    {
        cards = new ArrayList<Card>();
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

    @SuppressWarnings("static-access")
    public static void main(String[] args)
    {
        String hit = "";    //initialize hit that will later takes a user input (expecting either "h" or "s")
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the total deck of cards for the shoe: ");
        int numberOfDecks = input.nextInt();
        Shoe shoe = new Shoe(numberOfDecks);         //creates a Deck object named deck
        Deck deck = new Deck();         //creates a Deck object named deck
        Hand hand = new Hand();         //creates a Hand object named hand
        System.out.println("Blackjack round is starting...dealing 2 cards, good luck! \n");


        hand.addCard(deck.pick());  //add one card to the player's deck (initial card)

        //do...while loop that will let the user choose hit or stand
        //the loop will exit and displays messages when: it is a blackjack, busted (total is more than 21), or player enter "s" to stand
        //displays the current cards, current total, final cards, and the final total count
        do
        {
            hand.addCard(deck.pick()); //add one card to the player's deck (after first initial card or each time the player hit)
            if (isBusted())
            {
                System.out.println("Your cards are: " + cards.toString());
                System.out.println("Total: " + hand.getTotalValue());
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
            System.out.println("Total: " + hand.getTotalValue());
            System.out.print("Enter \"h\" to hit or \"s\" to stand: ");
            hit = input.nextLine();
            System.out.println("");

        } while (hit.equals("h") && !isBusted() && !hasBlackjack());

        if (hit.equals("s") && !isBusted())
        {
            System.out.printf("%-28s%s%s\n", "Your final cards are", ": ", cards.toString());
            System.out.printf("%-30s", "You total from this round is: " + getTotalValue());
        }
    }
}
*/
