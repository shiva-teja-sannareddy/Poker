package in.sannareddy.poker.engine.game;

import in.sannareddy.poker.engine.flow.RoundManager;
import in.sannareddy.poker.engine.model.table.Seat;
import in.sannareddy.poker.engine.model.table.Table;
import lombok.Getter;

@Getter
public class PokerGame {
    private final Table table;
    private final GameConfig config;
    private GameState gameState = GameState.WAITING_FOR_PLAYERS;
    private RoundManager roundManager;

    public PokerGame(GameConfig config) {
        this.table = new Table(config);
        this.config = config;
    }

    public PokerGame(Table table, GameConfig config) {
        this.table = table;
        this.config = config;
    }

    public Seat playerJoined(String id, String name) {
        return table.joinPlayer(id, name);
    }

    public Seat playerLeft(int seatNo) {
        return table.removePlayer(seatNo);
    }

    public void startGame() {
        if (table.getSeats().stream().filter(Seat::isOccupied).count() < 2) {
            throw new IllegalStateException("Not enough players to start a game");
        }
        gameState = GameState.IN_PROGRESS;
        roundManager = new RoundManager(table, config);
        roundManager.startHand(table);
    }
}
