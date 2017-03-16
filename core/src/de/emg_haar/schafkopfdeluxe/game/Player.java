package de.emg_haar.schafkopfdeluxe.game;

import java.util.LinkedList;
import java.util.Stack;
import java.io.*;
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
    private boolean online;

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
        online = false;
        turn = false;
    }

    //setter und getter Methoden
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

    public boolean getWannaplay() { return wannaplay; }

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
    //spielbare Karten werden angezeigt
    }

    //Die Person ist am Zug
    public void yourTurn()
    {
        turn = true;
    }

    public void iplay(){
        wannaplay = true;
        //Ansage, dass Player spielen möchte
    }


    //Was will Player spielen, wenn er spielen will
    public Mode play(Mode mode)
    {
        if (wannaplay == true){
        Mode x = mode;
        return x;
    }

    public void onlineSpiel()
    {
        online = true;
    }
    // sagt an ob er ein Bot-Game oder ein Online-Game spielen will
    //Problem : muss irgendwie in MainMenu vom Player gewählt werden
}
