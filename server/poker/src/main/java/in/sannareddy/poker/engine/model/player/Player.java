package in.sannareddy.poker.engine.model.player;

import in.sannareddy.poker.engine.model.card.Card;
import in.sannareddy.poker.engine.model.table.ChipStack;
import lombok.Data;

import java.util.List;

@Data
public class Player {
    private final String id;
    private final String name;
    private List<Card> holeCards;
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
}
