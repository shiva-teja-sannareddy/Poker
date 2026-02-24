package in.sannareddy.poker.engine.model.table;

import in.sannareddy.poker.engine.model.player.Player;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Pot {
    private ChipStack chipStack = new ChipStack(0);
    private final Set<Player> playersInPot = new HashSet<>();

    public void addChips(int chips) {
        chipStack.addChips(chips);
    }

    public void addPlayer(Player player) {
        playersInPot.add(player);
    }
}
