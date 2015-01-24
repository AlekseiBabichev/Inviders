package ru.babichevaleksey.androidgames.DroudInvaders;

import ru.babichevaleksey.androidgames.framework.Font;
import ru.babichevaleksey.androidgames.framework.Music;
import ru.babichevaleksey.androidgames.framework.Sound;
import ru.babichevaleksey.androidgames.framework.gl.*;
import ru.babichevaleksey.androidgames.framework.impl.GLGame;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Aleksei on 22.01.14.
 */
public class Assets {
    public static Texture background;
    public static TextureRegion backgroundRegion;
    public static Texture items;
    public static TextureRegion logoRegion;
    public static TextureRegion menuRegion;
    public static TextureRegion gameOverRegion;
    public static TextureRegion pausedRegion;
    public static TextureRegion settingRegion;
    public static TextureRegion touchRegion;
    public static TextureRegion accelRegion;
    public static TextureRegion touchEnableRegion;
    public static TextureRegion accelEnableRegion;
    public static TextureRegion soundRegion;
    public static TextureRegion soundEnableRegion;
    public static TextureRegion leftRegion;
    public static TextureRegion rightRegion;
    public static TextureRegion fireRegion;
    public static TextureRegion pauseButtonRegion;
    public static Font font;

    public static Texture explosionTexture;
    public static Animation explosionAnim;
    public static Vertices3 shipModel;
    public static Texture shipTexture;
    public static Vertices3 invaderModel;
    public static Texture invaderTexture;
    public static Vertices3 shotModel;
    public static Vertices3 shieldModel;

    public static Music music;
    public static Sound shotSound;
    public static Sound explosionSound;
    public static Sound clickSound;

    public static void load(GLGame game){
        background = new Texture(game, "background.jpg", true);
        backgroundRegion = new TextureRegion(background, 0, 0, 480, 320);
        items = new Texture(game, "items.png", true);
        logoRegion = new TextureRegion(items, 0, 256, 384, 128);
        menuRegion = new TextureRegion(items, 0, 128, 224, 64);
        gameOverRegion = new TextureRegion(items, 224, 128, 160, 64);
        pausedRegion = new TextureRegion(items, 0, 192, 160, 64);
        settingRegion = new TextureRegion(items, 0, 160, 224, 32);
        touchRegion = new TextureRegion(items, 0, 384, 64, 64);
        accelRegion = new TextureRegion(items, 64, 384, 64, 64);
        touchEnableRegion = new TextureRegion(items, 0, 448, 64, 64);
        accelEnableRegion = new TextureRegion(items, 64, 448, 64, 64);
        soundRegion = new TextureRegion(items, 128, 384, 64, 64);
        soundEnableRegion = new TextureRegion(items, 192, 384, 64, 64);
        leftRegion = new TextureRegion(items, 0, 0, 64, 64);
        rightRegion = new TextureRegion(items, 64, 0, 64, 64);
        fireRegion = new TextureRegion(items, 128, 0, 64, 64);
        pauseButtonRegion = new TextureRegion(items, 0, 64, 64, 64);
        font = new Font(items, 224, 0, 16, 16, 20);

        explosionTexture = new Texture(game, "explode.png", true);
        TextureRegion[] keyFrames = new TextureRegion[16];
        int frame = 0;
        for (int y = 0; y < 256; y += 64) {
            for (int x = 0; x < 256; x += 64) {
                keyFrames[frame++] = new TextureRegion(explosionTexture, x, y, 64, 64);
            }
        }
        explosionAnim = new Animation(0.1f, keyFrames);

        shipTexture = new Texture(game, "ship.png", true);
        shipModel = ObjLoader.load(game, "ship.obj");
        invaderTexture = new Texture(game, "invader.png", true);
        invaderModel = ObjLoader.load(game, "invader.obj");
        shotModel = ObjLoader.load(game, "shot.obj");
        shieldModel = ObjLoader.load(game, "shield.obj");

        music = game.getAudio().newMusic("music.mp3");
        music.setLooping(true);
        music.setVolume(0.5f);
        if (Settings.soundEnabled)
            music.play();
        clickSound = game.getAudio().newSound("click.ogg");
        explosionSound = game.getAudio().newSound("explosion.ogg");
        shotSound = game.getAudio().newSound("shot.ogg");
    }

    public static void reload(){
        background.reload();
        items.reload();explosionTexture.reload();
        shipTexture.reload();
        invaderTexture.reload();
        if (Settings.soundEnabled)
            music.play();
    }

    public static void playSound(Sound sound){
        if(Settings.soundEnabled)
            sound.play(1);
    }
}
