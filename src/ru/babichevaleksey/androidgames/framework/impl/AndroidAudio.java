package ru.babichevaleksey.androidgames.framework.impl;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import ru.babichevaleksey.androidgames.framework.Audio;
import ru.babichevaleksey.androidgames.framework.Music;
import ru.babichevaleksey.androidgames.framework.Sound;

import java.io.IOException;

/**
 * Created by aleksei on 17.12.13.
 */
public class AndroidAudio implements Audio {

    AssetManager assets;
    SoundPool soundPool;

    public AndroidAudio(Activity activity){
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    @Override
    public Music newMusic(String filename) {
        try {
            AssetFileDescriptor descriptor = assets.openFd(filename);
            return new AndroidMusic(descriptor);
        } catch (IOException e) {
            throw new RuntimeException("Невозможно загрузить музыку '" + filename + "'");
        }
    }

    @Override
    public Sound newSound(String filename) {
        try {
            AssetFileDescriptor descriptor = assets.openFd(filename);
            int soundId = soundPool.load(descriptor, 0);
            return new AndroidSound(soundPool, soundId);
        } catch (IOException e) {
            throw new RuntimeException("Невозможно загрузить звук '" + filename + "'");
        }
    }
}
