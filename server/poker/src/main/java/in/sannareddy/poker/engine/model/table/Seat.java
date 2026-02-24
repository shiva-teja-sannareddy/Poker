package in.sannareddy.poker.engine.model.table;

import in.sannareddy.poker.engine.model.player.Player;
import in.sannareddy.poker.engine.model.player.PlayerPosition;
import in.sannareddy.poker.engine.model.player.PlayerState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Seat {
    private final int index;
    private Player player;
    private PlayerPosition position;

    public Seat(int index) {
        this.index = index;
    }

    public boolean isOccupied() {
        return player != null;
    }

    public boolean isActive() {
        return player != null
                && player.getState() == PlayerState.ACTIVE;
    }
}
