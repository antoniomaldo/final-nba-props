package nba.dto;

public class RotowirePlayer {

    private final String name;
    private final String pmin;
    private final String pos;

    public RotowirePlayer(String name, String pmin, String pos) {
        this.name = name;
        this.pmin = pmin;
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public String getPmin() {
        return pmin;
    }

    public String getPos() {
        return pos;
    }
}
