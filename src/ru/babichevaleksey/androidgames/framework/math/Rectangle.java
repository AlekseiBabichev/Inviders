package ru.babichevaleksey.androidgames.framework.math;

/**
 * Created by aleksei on 09.01.14.
 */
public class Rectangle {
    public final Vector2 lowerLeft;
    public float width, height;

    public Rectangle(float x, float y, float width, float height){
        lowerLeft = new Vector2(x, y);
        this.width = width;
        this.height = height;
    }

}
