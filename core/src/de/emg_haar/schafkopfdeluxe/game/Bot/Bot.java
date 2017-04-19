//Wäre ganz cool Alex, wenn du hier die Kommentare reinmachen könntest
//Ulli

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
    protected boolean turn;

    public Bot() {

        name = "Nathanael";

        // Ich würde gerne bei mehreren Nathanaels durchnummerieren.
        // Game müsste eine Methode aufrufen, die dem Konstruktor dieser Klasse eine Zahl dalässt.
        // Diese Zahl ist die Position des Bots am Tisch.

        //dann baus dir ein
        //Ulli

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
