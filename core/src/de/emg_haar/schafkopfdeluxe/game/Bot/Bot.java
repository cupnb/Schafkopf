package de.emg_haar.schafkopfdeluxe.game.Bot;

import java.util.List;

import de.emg_haar.schafkopfdeluxe.game.Game;
import de.emg_haar.schafkopfdeluxe.game.card.Card;
import java.util.LinkedList;
import java.util.Stack;


public class Bot extends Player_ {

    protected LinkedList <Card> hand;
    protected String name;
    protected Game game;
    protected int points;
    protected boolean player;
    protected boolean contra;
    protected boolean re;
    protected boolean turn;

    public Bot() {

        name = "Nathanael";
        game = null;
        points = 0;
        player = false;
        hand = new LinkedList<>();
        turn = false;

    }


    public String getName(){
        return name;
    }

    public void setGame(Game g){
        this.game = g;
    }

    public void setPlayer(boolean p){
        player = p;
    }

    public boolean getPlayer(){
        return player;
    }

    public boolean getContra(){
        return contra;
    }

    public boolean getRe(){
        return re;
    }

    public void yourTurn(){
        turn = true;
    }

    public void addCards(Stack<Card> c)
    //Denke mal, dass du den Stack noch übergeben wolltest, Alex
    //Ulli
    {
        hand.add(c.pop());
        hand.add(c.pop());
        hand.add(c.pop());
        hand.add(c.pop());
    }

    public List<Card> getHand(){
        return hand;
    }

    // Wer könnte diese Methode noch schreiben?
    public void showPlayableCards(List <Card> l){
    }

}
