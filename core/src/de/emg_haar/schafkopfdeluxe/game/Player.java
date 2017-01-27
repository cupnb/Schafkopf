package de.emg_haar.schafkopfdeluxe.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


public class Player {
    private String name;
    private LinkedList<Card> hand;

    public Player(String name) {
        this.name = name;
    }

    public void addCards(Stack<Card> c)
    {
        hand.add(c.pop());
        hand.add(c.pop());
        hand.add(c.pop());
        hand.add(c.pop());
    }

    public List<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }
}
