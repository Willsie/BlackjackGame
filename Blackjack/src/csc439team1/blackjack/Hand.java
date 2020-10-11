package csc439team1.blackjack;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hand class is a collection of cards who are being played after being picked from the shoe / deck
 * <p>
 * has one private variable ArrayList<Card> cards
 */
public class Hand {
    /**
     * an array list of card objects called "cards"
     */
    private final ArrayList<Card> cardsOnHand;

    /**
     * Hand object default constructor
     */
    public Hand() {
        this.cardsOnHand = new ArrayList<>();
    }   //constructor for Hand method

    /**
     * this is a method to add card to the current Hand
     *
     * @param anotherCard is a card that is being added to the current Hand
     */
    public void addCard(Card anotherCard) {
        cardsOnHand.add(anotherCard);
    }

    /**
     * this is the size of the Hand / total cards in the current Hand
     *
     * @return the total cards in the current Hand
     */
    public int size() {
        return cardsOnHand.size();
    }

    /**
     * this is a method to calculate the total value of all cards in the current Hand
     * the ACEs value will be adjusted according to the play
     *
     * @return the total value of all cards in the current Hand
     */
    public int getTotalValue() {
        int acesCounter = 0, total = 0;
        for (Card currentCard : cardsOnHand) {
            if (currentCard.getNumber() < 2) {
                acesCounter++;
            } else {
                if (currentCard.getNumber() < 10) {
                    total = total + currentCard.getNumber();
                } else {
                    total = total + 10;
                }
            }
        }
        if (acesCounter > 0) {
            if ((acesCounter - 1 + 11) + total <= 21) {
                total = total + (acesCounter - 1 + 11);
            } else {
                total = total + acesCounter;
            }
        }
        return total;
    }

    /**
     * the getCards method returns the combination of all cards in the current Hand.
     *
     * @return an array list of card objects in the current Hand
     */

    public ArrayList<Card> getCards() {
        return cardsOnHand;
    }

    public static void main(String[] args) {
        String hit = "";    //initialize hit that will later takes a user input (expecting either "h" or "s")
        Boolean keepPlaying = true;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the total deck of cards for the shoe: ");
        int numberOfDecks = input.nextInt();

        Shoe shoe = new Shoe(numberOfDecks);         //creates a Deck object named deck
        Hand playerHand = new Hand();         //creates a Hand object named playerHand
        Hand dealerHand = new Hand();
        System.out.println("Blackjack round is starting...dealing 2 cards, good luck!");
        playerHand.addCard(shoe.pick());  //add one card to the player's hand (initial card)
        dealerHand.addCard(shoe.pick());  //add first card to the dealer's hand (initial card)
        dealerHand.addCard(shoe.pick());  //add second card to the dealer's hand (initial card)

        //do...while loop that will let the user choose hit or stand
        //the loop will exit and displays messages when: player hit blackjack or busted (total is more than 21), or player enter "s" to stand
        //displays the player cards, dealer's open card, player's total, dealer's total
        do {
            playerHand.addCard(shoe.pick()); //add one card to the player's deck (after first initial card or each time the player hit)
            System.out.printf("\n%-20s%s\n", "Player's cards    :", playerHand.getCards().toString());
            if (playerHand.getTotalValue() > 21) {
                System.out.printf("%-20s%s\n", "Dealer's cards    :", dealerHand.getCards().toString());
                System.out.printf("%-20s%d\n", "Player's total    : ", playerHand.getTotalValue());
                System.out.printf("%-20s%d\n", "Dealer's total    :", dealerHand.getTotalValue());
                System.out.println("BUSTED!  YOU LOSE!");
                keepPlaying = false;
            } else if (playerHand.getTotalValue() == 21) {
                System.out.println("BLACKJACK !!!");
                keepPlaying = false;
            } else {
                System.out.printf("%-20s[%s]\n", "Dealer's open card:", dealerHand.getCards().get(0).toString());
                System.out.printf("%-20s%d\n", "Player's total    : ", playerHand.getTotalValue());
                System.out.print("Enter \"h\" to hit or \"s\" to stand: ");
                hit = input.next();
            }
        } while (keepPlaying && shoe.size() > 0 && hit.equals("h"));

        if (hit.equals("s") || playerHand.getTotalValue() == 21) {
            while (dealerHand.getTotalValue() <= 16) {
                dealerHand.addCard(shoe.pick());
            }
            System.out.printf("\n%-20s%s\n", "Player's cards    :", playerHand.getCards().toString());
            System.out.printf("%-20s%s\n", "Dealer's cards    :", dealerHand.getCards().toString());
            System.out.printf("%-20s%d\n", "Player's total    : ", playerHand.getTotalValue());
            System.out.printf("%-20s%d\n", "Dealer's total    :", dealerHand.getTotalValue());

            if (playerHand.getTotalValue() < dealerHand.getTotalValue() && dealerHand.getTotalValue() <= 21) {
                System.out.printf("%s\n", "YOU LOST !!!");
            } else if (playerHand.getTotalValue() > dealerHand.getTotalValue() || dealerHand.getTotalValue() > 21) {
                System.out.printf("%s\n", "YOU WON !!!");
            } else {
                System.out.printf("%s\n", "DRAW !!!");
            }
        }
    }
}
