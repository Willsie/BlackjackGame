package csc439team1.CV;

import csc439team1.blackjack.Player;
import csc439team1.CV.CLView;

public class Controller {
    Player readyPlayer1 = new Player(100);
    CLView viewObject = new CLView();
    public void story1(){
        viewObject.purchasePrompt();
        readyPlayer1.addChips(viewObject.purchaseAmount());
    }
//Hi there.
}
