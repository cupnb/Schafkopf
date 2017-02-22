package de.emg_haar.schafkopfdeluxe;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import de.emg_haar.schafkopfdeluxe.game.Player;

/**
 * Created by NoahB on 27.11.16.
 */

public class MainMenu{

    private Schafkopf schafkopf;
    private Game game;

    private Button button;
    public Button.ButtonStyle ButtonStyle;
    private TextureAtlas buttonAtlas;

    public MainMenu(Schafkopf schafkopf)
    {

        buttonAtlas = new TextureAtlas("Button1/Button.atlas");
        TextureRegionDrawable drawableUp = new TextureRegionDrawable( buttonAtlas.findRegion("Button1") );
        TextureRegionDrawable drawableDown = new TextureRegionDrawable( buttonAtlas.findRegion("Button1Pressed") );
        TextureRegionDrawable drawableChecked = new TextureRegionDrawable( buttonAtlas.findRegion("Button1Pressed") );

        Button.ButtonStyle btnStyle = new Button.ButtonStyle(drawableUp, drawableDown, drawableChecked);
        button = new Button(btnStyle);

        button.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button Pressed");
                game = new de.emg_haar.schafkopfdeluxe.game.Game(new Player(), new Player(), new Player(), new Player());
            }
        });

        schafkopf.stage.addActor(button);


    }

    public void dispose()
    {
        buttonAtlas.dispose();
    }


}
