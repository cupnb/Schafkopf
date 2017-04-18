package de.emg_haar.schafkopfdeluxe.game.card;

/**
 * Created by Sebi on 04.02.2017.
 */

public enum CardRank {
    ASS("ass", 11, 5),
    ZEHN("zehn", 10, 4),
    KOENIG("koenig", 4, 3),
    OBER("ober", 3, 7),
    UNTER("unter", 2, 6),
    NEUN("neun", 0, 2),
    ACHT("acht", 0, 1),
    SIEBEN("sieben", 0, 0);

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
