package ru.babichevaleksey.androidgames.framework.math;

/**
 * Created by aleksei on 09.01.14.
 */
public class Circle {
    public final Vector2 center = new Vector2();
    public float radius;

    public Circle(float x, float y, float radius){
        this.center.set(x, y);
        this.radius = radius;
    }
}
