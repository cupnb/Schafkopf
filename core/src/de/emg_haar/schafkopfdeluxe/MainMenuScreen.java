package de.emg_haar.schafkopfdeluxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by Sebi on 09.03.17.
 */

public class MainMenuScreen extends ScreenAdapter {

    private OrthographicCamera guiCam;
    private Schafkopf game;

    public MainMenuScreen(Schafkopf game) {
        this.game = game;

        guiCam = new OrthographicCamera(320, 480);
        guiCam.position.set(320 / 2, 480 / 2, 0);
    }

    @Override
    public void render(float delta) {
        update();
        draw();
    }

    private void update() {
        //---> Listener
    }

    private void draw() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();
        game.getBatch().setProjectionMatrix(guiCam.combined);

        game.getBatch().disableBlending();
        game.getBatch().begin();
        //Background
        game.getBatch().end();

        game.getBatch().enableBlending();
        game.getBatch().begin();
        //
        game.getBatch().end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void dispose() {
    }
}
