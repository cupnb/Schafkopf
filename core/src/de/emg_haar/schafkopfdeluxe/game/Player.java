package de.emg_haar.schafkopfdeluxe.game;

import java.util.LinkedList;
import java.util.Stack;
import java.util.Scanner;
import de.emg_haar.schafkopfdeluxe.game.card.Card;

//Klasse, die alle Methoden um den Spieler beinhaltet
public class Player
{
    //"Hand" des Spielers --> Speichern aller 8 Karten in einer Liste
    protected LinkedList<Card> hand;
    //Name des Spielers
    protected String name;
    //Spiel, dem der Spieler beiwohnt
    protected Game game;
    //Punkte, die der Spieler besitzt
    protected int points;
    //Boolean zum Festlegen von Spieler bzw. Nicht-Spieler
    protected boolean player;
    //Boolean zum Festlegen, ob der Spieler spielen will, oder nicht
    protected boolean wannaplay;
    //Boolean zum festlegen, ob der Spieler online spielt, oder nicht
    protected boolean online;
    //Speichern der gewonnenen Stiche des Spielers
    protected Stack<Card> stiche;
    //Speichern der Punkt, die der Spieler hat
    protected int punkte;
    //Anzahl der Stiche zur
    protected int stichanzahl;
    //Boolean ob man dran ist oder nicht
    protected boolean turn;
    //Boolean zur UNterschiedung zwischen Bot und Human
    protected boolean bot;
    //Matrix, die für den Bot gebraucht wird --> Muss Karten speichern können
    protected Card [][] botMatrix;

    //Konstruktor der Klasse Player
    public Player(String name)
    {
        this.name = name;
        wannaplay = false;
        game = null;
        points = 0;
        player = false;
        hand = new LinkedList<>();
        online = false;
        turn = false;
        stiche = new Stack<Card>();
        punkte = 0;
        stichanzahl = 0;
        bot = false;
        botMatrix = new Card[4][8];
    }

    //setter Methode von game
    public void setGame(Game g)
    {
        game = g;
    }

    //setter Methode von player
    public void setPlayer(boolean p)
    {
        player = p;
    }
    // -#-#-#-#-#-#- Frage -#-#-#-#-#-#-
    // Was ist das?
    // - Alex D.
    // -#-#-#-#-#-# Antwort #-#-#-#-#-#-
    // Damit wird festgelegt, wer mit wem in einem Team ist.
    // Wenn du beispielsweise jetzt auf das Eichelass spielst,
    // wird bei dir und bei der Person, die das Eichelass hat Player gesettet.
    // - Ulli
    // -#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-

    //getter Methode von player
    public boolean getPlayer()
    {
        return player;
    }

    //getter Methode von wannaplay
    public boolean getWannaplay() { return wannaplay; }

    //Methode, die die Stichpunktanzahl des Players um 1 erhöht
    public void stichpunkterhöhen(){
        stichanzahl = stichanzahl + 1;
    }

    //Gibt die Punkte des Spielers wieder
    public int getPunkte(){

        for (int y = stichanzahl * 4; y > 0; y--){
            points = points + stiche.pop().getPoints();
        }
        return points;
    }

    //Stich von einem übergebenen Stack wird in stiche übertragen
    public void addStich(Stack<Card> s){
        for (int l = 0; l < 5; l++){
            stiche.push(s.pop());
        }
    }

    //Die Karten aus dem Stack werden auf die Hand gebracht
    public void addCards(Stack<Card> c)
    {
        hand.add(c.pop());
        hand.add(c.pop());
        hand.add(c.pop());
        hand.add(c.pop());
    }

    //getter Methode von hand
    public LinkedList<Card> getHand()
    {
        return hand;
    }

    //getter Methode von name
    public String getName()
    {
        return name;
    }

    //Die Person ist am Zug
    public void yourTurn()
    {
        turn = true;
    }

    //setter MEthode von wannaplay
    public void setWannaplay()
    {
        System.out.println("Spielst du?");
        Scanner sca = new Scanner(System.in);
        int k = sca.nextInt();
        if (k == 1)
        {
            wannaplay = true;
        }
        else if (k == 0)
        {
            wannaplay = false;
        }
        else
        {
            k = sca.nextInt();
        }
    }


    //Was will Player spielen, wenn er spielen will
    public Mode.MODE_TYPE play()
    {

        //darf nur gemacht werden, wenn wannaplay true ist
        if (wannaplay == true) {
            System.out.println("Was spielst du?");
            Scanner scan = new Scanner(System.in);
            int l = scan.nextInt();
            switch (l){
                case 1 : return Mode.MODE_TYPE.SAUSPIELSCHELLEN;
                case 2 : return Mode.MODE_TYPE.SAUSPIELGRAS;
                case 3 : return Mode.MODE_TYPE.SAUSPIELEICHEL;
                case 4 : return Mode.MODE_TYPE.WENZ;
                case 5 : return Mode.MODE_TYPE.SOLOSCHELLEN;
                case 6 : return Mode.MODE_TYPE.SOLOGRAS;
                case 7 : return Mode.MODE_TYPE.SOLOEICHEL;
                case 8 : return Mode.MODE_TYPE.SOLOHERZ;
                case 9 : return Mode.MODE_TYPE.RAMSCH;
                default : System.out.println("Error"); play();
            }
        }
        else
        {
            return null;
        }
        return null;
    }

    //mögliche karten werden gezeigt, eine ausgewählt und gelegt
    public Card kartelegen()
    {
        Mode m = game.getMode();
        m.showPlayableCards(hand, game.getDump(), game.getCallingColor(), game.getMode().getModeType());
        System.out.println("Gib eine Karte ein");
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        while (k > hand.size() - 1 || k < 1){
            k = sc.nextInt();
        }
        Card playingCard = hand.get(k - 1);
        hand.remove(hand.get(k - 1));
        game.addgespielteKarte(playingCard);
        return playingCard;
        //habe ich jetzt mal in loop() in Game gemacht -Ulli
    }

    // sagt an ob er ein Bot-Game oder ein Online-Game spielen will
    public void onlineSpiel()
    {
        online = true;
        //Problem: muss irgendwie in MainMenu vom Player gewählt werden
    }

    //getter Methode von bot


    public boolean isBot() {
        return bot;
    }

    public void setMatrix(Card[][] newMatrix)
    {
        botMatrix = newMatrix;
    }

    //Scanner - noch in Arbeit
    public Mode.MODE_TYPE ScannerBenutzen()
    {
        String input = new String("");
        Mode.MODE_TYPE modeuebergeben = null;
        Scanner s = new Scanner(input);
        modeuebergeben = modeuebergeben.vergleiche(input);
        return modeuebergeben;
    }

}
