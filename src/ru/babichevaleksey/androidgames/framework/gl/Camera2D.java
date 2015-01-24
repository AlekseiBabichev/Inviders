package ru.babichevaleksey.androidgames.framework.gl;

import ru.babichevaleksey.androidgames.framework.impl.GLGraphics;
import ru.babichevaleksey.androidgames.framework.math.Vector2;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by aleksei on 11.01.14.
 */
public class Camera2D {
    public final Vector2 position;
    public float zoom;
    public final float frustrumWidth;
    public final float frustrumHeight;
    final GLGraphics glGraphics;

    public Camera2D(GLGraphics glGraphics, float frustumWidth, float frustumHeight) {
        this.glGraphics = glGraphics;
        this.frustrumHeight = frustumHeight;
        this.frustrumWidth = frustumWidth;
        this.position = new Vector2(frustumWidth / 2, frustumHeight / 2);
        this.zoom = 1.0f;
    }

    public void setViewportAndMatrices(){
        GL10 gl = glGraphics.getGL();
        gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(position.x - frustrumWidth * zoom / 2,
                    position.x + frustrumWidth * zoom / 2,
                    position.y - frustrumHeight * zoom / 2,
                    position.y + frustrumHeight * zoom / 2,
                    1, -1);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void touchToWorld(Vector2 touch){
        touch.x = (touch.x / (float) glGraphics.getWidth()) * frustrumWidth * zoom;
        touch.y = (1 - touch.y / (float) glGraphics.getHeight()) * frustrumHeight * zoom;
        touch.add(position).sub(frustrumWidth * zoom / 2, frustrumHeight * zoom / 2);
    }
}
