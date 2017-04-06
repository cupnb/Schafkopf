package de.emg_haar.schafkopfdeluxe.game;

import java.util.Random;
import java.util.Stack;
import java.util.Scanner;
import java.io.*;
import de.emg_haar.schafkopfdeluxe.game.card.Card;

import static de.emg_haar.schafkopfdeluxe.game.Mode.MODE_TYPE.RAMSCH;
import static de.emg_haar.schafkopfdeluxe.game.Mode.MODE_TYPE.SAUSPIELEICHEL;


public class Game {

    private Player[] players;
    private enum Turnstate{P0, P1, P2, P3}
    private Turnstate turnState;
    private int dealer;
    private Stack<Card> played;



    private Mode mode;
    private Deck deck;
    private Stack<Card> dump;

    private int roundNumber;

    public Game(Player p0, Player p1, Player p2, Player p3) {
        Random rnd = new Random();
        players = new Player[4];

        InputStreamReader Alpha = new InputStreamReader(System.in);
        BufferedReader Eingabe = new BufferedReader(Alpha);

        //Referenz von Game wird den Spielern uebergeben
        p0.setGame(this);
        p1.setGame(this);
        p2.setGame(this);
        p3.setGame(this);

        // /Spieler werden in das Feld gesteckt
        players[0] = p0;
        players[1] = p1;
        players[2] = p2;
        players[3] = p3;

        deck = new Deck();
        dump = new Stack<Card>();
        played = new Stack<Card>();

        //Der Geber wird zufaellig bestimmt
        dealer = rnd.nextInt(4);
        roundNumber = 0;
        initialize();
    }
    public int getStapel(){
        if (played.empty() == true){
            return -1;
        }
        else{
            return 0;
        }
    }

    public void addgespielteKarte(Card f){
        dump.add(f);
        played.add(f);
    }

    public void initialize()
    {
        //Karten werden zu je 4 an die Spieler verteilt
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                players[j].addCards(deck.deal());
            }
        }

        //Der anfangende Spieler wird im turnState festgelegt

        if (dealer == 0)
        {
            turnState = Turnstate.P1;
        }
        else if (dealer == 1)
        {
            turnState = Turnstate.P2;
        }
        else if (dealer == 2)
        {
            turnState = Turnstate.P3;
        }
        else if (dealer == 3)
        {
            turnState = Turnstate.P0;
        }

        //Abfrage wer spielen WILL
        int auswähler = (dealer + 1);
        int anzahlSpielenWollen = 0;
        boolean[] willSpieler = new boolean[4];
         for (int i = 0; i<4; i++)
         {
             if(players[(auswähler + i)%4].getWannaplay() == true)
             {

                 willSpieler[i] = true;
                 anzahlSpielenWollen = anzahlSpielenWollen + 1;
             }
             else
             {
                 willSpieler[i] = false;
             }
         }
        //Abfrage wer SPIELT
        int willspieler = (dealer + 1);
        Mode[] modefeld = new Mode[4];
        if(anzahlSpielenWollen  == 0)
        {
            mode.setModeType(RAMSCH);
        }
        if(anzahlSpielenWollen == 1)
        {
            //Player EinzigerWillSpieler = null;
            for (int p = 0; p < 4; p++)
            {
                if (willSpieler[p] == true)
                {
                    mode.setModeType(players[p].play(SAUSPIELEICHEL));
                    //SauspielEichel nur ein Beispiel --> Eingabefeld einfügen
                }
            }
        }
        if(anzahlSpielenWollen > 1)
        {
            for (int i = 0; i < 4; i++) {
                Scanner scanner = new Scanner(System.in);
                while(scanner.hasNext()) {

                }

                modefeld[i].setModeType(players[(willspieler + i) % 4].play(SAUSPIELEICHEL));
            }

            //vergleicht ob jemand der später spielen will einen höher priorisierten Mode spielen will
            for (int z = 0; z < 4; z++) {
                if (modefeld[z] != null) {
                    if (mode == null)
                    {
                        mode.setModeType(modefeld[z].getModeType());
                    }
                    if (modefeld[z].getOrdinal(modefeld[z].toString()) > mode.getOrdinal(mode.toString())) {
                        mode.setModeType(modefeld[z].getModeType());
                        //Mode fürSpiel ist der endgültige Mode
                    }

                }
            }
        }



        //Geber wird um eins erhoeht (ganz am Ende von initialize einbauen)
        if (dealer == 3)
        {
            dealer = 0;
        }
        else
        {
            dealer = dealer++;
        }


        for (int i =0; i<8; i++)
        {
            loop();
        }
        }

    public void loop()
    {
        for (int w = 0;w < 8; w++) {
            Player best = null;
            Card highest = null;
            for (int x = 0; x < 4; x++) {
                //players[turnState.ordinal()].yourTurn();
                Card Spielkarte = players[(dealer + 1 + x) % 4].kartelegen();
                if (best == null) {
                    best = players[(dealer + 1 + x) % 4];
                    highest = Spielkarte;
                } else {
                    if (Spielkarte > highest) {
                        highest = Spielkarte;
                        best = players[(dealer + 1 + x) % 4];
                    }
                }
            }
            best.addStich(played);
            best.stichpunkt();
        }
        int punkte1 = 0;
        int punkte2 = 0;
        for (int b = 0; b < 4;b++){
            if (players[b].getPlayer() == true){
                punkte1 = punkte1 + players[b].getPunkte();
            }
            else{
                punkte2 = punkte2 + players[b].getPunkte();
            }
        }
        if (punkte1 < punkte2){
            System.out.println("Team 2 gewinnt!");
        }
        else{
            System.out.println("Team 1 gewinnt!");
        }
    }

    public void nextPlayer()
    {
        switch (turnState) {
            case P3:
                System.out.println("Spieler 0 ist jetzt dran");
                turnState = Turnstate.P0;
            case P0:
                System.out.println("Spieler 1 ist jetzt dran");
                turnState = Turnstate.P1;
            case P1:
                System.out.println("Spieler 2 ist jetzt dran");
                turnState = Turnstate.P2;
            case P2:
                System.out.println("Spieler 3 ist jetzt dran");
                turnState = Turnstate.P3;
        }

    }

    private void showPlayableCards(Player p)
    {
        p.showPlayableCards(mode.checkPlayable(p.getHand()), dump);
    }



}



