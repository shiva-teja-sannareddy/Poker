package in.sannareddy.poker.engine.game;

import in.sannareddy.poker.engine.model.card.Card;
import in.sannareddy.poker.engine.model.card.Deck;
import in.sannareddy.poker.engine.model.table.Seat;
import in.sannareddy.poker.engine.model.table.Table;

import java.util.List;

public class Hand {
    private final Table table;
    private final GameConfig config;
    private final Deck deck;
    private BettingRound currentRound;

    public Hand(Table table, GameConfig config) {
        this.table = table;
        this.config = config;
        this.deck = new Deck();
        this.deck.shuffle();
        this.currentRound = BettingRound.PRE_FLOP;
    }

    public void dealHoleCards() {
        for (Seat seat : table.getSeats()) {
            if (seat.isActive()) {
                List<Card> holeCards = List.of(deck.deal(), deck.deal());
                seat.getPlayer().setHoleCards(holeCards);
            }
        }
    }

    public void dealCommunityCards(int count) {
        for (int i = 0; i < count; i++) {
            table.getCommunityCards().add(deck.deal());
        }
    }

    public void advanceRound() {
        switch (currentRound) {
            case PRE_FLOP -> currentRound = BettingRound.FLOP;
            case FLOP -> currentRound = BettingRound.TURN;
            case TURN -> currentRound = BettingRound.RIVER;
            case RIVER -> currentRound = BettingRound.SHOWDOWN;
        }
    }
}
