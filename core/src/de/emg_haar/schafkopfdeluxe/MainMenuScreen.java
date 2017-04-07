package de.emg_haar.schafkopfdeluxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;


/**
 * Created by Sebi on 09.03.17.
 */

public class MainMenuScreen extends ScreenAdapter {

    private OrthographicCamera guiCam;
    private Schafkopf game;
    private Stage stage;

    public MainMenuScreen(Schafkopf game) {
        this.game = game;


        guiCam = new OrthographicCamera();
        guiCam.setToOrtho(false, 1280, 720);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(new MainMenuActor());

    }
    @Override
    public void render(float delta) {
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
        private Texture texture = new Texture("spuin.png");
        private float x = 720, y = 369;

        public MainMenuActor()
        {

            setTouchable(Touchable.enabled);
            setBounds(x, y, texture.getWidth(), texture.getHeight());
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    System.out.println("Tastendruck");
                    return true;
                }

            });
        }
        public void draw(SpriteBatch batch, float alpha){
            batch.draw(texture, x, y);
        }
    }
}

