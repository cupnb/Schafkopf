package de.emg_haar.schafkopfdeluxe.game;

import java.util.LinkedList;
import java.util.Stack;

import de.emg_haar.schafkopfdeluxe.game.card.Card;
import de.emg_haar.schafkopfdeluxe.game.card.CardColor;

/**
 * Created by noah on 30.01.17.
 */

public class Mode {

    public enum MODE_TYPE
    {
        SAUSPIELSCHELLEN,
        SAUSPIELGRAS,
        SAUSPIELEICHEL,
        WENZ,
        SOLOSCHELLEN,
        SOLOGRAS,
        SOLOEICHEL,
        SOLOHERZ,
        RAMSCH
    }
    private MODE_TYPE mode_type;

    public Mode(MODE_TYPE m)
    {
        mode_type = m;
    }

    //getter Methode
    public MODE_TYPE getModeType()
    {
        return mode_type;
    }

    //setter Methode
    public void setModeType(MODE_TYPE m)
    {
        m = mode_type;
    }

    //spielbare Karten werden geckeckt und mit einer LinkedList
    public LinkedList<Card> checkPlayable(LinkedList<Card> hand, Stack<Card> cardsliegen)
    {
        return hand;
        //einfach nur, damit kein Error kommt
    }

    public int getOrdinal( String name )
    {
        try {
            return MODE_TYPE.valueOf( name ).ordinal();
        }
        catch ( IllegalArgumentException e ) {
            return -1;
        }
    }

}
