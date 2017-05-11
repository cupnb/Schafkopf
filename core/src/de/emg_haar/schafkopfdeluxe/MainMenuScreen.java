package de.emg_haar.schafkopfdeluxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;


public class MainMenuScreen implements Screen
    {

        private OrthographicCamera camera;
        private Schafkopf game;
        private Stage stage;

        public MainMenuScreen(Schafkopf gam)
            {
                game = gam;
                stage = new Stage();


                Label title = new Label("Hauptmenu", game.skin);
                title.setAlignment(Align.center);
                title.setX((Gdx.graphics.getWidth() / 4));
                title.setWidth(Gdx.graphics.getWidth());
                stage.addActor(title);

                TextButton startButton = new TextButton("Spielen", game.skin);
                startButton.setX((Gdx.graphics.getHeight() / 4) * 3);
                startButton.setWidth(Gdx.graphics.getWidth() / 2);
                startButton.addListener(new InputListener()
                    {

                        @Override
                        public void touchUp(InputEvent event, float x, float y, int pointer, int button)
                            {
                                game.setScreen(new GameScreen(game));
                            }

                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
                            {
                                return true;
                            }
                    });

                TextButton optionsButton = new TextButton("Optionen", game.skin);
                optionsButton.setY((Gdx.graphics.getHeight() / 4) * 3);
                optionsButton.setX(Gdx.graphics.getWidth() / 2);
                optionsButton.setWidth(Gdx.graphics.getWidth() / 2);
                optionsButton.addListener(new InputListener()
                    {
                        @Override
                        public void touchUp(InputEvent event, float x, float y, int pointer, int button)
                            {
                                game.setScreen(game.optionsScreen);
                            }

                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
                            {
                                return true;
                            }
                    });
                stage.addActor(startButton);
                stage.addActor(optionsButton);

                camera = new OrthographicCamera();
                camera.setToOrtho(false, 720, 1280);



            }

        @Override
        public void show()
            {
                Gdx.input.setInputProcessor(stage);
            }

        @Override
        public void render(float delta)
            {

                Gdx.gl.glClearColor(0, 0, 0.2f, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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

