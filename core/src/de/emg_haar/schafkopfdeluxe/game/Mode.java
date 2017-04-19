package de.emg_haar.schafkopfdeluxe.game;

import java.util.LinkedList;
import java.util.Stack;

import de.emg_haar.schafkopfdeluxe.game.card.Card;

import static de.emg_haar.schafkopfdeluxe.game.Mode.MODE_TYPE.*;

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
                return WENZ;
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

    //Ruffarbe des Modes (bei Soli, Ramsch oder Wenz --> null)
    //1 = Schellen
    //2 = Laub
    //3 = Eichel
    //-1 = keine
    private int callingColor;
    //Beschreibung der Trumpffarbe mithilfe eines int
    //0 = Wenz
    //1 = Schellen
    //2 = Herz
    //3 = Laub
    //4 = Eichel
    private int trumpfcolor;

    public Mode(MODE_TYPE m)
    {
        mode_type = m;
        callingColor = -1;
        trumpfcolor = -1;
    }

    //getter Methode
    public MODE_TYPE getModeType()
    {
        return mode_type;
    }

    //setter Methode
    public void setModeType(MODE_TYPE m)
    {
        mode_type = m;
    }

    //getter Methode
    public int getCallingColor()
    {
        return callingColor;
    }

    //setter Methode
    public void setCallingColor(int x)
    {
        callingColor = x;
    }

    //getter Methode
    public int getTrumpfcolor()
    {
        return trumpfcolor;
    }

    //setter Methode
    public void setTrumpfcolor(int y)
    {
        callingColor = y;
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

    //geeignet für Wenz und alle Soli (außer Herz)
    public void comparisionAktualisieren(LinkedList<Card> c1, Mode.MODE_TYPE m)
    {
        Card temporary = null;
        for(int i = c1.size(); i>0; i--)
        {
            temporary = c1.getFirst();
            c1.removeFirst();
            if (m == WENZ && temporary.getRank().getName().equals("zehn"))
            {
                temporary.getRank().setComparision(35);
            }

            if (m == WENZ && temporary.getRank().getName().equals("ober"))
            {
                temporary.getRank().setComparision(36);
            }

            if (m == SOLOSCHELLEN && temporary.getRank().getName().equals("sieben") && temporary.getColor().equals("schellen"))
            {
                temporary.getRank().setComparision(61);
            }

            if (m == SOLOSCHELLEN && temporary.getRank().getName().equals("acht") && temporary.getColor().equals("schellen"))
            {
                temporary.getRank().setComparision(62);
            }

            if (m == SOLOSCHELLEN && temporary.getRank().getName().equals("neun") && temporary.getColor().equals("schellen"))
            {
                temporary.getRank().setComparision(63);
            }

            if (m == SOLOSCHELLEN && temporary.getRank().getName().equals("koenig") && temporary.getColor().equals("schellen"))
            {
                temporary.getRank().setComparision(64);
            }

            if (m == SOLOSCHELLEN && temporary.getRank().getName().equals("zehn") && temporary.getColor().equals("schellen"))
            {
                temporary.getRank().setComparision(65);
            }

            if (m == SOLOSCHELLEN && temporary.getRank().getName().equals("ass") && temporary.getColor().equals("schellen"))
            {
                temporary.getRank().setComparision(66);
            }

            if (m == SOLOGRAS && temporary.getRank().getName().equals("sieben") && temporary.getColor().equals("laub"))
            {
                temporary.getRank().setComparision(61);
            }

            if (m == SOLOGRAS && temporary.getRank().getName().equals("acht") && temporary.getColor().equals("laub"))
            {
                temporary.getRank().setComparision(62);
            }

            if (m == SOLOGRAS && temporary.getRank().getName().equals("neun") && temporary.getColor().equals("laub"))
            {
                temporary.getRank().setComparision(63);
            }

            if (m == SOLOGRAS && temporary.getRank().getName().equals("koenig") && temporary.getColor().equals("laub"))
            {
                temporary.getRank().setComparision(64);
            }

            if (m == SOLOGRAS && temporary.getRank().getName().equals("zehn") && temporary.getColor().equals("laub"))
            {
                temporary.getRank().setComparision(65);
            }

            if (m == SOLOGRAS && temporary.getRank().getName().equals("ass") && temporary.getColor().equals("laub"))
            {
                temporary.getRank().setComparision(66);
            }

            if (m == SOLOEICHEL && temporary.getRank().getName().equals("sieben") && temporary.getColor().equals("eichel"))
            {
                temporary.getRank().setComparision(61);
            }

            if (m == SOLOEICHEL && temporary.getRank().getName().equals("acht") && temporary.getColor().equals("eichel"))
            {
                temporary.getRank().setComparision(62);
            }

            if (m == SOLOEICHEL && temporary.getRank().getName().equals("neun") && temporary.getColor().equals("eichel"))
            {
                temporary.getRank().setComparision(63);
            }

            if (m == SOLOEICHEL && temporary.getRank().getName().equals("koenig") && temporary.getColor().equals("eichel"))
            {
                temporary.getRank().setComparision(64);
            }

            if (m == SOLOEICHEL && temporary.getRank().getName().equals("zehn") && temporary.getColor().equals("eichel"))
            {
                temporary.getRank().setComparision(65);
            }

            if (m == SOLOEICHEL && temporary.getRank().getName().equals("ass") && temporary.getColor().equals("eichel"))
            {
                temporary.getRank().setComparision(66);
            }

        }
    }

    //geeignet für Ramsch, Solo Herz und alle Sauspiele
    public void comparisionSetStandard(LinkedList<Card> c1)
    {
        Card temporary = null;
        for(int i = c1.size(); i>0; i--)
        {
            if (temporary.getRank().getName().equals("sieben") && temporary.getColor().getName().equals("herz"))
            {
                temporary.getRank().setComparision(61);
            }

            if (temporary.getRank().getName().equals("acht") && temporary.getColor().getName().equals("herz"))
            {
                temporary.getRank().setComparision(62);
            }

            if (temporary.getRank().getName().equals("neun") && temporary.getColor().getName().equals("herz"))
            {
                temporary.getRank().setComparision(63);
            }

            if (temporary.getRank().getName().equals("koenig") && temporary.getColor().getName().equals("herz"))
            {
                temporary.getRank().setComparision(64);
            }

            if (temporary.getRank().getName().equals("zehn") && temporary.getColor().getName().equals("herz"))
            {
                temporary.getRank().setComparision(65);
            }

            if (temporary.getRank().getName().equals("ass") && temporary.getColor().getName().equals("herz"))
            {
                temporary.getRank().setComparision(66);
            }
        }
    }

    //muss immer gemacht werden
    public void comparisionOberUnter(LinkedList<Card> c1)
    {
        Card temporary = null;
        for(int i = c1.size(); i>0; i--)
        {
            if (temporary.getRank().getName().equals("unter") && temporary.getColor().getName().equals("schellen"))
            {
                temporary.getRank().setComparision(71);
            }

            if (temporary.getRank().getName().equals("unter") && temporary.getColor().getName().equals("herz"))
            {
                temporary.getRank().setComparision(72);
            }

            if (temporary.getRank().getName().equals("unter") && temporary.getColor().getName().equals("laub"))
            {
                temporary.getRank().setComparision(73);
            }

            if (temporary.getRank().getName().equals("unter") && temporary.getColor().getName().equals("eichel"))
            {
                temporary.getRank().setComparision(74);
            }

            if (temporary.getRank().getName().equals("ober") && temporary.getColor().getName().equals("schellen"))
            {
                temporary.getRank().setComparision(81);
            }

            if (temporary.getRank().getName().equals("ober") && temporary.getColor().getName().equals("herz"))
            {
                temporary.getRank().setComparision(82);
            }

            if (temporary.getRank().getName().equals("ober") && temporary.getColor().getName().equals("laub"))
            {
                temporary.getRank().setComparision(83);
            }

            if (temporary.getRank().getName().equals("ober") && temporary.getColor().getName().equals("eichel"))
            {
                temporary.getRank().setComparision(84);
            }

        }
    }
}
