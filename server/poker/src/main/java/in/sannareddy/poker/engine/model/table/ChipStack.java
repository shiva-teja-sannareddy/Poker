package in.sannareddy.poker.engine.model.table;

import lombok.Getter;

@Getter
public class ChipStack {
    private int chips;

    public ChipStack(int chips) {
        this.chips = chips;
    }
    public int addChips(int chips) {
        this.chips += chips;
        return chips;
    }
    //TODO: Implement validation
    public int removeChips(int chips) {
        this.chips -= Math.min(this.chips, chips);
        return chips;
    }
}
