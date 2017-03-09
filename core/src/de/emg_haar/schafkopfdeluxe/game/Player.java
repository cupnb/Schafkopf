package de.emg_haar.schafkopfdeluxe.game;

import java.util.LinkedList;
import java.util.Stack;

import de.emg_haar.schafkopfdeluxe.game.card.Card;


public abstract class Player
{
    private LinkedList<Card> hand;
    private String name;
    private Game game;
    private int points;
    private boolean player;
    private boolean contra;
    private boolean re;
    private boolean wannaplay;

    private boolean turn;

    public Player(String name)
    {
        this.name = name;
        wannaplay = false;
        game = null;
        points = 0;
        player = false;
        contra = false;
        re = false;
        hand = new LinkedList<>();

        turn = false;
    }

    public void setGame(Game g)
    {
        game = g;
    }

    public void setPlayer(boolean p)
    {
        player = p;
    }

    public boolean getPlayer()
    {
        return player;
    }

    public boolean getContra()
    {
        return contra;
    }

    public boolean getRe()
    {
        return re;
    }

    //Die Karten aus dem Stack werden auf die Hand gebracht
    public void addCards(Stack<Card> c)
    {
        hand.add(c.pop());
        hand.add(c.pop());
        hand.add(c.pop());
        hand.add(c.pop());
    }

    public LinkedList<Card> getHand()
    {
        return hand;
    }

    public String getName()
    {
        return name;
    }

    public void showPlayableCards(LinkedList<Card> l)
    {

    }

    public void yourTurn()
    {
        turn = true;
    }

    public boolean iplay(){
        wannaplay = true;
        return wannaplay;
    }

    public Mode play(Mode mode)
    {

    }
}
