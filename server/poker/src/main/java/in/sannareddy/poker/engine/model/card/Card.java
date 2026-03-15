package in.sannareddy.poker.engine.model.card;

import jakarta.annotation.Nonnull;

public record Card(Suit suit, Rank rank) {
    @Override
    @Nonnull
    public String toString() {
        return rank.getSymbol() + suit.getSuitIcon();
    }
}
