package de.emg_haar.schafkopfdeluxe;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.emg_haar.schafkopfdeluxe.game.*;

public class Schafkopf extends ApplicationAdapter
{
	private SpriteBatch batch;
	private BitmapFont font;

	public Stage stage;
	MainMenu main;
	
	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		font = new BitmapFont();


		stage = new Stage();

		Gdx.input.setInputProcessor(stage);

		main = new MainMenu(this);
	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.draw(batch, "Hello World", 200, 200);
		stage.draw();
		batch.end();
	}
	
	@Override
	public void dispose ()
	{
		batch.dispose();

	}
}
