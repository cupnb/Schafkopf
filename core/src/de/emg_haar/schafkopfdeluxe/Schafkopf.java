package de.emg_haar.schafkopfdeluxe;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Schafkopf extends Game
    {

        //Die Ressourcen aller Klassen
        //public TextureAtlas cardAtlas; //Wahrscheinlich hier nicht benoetigt
        public SpriteBatch batch;
        public BitmapFont font;
        public Skin skin;

        //Die verschiedenen Screens um diese nur einmal zu erzeugen
        //public Screen gameScreen; //Mal sehen was hiermit passieren wird
        public Screen optionsScreen;
        public Screen mainMenuScreen;

        @Override
        public void create()
            {
                batch = new SpriteBatch();
                font = new BitmapFont();
                skin = new Skin(Gdx.files.internal("clean-crispy/skin/clean-crispy-ui.json"));
                //cardAtlas = new TextureAtlas("cards.atlas");

                optionsScreen = new OptionsScreen(this);
                mainMenuScreen = new MainMenuScreen(this);

                //setzt den Screen auf MainMenu
                setScreen(mainMenuScreen);
            }

        @Override
        public void render()
            {
                super.render();
            }

        //Alle genutzten Dateien werden fachgerecht entsorgt...
        @Override
        public void dispose()
            {
                //cardAtlas.dispose();
                batch.dispose();
                font.dispose();
                skin.dispose();
                optionsScreen.dispose();
                mainMenuScreen.dispose();
                screen.dispose();
            }
    }
