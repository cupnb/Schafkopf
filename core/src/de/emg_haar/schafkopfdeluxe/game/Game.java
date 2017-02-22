package de.emg_haar.schafkopfdeluxe.game;

import java.util.Random;
import java.util.Stack;

import de.emg_haar.schafkopfdeluxe.game.card.Card;


public class Game {

    private Player[] players;
    private enum Turnstate{P0, P1, P2, P3}
    private Turnstate turnState;
    private int dealer;


    private Mode mode;
    private Deck deck;
    private Stack<Card> dump;

    private int roundNumber;

    public Game(Player p0, Player p1, Player p2, Player p3) {
        Random rnd = new Random();
        players = new Player[4];

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

        //Der Geber wird zufaellig bestimmt
        dealer = rnd.nextInt(4);
        roundNumber = 0;
        initialize();
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

        //Geber wird um eins erhoeht
        if (dealer == 3)
        {
            dealer = 0;
        }
        else
        {
            dealer = dealer++;
        }


        loop();
        //Hier muss noch die Abfrage wer spielt in Player erstellt werden
        //


        }

    public void loop()
    {
        for (boolean i = true; )
        {
            players[turnState.ordinal()].yourTurn();

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
        p.showPlayableCards(mode.checkPlayable(p.getHand()));
    }



    }



