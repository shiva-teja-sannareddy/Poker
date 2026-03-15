package in.sannareddy.poker.engine.model.table;

import in.sannareddy.poker.engine.game.GameConfig;
import in.sannareddy.poker.engine.model.player.Player;
import in.sannareddy.poker.engine.util.RandomUtil;
import in.sannareddy.poker.engine.util.collections.CircularArrayList;
import lombok.Getter;

import java.util.List;


@Getter
public class Table {
    private final int maxTableSize;
    private final int initialPlayerChipStack;
    private final CircularArrayList<Seat> seats = new CircularArrayList<>();
    private Seat dealerSeat;

    public Table(GameConfig gameConfig) {
        this.maxTableSize = gameConfig.tableSize();
        this.initialPlayerChipStack = gameConfig.initialPlayerChipStack();
        for(int i = 0; i < maxTableSize; i++) {
            seats.add(new Seat(i));
        }
    }

    public Seat joinPlayer(String id, String name) {
        if (seats.size() == maxTableSize) {
            throw new IllegalStateException("Table is full");
        }
        Player player = new Player(id, name, new ChipStack(initialPlayerChipStack));
        return this.joinPlayer(player);
    }

    public Seat joinPlayer(Player player) {
        Seat seat = seats.get(getEmptySeatPosition());
        seat.sit(player);
        return seat;
    }

    public Seat removePlayer(int seatNo) {
        Seat seat = seats.get(seatNo);
        seat.leave();
        return seat;
    }

    public void pickDealer() {
        if(dealerSeat == null) {
            int dealerSeatNo = RandomUtil.getRandomNumber(0, seats.size()-1);
            this.dealerSeat = seats.get(dealerSeatNo);
        }
    }

    public void moveDealer() {
        dealerSeat = getNextActiveSeat(dealerSeat);
    }

    public List<Player> getActivePlayers() {
        return seats.stream()
                .map(Seat::getPlayer)
                .filter(player -> player != null && player.isActive())
                .toList();
    }

    public Seat getNextActiveSeat(Seat currentSeat) {
        int currentSeatNo = currentSeat.getIndex();
        for(int i=1; i<seats.size(); i++) {
            int position = (currentSeatNo + i) % seats.size();
            Seat seat = seats.get(position);
            if(seat.isOccupied() && seat.getPlayer().isActive()) {
                return seat;
            }
        }
        return null;
    }

    public int getEmptySeatPosition() {
        for(int i=0; i<seats.size(); i++) {
            if(seats.get(i).getPlayer() == null) {
                return i;
            }
        }
        return -1;
    }
}
