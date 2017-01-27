package de.emg_haar.schafkopfdeluxe.game;

import java.util.List;
import java.util.Stack;


public class Player {
    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }
}
