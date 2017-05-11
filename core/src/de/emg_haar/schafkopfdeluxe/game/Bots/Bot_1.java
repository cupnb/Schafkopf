package de.emg_haar.schafkopfdeluxe.game.Bots;
import java.util.LinkedList;

import de.emg_haar.schafkopfdeluxe.game.Mode;
import de.emg_haar.schafkopfdeluxe.game.card.Card;
import de.emg_haar.schafkopfdeluxe.game.card.CardColor;
import de.emg_haar.schafkopfdeluxe.game.card.CardRank;
import sun.awt.image.ImageWatched;

public class Bot_1 extends Bot {

    /*

    Die Attribute, die Bot_1 erbt:

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
    protected boolean bot;

    */

    public Bot_1() {
        this.name = "Faust";
        bot = true;
    }

    /**
     * Um das zu verstehen, bieten sich die Methoden an, die ich aus Javadocs habe:
     * https://docs.oracle.com/javase/8/docs/api/index.html?java/util/LinkedList.html
     *
     * Die Abfragen sehen wie eine ganze Menge Müll aus.
     * Sie erleichtern aber so gut wie ALLES, was danach kommt.
     *
     * @return gewünschter Spielmodus des Bots
     */
    private String chooseGamemode() {

        // Es braucht alle 8 Karten auf der Hand.
        if( hand.size() != 8 ) {
            return null;
        }

        // Hilfsvariablen:

        // Hilskarte
        Card Karte;

        // Anzahl Herz + Anzahl Ober + Anzahl Unter
        int anzahlTrumpf = 0;
        for(int i = 0; i <= 7; i++) {
            Karte = hand.get(i);
            if(
                    Karte.getColor() == CardColor.HERZ ||
                    Karte.getRank() == CardRank.UNTER ||
                    Karte.getRank() == CardRank.OBER
                    ){
                anzahlTrumpf++;
            }
        }

        // Anzahl der Säue, die nicht Trumpf sind
        int anzahlFarbSau = 0;
        for( int i = 0; i <= 7; i++ ) {
            Karte = hand.get(i);
            if(
                    Karte.getRank() == CardRank.ASS &&
                    Karte.getColor() != CardColor.HERZ
                    ) {
                anzahlFarbSau++;
            }
        }

        // Anzahl der Säue
        int anzahlSau = 0;
        for( int i = 0; i <= 7; i++ ) {
            Karte = hand.get(i);
            if(
                    Karte.getRank() == CardRank.ASS
                    ) {
                anzahlSau++;
            }
        }

        // Anzahl Ober
        int anzahlOber = 0;
        for( int i = 0; i <= 7; i++ ) {
            Karte = hand.get(i);
            if( Karte.getRank() == CardRank.OBER ) {
                anzahlOber++;
            }
        }

        // Anzahl Unter
        int anzahlUnter = 0;
        for( int i = 0; i <= 7; i++ ) {
            Karte = hand.get(i);
            if( Karte.getRank() == CardRank.UNTER ) {
                anzahlUnter++;
            }
        }

        // Anzahl Ober außer SchellenOber
        int anzahlBrems = anzahlOber;
        for( int i = 0; i <= 7; i++ ) {
            Karte = hand.get(i);
            if(
                    Karte.getRank() == CardRank.OBER &&
                    Karte.getColor() == CardColor.SCHELLEN
                    ) {
                anzahlBrems--;
            }
        }

        // Anzahl Eichel, die nicht Trumpf sind
        int anzahlFarbEichel = 0;
        for( int i = 0; i <= 7; i++ ) {
            Karte = hand.get(i);
            if(
                    Karte.getColor() == CardColor.EICHEL &&
                    Karte.getRank() != CardRank.OBER &&
                    Karte.getRank() != CardRank.UNTER
                    ) {
                anzahlFarbEichel++;
            }
        }

        // Anzahl Herz, die nicht Obber oder Unter sind
        int anzahlFarbHerz = 0;
        for( int i = 0; i <= 7; i++ ) {
            Karte = hand.get(i);
            if(
                    Karte.getColor() == CardColor.HERZ &&
                    Karte.getRank() != CardRank.OBER &&
                    Karte.getRank() != CardRank.UNTER
                    ) {
                anzahlFarbHerz++;
            }
        }

        // Anzahl Gras, die nicht Trumpf sind
        int anzahlFarbGras = 0;
        for( int i = 0; i <= 7; i++ ) {
            Karte = hand.get(i);
            if(
                    Karte.getColor() == CardColor.LAUB &&
                    Karte.getRank() != CardRank.OBER &&
                    Karte.getRank() != CardRank.UNTER
                    ) {
                anzahlFarbGras++;
            }
        }

        // Anzahl Schelle, die nicht Trumpf sind
        int anzahlFarbSchelle = 0;
        for( int i = 0; i <= 7; i++ ) {
            Karte = hand.get(i);
            if(
                    Karte.getColor() == CardColor.SCHELLEN &&
                    Karte.getRank() != CardRank.OBER &&
                    Karte.getRank() != CardRank.UNTER
                    ) {
                anzahlFarbSchelle++;
            }
        }

        // Anzahl Säue + Anzahl 10er
        int anzahlSchmier = 0;
        for( int i = 0; i <= 7; i++ ) {
            Karte = hand.get(i);
            if(
                    Karte.getRank() == CardRank.ASS ||
                    Karte.getRank() == CardRank.ZEHN
                    ) {
                anzahlSchmier++;
            }
        }

        /*
        Es tut mir so Leid, dass das so unüberisichtlich ist.
        Hier eine Zusammenfassung der Hilfsvariablen:

        anzahlTrumpf
        anzahlFarbSau
        anzahlSau
        anzahlOber
        anzahlUnter
        anzahlBrems
        anzahlFarbEichel
        anzahlFarbHerz
        anzahlFarbGras
        anzahlFarbSchelle
        anzahlSchmier
         */



        // Schritt 1:   Eichelsolo

        // Mit 8 Trumpfkarten:
        // Fast immer, eine Trumpfschmier oder eine hohe Karte (Eichel Ober) sollte man selber haben
        if(
                anzahlOber + anzahlUnter + anzahlFarbEichel == 8 &&
                anzahlBrems >= 1 &&
                anzahlSchmier >= 1
                ) {
            return "SOLOEICHEL";
        }
        // Mit 7 Trumpfkarten:
        // Mit einem Spatz (niedrige Karte): Mindestens 2 Ober und 2 Unter
        if(
                anzahlOber + anzahlUnter + anzahlFarbEichel == 7 &&
                anzahlOber >= 2 &&
                anzahlOber + anzahlUnter >= 4
                ) {
            return "SOLOEICHEL";
        }
        // Mit einer Fehlsau: Zumindest eine Trumpfschmier
        if(
                anzahlOber + anzahlUnter + anzahlFarbEichel == 7 &&
                ( anzahlSau == 2 || ( anzahlSau == 1 && anzahlSchmier >= 2 ) )
                ) {
            return "SOLOEICHEL";
        }
        // Mit 6 Trumpfkarten:
        // 2 Laufende Ober oder 3 Ober und eine Sau
        if(
                anzahlOber + anzahlUnter + anzahlFarbEichel == 6 &&
                ( anzahlBrems >= 2 || ( anzahlOber >= 1 && anzahlSau >= 1 ) )
                ) {
            return "SOLOEICHEL";
        }
        // Mit 5 Trumpfkarten:
        // 3 Laufende Ober und 2 Sauen, 3. Farbe frei
        if(
                anzahlOber + anzahlUnter + anzahlFarbEichel == 5 &&
                anzahlBrems == 3 &&
                anzahlSau >= 2 &&
                (anzahlFarbGras == 0 || anzahlFarbSchelle == 0 || anzahlFarbHerz == 0)
                ) {
            return "SOLOEICHEL";
        }


        // Schritt 2:   Grassolo

        // Mit 8 Trumpfkarten:
        // Fast immer, eine Trumpfschmier oder eine hohe Karte (Eichel Ober) sollte man selber haben
        if(
                anzahlOber + anzahlUnter + anzahlFarbGras == 8 &&
                anzahlBrems >= 1 &&
                anzahlSchmier >= 1
                ) {
            return "SOLOGRAS";
        }
        // Mit 7 Trumpfkarten:
        // Mit einem Spatz (niedrige Karte): Mindestens 2 Ober und 2 Unter
        if(
                anzahlOber + anzahlUnter + anzahlFarbGras == 7 &&
                anzahlOber >= 2 &&
                anzahlOber + anzahlUnter >= 4
                ) {
            return "SOLOGRAS";
        }
        // Mit einer Fehlsau: Zumindest eine Trumpfschmier
        if(
                anzahlOber + anzahlUnter + anzahlFarbGras == 7 &&
                ( anzahlSau == 2 || ( anzahlSau == 1 && anzahlSchmier >= 2 ) )
                ) {
            return "SOLOGRAS";
        }
        // Mit 6 Trumpfkarten:
        // 2 Laufende Ober oder 3 Ober und eine Sau
        if(
                anzahlOber + anzahlUnter + anzahlFarbGras == 6 &&
                ( anzahlBrems >= 2 || ( anzahlOber >= 1 && anzahlSau >= 1 ) )
                ) {
            return "SOLOGRAS";
        }
        // Mit 5 Trumpfkarten:
        // 3 Laufende Ober und 2 Sauen, 3. Farbe frei
        if(
                anzahlOber + anzahlUnter + anzahlFarbGras == 5 &&
                anzahlBrems == 3 &&
                anzahlSau >= 2 &&
                (anzahlFarbEichel == 0 || anzahlFarbHerz == 0 || anzahlFarbSchelle == 0)
                ) {
            return "SOLOGRAS";
        }


        // Schritt 3:   Herzsolo

        // Mit 8 Trumpfkarten:
        // Fast immer, eine Trumpfschmier oder eine hohe Karte (Eichel Ober) sollte man selber haben
        if(
                anzahlOber + anzahlUnter + anzahlFarbHerz == 8 &&
                        anzahlBrems >= 1 &&
                        anzahlSchmier >= 1
                ) {
            return "SOLOHERZ";
        }
        // Mit 7 Trumpfkarten:
        // Mit einem Spatz (niedrige Karte): Mindestens 2 Ober und 2 Unter
        if(
                anzahlOber + anzahlUnter + anzahlFarbHerz == 7 &&
                        anzahlOber >= 2 &&
                        anzahlOber + anzahlUnter >= 4
                ) {
            return "SOLOHERZ";
        }
        // Mit einer Fehlsau: Zumindest eine Trumpfschmier
        if(
                anzahlOber + anzahlUnter + anzahlFarbHerz == 7 &&
                        ( anzahlSau == 2 || ( anzahlSau == 1 && anzahlSchmier >= 2 ) )
                ) {
            return "SOLOHERZ";
        }
        // Mit 6 Trumpfkarten:
        // 2 Laufende Ober oder 3 Ober und eine Sau
        if(
                anzahlOber + anzahlUnter + anzahlFarbHerz == 6 &&
                        ( anzahlBrems >= 2 || ( anzahlOber >= 1 && anzahlSau >= 1 ) )
                ) {
            return "SOLOHERZ";
        }
        // Mit 5 Trumpfkarten:
        // 3 Laufende Ober und 2 Sauen, 3. Farbe frei
        if(
                anzahlOber + anzahlUnter + anzahlFarbHerz == 5 &&
                        anzahlBrems == 3 &&
                        anzahlSau >= 2 &&
                        (anzahlFarbEichel == 0 || anzahlFarbGras == 0 || anzahlFarbSchelle == 0)
                ) {
            return "SOLOHERZ";
        }


        // Schritt 4:   Schellensolo

        // Mit 8 Trumpfkarten:
        // Fast immer, eine Trumpfschmier oder eine hohe Karte (Eichel Ober) sollte man selber haben
        if(
                anzahlOber + anzahlUnter + anzahlFarbSchelle == 8 &&
                anzahlBrems >= 1 &&
                anzahlSchmier >= 1
                ) {
            return "SOLOSCHELLEN";
        }
        // Mit 7 Trumpfkarten:
        // Mit einem Spatz (niedrige Karte): Mindestens 2 Ober und 2 Unter
        if(
                anzahlOber + anzahlUnter + anzahlFarbSchelle == 7 &&
                anzahlOber >= 2 &&
                anzahlOber + anzahlUnter >= 4
                ) {
            return "SOLOSCHELLEN";
        }
        // Mit einer Fehlsau: Zumindest eine Trumpfschmier
        if(
                anzahlOber + anzahlUnter + anzahlFarbSchelle == 7 &&
                ( anzahlSau == 2 || ( anzahlSau == 1 && anzahlSchmier >= 2 ) )
                ) {
            return "SOLOSCHELLEN";
        }
        // Mit 6 Trumpfkarten:
        // 2 Laufende Ober oder 3 Ober und eine Sau
        if(
                anzahlOber + anzahlUnter + anzahlFarbSchelle == 6 &&
                ( anzahlBrems >= 2 || ( anzahlOber >= 1 && anzahlSau >= 1 ) )
                ) {
            return "SOLOSCHELLEN";
        }
        // Mit 5 Trumpfkarten:
        // 3 Laufende Ober und 2 Sauen, 3. Farbe frei
        if(
                anzahlOber + anzahlUnter + anzahlFarbSchelle == 5 &&
                anzahlBrems == 3 &&
                anzahlSau >= 2 &&
                (anzahlFarbEichel == 0 || anzahlFarbHerz == 0 || anzahlFarbSchelle == 0)
                ) {
            return "SOLOSCHELLEN";
        }


        // Schritt 5: Wenz

        // Eichel Unter auf der Hand?
        boolean EichelUnter = false;
        for (int i = 0; i <= 7; i++) {
            Karte = hand.get(i);
            if (
                    Karte.getRank() == CardRank.UNTER &&
                    Karte.getColor() == CardColor.EICHEL
                    ) {
                EichelUnter = true;
            }
        }

        // Einen regulären Wenz spielen wir bei 2 oder mehr Untern:
        if( anzahlUnter >= 3 || ( anzahlUnter == 2 && EichelUnter == true ) ) {

            // Wir sortieren die Karten in folgende Unterlisten:
            /*
             * Wofür ist die temphand da und wieso brauchst du diese Unterlisten?
             * Ulli
             */
            LinkedList<Card> temphand = new LinkedList();
            LinkedList<Card> UnterListe = new LinkedList();
            LinkedList<Card> EichelListe = new LinkedList();
            LinkedList<Card> GrasListe = new LinkedList();
            LinkedList<Card> HerzListe = new LinkedList();
            LinkedList<Card> SchelleListe = new LinkedList();

            // Stiche, die einen abgehen werden
            int verlust = 0;

            if ( EichelUnter == false ) {
                verlust++;
            }

            temphand = hand;

            for( int i = 0; i < temphand.size(); i++ ) {
                Karte = temphand.get(i);
                if( Karte.getRank() == CardRank.UNTER ) {
                    UnterListe.add(Karte);
                    temphand.remove(Karte);
                    i--;
                }
            }

            for( int i = 0; i < temphand.size(); i++ ) {
                Karte = temphand.get(i);
                if( Karte.getColor() == CardColor.EICHEL ) {
                    EichelListe.add(Karte);
                    temphand.remove(Karte);
                    i--;
                }
            }

            for( int i = 0; i < temphand.size(); i++ ) {
                Karte = temphand.get(i);
                if( Karte.getColor() == CardColor.LAUB ) {
                    GrasListe.add(Karte);
                    temphand.remove(Karte);
                    i--;
                }
            }

            for( int i = 0; i < temphand.size(); i++ ) {
                Karte = temphand.get(i);
                if( Karte.getColor() == CardColor.HERZ ) {
                    HerzListe.add(Karte);
                    temphand.remove(Karte);
                    i--;
                }
            }

            for( int i = 0; i < temphand.size(); i++ ) {
                Karte = temphand.get(i);
                if( Karte.getColor() == CardColor.SCHELLEN ) {
                    SchelleListe.add(Karte);
                    temphand.remove(Karte);
                    i--;
                }
            }

            verlust = verlust + verlustpruefen(EichelListe);
            verlust = verlust + verlustpruefen(GrasListe);
            verlust = verlust + verlustpruefen(HerzListe);
            verlust = verlust + verlustpruefen(SchelleListe);


            // Wir gehen alle Listen durch
            for( int i = 0; i <= 3; i++ ) {
                if( i == 0) {
                    temphand = EichelListe;
                }
                if( i == 1 ) {
                    temphand = GrasListe;
                }
                if( i == 2 ) {
                    temphand = HerzListe;
                }
                if( i == 3 ) {
                    temphand = SchelleListe;
                }

                /*

                kleine Karte heißt: König oder schwächer

                0 Karten:
                => 0 Stiche

                1 Karte:
                keine Sau => 1 Stich
                Sau => 0 Stiche

                2 Karten:
                2 kleine Karten => 2 Stiche
                10er ohne König => 2 Stiche
                10er mit König =>  1 Stich
                Sau ohne 10er => 1 Stich
                Sau mit 10er => 0 Stiche

                3 Karten:
                3 kleine Karten => 2 Stiche
                10er mit 2 kleinen Karten => 2 Stiche
                10er mit König und kleiner Karte => 1 Stich
                Sau mit 2 kleinen Karten => 1 Stich
                Sau, 10er und kleine Karte => 0 Stiche

                4 Karten:
                4 kleine Karten => 2 Stiche
                10er und 3 kleine Karten => 2 Stiche
                10er mit König und 2 kleine Karten => 1 Stich
                Sau und 3 kleine Karten => 1 Stich
                Sau, 10 und 2 kleine Karten => 0 Stiche

                5 Karten:
                ohne Sau und ohne 10er => 2 Stiche
                ohne Sau und mit 10er => 1 Stich
                mit Sau und ohne 10er => 1 Stich
                mit Sau und mit 10er => 0 Stiche

                6 Karten:
                ohne Sau und ohne 10er => 2 Stiche
                ohne Sau und mit 10er => 1 Stich
                mit Sau => 0 Stiche

                 */

            }

            if ( verlust <= 3 ) {
                return "WENZ";
            }

        }

        // Besondere Wenze

        if( anzahlUnter == 2 && EichelUnter == false && anzahlSau >= 3) {
            return "WENZ";
        }

        if ( anzahlUnter == 1 && EichelUnter == true && anzahlSau >= 3 && (anzahlSchmier - anzahlSau) >= 2 ){
            return "WENZ";
        }

        if ( anzahlUnter == 0 && anzahlSau == 4 && anzahlSchmier == 4) {
            return "WENZ";
        }


        // Schritt 6: Sauspiel

        // Ist das Blatt gut genug?
        boolean Sauspiel = false;

        if( anzahlTrumpf >= 6 ) {
            Sauspiel = true;
        }
        if( anzahlTrumpf == 5 ) {

        }

    }

    //Verlust an Stichen wird geprüft, die beim Wenz verloren gehen
    public int verlustpruefen(LinkedList<Card> c1)
    {
        //Wenn die LinkedList leer ist, dann geht kein Stich verloren, weil der Bot 0 Karten der Farbe auf der Hand hat
        if (c1.getFirst() == null) {
            return 0;
        }
        //Boolean Array zur Bestimmung, ob das Ass, die Zehn und Der König auf der Gand sind
        //c2[0] --> Ass
        //c2[1] --> Zehn
        //c2[2] --> König
        boolean[] c2 = new boolean[3];
        //LinkedList wird geclonet, damit man eine LinkedList leeren kann
        LinkedList<Card> c3 = (LinkedList<Card>) c1.clone();
        //LinkedList wird durchsucht, ob das Ass, die zehn und der König auf der Hand sind
        for(int z = c3.size(); z>0; z--)
        {
            //Durchsuchen
            int rank = rankFinden(c3.getFirst());
            //True setzen
            c2[rank] = true;
            //Erste Karte entfernen, damit die nächste genommen werden kann
            c3.removeFirst();
        }

        //Durchlaufen, je nachdem wie viele Karten der Farbe der Bot hat
        switch(c1.size())
        {
            //Wenn er eine Karte davon auf der Hand hat
            case 1:
                //Wenn die Karte das Ass ist
                if (c2[0])
                {
                    //Dann gehen null Stiche weg
                    return 0;
                }
                else
                {
                    //Sonst geht 1 Stich weg
                    return 1;
                }
            case 2:
                //Wenn die Zehn vorhanden ist
                if(c2[1])
                {
                    //Und wenn der König vorhanden ist
                    if(c2[2])
                    {
                        //Dann geht ein Stich weg
                        return 1;
                    }
                    //Oder wenn und das Ass vorhanden ist
                    else if(c2[0])
                    {
                        //Geht kein Stich weg
                        return 0;
                    }
                }
                //Wenn die Zehn nicht vorhanden ist, sondern das Ass mit einer anderen Karte
                else if(c2[0])
                {
                    return 1;
                }
                else
                {
                    return 2;
                }
            case 3:
                if(c2[0])
                {

                }
                else
                {
                    return 2;
                }
            default:
                return 123456789;
        }

    }

    //Karte wird geprüft, ob sie ein Ass, König oder Zehn ist --> Sonst automatisch König
    public int rankFinden(Card c1)
    {
        switch (c1.getRank()) {
            case ASS:
                return 0;
            case ZEHN:
                return 1;
            case KOENIG:
                return 2;
            default:
                return 2;
        }

    }

}
