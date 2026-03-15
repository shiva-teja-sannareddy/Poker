package in.sannareddy.poker.engine.model.player;

import in.sannareddy.poker.engine.model.table.ChipStack;
import lombok.Data;

@Data
public class Player {
    private final String id;
    private final String name;
    private ChipStack chipStack;
    private PlayerTableState playerTableState;

    public Player(String id, String name, ChipStack chipStack) {
        this.id = id;
        this.name = name;
        this.chipStack = chipStack;
        this.playerTableState = PlayerTableState.PLAYING;
    }

    public boolean isActive() {
        return playerTableState == PlayerTableState.PLAYING;
    }

    public boolean hasRequiredChips(int chips) {
        return chipStack.getChips() >= chips;
    }

    public int removeChips(int chips) {
        if(chips < 0) {
            throw new IllegalStateException("Bet should not be negative");
        }
        return chipStack.removeChips(chips);
    }
}
