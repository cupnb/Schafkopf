package de.emg_haar.schafkopfdeluxe.game.card;

/**
 * Created by Sebi on 04.02.2017.
 */

public enum CardRank {
    ASS("ass", 11, 40),
    ZEHN("zehn", 10, 50),
    KOENIG("koenig", 4, 40),
    OBER("ober", 3, 80),
    UNTER("unter", 2, 70),
    NEUN("neun", 0, 30),
    ACHT("acht", 0, 20),
    SIEBEN("sieben", 0, 10);

    private String name;
    private int points;
    private int comparison;

    CardRank(String displayName, int points, int comparision) {
        this.name = displayName;
        this.points = points;
        this.comparison = comparision;
    }

    public String getName()
    {
        return name;
    }

    public int getPoints()
    {
        return points;
    }

    public void setComparision(int comparision)
    {
        this.comparison = comparision;
    }
}
