package in.sannareddy.poker.engine.flow;

import in.sannareddy.poker.engine.game.GameConfig;
import in.sannareddy.poker.engine.game.PokerHand;
import in.sannareddy.poker.engine.model.table.Table;
import lombok.Getter;

@Getter
public class RoundManager {
    private final Table table;
    private PokerHand currentPokerHand;
    private int smallBlind;
    private int bigBlind;

    public RoundManager(Table table, GameConfig gameConfig) {
        this.table = table;
        this.table.pickDealer();
        this.smallBlind = gameConfig.initialSmallBlind();
        this.bigBlind = gameConfig.initialBigBlind();
    }

    public void startHand(Table table) {
        table.moveDealer();
        collectBlinds();
        currentPokerHand = new PokerHand(table);
        currentPokerHand.dealHoleCards();
    }

    public void advanceRound() {
        switch (currentPokerHand.getCurrentRound()) {
            case PRE_FLOP -> currentPokerHand.dealFlop();
            case FLOP -> currentPokerHand.dealTurn();
            case TURN -> currentPokerHand.dealRiver();
            case RIVER -> currentPokerHand.showDown();
            case SHOWDOWN -> currentPokerHand.distributePot();
            default -> throw new IllegalStateException("Unexpected value: " + currentPokerHand.getCurrentRound());
        }
    }


    public void collectBlinds() {
        collectSmallBlind();
        collectBigBlind();
    }

    private void collectSmallBlind() {

    }

    private void collectBigBlind() {

    }

}
