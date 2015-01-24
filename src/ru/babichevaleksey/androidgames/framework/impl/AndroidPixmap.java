package ru.babichevaleksey.androidgames.framework.impl;

import android.graphics.Bitmap;
import ru.babichevaleksey.androidgames.framework.Graphics;
import ru.babichevaleksey.androidgames.framework.Pixmap;

/**
 * Created by aleksei on 21.12.13.
 */
public class AndroidPixmap implements Pixmap {

    Bitmap bitmap;
    Graphics.PixmapFormat format;

    public AndroidPixmap(Bitmap bitmap, Graphics.PixmapFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public Graphics.PixmapFormat getFormat() {
        return format;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }
}
