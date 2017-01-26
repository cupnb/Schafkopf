package de.emg_haar.schafkopfdeluxe.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebi on 25.11.2016.
 */


public abstract class Game {
    private List<Player> players = new ArrayList<>();
    private Deck deck;
    protected List<Card> trumpCards;

    public Game() {
        initialize();
    }

    public void giveCards() {
        deck = new Deck();
        for (Player player : players) {
            player.addCards(deck.deal());
        }
    }

    public abstract void initialize();
}

