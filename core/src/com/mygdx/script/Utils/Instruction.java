package com.mygdx.script.Utils;

public class Instruction {
    private InstructionTypes type;

    public Instruction(InstructionTypes type){
        this.type = type;
    }

    public Boolean evaluate(){
        System.out.println(type.toString());
        return true;
        //TODO: connect it with actual game
    }



}
