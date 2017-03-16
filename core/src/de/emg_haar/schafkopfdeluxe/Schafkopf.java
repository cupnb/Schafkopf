package de.emg_haar.schafkopfdeluxe;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Schafkopf extends Game {

	private TextureAtlas cardAtlas;
	private SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		cardAtlas = new TextureAtlas("cards.pack");
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	public SpriteBatch getBatch() {
		return batch;
	}
}
