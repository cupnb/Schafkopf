package de.emg_haar.schafkopfdeluxe;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by noah on 13.04.17.
 */

public class GameScreen implements Screen
    {
        private Schafkopf game;

        public GameScreen(Schafkopf gam)
            {
                game = gam;
            }
        @Override
        public void show()
            {

            }

        @Override
        public void render(float delta)
            {
                Gdx.gl.glClearColor(0, 1, 0, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

                game.batch.begin();
                game.font.draw(game.batch, "More to come...", 20, 640);
                game.batch.end();
            }

        @Override
        public void resize(int width, int height)
            {

            }

        @Override
        public void pause()
            {

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
