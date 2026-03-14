package in.sannareddy.poker.engine.game;

import in.sannareddy.poker.engine.model.card.Card;
import in.sannareddy.poker.engine.model.card.Deck;
import in.sannareddy.poker.engine.model.player.Player;
import in.sannareddy.poker.engine.model.table.Pot;
import in.sannareddy.poker.engine.model.table.Table;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PokerHand {
    private final Table table;
    private final Deck deck;
    private final List<Pot> pots = new ArrayList<>(3);
    private BettingRound currentRound;
    private final List<Card> communityCards = new ArrayList<>(5);

    public PokerHand(Table table) {
        this.table = table;
        this.deck = new Deck();
        this.currentRound = BettingRound.PRE_FLOP;
    }

    public void dealHoleCards() {
        List<Player> activePlayers = table.getActivePlayers();
        //Deal First card
        for (Player player : activePlayers) {
            player.getHoleCards().add(deck.deal());
        }
        //Deal Second card
        for (Player player : activePlayers) {
            player.getHoleCards().add(deck.deal());
        }
    }

    public void dealCommunityCards(int count) {
        for (int i = 0; i < count; i++) {
            communityCards.add(deck.deal());
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

    public void startBetting() {
        //TODO: Implement Betting rounds
    }

    public void revealCommunityCards() {
        //TODO: Implement Revealing community cards
    }

    public void revealPlayerCards() {
    }
}
