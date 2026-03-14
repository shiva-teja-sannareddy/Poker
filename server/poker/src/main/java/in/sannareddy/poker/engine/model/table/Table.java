package in.sannareddy.poker.engine.model.table;

import in.sannareddy.poker.engine.game.GameConfig;
import in.sannareddy.poker.engine.model.player.Player;
import in.sannareddy.poker.engine.util.RandomUtil;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class Table {
    private final int maxTableSize;
    private final int initialPlayerChipStack;
    private final List<Seat> seats = new ArrayList<>();
    private final int smallBlind;
    private final int bigBlind;
    private int dealerSeatNo = -1;
    private int smallBlindSeatNo = -1;
    private int bigBlindSeatNo = -1;

    public Table(GameConfig gameConfig) {
        this.maxTableSize = gameConfig.tableSize();
        this.initialPlayerChipStack = gameConfig.initialPlayerChipStack();
        this.smallBlind = gameConfig.initialSmallBlind();
        this.bigBlind = gameConfig.initialBigBlind();
        for(int i = 0; i < maxTableSize; i++) {
            seats.add(new Seat(i));
        }
    }

    public Seat joinPlayer(String id, String name) {
        Player player = new Player(id, name, new ChipStack(initialPlayerChipStack));
        if(seats.size() == maxTableSize) {
            throw new IllegalStateException("Table is full");
        }
        Seat seat = seats.get(getEmptySeatPosition());
        seat.setPlayer(player);
        return seat;
    }

    public void removePlayer(int seatNo) {
        //TODO: Remove player logic
        //TODO: Identify efficient way to add & remove players
    }

    public void pickDealer() {
        if(dealerSeatNo == -1) {
            dealerSeatNo = RandomUtil.getRandomNumber(0, seats.size()-1);
        }
    }

    public void moveDealer() {
        dealerSeatNo = getNextActiveSeatPosition(dealerSeatNo);
        pickBlinds();
    }

    private void pickBlinds() {
        smallBlindSeatNo = getNextActiveSeatPosition(dealerSeatNo);
        bigBlindSeatNo =  getNextActiveSeatPosition(smallBlindSeatNo);
    }

    public void collectBlinds() {
        collectSmallBlind();
        collectBigBlind();
    }

    private void collectSmallBlind() {
        seats.get(smallBlindSeatNo).getPlayer().bet(smallBlind);
    }

    private void collectBigBlind() {
        seats.get(bigBlindSeatNo).getPlayer().bet(bigBlind);
    }

    public List<Player> getActivePlayers() {
        return seats.stream()
                .map(Seat::getPlayer)
                .filter(player -> player != null && (player.isActive() || player.isAllIn()))
                .toList();
    }

    public int getNextActiveSeatPosition(int currentSeatNo) {
        for(int i=1; i<seats.size(); i++) {
            int position = (currentSeatNo + i) % seats.size();
            if(seats.get(position).isOccupied()) {
                return position;
            }
        }
        return -1;
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
