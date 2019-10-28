package com.vvvgames.zombiebird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.vvvgames.screens.GameScreen;
import com.vvvgames.zbhelpers.AssetLoader;

public class ZBGame extends Game {

	private static final String TAG = ZBGame.class.getSimpleName();

	@Override
	public void create() {
		Gdx.app.log(TAG, "created");
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
