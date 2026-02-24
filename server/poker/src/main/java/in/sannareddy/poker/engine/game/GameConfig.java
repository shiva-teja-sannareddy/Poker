package in.sannareddy.poker.engine.game;

/**
 * TODO: Enhancements
 * Round Timings
 * Increment blinds each round
 * Different Poker Variants - Texas Holdem, PL4, PL5
 * Limit Type - NO Limit, POT Limit
 * Ante support
 */
public record GameConfig(int tableSize, int initialSmallBlind, int initialBigBlind) {
}
