package in.sannareddy.poker.engine.model.card;

import lombok.Getter;

@Getter
public enum Suit {
    CLUBS("♠"),
    DIAMONDS("♦"),
    HEARTS("♥"),
    SPADES("♣");

    private final String suitIcon;
    Suit(String suitIcon) {
        this.suitIcon = suitIcon;
    }
}
