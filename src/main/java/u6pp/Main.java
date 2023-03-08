package u6pp;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        //System.out.println("Welcome to u6pp! :)\nGood luck!");

        //UnoFrontend game = new UnoFrontend();
        //game.play();
         Uno uno;
         ArrayList<Player> players;
         Player p1, p2, p3;
         CardStack deck;
         CardStack discard;
        p1 = new Player("p1");
        p2 = new Player("p2");
        p3 = new Player("p3");

        p1.getHand().add(new Card(Card.RED, Card.ZERO));
        p1.getHand().add(new Card(Card.RED, Card.ONE));
        p1.getHand().add(new Card(Card.BLUE, Card.ZERO));
        p1.getHand().add(new Card(Card.WILD, Card.WILD));

        p2.getHand().add(new Card(Card.RED, Card.TWO));

        p3.getHand().add(new Card(Card.RED, Card.THREE));

        players = new ArrayList<Player>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        deck = new CardStack();
        deck.push(new Card(Card.GREEN, Card.ONE));
        deck.push(new Card(Card.GREEN, Card.ZERO));
        discard = new CardStack();
        discard.push(new Card(Card.RED, Card.ZERO));
        discard.push(new Card(Card.RED, Card.ZERO));
        discard.push(new Card(Card.RED, Card.ZERO));
        discard.push(new Card(Card.RED, Card.ZERO));
        discard.push(new Card(Card.RED, Card.ZERO));
        discard.push(new Card(Card.RED, Card.ZERO));
        uno = new Uno(players, deck, discard, 0, false);
        
        System.out.println(deck.getSize());
        uno = new Uno(7);
        System.out.println(deck.getSize());
        System.out.println(uno.getTopDiscard());
        // use this space to test your code :)
        System.out.println(uno.getPlayers().size());
    }
}
