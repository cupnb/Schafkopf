package de.emg_haar.schafkopfdeluxe.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public abstract class Game {
    private Player[] players;
    private Mode mode;
    private Deck deck;
    private Stack<Card> dump;
    private int roundNumber;
    public Game(Player p0, Player p1, Player p2, Player p3) {
        players = new Player[4];
        players[0] = p0;
        players[1] = p1;
        players[2] = p2;
        players[3] = p3;

        deck = new Deck();
        dump = new Stack<Card>();
        roundNumber = 0;
        initialize();
    }

    public void initialize()
    {
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                players[j].addCards(deck.deal());
            }
        }
    }
 }


