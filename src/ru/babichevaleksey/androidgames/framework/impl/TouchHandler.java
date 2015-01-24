package ru.babichevaleksey.androidgames.framework.impl;

import android.view.View;
import ru.babichevaleksey.androidgames.framework.Input;

import java.util.List;

/**
 * Created by aleksei on 21.12.13.
 */
public interface TouchHandler extends View.OnTouchListener {
    public boolean isTouchDown(int pointer);
    public int getTouchX(int pointer);
    public int getTouchY(int pointer);
    public List<Input.TouchEvent> getTouchEvent();

}
