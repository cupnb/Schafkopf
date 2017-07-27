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
    private int points;
    //Boolean zum Festlegen von Spieler bzw. Nicht-Spieler
    private boolean player;
    //Boolean zum Festlegen, ob der Spieler spielen will, oder nicht
    protected boolean wannaplay;
    //Boolean zum festlegen, ob der Spieler online spielt, oder nicht
    private boolean online;
    //Speichern der gewonnenen Stiche des Spielers
    private Stack<Card> stiche;
    //Anzahl der Stiche zur
    private int stichanzahl;
    //Boolean zur Unterschiedung zwischen Bot und Human
    protected boolean bot;
    //Matrix, die für den Bot gebraucht wird --> Muss Karten speichern können
    private Card [][] botMatrix;

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
        stiche = new Stack<>();
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
    void setPlayer(boolean p)
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
    boolean getPlayer()
    {
        return player;
    }

    //Methode, die die Stichpunktanzahl des Players um 1 erhöht
    void stichpunkterhöhen(){
        stichanzahl = stichanzahl + 1;
    }

    //Gibt die Punkte des Spielers wieder
    int getPunkte(){

        for (int y = stichanzahl * 4; y > 0; y--){
            points = points + stiche.pop().getPoints();
        }
        return points;
    }

    //Stich von einem übergebenen Stack wird in stiche übertragen
    void addStich(Stack<Card> s){
        for (int l = 0; l < 5; l++){
            stiche.push(s.pop());
        }
    }

    //Die Karten aus dem Stack werden auf die Hand gebracht
    void addCards(Stack<Card> c)
    {
        hand.add(c.pop());
        hand.add(c.pop());
        hand.add(c.pop());
        hand.add(c.pop());
    }

    //getter Methode von hand
    LinkedList<Card> getHand()
    {
        return hand;
    }

    //getter Methode von name
    public String getName()
    {
        return name;
    }

    //setter Methode von wannaplay
    int setWannaplay()
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
            System.out.println("Error. Tippe 1 für Spiel oder 0 für nicht spielen");
            k = sca.nextInt();
        }
        return k;
    }


    //Was will Player spielen, wenn er spielen will
    Mode.MODE_TYPE play()
    {

        //darf nur gemacht werden, wenn wannaplay true ist
        if (wannaplay) {
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
    Card kartelegen()
    {
        Card playingCard = null;
        Mode m = game.getMode();
        LinkedList<Card> temp = m.showPlayableCards(hand, game.getDump(), game.getCallingColor(), game.getMode().getModeType());
        System.out.println("Du hast die Karten : ");
        for (Card karte: temp)
        {
            System.out.println(karte.getColor() + " mit Wert " + karte.getRank());
            //Gibt spielbare Karten aus
        }
        System.out.println("Gib eine Karte ein");
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        System.out.println(hand.size());
        if (k > hand.size() || k < 1){
            System.out.println("Error. Wert nicht möglich! Neue Eingabe");
            k = sc.nextInt();
        }
        else {
            playingCard = hand.get(k - 1);
            hand.remove(k - 1);
            game.addgespielteKarte(playingCard);

        }
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


    boolean isBot() {
        return bot;
    }

    void setMatrix(Card[][] newMatrix)
    {
        botMatrix = newMatrix;
    }

}