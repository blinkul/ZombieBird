package com.vvvgames.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.vvvgames.gameobjects.Bird;
import com.vvvgames.gameobjects.ScrollHandler;
import com.vvvgames.zbhelpers.AssetLoader;

/**
 * Will update the game's objects
 */
public class GameWorld {

    private static final String TAG = GameWorld.class.getSimpleName();

    private Bird bird;
    private ScrollHandler scroller;
    private Rectangle ground;
    private int score;

    public GameWorld (int midPointY) {
        bird = new Bird(33, midPointY - 5, 17, 12);
        //The grass should start at 66 pixels below the midPointY
        scroller = new ScrollHandler(this,midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 136, 11);
    }

    public void update(float delta) {

        // Add a delta cap so that if our game takes too long
        // to update, we will not break our collision detection.
        if (delta > .15f) {
            delta = .15f;
        }

        bird.update(delta);
        scroller.update(delta);

        if (scroller.collides(bird) && bird.isAlive()) {
            // Clean up on game over
            scroller.stop();
            bird.die();
            AssetLoader.dead.play();
        }

        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            scroller.stop();
            bird.die();
            bird.decelerate();
        }
    }

    public Bird getBird() {
        return bird;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }

}
