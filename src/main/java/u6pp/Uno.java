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
        if (!currentPlayer.getHand().contains(card)) {
            return false;
        }
        if (!card.canPlayOn(getTopDiscard())) {
            return false;
        }
        //If both conditions are met, the card is removed from the player's hand and added to the discard pile
        currentPlayer.getHand().remove(card);
        discard.push(card);
        //If the player has only one card left in their hand, they set their saidUno flag to true
        if (currentPlayer.getHand().size() == 1) {
            currentPlayer.setSaidUno(true);
        }
        if (currentPlayer.getHand().size() == 0) {
            return true;
        }
        if (card.getRank() == Card.REVERSE) {
            isReversed = !isReversed;
        }
        getNextPlayer();
        return true;
    }

//calculates the index of the next player
    private int getNextPlayerIndex() {
        int direction = isReversed ? -1 : 1;
        int nextPlayerIndex = (currentPlayerIndex + direction + players.size()) % players.size();
        return nextPlayerIndex;
    }

    public ArrayList<Player> getPlayers() {
        return null;
    }
}
