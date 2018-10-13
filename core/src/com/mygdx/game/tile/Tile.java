package com.mygdx.game.tile;

import com.badlogic.gdx.physics.box2d.Fixture;

public class Tile {
    public enum TileType {
        ROCK("Rock"),
        SKY("Sky");

        String string;

        TileType(String s) {
            string = s;
        }

        public static TileType getTypeFromString(String s) {
            for (TileType t : TileType.values()) {
                if (t.string.equals(s)) {
                    return t;
                }
            }
            return null;
        }
    }

    public TileType type;

    public Tile(TileType t) {
        type = t;
    }

    public Fixture collision = null;

    public  static  boolean Blocks(TileType t){
        switch (t){

            case ROCK:
                return  true;

            case SKY:
            default:
                return false;

        }

    }



}
