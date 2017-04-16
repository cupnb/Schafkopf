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
        RAMSCH;

        public String toString(){
            switch(this){
                case SAUSPIELSCHELLEN :
                    return "SAUSPIELSCHELLEN";
                case SAUSPIELGRAS :
                    return "SAUSPIELGRAS";
                case SAUSPIELEICHEL :
                    return "SAUSPIELEICHEL";
                case WENZ :
                    return "WENZ";
                case SOLOSCHELLEN :
                    return "SOLOSCHELLEN";
                case SOLOGRAS :
                    return "SOLOGRAS";
                case SOLOEICHEL :
                    return "SOLOEICHEL";
                case SOLOHERZ :
                    return "SOLOHERZ";
                case RAMSCH :
                    return "RAMSCH";
            }
            return null;
        }

        public static MODE_TYPE vergleiche(String value){
            if(value.equalsIgnoreCase(SAUSPIELSCHELLEN.toString()))
                return MODE_TYPE.SAUSPIELSCHELLEN;
            else if(value.equalsIgnoreCase(SAUSPIELGRAS.toString()))
                return MODE_TYPE.SAUSPIELGRAS;
            else if(value.equalsIgnoreCase(SAUSPIELEICHEL.toString()))
                return MODE_TYPE.SAUSPIELEICHEL;
            else if(value.equalsIgnoreCase(WENZ.toString()))
                return MODE_TYPE.WENZ;
            else if(value.equalsIgnoreCase(SOLOSCHELLEN.toString()))
                return MODE_TYPE.SOLOSCHELLEN;
            else if(value.equalsIgnoreCase(SOLOGRAS.toString()))
                return MODE_TYPE.SOLOGRAS;
            else if(value.equalsIgnoreCase(SOLOEICHEL.toString()))
                return MODE_TYPE.SOLOEICHEL;
            else if(value.equalsIgnoreCase(SOLOHERZ.toString()))
                return MODE_TYPE.SOLOHERZ;
            else if(value.equalsIgnoreCase(RAMSCH.toString()))
                return MODE_TYPE.RAMSCH;
            else
                return null;
        }
    }

    private String string;

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

    public void compareString(String name){
        MODE_TYPE.valueOf(name);
    }
}
