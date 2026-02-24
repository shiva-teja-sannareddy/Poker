package in.sannareddy.poker.engine.model.card;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private final ArrayList<Card> cards = new ArrayList<>(52);
    public Deck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(cards);
    }

    public Card deal() {
        return cards.removeLast();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
