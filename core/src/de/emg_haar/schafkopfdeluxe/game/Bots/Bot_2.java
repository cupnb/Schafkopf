package de.emg_haar.schafkopfdeluxe.game.Bots;
import java.util.LinkedList;
import java.util.Stack;

import de.emg_haar.schafkopfdeluxe.game.*;
import de.emg_haar.schafkopfdeluxe.game.card.Card;


public class Bot_2
{
    private LinkedList<Card> hand;
    private String name;
    private Game game;
    private int points;
    private boolean player;
    private boolean wannaplay;
    private boolean bot;
    private Card [][] botMatrix;
    private

    Bot_2()
    {
        name = "Fuzzy";
        wannaplay = false;
        game = null;
        points = 0;
        player = false;
        hand = new LinkedList<>();
        bot = true;
        botMatrix = new Card[4][8];
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isWannaplay() {
        return wannaplay;
    }

    public Card[][] getBotMatrix() {
        return botMatrix;
    }

    public void setBotMatrix(Card[][] botMatrix) {
        this.botMatrix = botMatrix;
    }

    public LinkedList<Card> getHand() {
        return hand;
    }

    public void setHand(Stack<Card> c) {
        hand.add(c.pop());
        hand.add(c.pop());
        hand.add(c.pop());
        hand.add(c.pop());
    }

    public Card legeKarte()
    {

    }
    //---------------------------Anfang Fuzzy Methoden-------------------------------------------
    //Fuzzyfiziert die Punktzahl.
    //Die Rueckgabe ist ein Array mit der Zugehoerigkeit der Punktzahl zu fuenf verschiedenen linguistischen Variablen.
    //Genannte Variablen sind in Ihrer "Positivitaet" aufsteigend angeordnet.
    public double[] fuzzyPunkte()
    {
        //Wenn mir der Stich nicht gehoert, dann gibt es keine Punkte zu holen:
        boolean stichGehoertUns = false;
        int maximalePunktzahlImStich;
        double[] toReturn = new double[5];
        if(!stichGehoertUns)
        {
            //Alle linguistischen Variablen sind auf 0 bis auf die schlechteste, die auf 1 ist.
            toReturn[0] = 1;
            toReturn[1] = 0;
            toReturn[2] = 0;
            toReturn[3] = 0;
            toReturn[4] = 0;
            return toReturn;
            //Wenn es weniger als 20 Punkte im Stich gibt, sind Punkte, die eigentlich mehr wert:
        }
        if (maximalePunktzahlImStich < 20) {
            //In diesem Fall haben wir eine gestauchte Fuzzy-Akkordeonsfunktion:
            return fuzzyAkkordeonsfunktion(punkte, maximalePunktzahlImStich);
        }
        else if (maximalePunktzahlImStich >= 20)
        {//In diesem Fall haben wir eine ungestauchteFuzzy-Akkordeonsfunktion über das Intervall [0, 20]:
            if punkte< 20:
            return fuzzyAkkordeonsfunktion(punkte, 20);
        }
        //Ist die Punktzahl groesser 20, so liegen eindeutig viele Punkte im Stich:
        else if (punkte >= 20)
        {
            toReturn[0] = 0;
            toReturn[1] = 0;
            toReturn[2] = 0;
            toReturn[3] = 0;
            toReturn[4] = 1;
            return toReturn;
        }
    }
    //---------------------------Ende Fuzzy Methoden---------------------------------------------
    //---------------------------Anfang Spielmechanik Methoden-----------------------------------
    public boolean stichGehoertUns()
    {

    }
    //---------------------------Ende Spielmechanik Methoden-------------------------------------


    /*
    public void setWannaplay() {
        wannaplay = MethodeDieSagtObManSpielenWillVomBotAus;
    }
    */

}
