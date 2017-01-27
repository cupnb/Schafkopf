package de.emg_haar.schafkopfdeluxe.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebi on 25.11.2016.
 */


public abstract class Game {
    private List<Player> players = new ArrayList<>();
    private Deck deck;
    public Game() {
        initialize();
    }



    public abstract void initialize();
}

