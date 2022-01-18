package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.star.app.game.GameController;
import com.star.app.game.WorldRenderer;
import screen.utils.Assets;

public class GameScreen extends AbstractScreen {
    private GameController gc;
    private WorldRenderer worldRenderer;

    public GameScreen(SpriteBatch batch) {
        super(batch);
    }

    @Override
    public void show() {
        Assets.getInstance().loadAssets(ScreenManager.ScreenType.GAME);
        this.gc = new GameController(batch);
        this.worldRenderer = new WorldRenderer(gc, batch);
    }

    @Override
    public void render(float delta) {
        makePause(delta);
        gc.update(delta);
        worldRenderer.render();
    }

    public float makePause(float delta) {
        boolean isPaused;
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            isPaused = false;
            System.out.println("backspace");
            delta = 0;
        }else {
            delta = Gdx.graphics.getDeltaTime();
        }
        return delta;
    }
}
