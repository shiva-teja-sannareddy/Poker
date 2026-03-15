package in.sannareddy.poker.engine.game;

import in.sannareddy.poker.engine.model.card.Card;
import in.sannareddy.poker.engine.model.card.Deck;
import in.sannareddy.poker.engine.model.player.Player;
import in.sannareddy.poker.engine.model.player.PlayerHandState;
import in.sannareddy.poker.engine.model.table.Pot;
import in.sannareddy.poker.engine.model.table.Table;
import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class PokerHand {
    private final Table table;
    private final Deck deck;
    private final Map<Player, PlayerHand> playerHands;
    private final List<Pot> pots = new ArrayList<>(3);
    private BettingRound currentRound;
    private final List<Card> communityCards = new ArrayList<>(5);

    public PokerHand(Table table) {
        this.table = table;
        this.deck = new Deck();
        this.currentRound = BettingRound.PRE_FLOP;
        this.playerHands = table.getActivePlayers().stream()
                .collect(Collectors.toMap(
                        player -> player,
                        _ -> new PlayerHand(),
                        (oldVal, newVal) -> newVal,
                        LinkedHashMap::new
                ));
    }

    public void dealHoleCards() {
        //Deal First card
        for (PlayerHand playerHand : playerHands.values()) {
            playerHand.getHoleCards().add(deck.deal());
        }
        //Deal Second card
        for (PlayerHand playerHand : playerHands.values()) {
            playerHand.getHoleCards().add(deck.deal());
        }
    }

    public void dealCommunityCards(int count) {
        for (int i = 0; i < count; i++) {
            communityCards.add(deck.deal());
        }
    }

    public void dealPreFlop() {
        dealHoleCards();
        startBetting();
    }

    public void dealFlop() {
        currentRound = BettingRound.FLOP;
        dealCommunityCards(3);
        startBetting();
        revealCommunityCards();
    }

    public void dealTurn() {
        currentRound = BettingRound.TURN;
        dealCommunityCards(1);
        startBetting();
        revealCommunityCards();
    }

    public void dealRiver() {
        currentRound = BettingRound.RIVER;
        dealCommunityCards(1);
        startBetting();
        revealCommunityCards();
    }

    public void showDown() {
        currentRound = BettingRound.SHOWDOWN;
        startBetting();
        revealPlayerCards();
    }

    public void startBetting() {
        //TODO: Implement Betting rounds
    }

    private void revealCommunityCards() {
        //TODO: Implement Revealing community cards
    }

    private void revealPlayerCards() {
    }

    public void distributePot() {

    }

    @Getter
    class PlayerHand {
        private final List<Card> holeCards = new ArrayList<>();
        private int currentBet = 0;
        private PlayerHandState state = PlayerHandState.ACTIVE;

        public boolean isActive() {
            return state == PlayerHandState.ACTIVE;
        }
        public boolean isAllIn() {
            return state == PlayerHandState.ALL_IN;
        }
    }
}
