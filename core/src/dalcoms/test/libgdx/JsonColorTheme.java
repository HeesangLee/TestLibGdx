package dalcoms.test.libgdx;

import com.badlogic.gdx.utils.Array;

public class JsonColorTheme {
    private Array<Integer> colorThemeInt;

    public JsonColorTheme() {

    }

    public Array<Integer> getColorThemeInt() {
        return colorThemeInt;
    }

    public void setColorThemeInt(Array<Integer> colorThemeInt) {
        this.colorThemeInt = colorThemeInt;
    }

    public void addColorTheme(int colorInt) {
        if (colorThemeInt == null) {
            colorThemeInt = new Array<>();
        }
        colorThemeInt.add(colorInt);
    }
}
