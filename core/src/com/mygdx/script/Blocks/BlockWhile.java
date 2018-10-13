package com.mygdx.script.Blocks;
import com.mygdx.script.LogicOps.LogicExp;

public class BlockWhile extends Block {

    private Block executable;

    private LogicExp statement;

    public BlockWhile(LogicExp le){
        this.statement = le;
    }

    public void functionality(){
        if (statement != null) {
            if (statement.getException() == null) {
                while (statement.evaluate()) {
                    executable.execute();
                }
            }else{
                statement.getException().announce();
            }
        }

    }

}
