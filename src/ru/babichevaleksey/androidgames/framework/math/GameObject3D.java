package ru.babichevaleksey.androidgames.framework.math;

import ru.babichevaleksey.androidgames.framework.math.Sphere;
import ru.babichevaleksey.androidgames.framework.math.Vector3;

/**
 * Created by Aleksei on 21.01.14.
 */
public class GameObject3D {
    public final Vector3 position;
    public final Sphere sphere;

    public GameObject3D(float x, float y, float z, float radius) {
        this.sphere = new Sphere(x, y, z, radius);
        this.position = sphere.center;
    }
}
