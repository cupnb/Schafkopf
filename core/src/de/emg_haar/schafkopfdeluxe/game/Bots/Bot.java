package de.emg_haar.schafkopfdeluxe.game.Bots;
import de.emg_haar.schafkopfdeluxe.game.Player;

class Bot extends Player {

    /*

    Die Attribute, die Bot erbt:

    protected LinkedList<Card> hand;
    protected String name;
    protected Game game;
    protected int points;
    protected boolean player;
    protected boolean wannaplay;
    protected boolean online;
    protected Stack<Card> stiche;
    protected int punkte;
    protected int stichanzahl;
    protected boolean turn;

    */


    Bot(){
        super("Rudi");
        //wannaplay = false;  // Rudi spielt nie!
        //bot = true;
    }

}