package in.sannareddy.poker.engine.model.table;

import in.sannareddy.poker.engine.model.player.Player;
import in.sannareddy.poker.engine.model.player.PlayerPosition;
import lombok.Getter;

@Getter
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

    public int sit(Player player) {
        this.player = player;
        return index;
    }

    public int leave() {
        this.player = null;
        return index;
    }
}
