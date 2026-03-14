package in.sannareddy.poker.engine.model.card;

public record Card(Suit suit, Rank rank) {
    @Override
    public String toString() {
        return rank.getSymbol() + suit.getSuitIcon();
    }
}
