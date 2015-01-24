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
 * Created by Aleksei on 23.01.14.
 */
public class SettingsScreen extends GLScreen {

    Camera2D guiCam;
    SpriteBatcher batcher;
    Vector2 touchPoint;
    Rectangle touchBounds;
    Rectangle accelBounds;
    Rectangle soundBounds;
    Rectangle backBounds;

    public SettingsScreen(Game game) {
        super(game);
        guiCam = new Camera2D(glGraphics, 480, 320);
        batcher = new SpriteBatcher(glGraphics, 10);
        touchPoint = new Vector2();
        touchBounds = new Rectangle(120 - 32, 160 - 32, 64, 64);
        accelBounds = new Rectangle(240 - 32, 160 - 32, 64, 64);
        soundBounds = new Rectangle(360 - 32, 160 - 32, 64, 64);
        backBounds = new Rectangle(32, 32, 64, 64);
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> events = game.getInput().getTouchEvents();
        for(Input.TouchEvent event : events){
            if (event.type != Input.TouchEvent.TOUCH_UP)
                continue;

            guiCam.touchToWorld(touchPoint.set(event.x, event.y));
            if (OverlapTester.pointInRectangle(touchBounds, touchPoint)){
                Assets.playSound(Assets.clickSound);
                Settings.touchEnable = true;
                Settings.save(game.getFileIO());
            }
            if (OverlapTester.pointInRectangle(accelBounds, touchPoint)){
                Assets.playSound(Assets.clickSound);
                Settings.touchEnable = false;
                Settings.save(game.getFileIO());
            }
            if (OverlapTester.pointInRectangle(soundBounds, touchPoint)){
                Settings.soundEnabled = !Settings.soundEnabled;
                Assets.playSound(Assets.clickSound);
                if (Settings.soundEnabled)
                    Assets.music.play();
                else Assets.music.pause();
            }
            if (OverlapTester.pointInRectangle(backBounds, touchPoint)){
                Assets.playSound(Assets.clickSound);
                game.setScreen(new MainMenuScreen(game));
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
        batcher.drawSprite(240, 160, 480, 320, Assets.backgroundRegion);
        batcher.endBatch();

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        batcher.beginBatch(Assets.items);
        batcher.drawSprite(240, 280, 224, 32, Assets.settingRegion);
        batcher.drawSprite(120, 160, 64, 64, Settings.touchEnable ? Assets.touchEnableRegion : Assets.touchRegion);
        batcher.drawSprite(240, 160, 64, 64, Settings.touchEnable ? Assets.accelRegion : Assets.accelEnableRegion);
        batcher.drawSprite(360, 160, 64, 64, Settings.soundEnabled ? Assets.soundEnableRegion : Assets.soundRegion);
        batcher.drawSprite(32, 32, 64, 64, Assets.leftRegion);
        batcher.endBatch();

        gl.glDisable(GL10.GL_BLEND);
        gl.glDisable(GL10.GL_TEXTURE_2D);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
