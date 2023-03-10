package u6pp;
import java.util.ArrayList;

public class Uno {

    private ArrayList<Player> players;
    private CardStack deck;
    private CardStack discard;
    private int currentPlayerIndex;
    private boolean isReversed;

    public Uno(ArrayList<Player> players, CardStack deck, CardStack discard, int startingPlayerIndex, boolean isReversed) {
        this.players = players;
        this.deck = deck;
        this.discard = discard;
        this.currentPlayerIndex = startingPlayerIndex;
        this.isReversed = isReversed;
        deck.shuffle();
    }
    public Uno(int playerCount) {
        players = new ArrayList<Player>();
        for(int i = 0; i < playerCount; i++){
            
            players.add(new Player("p" + Integer.toString(i + 1)));
        }
        this.deck = CardStack.createUnoDeck();
        this.discard = new CardStack();
        this.discard.push(deck.pop());
        this.currentPlayerIndex = 0;
        this.isReversed = false;
        deck.shuffle();
        for(int i = 0; i < 7; i++){
            for (Player curr : players){
                curr.getHand().add(deck.pop());
            }
        }
    }
//returns the current player in the game.
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }
//method returns the next player in the game and updates the currentPlayerIndex variable
    public Player getNextPlayer() {
        int nextPlayerIndex = getNextPlayerIndex();
        currentPlayerIndex = nextPlayerIndex;
        return players.get(nextPlayerIndex);
    }
//returns the top card in the discard pile
    public Card getTopDiscard() {
        return discard.peek();
    }
//method returns the player with an empty hand or null if there is no winner yet
    public Player getWinner() {
        for (Player player : players) {
            if (player.getHand().size() == 0) {
                return player;
            }
        }
        return null;
    }
    //checks if the current player has the card in their hand, and if it can be played on top of the discard pile
    public boolean playCard(Card card, String color) {
        Player currentPlayer = getCurrentPlayer();
        if(deck.getSize() - 1 <= 0){
            System.out.print("ran");
            Card tempCard = discard.pop();
            deck.addAll(discard);
            discard.push(tempCard);
        }
        if (color == null || card == null){
            currentPlayer.addCardToHand(deck.pop());
            getNextPlayer();
        }
        if (!currentPlayer.getHand().contains(card)) {
            return false;
        }
        else if (!card.canPlayOn(getTopDiscard())) {
            return false;
        }
        //If both conditions are met, the card is removed from the player's hand and added to the discard pile
        currentPlayer.getHand().remove(card);
        discard.push(card);
        //If the player has only one card left in their hand, they set their saidUno flag to true
        if (currentPlayer.getHand().size() == 1) {
            currentPlayer.setSaidUno(true);
        }
        if (card.getValue() == Card.SKIP && card.canPlayOn(getTopDiscard())){
            getNextPlayer();
        }
        else if (card.getValue() == Card.DRAW_2 && card.canPlayOn(getTopDiscard())){
            getNextPlayer();
            players.get(currentPlayerIndex).addCardToHand(deck.pop());
            players.get(currentPlayerIndex).addCardToHand(deck.pop());
        }
        else if (card.getValue() == Card.WILD && !(card.getColor() == Card.WILD_DRAW_4)){
            getTopDiscard().trySetColor(color);
        }
        else if (card.getValue() == Card.WILD_DRAW_4 || card.getColor() == Card.WILD_DRAW_4){
            System.out.println("ran");
            getTopDiscard().trySetColor(color);
            players.get(getNextPlayerIndex()).addCardToHand(deck.pop());
            players.get(getNextPlayerIndex()).addCardToHand(deck.pop());
            players.get(getNextPlayerIndex()).addCardToHand(deck.pop());
            players.get(getNextPlayerIndex()).addCardToHand(deck.pop());
            getNextPlayer();
        }
        else if (currentPlayer.getHand().size() == 0) {
            return true;
        }
        else if (card.getValue() == Card.REVERSE) {
            isReversed = !isReversed;
        }
        getNextPlayer();
        return true;
    }

//calculates the index of the next player.
    private int getNextPlayerIndex() {
        int direction = isReversed ? -1 : 1;
        int nextPlayerIndex = (currentPlayerIndex + direction + players.size()) % players.size();
        return nextPlayerIndex;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
