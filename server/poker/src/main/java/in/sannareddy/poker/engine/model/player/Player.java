package in.sannareddy.poker.engine.model.player;

import in.sannareddy.poker.engine.model.card.Card;
import in.sannareddy.poker.engine.model.table.ChipStack;
import in.sannareddy.poker.engine.model.table.Pot;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Player {
    private final String id;
    private final String name;
    private List<Card> holeCards = new ArrayList<>();
    private ChipStack chipStack;
    private int currentBet;
    private PlayerState state = PlayerState.ACTIVE;

    public Player(String id, String name, ChipStack chipStack) {
        this.id = id;
        this.name = name;
        this.chipStack = chipStack;
    }

    public boolean isActive() {
        return state == PlayerState.ACTIVE;
    }
    public boolean isAllIn() {
        return state == PlayerState.ALL_IN;
    }

    public boolean hasRequiredChips(int chips) {
        return chipStack.getChips() >= chips;
    }

    public int bet(int chips) {
        if(chips < 0) {
            throw new IllegalStateException("Bet should not be negative");
        }
        int chipsRemaining = chipStack.removeChips(chips);
        currentBet += chips;
        if (chipsRemaining == 0) {
            state = PlayerState.ALL_IN;
        }
        return currentBet;
    }
}
