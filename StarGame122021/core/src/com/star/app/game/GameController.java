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
        this.asteroidController = new AsteroidController(this);
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
            Bullet bullet = bulletController.getActiveList().get(i);
            for (int j = 0; j < asteroidController.getActiveList().size(); j++) {
                Asteroid asteroid = asteroidController.getActiveList().get(j);
                if (asteroid.getHitArea().contains(bullet.getPosition())) {
                    bullet.deactivate();
                    if (asteroid.takeDamage(1)) {
                        hero.addScore(asteroid.getHpMax() * 100);
                    }
                    break;
                }
            }
        }
        for (int j = 0; j < asteroidController.getActiveList().size(); j++) {
            Asteroid asteroid = asteroidController.getActiveList().get(j);
            if (getHero().getHitArea().contains(asteroid.getPosition())) {
                asteroid.takeDamage(1);
                hero.takeDamage(10);
                System.out.println(hero.getHp());
                if (asteroid.takeDamage(1)) {
                    hero.addScore(asteroid.getHpMax() * 100);
                }
            }
        }
    }
}
