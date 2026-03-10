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
        return this.chips;
    }
    //TODO: Implement validation
    public int removeChips(int chips) throws Exception {
        if(this.chips < chips) {
            //TODO: Throw Exception - Not enough chips;
            throw new Exception("Not enough chips");
        }
        this.chips -= chips;
        return this.chips;
    }
}
