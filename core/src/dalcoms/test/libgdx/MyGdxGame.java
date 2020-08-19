package dalcoms.test.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        testJson();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

    private void testJson() {
        Level level = new Level();
        level.setAngularVelocity(1);
        level.setAngularVelocityMin(0.1f);
        level.setAngularVelocityMax(1.5f);
        level.setLevelNum(3);
        level.setRadius(54.3f);
        level.setRotatingType(Level.Rotatingtype.CONSTANT);
        level.setRotationDirection(Level.RotationDirection.CW);
        level.setShootingVelocity(3.3f);

        Json json = new Json();
        System.out.println(json.prettyPrint(level));
    }
}
