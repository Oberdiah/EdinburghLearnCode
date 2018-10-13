package com.mygdx.game.tile;

import com.badlogic.gdx.physics.box2d.Fixture;

import java.util.HashMap;

public class Tile {
    public enum TileType {
        ROCK("Rock", true),
        SKY("Sky", false);

        String string;
        boolean solid;
        public static HashMap<String, TileType> names = new HashMap<>();

        TileType(String s, boolean solid) {
            string = s;
            this.solid = solid;

        }

        public static TileType getTypeFromString(String s) {
            return names.get(s);
        }

        static {
            for (TileType t : TileType.values()) {
                names.put(t.string, t);
            }
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
