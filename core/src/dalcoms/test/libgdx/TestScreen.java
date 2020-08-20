package dalcoms.test.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import dalcoms.lib.libgdx.IGestureInput;
import dalcoms.lib.libgdx.Renderable;
import dalcoms.lib.libgdx.SpriteGameObject;

public class TestScreen implements Screen {
    private final MyGdxGame game;
    private OrthographicCamera camera;
    private Viewport viewport;

    private Array<Renderable> renderables;
    private Array<IGestureInput> gestureDetectables;
    private final String strDebug = "DebTestScreen";

    public TestScreen(MyGdxGame game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 1920);
        this.viewport = new FitViewport(1080, 1920, camera);
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
    }

    @Override
    public void show() {
        renderables = new Array<>();
        gestureDetectables = new Array<>();
        initGameObject();
    }

    @Override
    public void render(float delta) {
        draw(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void draw(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.getSpriteBatch().setProjectionMatrix(camera.combined);
        game.getSpriteBatch().begin();

        for (Renderable renderable : renderables) {
            renderable.render(delta);
        }

        game.getSpriteBatch().end();
    }

    private void initGameObject() {
        testRotate();
    }

    private void testRotate() {
        float radius = 300;
        SpriteGameObject center = getCircle();
        center.setCenterLocation(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2);
        renderables.add(center);
        renderables.add(getCircle(getAngularX(center.getCenterLocationX(), radius, 0),
                                  getAngularY(center.getCenterLocationY(), radius, 0)));
        for(int i=0;i<10;i++){
            renderables.add(getCircle(getAngularX(center.getCenterLocationX(), radius, i*36),
                                      getAngularY(center.getCenterLocationY(), radius, i*36)));
        }
    }


    private float getAngularX(float originX, float radius, float angle) {
        return (float) (originX + radius * Math.cos(Math.toRadians(angle)));
    }

    private float getAngularY(float originY, float radius, float angle) {
        return (float) (originY + radius * Math.sin(Math.toRadians(angle)));
    }

    private SpriteGameObject getCircle() {
        return new SpriteGameObject(new Texture(Gdx.files.internal("circle_50.png")),
                                    0, 0)
                .setSpriteBatch(this.game.getSpriteBatch());
    }

    private SpriteGameObject getCircle(float cx, float cy) {
        SpriteGameObject s = new SpriteGameObject(new Texture(Gdx.files.internal("circle_50.png")),
                                                  0, 0)
                .setSpriteBatch(this.game.getSpriteBatch());
        s.setCenterLocation(cx, cy);
        return s;
    }
}
