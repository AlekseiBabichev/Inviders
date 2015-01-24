package ru.babichevaleksey.androidgames.framework.math;

/**
 * Created by Aleksei on 21.01.14.
 */
public class DynamicGameObject3D extends GameObject3D{

    public final Vector3 velocity;
    public final Vector3 accel;

    public DynamicGameObject3D(float x, float y, float z, float radius) {
        super(x, y, z, radius);
        velocity = new Vector3();
        accel = new Vector3();
    }
}
