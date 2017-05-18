package de.emg_haar.schafkopfdeluxe;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 360;
		config.width = 720;
		new LwjglApplication(new Schafkopf(), config);
	}
}
