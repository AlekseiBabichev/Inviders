package ru.babichevaleksey.androidgames.framework.impl;

import android.content.res.AssetManager;
import android.os.Environment;
import ru.babichevaleksey.androidgames.framework.FileIO;

import java.io.*;

/**
 * Created by aleksei on 17.12.13.
 */
public class AndroidFileIO implements FileIO{

    AssetManager asset;
    String externalStoragePath;

    public AndroidFileIO(AssetManager asset) {
        this.asset = asset;
        this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    @Override
    public InputStream readAsset(String fileName) throws IOException {
        return asset.open(fileName);
    }

    @Override
    public InputStream readFile(String fileName) throws IOException {
        return new FileInputStream(externalStoragePath + fileName);
    }

    @Override
    public OutputStream writeFile(String fileName) throws IOException {
        return new FileOutputStream(externalStoragePath + fileName);
    }
}
