package de.emg_haar.schafkopfdeluxe.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


public abstract class Player {
    private Game game;
    private String name;
    private LinkedList<Card> hand;

    public Player(String name) {
        this.name = name;
        this.game = null;
        this.hand = new LinkedList<>();

    }

    //Methode um die Referenz auf Game einzufuegen
    public void setGame(Game g)
    {
        this.game = g;
    }

    //Das uebergebene Stack wird auf die Hand gesetzt
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
