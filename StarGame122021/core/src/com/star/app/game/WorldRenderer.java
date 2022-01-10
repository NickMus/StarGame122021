package com.star.app.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import screen.utils.Assets;

public class WorldRenderer {
    private GameController gc;
    private SpriteBatch batch;
    private BitmapFont font32;
    private StringBuilder sb;
    private StringBuilder sb1;

    public WorldRenderer(GameController gc, SpriteBatch batch) {
        this.gc = gc;
        this.batch = batch;
        this.font32 = Assets.getInstance().getAssetManager().get("fonts/font32.ttf", BitmapFont.class);
        this.sb = new StringBuilder();
        this.sb1 = new StringBuilder();

    }

    public void render() {
        ScreenUtils.clear(0.0f, 0.1f, 0.5f, 1);
        batch.begin();
        gc.getBackground().render(batch);
        gc.getAsteroidController().render(batch);
        gc.getBulletController().render(batch);
        gc.getHero().render(batch);
        sb.setLength(0);
        sb1.setLength(0);
        sb1.append("HP: ").append(gc.getHero().getHp());
        sb.append("SCORE: ").append(gc.getHero().getScoreView());
        font32.draw(batch, sb, 20, 700);
        font32.draw(batch, sb1, 20, 660);
        batch.end();
    }
}
