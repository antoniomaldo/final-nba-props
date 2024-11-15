library(arm)

fullGames <- read.csv(file = "C:\\models\\nba-player-props\\minutes distribution\\mappedDataWithPreds.csv" )
fullGames <- subset(fullGames, fullGames$pmin > 0)

noOt <- subset(fullGames, fullGames$hasOt == 0)

noOt <- subset(noOt, noOt$pmin > 0)

aggMinutesPerNumb <- aggregate(Min ~ GameId + Team + numbPlayers, noOt, sum)

fullGames$id <- paste0(fullGames$GameId, "-", fullGames$Team)
aggMinutesPerNumb$id <- paste0(aggMinutesPerNumb$GameId, "-", aggMinutesPerNumb$Team)

aggMinutesPerNumb <- subset(aggMinutesPerNumb, aggMinutesPerNumb$id %in% fullGames$id)

aggMins <- aggregate(Min ~ numbPlayers, aggMinutesPerNumb, mean)

lm <- lm(Min ~ numbPlayers, aggMins)
summary(lm)

aggMins$predMins <- predict(lm, aggMins)


aggPmin <- aggregate(pmin ~ GameId + Team, fullGames, sum)
fullGames <- subset(aggPmin, aggPmin$pmin %in% c(239, 240, 241))

allData <- data.frame()

for(i in 1:nrow(fullGames)){
  game <- subset(noOt, noOt$GameId == fullGames$GameId[i] & noOt$Team == fullGames$Team[i])
  if(nrow(game) > 0){
    game <- subset(game, game$pmin > 0)
    
    game$minutesAvg <- (1 - game$zeroPred) * game$predGivenPlayed
    
    game$sumRawAvg <- sum(game$minutesAvg)
    
    #Only normalize players with less than n minutes avg
    
    game$toDistribute <- sum(game$minutesAvg) - aggMins$predMins[aggMins$numbPlayers == nrow(game)]
    
    game$sumRawAvg <- sum(game$minutesAvg)
    
    n = 20 
    if(sum(game$minutesAvg < n) > 2){
      game$sumRawAvgNonStarters <- sum(game$minutesAvg[game$Starter == 0])
      gamePredMis <- aggMins$predMins[aggMins$numbPlayers == nrow(game)] - sum(game$minutesAvg[game$Starter == 1])
      
      game$predMinsGivenNumbPlayers <- aggMins$predMins[aggMins$numbPlayers == nrow(game)]
      game$adjMinutes <- ifelse(game$Starter == 1, game$minutesAvg, game$minutesAvg * gamePredMis / game$sumRawAvgNonStarters)
    }else{
      n = 0
      game$sumRawAvgNonStarters <- sum(game$minutesAvg)
      gamePredMis <- aggMins$predMins[aggMins$numbPlayers == nrow(game)]
      
      game$predMinsGivenNumbPlayers <- aggMins$predMins[aggMins$numbPlayers == nrow(game)]
      game$adjMinutes <- game$minutesAvg * gamePredMis / game$sumRawAvgNonStarters
      
    }
    allData <- rbind(allData, game[c("GameId","PlayerId" ,  "Team", "Name", "Starter", "Min", "averageMinutesInSeason", "teamSumAvgMinutes", "pmin", "zeroPred", "predGivenPlayed", "minutesAvg", "sumRawAvg", "predMinsGivenNumbPlayers", "adjMinutes", "toDistribute")])
  }
}


allData$resid <- allData$Min - allData$adjMinutes
allData$rawResid <- allData$Min - allData$pmin

mean(allData$resid)
mean(allData$rawResid)

binnedplot(allData$pmin, allData$rawResid)
binnedplot(allData$pmin, allData$resid)

binnedplot(allData$averageMinutesInSeason, allData$resid)
binnedplot(allData$teamSumAvgMinutes, allData$resid)
binnedplot(allData$adjMinutes[allData$teamSumAvgMinutes < 230], allData$resid[allData$teamSumAvgMinutes < 230])
binnedplot(allData$adjMinutes[allData$teamSumAvgMinutes > 240], allData$resid[allData$teamSumAvgMinutes > 240])

binnedplot(allData$adjMinutes, allData$rawResid)
binnedplot(allData$adjMinutes[allData$Starter == 1], allData$resid[allData$Starter == 1])
binnedplot(allData$adjMinutes[allData$Starter == 0], allData$resid[allData$Starter == 0])


binnedplot(allData$adjMinutes[allData$sumRawAvg>236], allData$resid[allData$sumRawAvg>236])
binnedplot(allData$adjMinutes[allData$sumRawAvg>240], allData$resid[allData$sumRawAvg>240])
binnedplot(allData$adjMinutes[allData$sumRawAvg>245], allData$resid[allData$sumRawAvg>245])

binnedplot(allData$adjMinutes[allData$sumRawAvg<230], allData$resid[allData$sumRawAvg<230])
binnedplot(allData$adjMinutes[allData$sumRawAvg<228], allData$resid[allData$sumRawAvg<228])
binnedplot(allData$adjMinutes[allData$sumRawAvg<225], allData$resid[allData$sumRawAvg<225])

write.csv(allData, file = "C:\\models\\nba-player-props\\minutes distribution\\mappedDataWithAdjustedPreds.csv" )

## TO DO

#raw cdf 

allData <- read.csv( file = "C:\\models\\nba-player-props\\minutes distribution\\mappedDataWithAdjustedPreds.csv" )
allData <- subset(allData, allData$Min > 0)

javaData <- data.frame()
for(i in 4:36){
  subsetData <- subset(allData, round(allData$adjMinutes) == i)
  
  probs <- data.frame(minExpected = i, MinPlayed = 1:48)
  distribution <- data.frame(table(subsetData$Min) / length(subsetData$Min))
  colnames(distribution) <- c("MinPlayed", "Prob")
  probs <- merge(probs, distribution, all.x = T)
  probs$Prob[is.na(probs$Prob)] <- 0

  javaData <- rbind(javaData, probs)
}

javaData$MinPlayed <- as.numeric(as.character(javaData$MinPlayed))

sum(javaData$MinPlayed[javaData$minExpected == 7] * javaData$Prob[javaData$minExpected==7])
sum(javaData$MinPlayed[javaData$minExpected == 8] * javaData$Prob[javaData$minExpected==8])
sum(javaData$MinPlayed[javaData$minExpected == 9] * javaData$Prob[javaData$minExpected==9])
sum(javaData$MinPlayed[javaData$minExpected == 18] * javaData$Prob[javaData$minExpected==18])
sum(javaData$MinPlayed[javaData$minExpected == 18] * javaData$Prob[javaData$minExpected==18])
sum(javaData$MinPlayed[javaData$minExpected == 25] * javaData$Prob[javaData$minExpected==25])
sum(javaData$MinPlayed[javaData$minExpected == 37] * javaData$Prob[javaData$minExpected==37])
sum(javaData$MinPlayed[javaData$minExpected == 40] * javaData$Prob[javaData$minExpected==40])
sum(javaData$MinPlayed[javaData$minExpected == 38] * javaData$Prob[javaData$minExpected==38])

write.csv(javaData, file = "C:\\models\\nba-player-props\\minutes distribution\\javaData.csv" )

