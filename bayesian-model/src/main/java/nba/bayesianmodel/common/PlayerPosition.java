package nba.bayesianmodel.common;

public enum PlayerPosition {
    C,
    PF,
    PG,
    SF,
    SG;

    public static PlayerPosition mapToPlayerPosition(String positionString){
        for(PlayerPosition playerPosition : PlayerPosition.values()){
            if(playerPosition.name().equalsIgnoreCase(positionString)){
                return playerPosition;
            }
        }
        if(positionString.equalsIgnoreCase("F")){
            return PF;
        }else if(positionString.equalsIgnoreCase("G")){
            return SG;
        }
        return null;
    }
}
