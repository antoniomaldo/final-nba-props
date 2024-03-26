package nba.dto;

public class RotowirePlayer {

    private final String name;
    private final String pmin;

    public RotowirePlayer(String name, String pmin) {
        this.name = name;
        this.pmin = pmin;
    }

    public String getName() {
        return name;
    }

    public String getPmin() {
        return pmin;
    }
}
