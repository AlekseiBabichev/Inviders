package ru.babichevaleksey.androidgames.framework;

import ru.babichevaleksey.androidgames.framework.gl.SpriteBatcher;
import ru.babichevaleksey.androidgames.framework.gl.Texture;
import ru.babichevaleksey.androidgames.framework.gl.TextureRegion;

/**
 * Created by aleksei on 14.01.14.
 */
public class Font {
    public final Texture texture;
    public final int glyphWidth;
    public final int glyphHeight;
    public final TextureRegion[] glyphs = new TextureRegion[96];

    public Font(Texture texture, int offsetX, int offsetY, int glyphPerRow, int glyphWidth, int glyphHeight) {
        this.texture = texture;
        this.glyphWidth = glyphWidth;
        this.glyphHeight = glyphHeight;
        int x = offsetX;
        int y = offsetY;
        for (int i = 0; i < 96; i++) {
            glyphs[i] = new TextureRegion(texture, x, y, glyphWidth, glyphHeight);
            x += glyphWidth;
            if (x == offsetX + glyphPerRow * glyphWidth){
                x = offsetX;
                y += glyphHeight;
            }
        }
    }

    public void drawText(SpriteBatcher batcher, String text, float x, float y){
        int len = text.length();
        for (int i = 0; i < len; i++) {
            int c = text.charAt(i) - ' ';
            if (c < 0 || c > glyphs.length - 1){
                continue;
            }

            TextureRegion glyph = glyphs[c];
            batcher.drawSprite(x, y, glyphWidth, glyphHeight, glyph);
            x += glyphWidth;
        }
    }
}