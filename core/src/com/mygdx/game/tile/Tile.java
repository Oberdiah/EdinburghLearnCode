package com.mygdx.game.tile;

public class Tile {
    public enum TileType {
        Rock,
        Sky
    }

    public  static  boolean Blocks(Tile.TileType t){
        switch (t){

            case Rock:
                return  true;

            case Sky:
            default:
                return false;

        }

    }



}
