package de.emg_haar.schafkopfdeluxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FillViewport;


public class MainMenuScreen implements Screen
    {

        private OrthographicCamera camera;
        private Schafkopf game;
        private Stage stage;
        private Table table;

        public MainMenuScreen(Schafkopf gam)
            {
                //camera = new OrthographicCamera(1280, 720);
                game = gam;
                stage = new Stage(new FillViewport(1280, 720));

                table = new Table(game.skin);
                table.setFillParent(true);


                //Label title = new Label("Hauptmenu", game.skin);
                /*title.setAlignment(Align.center);
                title.setX(700);
                title.setWidth(Gdx.graphics.getWidth());*/
                //table.addActor(title);

                TextButton startButton = new TextButton("Spielen", game.skin);
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
                table.add(startButton);
                table.row();
                table.add(optionsButton);
                table.setDebug(true);
                stage.addActor(table);

                //camera = new OrthographicCamera();
                //camera.setToOrtho(false, 720, 1280);



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
                stage.getBatch().begin();
                stage.getBatch().draw(new Texture("Bilder/Hauptmenue.png"), 0, 0);
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

