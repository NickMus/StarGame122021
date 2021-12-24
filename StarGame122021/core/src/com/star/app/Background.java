package com.star.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Background {
    private class Star {
        Vector2 position;
        Vector2 velocity;
        float scale;

        public Star() {
            this.position = new Vector2(MathUtils.random(-200, ScreenManager.SCREEN_WIDTH + 200),
                    MathUtils.random(-200, ScreenManager.SCREEN_HEIGHT + 200));
            this.velocity = new Vector2(MathUtils.random(-40, -5), 0);
            this.scale = Math.abs(velocity.x) / 40f * 0.8f;
        }

        public void update(float dt) {
            position.x += (velocity.x - starGame.getHero().getLastDisplacement().x * 15) * dt;
            position.y += (velocity.y - starGame.getHero().getLastDisplacement().y * 15) * dt;

            if (position.x < -200) {
                position.x = ScreenManager.SCREEN_WIDTH + 200;
                position.y = MathUtils.random(-200, ScreenManager.SCREEN_HEIGHT + 200);
            }
        }
    }

    private class Asteroid {
        Vector2 position;
        Vector2 velocity;

        public Asteroid() {
            this.position = new Vector2(MathUtils.random(-200, ScreenManager.SCREEN_WIDTH + 200),
                    MathUtils.random(-200, ScreenManager.SCREEN_HEIGHT + 200));
            this.velocity = new Vector2(MathUtils.random(-60, -5), 0);
        }

        public void update(float dt) {
            position.x += (velocity.x - starGame.getHero().getLastDisplacement().x * 10) * dt;
            position.y += (velocity.y - starGame.getHero().getLastDisplacement().y * 10) * dt;

            if (position.x < -200) {
                position.x = ScreenManager.SCREEN_WIDTH + 200;
                position.y = MathUtils.random(-200, ScreenManager.SCREEN_HEIGHT + 200);
            }
        }

    }

    private final int STAR_COUNT = 1000;
    private final int ASTEROID_COUNT = 3;
    private StarGame starGame;
    private Texture textureCosmos;
    private Texture textureStar;
    private Texture asteroid;
    private Star[] stars;
    private Asteroid[] asteroids;

    public Background(StarGame starGame) {
        this.starGame = starGame;
        this.textureCosmos = new Texture("bg.png");
        this.textureStar = new Texture("star16.png");
        this.asteroid = new Texture("asteroid.png");
        this.stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star();
        }
        this.asteroids = new Asteroid[ASTEROID_COUNT];
        for (int i = 0; i < asteroids.length; i++) {
            asteroids[i] = new Asteroid();
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(textureCosmos, 0, 0);
        for (int i = 0; i < stars.length; i++) {
            batch.draw(textureStar, stars[i].position.x - 8, stars[i].position.y - 8, 8, 8, 16, 16,
                    stars[i].scale, stars[i].scale, 0, 0, 0, 16, 16, false, false);
            if (MathUtils.random(0, 300) < 1) {
                batch.draw(textureStar, stars[i].position.x - 8, stars[i].position.y - 8, 8, 8, 16, 16,
                        stars[i].scale * 2, stars[i].scale * 2, 0, 0, 0, 16, 16, false, false);
            }

            for (int k = 0; k < asteroids.length; k++) {
                batch.draw(asteroid, asteroids[k].position.x - 128, asteroids[k].position.y - 128, 128, 128, 256, 256,
                        1, 1, 0, 0, 0, 256, 256, false, false);
            }
        }
    }

        public void update ( float dt){
            for (int i = 0; i < stars.length; i++) {
                stars[i].update(dt);
            }
            for (int k = 0; k < asteroids.length; k++) {
                asteroids[k].update(dt);
            }
        }
    }

