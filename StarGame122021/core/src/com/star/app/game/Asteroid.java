package com.star.app.game;

import com.badlogic.gdx.math.Vector2;
import com.star.app.helpers.Poolable;
import screen.ScreenManager;

public class Asteroid implements Poolable {

    private Vector2 position;
    private Vector2 velocity;
    private boolean active;


    @Override
    public boolean isActive() {
        return active;
    }
    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Asteroid() {
        this.position = new Vector2(0, 0);
        this.velocity = new Vector2(0, 0);
        this.active = false;
    }

    public void deactivate() {
        active = false;
    }

    public void update(float dt) {
        position.mulAdd(velocity, dt);
        if (position.x <= -256 || position.y <= -256 || position.x >= ScreenManager.SCREEN_WIDTH + 200 ||
                position.y >= ScreenManager.SCREEN_HEIGHT + 200) {
            deactivate();
        }
    }

    public void activate(float x, float y, float vx, float vy) {
        position.set(x, y);
        velocity.set(vx, vy);
        active = true;
    }
}
