package de.emg_haar.schafkopfdeluxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by Sebi on 09.03.17.
 */

public class MainMenuScreen extends ScreenAdapter {

    private OrthographicCamera guiCam;
    private Schafkopf game;
    private Stage stage;

    public MainMenuScreen(Schafkopf game) {
        this.game = game;

        guiCam = new OrthographicCamera(320, 480);
        guiCam.position.set(320 / 2, 480 / 2, 0);
        stage = new Stage(new ScreenViewport(guiCam));
        Gdx.input.setInputProcessor(stage);
        stage.addActor(new MainMenuActor());
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private class MainMenuActor extends Actor {
        TextureRegion region;

        public MainMenuActor () {
            region = new TextureRegion();
        }

        @Override
        public void draw (Batch batch, float parentAlpha) {
            Color color = getColor();
            batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
            batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }
}
