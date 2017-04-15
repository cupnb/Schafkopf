package de.emg_haar.schafkopfdeluxe.game;

import java.util.LinkedList;
import java.util.Stack;
import de.emg_haar.schafkopfdeluxe.game.card.Card;



public class Player
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
    private Player mitspieler;
    private Stack<Card> stiche;
    private int punkte;
    private int stichanzahl;

    private boolean turn;

    public Player(String name)
    {
        this.name = name;
        wannaplay = false;
        game = null;
        points = 0;
        player = false;
        hand = new LinkedList<>();
        online = false;
        turn = false;
        mitspieler = null;
        stiche = new Stack<Card>();
        punkte = 0;
        stichanzahl = 0;
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

    public int stichpunkt(){
        stichanzahl = stichanzahl + 1;
        return stichanzahl;
    }

    public int getPunkte(){
        for (int y = stichanzahl * 4; y > 0; y--){
            points = points + stiche.pop().getPoints();
        }
        return points;
    }

    public void addStich(Stack<Card> s){
        for (int l = 0; l < 5; l++){
            stiche.push(s.pop());
        }
    }

    public Player setMitspieler(Player p){
        mitspieler = p;
        return mitspieler;
        //zum festlegen der teams
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
    public Mode.MODE_TYPE play(String mode)
    {
        if (wannaplay == true) {
            Mode.MODE_TYPE x = Mode.MODE_TYPE.vergleiche(mode);
            return x;
        }
        else
        {
            return null;
        }
    }

    public Card kartelegen(){
        //spieler wählt karte aus
        //karte wird zu dump und played hinzugefügt
        return null;
    }

    public void onlineSpiel()
    {
        online = true;
    }
    // sagt an ob er ein Bot-Game oder ein Online-Game spielen will
    //Problem: muss irgendwie in MainMenu vom Player gewählt werden
}
