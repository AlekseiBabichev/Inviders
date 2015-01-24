package ru.babichevaleksey.androidgames.DroudInvaders;

import ru.babichevaleksey.androidgames.framework.math.GameObject3D;

/**
 * Created by Aleksei on 26.01.14.
 */
public class Shield extends GameObject3D {

    static float SHIELD_RADIUS = 0.5f;

    public Shield(float x, float y, float z) {
        super(x, y, z, SHIELD_RADIUS);
    }
}
