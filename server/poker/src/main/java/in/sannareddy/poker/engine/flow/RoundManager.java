package in.sannareddy.poker.engine.flow;

import in.sannareddy.poker.engine.game.GameState;
import in.sannareddy.poker.engine.game.PokerHand;
import in.sannareddy.poker.engine.model.table.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
public class RoundManager {
    private Table table;
    @Setter
    private PokerHand currentPokerHand;

    public RoundManager(Table table) {
        this.table = table;
    }

    public void startHand(Table table) {
        table.moveDealer();
        table.collectBlinds();
        currentPokerHand = new PokerHand(table);
    }

    public void dealPreFlop() {
        currentPokerHand.dealHoleCards();
        currentPokerHand.startBetting();
    }

    public void dealFlop() {
        currentPokerHand.dealCommunityCards(3);
        currentPokerHand.startBetting();
        currentPokerHand.revealCommunityCards();
    }

    public void dealLondon() {
        currentPokerHand.dealCommunityCards(1);
        currentPokerHand.startBetting();
        currentPokerHand.revealCommunityCards();
    }

    public void dealRiver() {
        currentPokerHand.dealCommunityCards(1);
        currentPokerHand.startBetting();
        currentPokerHand.revealCommunityCards();
    }

    public void showDown() {
        currentPokerHand.startBetting();
        currentPokerHand.revealPlayerCards();
    }
}
