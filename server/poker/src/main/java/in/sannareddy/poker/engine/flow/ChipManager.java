package in.sannareddy.poker.engine.flow;

import in.sannareddy.poker.engine.model.table.ChipStack;

public class ChipManager {
    public static void transferChips(ChipStack from, ChipStack to, int chips) throws Exception {
        from.removeChips(chips);
        to.addChips(chips);
    }
}
