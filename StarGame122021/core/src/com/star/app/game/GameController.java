package com.star.app.game;

public class GameController {
    private Background background;
    private BulletController bulletController;
    private Hero hero;
    private AsteroidController asteroidController;

    public AsteroidController getAsteroidController() {
        return asteroidController;
    }

    public Hero getHero() {
        return hero;
    }

    public Background getBackground() {
        return background;
    }

    public BulletController getBulletController() {
        return bulletController;
    }

    public GameController() {
        this.background = new Background(this);
        this.hero = new Hero(this);
        this.bulletController = new BulletController();
        this.asteroidController = new AsteroidController();
    }

    public void update(float dt) {
        background.update(dt);
        hero.update(dt);
        bulletController.update(dt);
        asteroidController.update(dt);
        checkCollisions();
    }

    private void checkCollisions() {
        for (int i = 0; i < bulletController.getActiveList().size(); i++) {
            Bullet b = bulletController.getActiveList().get(i);
            for (int j = 0; j < asteroidController.getActiveList().size(); j++) {
                Asteroid asteroid = asteroidController.getActiveList().get(j);
                if (asteroid.getPosition().dst(b.getPosition()) < 128.0f) {
                    b.deactivate();
                    asteroid.deactivate();
//                //
//            }
                }
            }
        }
    }
}
