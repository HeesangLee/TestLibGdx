package dalcoms.test.libgdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import dalcoms.lib.libgdx.SpriteSimpleToggleButton;

public class ColorSelRadioButton extends SpriteSimpleToggleButton {
    Texture baseTexture;
    Array<Sprite> colorSprites;
    float colorPalletX = 75f;
    float colorPosOffset = 8.53f;

    public ColorSelRadioButton(Texture texture1, Texture texture2, Viewport viewport,
                               float locationX, float locationY, SpriteBatch spriteBatch) {
        super(texture1, texture2, viewport, locationX, locationY);
        setSpriteBatch(spriteBatch);
    }

    public ColorSelRadioButton setColorPalletTextures(Texture baseTexture, Texture colorTexture) {
        this.baseTexture = baseTexture;
        colorSprites = new Array<>();
        for (int i = 0; i < 5; i++) {
            colorSprites.add(new Sprite(colorTexture));
        }
        return this;
    }

    public void setColorThemeColors(Color c0, Color c1, Color c2, Color c3, Color c4) {
        Color[] colors = new Color[5];
        colors[0] = c0;
        colors[1] = c1;
        colors[2] = c2;
        colors[3] = c3;
        colors[4] = c4;
        int index = 0;
        for (Sprite sprite : colorSprites) {
            sprite.setColor(colors[index++]);
        }
    }

    @Override public void render(float delta) {
        super.render(delta);
        drawColorPallet();
    }

    private void drawColorPallet() {
        float x = getLocationX() + colorPalletX;
        float y = getLocationY() + getSprite().getHeight() / 2f - baseTexture.getHeight() / 2f;
        this.getSpriteBatch().draw(baseTexture, x, y);
        int i = 0;
        for (Sprite sprite : colorSprites) {
            sprite.setX(x + colorPosOffset + sprite.getWidth() * i++);
            sprite.setY(y + colorPosOffset);
            sprite.draw(getSpriteBatch());
        }
    }
}
