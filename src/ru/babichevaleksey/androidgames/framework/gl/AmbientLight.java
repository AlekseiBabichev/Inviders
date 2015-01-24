package ru.babichevaleksey.androidgames.framework.gl;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Aleksei on 18.01.14.
 */
public class AmbientLight {
    float[] ambientColor = new float[] {0.2f, 0.2f, 0.2f, 1};

    public void setColor(float r, float g, float b, float a){
        ambientColor[0] = r;
        ambientColor[1] = g;
        ambientColor[2] = b;
        ambientColor[3] = a;
    }

    public void enable(GL10 gl){
        gl.glLightModelfv(GL10.GL_LIGHT_MODEL_AMBIENT, ambientColor, 0);
    }
}
