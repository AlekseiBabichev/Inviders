package ru.babichevaleksey.androidgames.DroudInvaders;

import ru.babichevaleksey.androidgames.framework.math.DynamicGameObject3D;
import ru.babichevaleksey.androidgames.framework.math.Sphere;

/**
 * Created by Aleksei on 26.01.14.
 */
public class Shot extends DynamicGameObject3D {

    static float SHOT_VELOCITY = 10;
    static float SPHERE_RADIUS = 0.1f;

    public Shot(float x, float y, float z, float velocityZ) {
        super(x, y, z, SPHERE_RADIUS);
        velocity.z = velocityZ;
    }

    public void update(float deltaTime){
        position.z += velocity.z * deltaTime;
        sphere.center.set(position);
    }
}
