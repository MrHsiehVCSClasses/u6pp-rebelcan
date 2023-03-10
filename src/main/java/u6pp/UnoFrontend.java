package u6pp;
import java.util.ArrayList;
import java.util.Scanner;

public class UnoFrontend {
    Scanner input = new Scanner(System.in);
    public void play() {
        boolean ongoing = true;
        ArrayList<Player> players = new ArrayList<Player>();
        CardStack deck = CardStack.createUnoDeck();
        CardStack discard = new CardStack();
        Uno uno = new Uno(players, deck, discard, 0, false);
        Boolean notWild = false;
        Card first = deck.pop();
        while(!notWild) {
            if(first.getColor() != "WILD" && first.getColor() != "WILD_DRAW_4") {
                discard.push(first);
                notWild = true;
            }
            else {
                deck.push(first);
                first = deck.pop();
            }
        }
        
        deck.shuffle();

        System.out.println("Welcome to Uno! How many people would you like to play with?");
        int numPlay = Integer.parseInt(input.nextLine());

        //makes all the player names
        for(int i = 0; i < numPlay; i++) {
            System.out.println("What is player " + Integer.toString(i + 1) + "'s name?");
            Player playerName = new Player(input.nextLine());
            players.add(playerName);
        }
        //adds cards to players hands
        for(int i = 0; i < 7; i++) {
            for (Player curr : players){
                curr.getHand().add(deck.pop());
            }
        }
        while(ongoing) {
            boolean playable = false;
            System.out.print("\nIt's " + uno.getCurrentPlayer().getName() + "'s turn\nThe last played card is " + uno.getTopDiscard().getColor() + " " + uno.getTopDiscard().getValue() + "\nHere is your hand: ");
            //prints player's hand
            for(int i = 0; i < uno.getCurrentPlayer().getHand().size(); i++) {
                if(i < uno.getCurrentPlayer().getHand().size() - 1) {
                    if(uno.getCurrentPlayer().getHand().get(i).getColor() == "WILD_DRAW_4") {
                        System.out.print("WILD_DRAW_4" + ", ");
                    }
                    else if(uno.getCurrentPlayer().getHand().get(i).getColor() == "WILD") {
                        System.out.print("WILD" + ", ");
                    }
                    else{
                        System.out.print(uno.getCurrentPlayer().getHand().get(i).getColor() + " " + uno.getCurrentPlayer().getHand().get(i).getValue() + ", ");
                    }
                }
                else {
                    if(uno.getCurrentPlayer().getHand().get(i).getColor() == "WILD_DRAW_4") {
                        System.out.print("WILD_DRAW_4");
                    }
                    else if(uno.getCurrentPlayer().getHand().get(i).getColor() == "WILD") {
                        System.out.print("WILD");
                    }
                    else{
                        System.out.print(uno.getCurrentPlayer().getHand().get(i).getColor() + " " + uno.getCurrentPlayer().getHand().get(i).getValue());
                    }
                }
            }
            System.out.println("\nWould you like to draw or play?");
            String decision = input.nextLine();
            if(decision.equals("draw")){
                uno.playCard(null,null);
            }
            else{
                while(!playable) {
                    System.out.println("What would you like to play? (Choose a number from 1-" + Integer.toString(uno.getCurrentPlayer().getHand().size()) + ")");
                    int cardIndex = Integer.parseInt(input.nextLine()) - 1;
                    if(uno.getCurrentPlayer().getHand().get(cardIndex).canPlayOn(uno.getTopDiscard())) {
                        playable = true;
                        Card selected = uno.getCurrentPlayer().getHand().get(cardIndex);
                        if(selected.getColor() == "WILD" || selected.getColor() == "WILD_DRAW_4") {
                            System.out.println("What color would you like to change it to?");
                            String color = input.nextLine().toUpperCase();
                            uno.playCard(selected, color);
                            Card newWild;
                            if(selected.getColor() == "WILD") {
                                newWild = new Card(color, "WILD");
                            }
                            else{
                                newWild = new Card(color, "WILD_DRAW_4");
                            }
                            discard.push(newWild);
                            uno.getCurrentPlayer().getHand().remove(selected);
                        }
                        else{
                            uno.playCard(selected, uno.getCurrentPlayer().getHand().get(cardIndex).getColor());
                            uno.getCurrentPlayer().getHand().remove(selected);
                        }
                    }
                    else{
                        System.out.println("You can't play that card!");
                    }
                }
            }
            if (!(uno.getWinner() == (null))) {
                System.out.print(uno.getWinner().getName() + " is the winner! Congrats!");
                ongoing = false;
            }
        }
    }
}
