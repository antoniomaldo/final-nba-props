package nba.bayesianmodel.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PlayersData implements Iterable<Integer>{

    private final Map<Integer, List<PlayerGameData>> playersData;

    public PlayersData(Map<Integer, List<PlayerGameData>> playersData) {
        this.playersData = playersData;
    }

    public List<PlayerGameData> getPlayerGameDataForPlayer(int playerId){
        return this.playersData.get(playerId);
    }
    public Map<Integer, List<PlayerGameData>> getPlayersData() {
        return playersData;
    }

    public final PlayersData getPlayersDataForSeason(int seasonYear){
        Map<Integer, List<PlayerGameData>> seasonPlayers = new HashMap<>();
        for(Integer playerId : this.playersData.keySet()){
            List<PlayerGameData> playerGameData = playersData.get(playerId);
            if(playerGameData.stream().anyMatch(p->p.getSeasonYear()==seasonYear)){
                seasonPlayers.put(playerId, playerGameData);
            }
        }
        return new PlayersData(seasonPlayers);
    }

    @Override
    public Iterator<Integer> iterator() {
        return this.playersData.keySet().iterator();
    }
}
