package ru.babichevaleksey.androidgames.DroudInvaders;

import ru.babichevaleksey.androidgames.framework.Game;
import ru.babichevaleksey.androidgames.framework.Input;
import ru.babichevaleksey.androidgames.framework.Screen;
import ru.babichevaleksey.androidgames.framework.gl.Camera2D;
import ru.babichevaleksey.androidgames.framework.gl.SpriteBatcher;
import ru.babichevaleksey.androidgames.framework.impl.GLScreen;
import ru.babichevaleksey.androidgames.framework.math.OverlapTester;
import ru.babichevaleksey.androidgames.framework.math.Rectangle;
import ru.babichevaleksey.androidgames.framework.math.Vector2;

import javax.microedition.khronos.opengles.GL10;
import java.util.List;

/**
 * Created by Aleksei on 22.01.14.
 */
public class MainMenuScreen extends GLScreen {

    Camera2D guiCam;
    SpriteBatcher batcher;
    Vector2 touchPoint;
    Rectangle playBounds;
    Rectangle settingBounds;

    public MainMenuScreen(Game game) {
        super(game);

        guiCam = new Camera2D(glGraphics, 480, 320);
        batcher = new SpriteBatcher(glGraphics, 10);
        touchPoint = new Vector2();
        playBounds = new Rectangle(240 - 112, 100, 224, 32);
        settingBounds = new Rectangle(249 - 112, 100 - 32, 224, 32);
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        for(Input.TouchEvent event : touchEvents){
            if (event.type != Input.TouchEvent.TOUCH_UP)
                continue;

            guiCam.touchToWorld(touchPoint.set(event.x, event.y));
            if (OverlapTester.pointInRectangle(playBounds, touchPoint)){
                Assets.playSound(Assets.clickSound);
                game.setScreen(new GameScreen(game));
            }
            if (OverlapTester.pointInRectangle(settingBounds, touchPoint)){
                Assets.playSound(Assets.clickSound);
                game.setScreen(new SettingsScreen(game));
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGL();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();

        gl.glEnable(GL10.GL_TEXTURE_2D);

        batcher.beginBatch(Assets.background);
        batcher.drawSprite(240, 160, 480,320, Assets.backgroundRegion);
        batcher.endBatch();

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        batcher.beginBatch(Assets.items);
        batcher.drawSprite(240, 240, 384, 128, Assets.logoRegion);
        batcher.drawSprite(240, 100, 224, 64, Assets.menuRegion);
        batcher.endBatch();

        gl.glDisable(GL10.GL_BLEND);
        gl.glDisable(GL10.GL_TEXTURE_2D);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
