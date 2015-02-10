package ru.babichevaleksey.androidgames.Invaders;

import ru.babichevaleksey.androidgames.framework.FileIO;

import java.io.*;

public class Settings {
    public static boolean soundEnabled = true;
    public static boolean touchEnable = true;
    public final static String file = ".invader";
    public static void load(FileIO files) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new
                    InputStreamReader(files.readFile(file)));
            soundEnabled = Boolean.parseBoolean(in.readLine());
            touchEnable = Boolean.parseBoolean(in.readLine());
        } catch(IOException e) {
            // :( Не страшно, у нас есть значения по умолчанию
        } catch (NumberFormatException e) {
            // :/ Не страшно, опять же воспользуемся стандартными значениями
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException ignored) {
            }
        }
    }
    public static void save(FileIO files) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    files.writeFile(file)));
            out.write(Boolean.toString(soundEnabled));
            out.write("\n");
            out.write(Boolean.toString(touchEnable));
        } catch (IOException ignored) {
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException ignored) {
            }
        }
    }
}

