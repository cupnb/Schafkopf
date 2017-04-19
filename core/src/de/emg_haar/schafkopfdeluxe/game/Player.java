package de.emg_haar.schafkopfdeluxe.game;

import java.util.LinkedList;
import java.util.Stack;
import java.util.Scanner;
import de.emg_haar.schafkopfdeluxe.game.card.Card;

//Klasse, die alle Methoden um den Spieler beinhaltet
public class Player
{
    //"Hand" des Spielers --> Speichern aller 8 Karten in einer Liste
    private LinkedList<Card> hand;
    //Name des Spielers
    private String name;
    //Spiel, dem der Spieler beiwohnt
    private Game game;
    //Punkte, die der Spieler besitzt
    private int points;
    //Boolean zum Festlegen von Spieler bzw. Nicht-Spieler
    private boolean player;
    //Boolean zum Festlegen, ob der Spieler spielen will, oder nicht
    private boolean wannaplay;
    //Boolean zum festlegen, ob der Spieler online spielt, oder nicht
    private boolean online;
    //Speichern der gewonnenen Stiche des Spielers
    private Stack<Card> stiche;
    //Speichern der Punkt, die der Spieler hat
    private int punkte;
    //Anzahl der Stiche zur
    private int stichanzahl;
    //Boolean ob man dran ist oder nicht
    private boolean turn;

    //Konstruktor der Klasse Player
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
        stiche = new Stack<Card>();
        punkte = 0;
        stichanzahl = 0;
    }

    //setter Methode von game
    public void setGame(Game g)
    {
        game = g;
    }

    //setter Methode von player
    public void setPlayer(boolean p)
    {
        player = p;
    }

    //getter Methode von player
    public boolean getPlayer()
    {
        return player;
    }

    //getter Methode von wannaplay
    public boolean getWannaplay() { return wannaplay; }

    //Methode, die die Stichpunktanzahl des Players um 1 erhöht
    public void stichpunkterhöhen(){
        stichanzahl = stichanzahl + 1;
    }

    //Gibt die Punkte des Spielers wieder
    public int getPunkte(){

        for (int y = stichanzahl * 4; y > 0; y--){
            points = points + stiche.pop().getPoints();
        }
        return points;
    }

    //Stich von einem übergebenen Stack wird in stiche übertragen
    public void addStich(Stack<Card> s){
        for (int l = 0; l < 5; l++){
            stiche.push(s.pop());
        }
    }

    //Die Karten aus dem Stack werden auf die Hand gebracht
    public void addCards(Stack<Card> c)
    {
        hand.add(c.pop());
        hand.add(c.pop());
        hand.add(c.pop());
        hand.add(c.pop());
    }

    //getter Methode von hand
    public LinkedList<Card> getHand()
    {
        return hand;
    }

    //getter Methode von name
    public String getName()
    {
        return name;
    }


    //
    // Wäre noch wichtig, ich brauche diese Methode noch.
    // - Alex D.
    //

    //zeigt die spielbaren Karten einer Person
    public void showPlayableCards(LinkedList<Card> l)
    {

    }

    //Die Person ist am Zug
    public void yourTurn()
    {
        turn = true;
    }

    //setter MEthode von wannaplay
    public void setWannaplay(boolean wannaplayNew)
    {
        wannaplay = wannaplayNew;
    }


    //Was will Player spielen, wenn er spielen will
    public Mode.MODE_TYPE play(String mode)
    {
        //darf nur gemacht werden, wenn wannaplay true ist
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
        //habe ich jetzt mal in loop() in Game gemacht -Ulli
        return null;
    }

    // sagt an ob er ein Bot-Game oder ein Online-Game spielen will
    public void onlineSpiel()
    {
        online = true;
    }
    //Problem: muss irgendwie in MainMenu vom Player gewählt werden

    //Scanner - noch in Arbeit
    public Mode.MODE_TYPE ScannerBenutzen()
    {
        String input = new String("");
        Mode.MODE_TYPE modeuebergeben = null;
        Scanner s = new Scanner(input);
        modeuebergeben = modeuebergeben.vergleiche(input);
        return modeuebergeben;
    }
}
