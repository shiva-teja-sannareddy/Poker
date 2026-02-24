package in.sannareddy.poker.engine.game;

import in.sannareddy.poker.engine.model.table.Seat;
import in.sannareddy.poker.engine.model.table.Table;

public class PokerGame {
    private final Table table;
    private final GameConfig config;
    private Hand currentHand;
    private GameState gameState = GameState.WAITING_FOR_PLAYERS;

    public PokerGame(Table table, GameConfig config) {
        this.table = table;
        this.config = config;
    }

    public void startGame() {
        if (table.getSeats().stream().filter(Seat::isOccupied).count() < 2) {
            throw new IllegalStateException("Not enough players to start game");
        }
        gameState = GameState.IN_PROGRESS;
        //TODO: Implement startHand();
    }
}
