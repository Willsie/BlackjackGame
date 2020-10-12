package csc439team1.blackjack;

import java.util.ArrayList;

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
     * the getCards method returns the combination of all cards in the current Hand.
     *
     * @return an array list of card objects in the current Hand
     */

    public ArrayList<Card> getCards() {
        return cardsOnHand;
    }


    /**
     * this is the size of the Hand / total cards in the current Hand
     *
     * @return the total cards in the current Hand
     */
    public int size() {
        return cardsOnHand.size();
    }
}




