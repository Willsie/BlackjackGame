package csc439team1.blackjack.model;

/**
 * Dealer class contains a private variable type Hand
 */
public class Dealer
{
    /**
     * this is the collection of cards on the current Dealer hand
     */
   private final Hand dealerHand;

    /**
     * this is the Dealer(...) constructor
     * @param dealerHand is the Hand type consists of collection of dealer cards
     */
    public Dealer(Hand dealerHand){
        this.dealerHand = dealerHand;
    }

    /**
     * this method return Hand object consists of collection of cards
     *
     * @return Hand object
     */
    public Hand getDealerHand(){
        if (this.dealerHand.size() < 1) throw new IndexOutOfBoundsException();
        return this.dealerHand;
    }
}
