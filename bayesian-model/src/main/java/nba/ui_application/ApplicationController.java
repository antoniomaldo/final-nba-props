package nba.ui_application;


import hex.genmodel.easy.exception.PredictException;
import nba.dto.NbaGameEventDto;
import nba.dto.PlayerRequest;
import nba.simulator.ModelOutput;
import nba.simulator.NbaPlayerModel;
import nba.simulator.PlayerWithCoefs;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
public class ApplicationController {

    @ResponseBody
    @PostMapping(value = "/getMatchPredictions", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getGamePred(@RequestBody NbaGameEventDto gameRequest) throws PredictException {

        List<PlayerWithCoefs> homePlayers = toPlayerList(gameRequest.getHomePlayers(), "home");
        List<PlayerWithCoefs> awayPlayers = toPlayerList(gameRequest.getAwayPlayers(), "away");

        List<PlayerWithCoefs> collect = Stream.concat(homePlayers.stream(), awayPlayers.stream()).collect(Collectors.toList());

        ModelOutput modelOutput = NbaPlayerModel.runModel(collect);
        Map<String, Double> percShotsGivenSpread = new HashMap<>();

        Map<String, Object> modelOutputMap = new HashMap<>();

        modelOutputMap.put("percShots", percShotsGivenSpread);
        modelOutputMap.put("modelOutput", modelOutput.getPlayerOverProb());
        modelOutputMap.put("playerThreePoints", modelOutput.getPlayerThreePointsOverProb());

        return new ResponseEntity<>(modelOutputMap, HttpStatus.OK);
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
                1,
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
                playerRequest.getFoulsPrior());
    }

}
