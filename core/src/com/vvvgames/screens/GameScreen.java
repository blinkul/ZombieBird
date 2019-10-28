package com.vvvgames.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.vvvgames.gameworld.GameRenderer;
import com.vvvgames.gameworld.GameWorld;
import com.vvvgames.zbhelpers.InputHandler;

/**
1. GameScreen should only do one thing well
2. Updating the game objects should be the responsibility of a helper class {@link GameWorld}
3. Rendering these game objects should be the responsibility of another helper class {@link GameRenderer}
 */
public class GameScreen implements Screen {

    private static final String TAG = GameScreen.class.getSimpleName();

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime; //for how long the game is running

    public GameScreen() {
        Gdx.app.log(TAG, "Attached");

        //game is 136 wu in width, but the height may change depending on the device
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);

        world = new GameWorld(midPointY);
        renderer = new GameRenderer(world, (int) gameHeight, midPointY);

        Gdx.input.setInputProcessor(new InputHandler(world.getBird()));
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(TAG, "resizing");
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "show called");
    }

    @Override
    public void pause() {
        Gdx.app.log(TAG, "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log(TAG, "resume called");
    }

    @Override
    public void hide() {
        Gdx.app.log(TAG, "hide called");
    }

    @Override
    public void dispose() {

    }
}
