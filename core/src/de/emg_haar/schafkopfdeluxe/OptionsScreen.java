package de.emg_haar.schafkopfdeluxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FillViewport;


public class OptionsScreen implements Screen
    {
        private Stage stage;
        private Schafkopf game;
        public OptionsScreen(Schafkopf gam)
            {
                game = gam;
                stage = new Stage(new FillViewport(720, 1280));

                Label title = new Label("Optionen", game.skin);
                title.setAlignment(Align.center);
                title.setX(Gdx.graphics.getWidth()/2);
                title.setWidth(Gdx.graphics.getWidth());
                stage.addActor(title);

                CheckBox soundButton = new CheckBox("Sounds", game.skin);
                soundButton.setX(Gdx.graphics.getWidth()/2);
                soundButton.setY(Gdx.graphics.getHeight()/2);
                        soundButton.addListener(new InputListener()
                    {

                        @Override
                        public void touchUp(InputEvent event, float x, float y, int pointer, int button)                      {
                        game.setScreen(new GameScreen(game));
                    }

                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
                            {
                                return true;
                            }
                    });
                stage.addActor(soundButton);
                
            }
        @Override
        public void show()
            {

            }

        @Override
        public void render(float delta)
            {
                stage.act();
                stage.draw();
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
                stage.dispose();
            }
    }
