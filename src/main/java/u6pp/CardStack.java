package u6pp;

import java.util.ArrayList;
import java.util.List;

public class CardStack {

    private List<Card> cards;

    public CardStack() {
        this.cards = new ArrayList<>();
    }

    public void push(Card card) {
        cards.add(card);
    }
//if no cards then null
    public Card pop() {
        if (cards.size() == 0) {
            return null;
        }
        return cards.remove(cards.size() - 1);
    }
//if no more cards to get then null
    public Card peek() {
        if (cards.size() == 0) {
            return null;
        }
        return cards.get(cards.size() - 1);
    }

    public int getSize() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public void clear() {
        cards.clear();
    }

    public void shuffle() {
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    public void addAll(CardStack other) {
        CardStack temp = new CardStack();
        while (!other.isEmpty()) {
            temp.push(other.pop());
        }
        while (!temp.isEmpty()) {
            Card card = temp.pop();
            if (!cards.contains(card)) {
                push(card);
            } else {
                other.push(card);
            }
        }
    }
}
