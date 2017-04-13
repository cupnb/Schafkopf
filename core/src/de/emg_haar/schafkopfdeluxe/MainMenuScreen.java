package de.emg_haar.schafkopfdeluxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;



/**
 * Created by Sebi on 09.03.17.
 */

public class MainMenuScreen implements Screen {

    private OrthographicCamera camera;
    private Schafkopf game;

    public MainMenuScreen(Schafkopf gam) {
        game = gam;


        camera = new OrthographicCamera();
        camera.setToOrtho(false, 720, 1280);


    }

    @Override
    public void show()
        {

        }

    @Override
    public void render(float delta)
        {
            if (Gdx.input.isTouched())
                {
                    game.setScreen(new GameScreen(game));
                }

            Gdx.gl.glClearColor(0, 0, 0.2f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            game.batch.begin();
            game.font.draw(game.batch, "Bitte Bildschirm beruehren!", 20, 640);
            game.batch.end();


    }

    @Override
    public void resize(int width, int height)
        {

        }

    @Override
    public void pause() {

    }

    @Override
    public void resume()
        {

        }

    @Override
    public void hide()
        {

        }

    @Override
    public void dispose()
        {

        }


}

