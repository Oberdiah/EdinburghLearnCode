package com.mygdx.script.TestScript;
import com.mygdx.script.Blocks.*;
import com.mygdx.script.Blocks.Actions.Action;
import com.mygdx.script.LogicOps.*;
import com.mygdx.script.Utils.Instruction;
import com.mygdx.script.Utils.InstructionTypes;

public class Main {

    public static void main(String[] args) {
        LogicExp condition = new LogicAnd();
        ((LogicAnd) condition).setLeft(new LogicTerminal(new Instruction(InstructionTypes.IS_OTHERTHING)));
        ((LogicAnd) condition).setRight(new LogicTerminal(new Instruction(InstructionTypes.IS_SOMETHING)));
        BlockIF if1 = new BlockIF(condition);
        Instruction a = new Instruction(InstructionTypes.PLACE_BLOCK);
        Instruction b = new Instruction(InstructionTypes.VAR_DECLARE);
        Block action1 = new Action(a);
        Block action2 = new Action(b);

        if1.setTBranch(action1);
        if1.setFBranch(action2);

        if1.execute();
    }
}
