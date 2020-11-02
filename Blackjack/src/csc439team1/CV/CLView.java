package csc439team1.CV;


import java.util.Scanner;

public class CLView extends View{
    public void purchasePrompt(){
        System.out.println("How many chips do you wish to purchase, there is a maximum of $5,000");
    }
    public int purchaseAmount(){
        Scanner scanner = new Scanner(System.in);
        int purchase;
        if (scanner.nextInt() < 1)
        {
            throw new IllegalArgumentException();
        }
        purchase = scanner.nextInt();
        return purchase;
    }
}
