package nba.ui_application;


import nba.dto.NbaGameEventDto;
import nba.dto.PlayerRequest;
import nba.minutedistribution.MinutesExpectedModel;
import nba.minutedistribution.NormalizedGivenPlayedPredictions;
import nba.simulator.ModelOutput;
import nba.simulator.NbaPlayerModel;
import nba.simulator.PlayerWithCoefs;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
public class ApplicationController {

    @ResponseBody
    @PostMapping(value = "/getMatchPredictions", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getGamePred(@RequestBody NbaGameEventDto gameRequest) {

        Pair<Map<Integer, Double>, Map<Integer, Double>> mapMapPair = MinutesExpectedModel.buildMinutesExpectedMap(gameRequest);
        Map<Integer, Double> minutesExpected =  mapMapPair.getLeft();
        Map<Integer, Double> zeroMinProb = mapMapPair.getRight();

        List<PlayerWithCoefs> homePlayers = toPlayerList(gameRequest.getHomePlayers(), "home");
        List<PlayerWithCoefs> awayPlayers = toPlayerList(gameRequest.getAwayPlayers(), "away");

        ModelOutput modelOutput = NbaPlayerModel.runModel(homePlayers, awayPlayers, minutesExpected, zeroMinProb, gameRequest.getTotalPoints(), gameRequest.getMatchSpread(), false);

        Map<String, Object> modelOutputMap = new HashMap<>();

        modelOutputMap.put("modelOutput", modelOutput.getPlayerOverProb());
        modelOutputMap.put("playerThreePoints", modelOutput.getPlayerThreePointsOverProb());
        modelOutputMap.put("fgAttempted", modelOutput.getPlayerFgAttemptedOverProb());
        modelOutputMap.put("playerTwoPoints", modelOutput.getPlayerTwoPointsOverProb());
        modelOutputMap.put("playerFts", modelOutput.getPlayerFtsOverProb());
        modelOutputMap.put("mins", modelOutput.getPlayerMinsMap());

        return new ResponseEntity<>(modelOutputMap, HttpStatus.OK);
    }

    private Pair<Map<String, Double>, Map<Integer, Double>> buildMinutesExpectedMap(NbaGameEventDto gameRequest) {
        Map<String, Double> map = new HashMap<>();
        Map<Integer, Double> zeroPredMap = new HashMap<>();

        double matchTotalPoints = gameRequest.getTotalPoints();
        double matchSpread = gameRequest.getMatchSpread();

        double homeExp = (matchTotalPoints - matchSpread) / 2d;
        double awayExp = matchTotalPoints - homeExp;

        List<PlayerRequest> homePlayers = NormalizedGivenPlayedPredictions.addNormalizedPredictions(gameRequest.getHomePlayers(), homeExp, awayExp);
        List<PlayerRequest> awayPlayers = NormalizedGivenPlayedPredictions.addNormalizedPredictions(gameRequest.getAwayPlayers(), awayExp,homeExp);

        for(PlayerRequest playerRequest : homePlayers){
            map.put(playerRequest.getName(), playerRequest.getTeamAdjustedMinAvg() / (1 - playerRequest.getZeroPred()));
            zeroPredMap.put(playerRequest.getPlayerId(), playerRequest.getZeroPred());
        }

        for(PlayerRequest playerRequest : awayPlayers){
            map.put(playerRequest.getName(), playerRequest.getTeamAdjustedMinAvg() / (1 - playerRequest.getZeroPred()));
            zeroPredMap.put(playerRequest.getPlayerId(), playerRequest.getZeroPred());

        }

        return Pair.of(map, zeroPredMap);
    }

    private List<PlayerWithCoefs> toPlayerList(List<PlayerRequest> playerRequestList, String team) {
        List<PlayerWithCoefs> list = new ArrayList<>();
        for (PlayerRequest playerRequest : playerRequestList) {
            list.add(toPlayer(playerRequest, team));
        }
        return list;
    }

    private PlayerWithCoefs toPlayer(PlayerRequest playerRequest, String team) {
        return new PlayerWithCoefs(1,
                2024,
                team,
                playerRequest.getName(),
                playerRequest.getPlayerId(),
                (int) playerRequest.getPmin(),
                playerRequest.getFgAttemptedCoef(),
                playerRequest.getFgAttemptedPrior(),
                playerRequest.getThreePropCoef(),
                playerRequest.getThreePropPrior(),
                playerRequest.getThreePercCoef(),
                playerRequest.getThreePercPrior(),
                playerRequest.getTwoPercCoef(),
                playerRequest.getTwoPercPrior(),
                playerRequest.getFtsAttemptedCoef(),
                playerRequest.getFtsAttemptedPrior(),
                playerRequest.getFtPercCoef(),
                playerRequest.getFtPercPrior(),
                playerRequest.getReboundsCoef(),
                playerRequest.getReboundsPrior(),
                playerRequest.getOffReboundsPropCoef(),
                playerRequest.getOffReboundsPropPrior(),
                playerRequest.getBlocksCoef(),
                playerRequest.getBlocksPrior(),
                playerRequest.getStealsCoef(),
                playerRequest.getStealsPrior(),
                playerRequest.getTurnoversCoef(),
                playerRequest.getTurnoversPrior(),
                playerRequest.getFoulsCoef(),
                playerRequest.getFoulsPrior(),
                playerRequest.getAverageMinutesInSeason(),
                playerRequest.getLastGameMin()
                );
    }

}
