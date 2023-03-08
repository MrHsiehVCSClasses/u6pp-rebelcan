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
        Card temp = cards.get(cards.size() - 1);
        cards.remove(cards.size() - 1);
        return temp;
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
    public static CardStack createUnoDeck() {
        CardStack stack = new CardStack();
        for (String color : Card.COLORS) {
            if (color.equalsIgnoreCase(Card.WILD)) {
                continue;
            }
    
            for (String value : Card.VALUES) {
                if (value.equals(Card.WILD) || value.equals(Card.WILD_DRAW_4)) {
                    continue;
                }
                stack.push(new Card(color, value));
                if (!value.equalsIgnoreCase(Card.ZERO)) {
                    stack.push(new Card(color, value));
                }
            }
        }
    
        for (int i = 0; i < 4; i++) {
            stack.push(new Card(Card.WILD, Card.WILD));
            stack.push(new Card(Card.WILD, Card.WILD_DRAW_4));
        }
    
        return stack;
    }
    
}
