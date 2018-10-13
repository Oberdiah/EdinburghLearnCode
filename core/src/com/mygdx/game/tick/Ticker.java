package com.mygdx.game.tick;

import com.mygdx.game.Main;

public class Ticker {
    public void tick() {
        Main.world.step(1/60f, 6, 2);
    }
}
