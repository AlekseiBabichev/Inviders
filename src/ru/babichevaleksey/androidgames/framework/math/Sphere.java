package ru.babichevaleksey.androidgames.framework.math;

/**
 * Created by Aleksei on 21.01.14.
 */
public class Sphere {
    public final Vector3 center = new Vector3();
    public float radius;

    public Sphere(float x, float y, float z, float radius){
        this.center.set(x, y, z);
        this.radius = radius;
    }

}
