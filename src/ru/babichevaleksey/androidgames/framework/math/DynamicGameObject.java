package ru.babichevaleksey.androidgames.framework.math;

/**
 * Created by aleksei on 10.01.14.
 */
public class DynamicGameObject extends GameObject {

    public final Vector2 velocity;
    public final Vector2 accel;

    public DynamicGameObject(float x, float y, float width, float height) {
        super(x, y, width, height);
        velocity = new Vector2();
        accel = new Vector2();
    }


}
