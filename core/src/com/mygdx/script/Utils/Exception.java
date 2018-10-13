package com.mygdx.script.Utils;

public class Exception {
    private String type;
    public Exception(String type){
        this.type = type;
    }

    public void announce(){
        System.out.println(type);
    }
}
