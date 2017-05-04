package de.emg_haar.schafkopfdeluxe.game;

import java.util.LinkedList;
import java.util.Stack;

import de.emg_haar.schafkopfdeluxe.game.card.Card;
import de.emg_haar.schafkopfdeluxe.game.card.CardRank;

import static de.emg_haar.schafkopfdeluxe.game.Mode.MODE_TYPE.*;

/**
 * Created by noah on 30.01.17.
 */

//Klasse die alles um den Spielmodus enthält
public class Mode {
    //Enumeration für alle Modes
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

        //Methode, die einen Modues zum String macht
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

        //Vergleichen von zwei Modes
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
    private int trumpfcolor;

    public Mode(MODE_TYPE m)
    {
        mode_type = m;
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
    public int getTrumpfcolor()
    {
        return trumpfcolor;
    }

    //setter Methode
    public void setTrumpfcolor(int y)
    {
        trumpfcolor = y;
    }

    //spielbare Karten werden geckeckt und mit einer LinkedList
    //Methode zur anzeigen der spielbaren Karten
    public LinkedList showPlayableCards(LinkedList<Card> c1, Stack<Card> c2, int Ruffarbe)
    {
        //Karte, die im Stich ganz unten liegt
        Card unten = null;
        //unterste Karte wird durch pop() vom Stack geholt (Solange noch eine Karte unten liegt, wird diese genommen)
        while(c2.pop() != null)
        {
            unten = c2.pop();
        }
        //LinkedList wird in einen Array umgewandelt
        Card[] temporaryArray = new Card[c1.size()];
        //Erste Karte von der LinkedList wird einem Index zugeordnet und danach aus der LinkedList gelöscht
        for(int j=c1.size(); j>0; j--)
        {
            temporaryArray[j]= c1.removeFirst();
        }
        //LinkedList die am Ende zurückgegeben wird
        LinkedList<Card> giveBack = new LinkedList<Card>();
        //Alle Karten im Array werden geprüft
        for(int i=temporaryArray.length; i>0; i--)
        {
            //Wenn die "Prüfung" positiv ausfällt, wird die Karte zur LinkedList hinzugefügt
            if(pruefen(temporaryArray[i-1], unten, temporaryArray, Ruffarbe) == true)
            {
                //Karte wird zur LinkedList hinzugefügt
                giveBack.addFirst(temporaryArray[i-1]);
            }
        }
        //LinkedList wird zurückgegeben
        return giveBack;
    }

    //Methode zum Prüfen einer Karte und ob sie gespielt werden darf bzw. nicht
    public boolean pruefen(Card c1, Card unten, Card[] c3, int Ruffarbe)
    {
        //Boolean zur Bestimmung, ob das Ass auf der Hand ist
        boolean ass = false;
        //Wenn unten keine Karte liegt
        if(unten == null)
        {
            //Wenn die angespielte Farbe die Ruffarbe ist (Farbe, auf die gespielt wird)
            if(c1.getColor().convertToInt() == Ruffarbe)
            {
                //Durchschauen, ob das Ass auf der Hand ist
                for(int p=c3.length;p>0;p--)
                {
                    //Wenn das Ass dabei ist wird der Boolean auf true gesetzt
                    if(c3[p-1].getColor().convertToInt() == Ruffarbe && c3[p-1].getRank().getName().equals("ass"))
                    {
                        ass = true;
                    }
                }
                //Wenn das Ass vorhanden ist
                if(ass == true)
                {
                    //Wenn die zu überprüfende Karte das Ass ist, darf sie gelegt werden
                    if(c1.getRank().getName().equals("ass"))
                    {
                        return true;
                    }
                    //Sonst wird geschaut, ob der Spieler mehr als 4 Karten der Farbe hat
				else
                    {
                        //Int zur Bestimmung der Anzahl der Karten
                        int y = 0;
                        //Durchlaufen aller Karten der Hand
                        for(int k=c3.length;k>0;k--)
                        {
                            //Farbe der Karten wird mit der Ruffarbe abgeglichen --> Ja, dann wird y um 1 erhöht
                            if(c3[k-1].getColor().convertToInt() == Ruffarbe)
                            {
                                //Ober und Unter der Ruffarbe werden ausgeschlossen --> Zählen nicht als normale Farbkarten
                                if(c3[k-1].getRank().getName().equals("ober") || c3[k-1].getRank().getName().equals("unter"))
                                {}
                                //y wird um 1 erhöht
                                else
                                {
                                    y = y+1;
                                }
                            }
                        }
                        //Wenn y größer vier, darf sie gespielt werden
                        return y >= 4;
                    }
                }
                //Wenn das Ass nicht da ist, darf er die Karte spielen
                else
                {
                    return true;
                }

            }
            //Wenn es nicht mal die Ruffarbe ist, dann darf er sie auch spielen
            else
            {
                return true;
            }
        }
        //Wenn unten eine Karte liegt
        else {
            //Wenn die unterste Karte die gleiche Farbe hat, wie die Karte, die geprüft wird und die Karte kein Ober bzw. Unter ist
            if (c1.getColor() == unten.getColor() && c1.getRank() != CardRank.UNTER && c1.getRank() != CardRank.OBER)
            {
                //Wenn die angespielte Farbe die Ruffarbe ist (Farbe, auf die gespielt wird)
                if (c1.getColor().convertToInt() == Ruffarbe) {
                    //Durchschauen, ob das Ass auf der Hand ist
                    for (int z = c3.length; z > 0; z--) {
                        //Wenn ja, wird der boolean auf true gesetzt
                        if (c3[z - 1].getColor().convertToInt() == Ruffarbe && c3[z - 1].getRank().getName().equals("ass"))
                        {
                            ass = true;
                        }
                    }
                    //Wenn das Ass auf der Hand vorhanden ist
                    if (ass == true)
                    {
                        //Wenn die Karte das Ass ist, darf er sie legen
                        if (c1.getRank() == CardRank.ASS)
                        {
                            return true;
                        }
                        //Wenn die Karte nicht das Ass ist, darf er sie nicht legen
                        else
                        {
                            return false;
                        }
                    }
                    //Er darf die Karte legen, wenn es nicht das Ass ist
                    else
                    {
                        return true;
                    }
                }
                //Er darf die Karte legen, wenn die vorherigen Bedingungen nicht erfüllt sind (RufAss nicht auf Hand vorhanden bzw. Karte ist nicht das RufAss)
                else
                    {
                    return true;
                }
            } else if (c1.getRank() == CardRank.UNTER && c1.getRank() == CardRank.OBER) {
                //Boolean zum Herausfinden, ob eine weitere Karte der gleichen Farbe in der Hand ist, die nicht der gespielte Ober bzw. Unter ist
                boolean weitereKarteOU = false;
                //Durchsuchen des Feldes auf andere Karten der gleichen Farben, die nicht der gesuchte Ober bzw. Unter sind
                for (int q = c3.length; q > 0; q--) {
                    if (c3[q - 1].getColor() == unten.getColor() && c1.getRank().getName() != "unter" && c1.getRank().getName() != "ober") {
                        weitereKarteOU = true;
                    }
                }
                //Wenn keine weitere Karte der Farbe vorhanden ist, darf die Karte gelegt werden
                return !weitereKarteOU;
            }
            //Wenn die unterste Karte eine andere Farbe hat und die Karte kein Ober bzw. Unter ist
            else {
                //Boolean zum Herausfinden, ob eine weitere Karte der gleichen Farbe in der Hand ist
                boolean weitereKarte = false;
                for (int q = c3.length; q > 0; q--) {
                    if (c3[q - 1].getColor() == unten.getColor()) {
                        weitereKarte = true;
                    }
                }
                //Wenn keine weitere Karte der Farbe vorhanden ist, darf die Karte gelegt werden
                return !weitereKarte;
            }
        }
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

    public boolean SauSpielSpielbar(LinkedList<Card> c1, Mode c2)
    {
        switch(c2.getModeType()) {
            case SAUSPIELGRAS: return assSuchen(c1, c2, "laub");
            case SAUSPIELEICHEL: return assSuchen(c1, c2, "eichel");
            case SAUSPIELSCHELLEN: return assSuchen(c1, c2, "schellen");
            default: return true;
        }
    }

    public boolean assSuchen(LinkedList<Card> c3, Mode c4, String colorNew)
    {
        for(int g=c3.size(); g>0; g--)
        {
            Card card = c3.removeFirst();
            if(card.getRank() == CardRank.ASS && card.getColor().getName().equals(colorNew))
            {
                return false;
            }
        }
        return true;
    }
}