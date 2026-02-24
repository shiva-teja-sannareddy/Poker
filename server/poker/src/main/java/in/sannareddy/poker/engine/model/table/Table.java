package in.sannareddy.poker.engine.model.table;

import in.sannareddy.poker.engine.model.card.Card;
import in.sannareddy.poker.engine.model.player.Player;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class Table {
    private final List<Seat> seats = new ArrayList<>();
    private final List<Card> communityCards = new ArrayList<>(5);
    private final List<Pot> pots = new ArrayList<>(3);

    public List<Player> getActivePlayers() {
        return seats.stream()
                .map(Seat::getPlayer)
                .filter(player -> player != null && (player.isActive() || player.isAllIn()))
                .toList();
    }

    public void resetCommunityCards() {
        communityCards.clear();
    }

    public void resetPots() {
        pots.clear();
        pots.add(new Pot());
    }
}
