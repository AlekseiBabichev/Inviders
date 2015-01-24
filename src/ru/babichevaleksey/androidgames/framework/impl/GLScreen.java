package ru.babichevaleksey.androidgames.framework.impl;

import ru.babichevaleksey.androidgames.framework.Game;
import ru.babichevaleksey.androidgames.framework.Screen;

/**
 * Created by aleksei on 15.01.14.
 */
public class GLScreen extends Screen{

    protected final GLGraphics glGraphics;
    protected final GLGame glGame;

    public GLScreen(Game game) {
        super(game);
        glGame = (GLGame) game;
        glGraphics = ((GLGame) game).getGLGraphics();
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void present(float deltaTime) {

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
