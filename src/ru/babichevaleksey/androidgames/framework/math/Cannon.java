package ru.babichevaleksey.androidgames.framework.math;

/**
 * Created by aleksei on 10.01.14.
 */
public class Cannon extends GameObject{

    public float angle;

    public Cannon(float x, float y, float width, float height) {
        super(x, y, width, height);
        angle = 0;
    }
}
