package de.emg_haar.schafkopfdeluxe.game.Bots;
import java.util.LinkedList;
import java.util.Stack;

import de.emg_haar.schafkopfdeluxe.game.*;
import de.emg_haar.schafkopfdeluxe.game.card.Card;
import de.emg_haar.schafkopfdeluxe.game.card.CardColor;
import de.emg_haar.schafkopfdeluxe.game.card.CardRank;


public class Bot implements Player
{
    private LinkedList<Card> hand;
    private String name;
    private Game game;
    private int points;
    private boolean player;
    private boolean wannaplay;
    private Card [][] botMatrix;
    private Stack<Card> stiche;
    private int stichanzahl;
    private boolean[] mitspieler;
    private Mode.MODE_TYPE gamemode;
    private int spielender;
    private int playernumber;

    Bot()
    {
        name = "Fuzzy";
        wannaplay = false;
        game = null;
        points = 0;
        player = false;
        hand = new LinkedList<>();
        botMatrix = new Card[4][8];
        stiche = new Stack<>();
        stichanzahl = 0;
        mitspieler = new boolean[4];
        gamemode = Mode.MODE_TYPE.NICHTS;
        spielender = 0;
        playernumber = 0;
    }

    //------------------------Anfang Default Methoden------------------
    //Methode, die die Stichpunktanzahl des Players um 1 erhöht
    public void stichpunkterhöhen(){
        stichanzahl = stichanzahl + 1;
    }

    public String getName() {
        return name;
    }

    public boolean getPlayer() {
        return player;
    }

    public void giveNumber(int n)
    {
        mitspieler[n] = true;
        playernumber = n;
    }

    //Gibt die Punkte des Spielers wieder
    public int getPunkte(){

        for (int y = stichanzahl * 4; y > 0; y--){
            points = points + stiche.pop().getPoints();
        }
        return points;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setPlayer(boolean p) {
        player = p;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isWannaplay() {
        return wannaplay;
    }

    public boolean isBot()
    {
        return true;
    }

    //1 heißt ja; 0 heißt nein
    public int setWannaplay()
    {
        return 0;
    }

    public Mode.MODE_TYPE play() {
        return null;
    }

    public Card kartelegen() {
        return null;
    }

    public void onlineSpiel()
    {}

    public Card[][] getBotMatrix() {
        return botMatrix;
    }

    public void setMatrix(Card[][] botMatrix) {
        this.botMatrix = botMatrix;
    }

    public void giveMode(Mode.MODE_TYPE m) {
        gamemode = m;
    }

    public void giveSpielender(int p)
    {
        spielender = p;
    }

    public LinkedList<Card> getHand() {
        return hand;
    }

    public void addCards(Stack<Card> c)
    {
        hand.add(c.pop());
        hand.add(c.pop());
        hand.add(c.pop());
        hand.add(c.pop());
    }

    //Stich von einem übergebenen Stack wird in stiche übertragen
    public void addStich(Stack<Card> s){
        for (int l = 0; l < 5; l++){
            stiche.push(s.pop());
        }
    }
    //------------------------Ende Default Methoden--------------------

    public void bestimmeMitspielerAnfang()
    {

        if(spielender != playernumber)
        {
            if(gamemode == Mode.MODE_TYPE.SOLOEICHEL || gamemode == Mode.MODE_TYPE.SOLOGRAS|| gamemode == Mode.MODE_TYPE.SOLOHERZ || gamemode == Mode.MODE_TYPE.SOLOSCHELLEN || gamemode == Mode.MODE_TYPE.WENZ)
            {
                for(int i = 0; i<4; i++)
                {
                    mitspieler[i] = i != spielender;
                }
            }
            else if(gamemode == Mode.MODE_TYPE.SAUSPIELEICHEL)
            {
                for(Card karte:hand)
                {
                    if(karte.getRank() == CardRank.ASS && karte.getColor() == CardColor.EICHEL)
                    {
                        mitspieler[spielender] = true;
                    }
                }
            }
            else if(gamemode == Mode.MODE_TYPE.SAUSPIELGRAS)
            {
                for(Card karte:hand)
                {
                    if(karte.getRank() == CardRank.ASS && karte.getColor() == CardColor.LAUB)
                    {
                        mitspieler[spielender] = true;
                    }
                }
            }
            else if(gamemode == Mode.MODE_TYPE.SAUSPIELSCHELLEN)
            {
                for(Card karte:hand)
                {
                    if(karte.getRank() == CardRank.ASS && karte.getColor() == CardColor.SCHELLEN)
                    {
                        mitspieler[spielender] = true;
                    }
                }
            }
        }
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
