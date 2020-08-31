package dalcoms.test.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import dalcoms.lib.libgdx.GameGestureListener;
import dalcoms.lib.libgdx.IGestureInput;
import dalcoms.lib.libgdx.Renderable;
import dalcoms.lib.libgdx.SpriteGameObject;
import dalcoms.lib.libgdx.VariationPerTime;

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
        setInputProcessor();
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
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);
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
//        testRotate();
//        testIntersection();
        testJson();
        testRadioButtons();
    }

    private void testRadioButtons() {
        ColorSelRadioButton btn =
                new ColorSelRadioButton(new Texture(Gdx.files.internal("circleStroke52px.png")),
                                        new Texture(Gdx.files.internal("radioSelected52.png")),
                                        viewport, 38, 100, game.getSpriteBatch())
                        .setColorPalletTextures(new Texture(Gdx.files.internal("rect230x68.png")),
                                                new Texture(Gdx.files.internal("rect43x51.png")));
        btn.setColorThemeColors(new Color(1, 0, 0, 1), new Color(0, 1, 0, 1),
                                new Color(0, 0, 1, 1), new Color(1, 1, 0, 1),
                                new Color(0, 1, 1, 1));
//        btn.setWidth(304);
//        btn.move(300, 300, 3f);
        ColorSelRadioButton btn2 =
                new ColorSelRadioButton(new Texture(Gdx.files.internal("circleStroke52px.png")),
                                        new Texture(Gdx.files.internal("radioSelected52.png")),
                                        viewport, 385, 100, game.getSpriteBatch())
                        .setColorPalletTextures(new Texture(Gdx.files.internal("rect230x68.png")),
                                                new Texture(Gdx.files.internal("rect43x51.png")));
        btn2.setColorThemeColors(new Color(1, 0, 0, 1), new Color(0, 1, 0, 1),
                                new Color(0, 0, 1, 1), new Color(1, 1, 0, 1),
                                new Color(0, 1, 1, 1));
        ColorSelRadioButton btn3 =
                new ColorSelRadioButton(new Texture(Gdx.files.internal("circleStroke52px.png")),
                                        new Texture(Gdx.files.internal("radioSelected52.png")),
                                        viewport, 731, 100, game.getSpriteBatch())
                        .setColorPalletTextures(new Texture(Gdx.files.internal("rect230x68.png")),
                                                new Texture(Gdx.files.internal("rect43x51.png")));
        btn3.setColorThemeColors(new Color(1, 0, 0, 1), new Color(0, 1, 0, 1),
                                 new Color(0, 0, 1, 1), new Color(1, 1, 0, 1),
                                 new Color(0, 1, 1, 1));

        renderables.add(btn,btn2,btn3);
        gestureDetectables.add(btn);
    }

    private void testJson() {
        JsonColorTheme jc = new JsonColorTheme();
        jc.addColorTheme(0xff0000ff);
        jc.addColorTheme(0xffff00ff);
        jc.addColorTheme(0xff00ffff);
        jc.addColorTheme(0x00ffffff);
        jc.addColorTheme(0x0000ffff);

        Json json = new Json();
        System.out.println(json.prettyPrint(jc));
    }

    private void testIntersection() {
        final SpriteGameObject sgoTriangle =
                new SpriteGameObject(new Texture(Gdx.files.internal("triangle.png")),
                                     249, 455).setSpriteBatch(this.game.getSpriteBatch());
        SpriteGameObject sgoCircle =
                new SpriteGameObject(new Texture(Gdx.files.internal("circle_200.png")),
                                     421, 477).setSpriteBatch(this.game.getSpriteBatch());

        final Circle c = new Circle(sgoCircle.getCenterLocationX(), sgoCircle.getLocationY(),
                                    sgoCircle.getWidth() / 2);
        final Polygon p =
                new Polygon(new float[]{0, 0,
                        sgoTriangle.getWidth() / 2f,
                        sgoTriangle.getHeight(),
                        sgoTriangle.getWidth(),
                        0});
        p.setOrigin(sgoTriangle.getWidth() / 2f, sgoTriangle.getHeight() / 2f);
        p.setPosition(sgoTriangle.getLocationX(), sgoTriangle.getLocationY());
        final Polygon p2 = new Polygon(
                circleToPolygon(sgoCircle.getWidth() / 2, sgoCircle.getHeight() / 2,
                                sgoCircle.getWidth() / 2, 36));
        p2.setPosition(sgoCircle.getLocationX(), sgoCircle.getLocationY());
//        sgoTriangle.moveX(1000f, 5f);
//        sgoTriangle.setEventListenerMoveX(new VariationPerTime.EventListener() {
//            @Override public void onUpdate(float v, float v1) {
//                p.setPosition(sgoTriangle.getLocationX(), sgoTriangle.getLocationY());
//                if (Intersector.overlapConvexPolygons(p, p2)) {
//                    sgoTriangle.setColor(1, 0, 0, 1);
//                } else {
//                    sgoTriangle.setColor(1, 1, 1, 1);
//                }
//            }
//
//            @Override public void onStart(float v) {
//
//            }
//
//            @Override public void onFinish(float v) {
//
//            }
//        });
        sgoTriangle.rotate(8000, 30);
        sgoTriangle.setEventListenerRotate(new VariationPerTime.EventListener() {
            @Override public void onUpdate(float v, float v1) {
                p.setRotation(sgoTriangle.getRotationAngle());
                if (Intersector.overlapConvexPolygons(p, p2)) {
                    sgoTriangle.setColor(1, 0, 0, 1);
                } else {
                    sgoTriangle.setColor(1, 1, 1, 1);
                }
            }

            @Override public void onStart(float v) {

            }

            @Override public void onFinish(float v) {

            }
        });

        renderables.add(sgoTriangle, sgoCircle);
    }

    private float[] circleToPolygon(float centerX, float centerY, float radius, int verticesNum) {
        float[] ret = new float[verticesNum * 2];
        double angle = 2 * Math.PI / verticesNum;
        for (int i = 0; i < verticesNum; i++) {
            ret[i * 2] = (float) (centerX + radius * Math.cos(angle * i));
            ret[i * 2 + 1] = (float) (centerY + radius * Math.sin(angle * i));
            Gdx.app.log("deg", "result " + ret[i] + "," + ret[i + 1]);
        }
        return ret;
    }

    private void testRotate() {
        float radius = 300;
        SpriteGameObject center = getCircle();
        center.setCenterLocation(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2);
        renderables.add(center);
        renderables.add(getCircle(getAngularX(center.getCenterLocationX(), radius, 0),
                                  getAngularY(center.getCenterLocationY(), radius, 0)));
        for (int i = 0; i < 10; i++) {
            renderables.add(getCircle(getAngularX(center.getCenterLocationX(), radius, i * 36),
                                      getAngularY(center.getCenterLocationY(), radius, i * 36)));
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

    Vector2 getNewTouchPoint(float x, float y) {
        return viewport.unproject(new Vector2(x, y));
    }

    private void setInputProcessor() {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new GestureDetector(new GameGestureListener() {
            @Override
            public boolean touchDown(float x, float y, int pointer, int button) {
                Vector2 newTouchPoint = getNewTouchPoint(x, y);
                for (IGestureInput iGestureInput : gestureDetectables) {
                    iGestureInput.touchDown(newTouchPoint.x, newTouchPoint.y, pointer, button);
                }
                return super.touchDown(x, y, pointer, button);
            }

            @Override
            public boolean tap(float x, float y, int count, int button) {
                Vector2 newTouchPoint = getNewTouchPoint(x, y);
                for (IGestureInput iGestureInput : gestureDetectables) {
                    iGestureInput.tap(newTouchPoint.x, newTouchPoint.y, count, button);
                }
                return super.tap(x, y, count, button);
            }

            @Override
            public boolean longPress(float x, float y) {
                Vector2 newTouchPoint = getNewTouchPoint(x, y);
                for (IGestureInput iGestureInput : gestureDetectables) {
                    iGestureInput.longPress(newTouchPoint.x, newTouchPoint.y);
                }
                return super.longPress(x, y);
            }

            @Override
            public boolean fling(float velocityX, float velocityY, int button) {
                return super.fling(velocityX, velocityY, button);
            }
        }));
        inputMultiplexer.addProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.BACK) {
                    Gdx.app.log(strDebug, "Input.Keys.BACK : Key down");
                    return true;
                }
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                if (keycode == Input.Keys.BACK) {
                    Gdx.app.log(strDebug, "Input.Keys.BACK : Key up");
                    return true;
                }
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                Vector2 newTouchPoint = getNewTouchPoint(screenX, screenY);
                for (IGestureInput iGestureInput : gestureDetectables) {
                    iGestureInput
                            .touchUp((int) newTouchPoint.x, (int) newTouchPoint.y, pointer, button);
                }
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                Vector2 newTouchPoint = getNewTouchPoint(screenX, screenY);
                for (IGestureInput iGestureInput : gestureDetectables) {
                    iGestureInput
                            .touchDragged((int) newTouchPoint.x, (int) newTouchPoint.y, pointer);
                }
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        });
        Gdx.input.setInputProcessor(inputMultiplexer);
    }
}
