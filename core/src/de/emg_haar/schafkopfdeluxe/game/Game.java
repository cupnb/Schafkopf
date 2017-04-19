package de.emg_haar.schafkopfdeluxe.game;

import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;
import java.io.*;
import de.emg_haar.schafkopfdeluxe.game.card.Card;
import de.emg_haar.schafkopfdeluxe.game.card.CardColor;
import de.emg_haar.schafkopfdeluxe.game.card.CardRank;
import sun.security.provider.ConfigFile;

//Hauptklasse, die alles steuert, und über die das Game hauptsächlich läuft --> Vergleiche typisches Schafkopfspiel
public class Game {

    //Feld für die Spieler; 4 Spieler werden dem Feld im Konstruktor zugewiesen
    private Player[] players;
    //dient zur Bestimmung, wer dran ist
    private enum Turnstate{P0, P1, P2, P3}
    //speichert, wer dran ist
    private Turnstate turnState;
    //Person, die imaginär die Karten austeilt; wichtig für Ansage wer spielt und wer rauskommt
    private int dealer;
    //Stack für die Karten, die gerade im Stich gespielt wurden
    private Stack<Card> played;
    //Modus des SPiels (Bsp.: Herzsolo)
    private Mode mode;
    //Beinhaltet 32 Spielkarten
    private Deck deck;
    //Stack, der alle Karten aufnimmt --> wird zum Mischen in der nächsten Runde verwendet
    private Stack<Card> dump;
    //Beschreibung der Trumpffarbe mithilfe eines int
    //0 = Wenz
    //1 = Schellen
    //2 = Herz
    //3 = Laub
    //4 = Eichel
    private int trumpfcolor;
    //Rundennummer (zählt nach jeder gespielte Partie hoch)
    private int roundNumber;
    //Matrix zum Speichern der Karten --> Nötig für den Bot
    private Card [][] matrix;
    //Anzahl der gespielten Stiche
    private int playedStiche;

    public Game(Player p0, Player p1, Player p2, Player p3) {
        //Random Zahl zur Bestimmung des Dealers in der ersten Runde
        Random rnd = new Random();
        //Initialisierung des Feld Players (siehe Attribute)
        players = new Player[4];

        //irgendein Graphikzeugs
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

        //Initialisierung von Stacks und dem Deck
        deck = new Deck();
        dump = new Stack<Card>();
        played = new Stack<Card>();

        //Der Geber wird zufaellig bestimmt
        dealer = rnd.nextInt(4);
        roundNumber = 0;
        trumpfcolor = 2;
        matrix = new Card[4][8];
        playedStiche = 0;
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

    //Fügt eine gespielte Karte zu dump und played hinzu
    public void addgespielteKarte(Card f){
        dump.add(f);
        played.add(f);
    }

    public void initialize()
    {
        //PlayedStiche wird reseted
        playedStiche = 0;

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

        //int zum "Anfänger" der "Fragerunde"
        int auswähler = (dealer + 1);
        //Anzahl der Leute, die spielen wollen
        int anzahlSpielenWollen = 0;
        //boolean Feld zur Bestimmung, wer spielen will und wer davon spielt
        boolean[] willSpieler = new boolean[4];
        //for: Abfrage wer spielen will --> True setzen des jeweiligen Indexes
        for (int i = 0; i<4; i++)
        {
             if(players[(auswähler + i)%4].getWannaplay() == true)
             {

                 willSpieler[i] = true;
                 //Anzahl der Personen, die spielen wollen wird um 1 erhöht
                 anzahlSpielenWollen = anzahlSpielenWollen + 1;
             }
             else
             {
                 willSpieler[i] = false;
             }
        }
        //Abfrage wer SPIELT
        //int zum Anfänger des Auswahlverfahrens
        int willspieler = (dealer + 1);
        //Arry Mode zur Auswahl des Mode's durch Ausnutzen des Enums
        Mode[] modefeld = new Mode[4];
        //int zur Festlegung des endgültigen Spielers --> Festlegen von Spieler und NIcht-Spieler
        int endgültigerPlayer = 4;
        //Wenn niemand spielen will --> Ramsch
        if(anzahlSpielenWollen  == 0)
        {
            mode.setModeType(Mode.MODE_TYPE.RAMSCH);
        }
        //Wenn genau eine Person spielen will, dann wird der gewünschte Mode der Person genommen
        if(anzahlSpielenWollen == 1)
        {
            for (int p = 0; p < 4; p++)
            {
                if (willSpieler[p] == true)
                {
                    mode.setModeType(players[p].play("SAUSPIELEICHEL"));
                    //SauspielEichel nur ein Beispiel --> Eingabefeld einfügen
                    endgültigerPlayer = p;
                }
            }
        }
        //Wenn mehr als eine Person spielen will, wird aufgrnd der Ordinalzahl des Modes abgewägt, was gespielt wird
        if(anzahlSpielenWollen > 1)
        {
            //Modes der Spieler, die spielen wollen werden aufgenommen in das Mode Array
            for (int i = 0; i < 4; i++)
            {
                modefeld[i].setModeType(players[(willspieler + i) % 4].play("SAUSPIELEICHEL"));
            }

            //vergleicht ob jemand der später spielen will einen höher priorisierten Mode spielen will (über die Ordinalzahl)
            for (int z = 0; z < 4; z++) {
                if (modefeld[z] != null) {
                    //erster Mode wird gesetzt
                    if (mode == null)
                    {
                        mode.setModeType(modefeld[z].getModeType());
                        endgültigerPlayer = z;
                    }
                    //Vergleich der Modes aufgrund der Ordinalzahl im Enum
                    if (modefeld[z].getOrdinal(modefeld[z].toString()) > mode.getOrdinal(mode.toString())) {
                        mode.setModeType(modefeld[z].getModeType());
                        endgültigerPlayer = z;
                    }

                }
            }
            //Mode fürSpiel ist der endgültige Mode
        }
        if(endgültigerPlayer<4)
        {
            //Spieler wird gesetzt
            players[endgültigerPlayer].setPlayer(true);
        }

        //Zweiter Spieler wird gesucht: Bei Sauspiel Eichel
        if (mode.getModeType() == Mode.MODE_TYPE.SAUSPIELEICHEL)
        {
            int now = sucheKarte(players[0].getHand(), players[1].getHand(), players[2].getHand(), players[3].getHand(),CardRank.ASS, CardColor.EICHEL);
            players[now].setPlayer(true);
        }

        //Zweiter Spieler wird gesucht: Bei Sauspiel Schellen
        if (mode.getModeType() == Mode.MODE_TYPE.SAUSPIELSCHELLEN)
        {
            int now = sucheKarte(players[0].getHand(), players[1].getHand(), players[2].getHand(), players[3].getHand(),CardRank.ASS, CardColor.SCHELLEN);
            players[now].setPlayer(true);
        }

        //Zweiter Spieler wird gesucht: Bei Sauspiel Gras
        if (mode.getModeType() == Mode.MODE_TYPE.SAUSPIELGRAS)
        {
            int now = sucheKarte(players[0].getHand(), players[1].getHand(), players[2].getHand(), players[3].getHand(),CardRank.ASS, CardColor.LAUB);
            players[now].setPlayer(true);
        }

        //Trumpfcolor wird aufgrund des MOdes ferstgelegt
        if (mode.getModeType() == Mode.MODE_TYPE.SOLOGRAS)
        {
            trumpfcolor = 3;
        }

        if (mode.getModeType() == Mode.MODE_TYPE.SOLOEICHEL)
        {
            trumpfcolor = 4;
        }

        if (mode.getModeType() == Mode.MODE_TYPE.SOLOSCHELLEN)
        {
            trumpfcolor = 1;
        }

        if (mode.getModeType() == Mode.MODE_TYPE.WENZ)
        {
            trumpfcolor = 0;
        }

        //Vergleichswerte für Ober und Unter werden angepasst (z.B. Eichel Ober ist über Schellen Ober)
        mode.comparisionOberUnter(players[0].getHand());
        mode.comparisionOberUnter(players[1].getHand());
        mode.comparisionOberUnter(players[2].getHand());
        mode.comparisionOberUnter(players[3].getHand());

        //Vergleichswerte der Trumpfarbe werden angepasst (solange es nicht Herz ist) bzw. die Vergleichswerte werden für einen Wenz angepasst
        if(trumpfcolor == 0 || trumpfcolor == 1 || trumpfcolor == 3 || trumpfcolor == 4)
        {
            mode.comparisionAktualisieren(players[0].getHand(), mode.getModeType());
            mode.comparisionAktualisieren(players[1].getHand(), mode.getModeType());
            mode.comparisionAktualisieren(players[2].getHand(), mode.getModeType());
            mode.comparisionAktualisieren(players[3].getHand(), mode.getModeType());
        }

        //Vergleichswerte von Herz werden erhöht, wenn Herz Trumpffarbe ist
        if(trumpfcolor == 2)
        {
            mode.comparisionSetStandard(players[0].getHand());
            mode.comparisionSetStandard(players[1].getHand());
            mode.comparisionSetStandard(players[2].getHand());
            mode.comparisionSetStandard(players[3].getHand());
        }

        //Aufruf der Methode loop führt 8 Stiche durch
        for (int i =0; i<8; i++)
        {
            loop();
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

        //Auszählen der Punkte und Bekanntmachung des Gewinners
        ende();
    }

    //Methode für einen Stich
    public void loop()
    {
        //Die Anzahl der gespielten Stiche wird um 1 erhöht
        playedStiche++;
        //Spieler, der die höchste Karte gelegt hat
        Player best = null;
        //höchste Karte, die gerade im Stich liegt
        Card highest = null;
        //for: 4 Spieler legen Karte
        for (int x = 0; x < 4; x++) {
            //players[turnState.ordinal()].yourTurn();
            Card Spielkarte = players[(dealer + 1 + x) % 4].kartelegen();
            matrix[(dealer + 1 + x) % 4][playedStiche-1] = Spielkarte;
            //Karte wird zu Dump und Played hinzugefügt
            addgespielteKarte(Spielkarte);
            //erste Karte wird hingelegt
            if (best == null)
            {
                //bester Spieler wird auf den Anfangsspieler gesetzt
                best = players[(dealer + 1 + x) % 4];
                //Karte wird als höchste bezeichnet
                highest = Spielkarte;
            }
            else
            {
                //Vergleich der Spielkarten aufgrund der Farbe --> Farbe mit der höheren Zahl bei gleicher Farbe wird highest
                if (Spielkarte.getColor() == highest.getColor())
                {
                    if(Spielkarte.getRank().getComparision() > highest.getRank().getComparision())
                    {
                        highest = Spielkarte;
                        best = players[(dealer + 1 + x) % 4];
                    }
                }
                //Wenn es nicht die gleiche Farbe ist, bleibt die liegende Karte die höchste, außer der Vergleichswert ist größer als 60 (Vergleichswerte höher 60 --> Trumpf)
                else
                {
                    if(Spielkarte.getRank().getComparision() < 60)
                    {
                        highest = Spielkarte;
                        best = players[(dealer + 1 + x) % 4];
                    }
                }
            }
        }
        //Stich wird dem Winner des Stichs zugeordnet
        best.addStich(played);
        //Stichpunktanzahl des Winners wird erhöht --> Dient zur Auszählung am Ende (siehe Methode getPunkte() in Player)
        best.stichpunkterhöhen();

    }

    //nächster Spieler wird ausgewählt
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

    //spielbare KArten werden dem Spieler gezeigt
    private void showPlayableCards(Player p)
    {
        p.showPlayableCards(mode.checkPlayable(p.getHand(), played));
    }

    //Suche nach einer bestimmten Karte aufgrund von 4 Listen und dem/der CardRank/CardColor
    public int sucheKarte(LinkedList<Card> c1, LinkedList<Card> c2, LinkedList<Card> c3, LinkedList<Card> c4, CardRank gesuchtRank, CardColor gesuchtColor)
    {

        //1. Liste wird durchsucht
        for(int i=c1.size(); i>0; i--)
        {
            if (c1.getFirst().getColor() == gesuchtColor && c1.getFirst().getRank() == gesuchtRank)
            {
                return 0;
            }
            c1.removeFirst();
        }

        //2. Liste wird durchsucht
        for(int i=c2.size(); i>0; i--)
        {
            if (c2.getFirst().getColor() == gesuchtColor && c2.getFirst().getRank() == gesuchtRank)
            {
                return 1;
            }
            c1.removeFirst();
        }

        //3. Liste wird durchsucht
        for(int i=c3.size(); i>0; i--)
        {
            if (c3.getFirst().getColor() == gesuchtColor && c3.getFirst().getRank() == gesuchtRank)
            {
                return 2;
            }
            c3.removeFirst();
        }

        //4. Liste wird durchsucht
        for(int i=c4.size(); i>0; i--)
        {
            if (c4.getFirst().getColor() == gesuchtColor && c4.getFirst().getRank() == gesuchtRank)
            {
                return 3;
            }
            c4.removeFirst();
        }

        return -1;
    }

    //Methode, die am Ende aufgerufen wird
    public void ende()
    {
        //Punkte der Spieler
        int punktePlayer = 0;
        //Punkte der Nichtspieler
        int punkteNotPlayer = 0;
        //Feststellen des Verlierers aufgrund der Punktanzahl
        if(mode.getModeType() == Mode.MODE_TYPE.RAMSCH)
        {
            //int zum Speichern des Spielers mit der höchsten Punktezahl
            int lost = 5;
            //int zum Speichern des Punktestands dieses Spielers
            int most = 0;
            //4 Spieler werden durchgegangen
            for(int i = 0; i<4; i++)
            {
                //Wenn der nächste Spieler mehr Punkte hat, wird er gespeichert
                if(players[i].getPunkte()> most)
                {
                    //Festelegung der Punkte zum weiteren Vergleich
                    most = players[i].getPunkte();
                    //Festlegung des Spielers zum späteren Aufrufen
                    lost = i;
                }
            }
            //Bekanntmachung des Verlierers
            System.out.println(players[lost].getName() +" hat verloren!");
        }
        else
        {
            //Spieler werden festgelegt in einem Feld
            Player[] playerspp = new Player[2];
            //Nicht-Spieler werden festgelegt in einem Feld
            Player[] notplayerspp = new Player[3];
            //Durchlaufen der 4 Spieler
            for (int b = 0; b < 4; b++)
            {
                //Speichern der Punkte der Spieler
                if (players[b].getPlayer() == true) {
                    playerspp[b % 2] = players[b];
                    punktePlayer = punktePlayer + players[b].getPunkte();
                }
                //Speichern der Punkte der Nicht-Spieler
                else
                    {
                    notplayerspp[b % 2] = players[b];
                    punkteNotPlayer = punkteNotPlayer + players[b].getPunkte();
                }
            }

            //Auswertung der Punktzahl
            if (punktePlayer <= punkteNotPlayer)
            {
                //Bekanntmachung, dass die NIcht-Spieler gewonnen haben
                System.out.println(notplayerspp[0].getName() + " und " + notplayerspp[1] + " haben gewonnen!");
            }
            else
            {
                //Bekanntmachung, dass die Spieler gewonnen haben
                System.out.println(playerspp[0].getName() + " und " + playerspp[1] + " haben gewonnen!");
            }
        }
    }

}



