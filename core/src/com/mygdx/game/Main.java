package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.rendering.MainRenderer;
import com.mygdx.game.tick.Ticker;
import com.mygdx.game.world.World;

public class Main extends ApplicationAdapter {

	public static MainRenderer renderer;

	public static World world;

	public static Ticker tick;

	@Override
	public void create () {
        renderer = new MainRenderer();
        renderer.init();
        world = new World();
        world.init();
        tick = new Ticker();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        tick.tick();
	}
	
	@Override
	public void dispose () {
	    renderer.dispose();
	}
}
