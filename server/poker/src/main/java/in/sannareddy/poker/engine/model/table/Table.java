package in.sannareddy.poker.engine.model.table;

import in.sannareddy.poker.engine.flow.ChipManager;
import in.sannareddy.poker.engine.game.GameConfig;
import in.sannareddy.poker.engine.model.card.Card;
import in.sannareddy.poker.engine.model.player.Player;
import in.sannareddy.poker.engine.util.RandomUtil;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class Table {
    private final GameConfig gameConfig;
    private final List<Seat> seats = new ArrayList<>();
    private final List<Card> communityCards = new ArrayList<>(5);
    private final List<Pot> pots = new ArrayList<>(3);
    private int dealerSeatNo = -1;
    private int smallBlindSeatNo = -1;
    private int bigBlindSeatNo = -1;

    public Table(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    public Seat joinPlayer(Player player) {
        if(seats.size() == getGameConfig().tableSize()) {
            //TODO: Throw Exception - table full
            return null;
        }
        Seat seat = new Seat(seats.size(), player);
        this.seats.add(seat);
        return seat;
    }

    public void pickDealer() {
        if(dealerSeatNo == -1) {
            dealerSeatNo = RandomUtil.getRandomNumber(0, seats.size()-1);
        }
        while(!seats.get(dealerSeatNo).isActive()) {
            dealerSeatNo = getNextActiveSeatPosition(dealerSeatNo);
        }
    }

    public void pickBlinds() {
        smallBlindSeatNo = getNextActiveSeatPosition(dealerSeatNo);
        bigBlindSeatNo =  getNextActiveSeatPosition(smallBlindSeatNo);
    }

    public void collectBlinds() throws Exception {
        Pot initialPot = pots.get(0);

        Seat smallBlind = seats.get(smallBlindSeatNo);
        ChipManager.transferChips(smallBlind.getPlayer().getChipStack(), initialPot.getChipStack(), gameConfig.initialSmallBlind());

        Seat bigBlind = seats.get(bigBlindSeatNo);
        ChipManager.transferChips(bigBlind.getPlayer().getChipStack(), initialPot.getChipStack(), gameConfig.initialBigBlind());
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

    public void resetCommunityCards() {
        communityCards.clear();
    }

    public void resetPots() {
        pots.clear();
        pots.add(new Pot());
    }
}
