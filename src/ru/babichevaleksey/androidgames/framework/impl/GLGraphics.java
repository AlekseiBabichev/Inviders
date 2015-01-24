package ru.babichevaleksey.androidgames.framework.impl;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by aleksei on 26.12.13.
 */
public class GLGraphics {
    GLSurfaceView glView;
    GL10 gl;

    public GLGraphics(GLSurfaceView glView) {
        this.glView = glView;
    }

    public GL10 getGL() {
        return gl;
    }

    public void setGl(GL10 gl) {
        this.gl = gl;
    }

    public int getWidth(){
        return glView.getWidth();
    }

    public int getHeight(){
        return glView.getHeight();
    }
}
