package de.emg_haar.schafkopfdeluxe;

import com.badlogic.gdx.ApplicationAdapter;


import de.emg_haar.schafkopfdeluxe.game.*;

public class Schafkopf extends ApplicationAdapter
{
	private Game game;
	@Override
	public void create ()
	{
		game = new Game(new Player("0"), new Player("1"), new Player("2"), new Player("3"));
	}

	@Override
	public void render ()
	{
	}
    @Override
	public void dispose ()
	{

	}

	//test

}
