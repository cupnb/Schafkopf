package de.emg_haar.schafkopfdeluxe.game;

import java.util.List;
import java.util.Stack;

/**
 * Created by Sebi on 30.11.2016.
 */

public class Player {
    private String name;
    private State state = State.WAITING;
    private Stack<Card> cardsInHand = new Stack<>();

    public Player(String name) {
        this.name = name;
    }

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPlaying() {
        return state == State.PLAYING;
    }

    public void addCards(List<Card> cards) {
        cardsInHand.addAll(cards);
    }
}
