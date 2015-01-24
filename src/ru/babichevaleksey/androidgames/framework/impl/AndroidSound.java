package ru.babichevaleksey.androidgames.framework.impl;

import android.media.SoundPool;
import ru.babichevaleksey.androidgames.framework.Sound;

/**
 * Created by aleksei on 17.12.13.
 */
public class AndroidSound implements Sound {

    int soundId;
    SoundPool soundPool;

    public AndroidSound(SoundPool soundPool, int soundId) {
        this.soundPool = soundPool;
        this.soundId = soundId;
    }

    @Override
    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    @Override
    public void dispose() {
        soundPool.unload(soundId);
    }
}
