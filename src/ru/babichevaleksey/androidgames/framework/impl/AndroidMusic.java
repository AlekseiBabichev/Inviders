package ru.babichevaleksey.androidgames.framework.impl;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import ru.babichevaleksey.androidgames.framework.Music;

import java.io.IOException;

/**
 * Created by aleksei on 17.12.13.
 */
public class AndroidMusic implements Music, MediaPlayer.OnCompletionListener {

    MediaPlayer mediaPlayer;
    boolean isPrepared = false;


    public AndroidMusic(AssetFileDescriptor descriptor) {
        mediaPlayer = new MediaPlayer();
        try{
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(),
                    descriptor.getStartOffset(), descriptor.getLength());
            mediaPlayer.prepare();
            isPrepared = true;
            mediaPlayer.setOnCompletionListener(this);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load music");
        }
    }

    @Override
    public void play() {
        if (mediaPlayer.isPlaying()) return;
        try{
            synchronized (this){
                if (!isPrepared) mediaPlayer.prepare();
                mediaPlayer.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
        synchronized (this){
            isPrepared = false;
        }
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public void setLooping(boolean looping) {
        mediaPlayer.setLooping(looping);
    }

    @Override
    public void setVolume(float volume) {
        mediaPlayer.setVolume(volume, volume);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public boolean isStopped() {
        return !isPrepared;
    }

    @Override
    public boolean isLooping() {
        return mediaPlayer.isLooping();
    }

    @Override
    public void dispose() {
        if (mediaPlayer.isPlaying()) mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        synchronized (this){
            isPrepared = false;
        }
    }
}
