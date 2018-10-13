package com.mygdx.script.Blocks;
import com.mygdx.script.LogicOps.LogicExp;


public class BlockIF extends Block {
    private Block TBranch;
    private Block FBranch;

    private LogicExp statement;

    public BlockIF(LogicExp le){
        this.statement = le;
    }

    @Override
    protected final void functionality (){
        if (statement != null) {
            if (statement.getException() == null) {
                if (statement.evaluate() == true) {
                    if (TBranch != null) {
                        TBranch.execute();
                    }
                } else {
                    if (FBranch != null) {
                        FBranch.execute();
                    }
                }
            }else{
                statement.getException().announce();
            }
        }

    };

    public void setTBranch(Block TBranch) {
        this.TBranch = TBranch;
    }

    public void setFBranch(Block FBranch) {
        this.FBranch = FBranch;
    }
}
