fullGames <- read.csv(file = "C:\\czrs-ds-models\\nba-player-props\\minutes distribution\\mappedDataWithPreds.csv" )

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
    allData <- rbind(allData, game[c("GameId", "Team", "Name", "Starter", "Min", "averageMinutesInSeason", "teamSumAvgMinutes", "pmin", "zeroPred", "predGivenPlayed", "minutesAvg", "sumRawAvg", "predMinsGivenNumbPlayers", "adjMinutes", "toDistribute")])
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

write.csv(allData, file = "C:\\czrs-ds-models\\nba-player-props\\minutes distribution\\mappedDataWithAdjustedPreds.csv" )

## TO DO

### Attach a gamma distribution for no OT
allData <- read.csv( file = "C:\\czrs-ds-models\\nba-player-props\\minutes distribution\\mappedDataWithAdjustedPreds.csv" )

discretizeGamma <- function(sc, sh){
  probs <- c()
  for(i in 1:60){
    probs <- c(probs, pgamma(i-1, shape = sh, scale = sc) - pgamma(i, shape = sh, scale = sc))
  }
  probs[1:5] <- 0
  probs <- probs / sum(probs)
  return(probs)
}

optimizeForMedianVarianceGamma <- function(par, wantedMean, wantedVar){
  sh = exp(par[1])
  sc = exp(par[2])
  
  probs = discretizeGamma(sc = sc, sh = sh)
  
  meanProbs <- sum(1:60 * probs)
  varProbs <- sum((1:60 - meanProbs) ^ 2 * probs)
  
  meanDistance = (wantedMean - meanProbs) ^ 2
  varDistance = (wantedVar - varProbs) ^ 2
  
  return(meanDistance + varDistance)
}

allData <- subset(allData, allData$Min > 0)

allData$averageMin <- round(allData$predGivenPlayed)
allData$minsInBench <- 48 - allData$Min 

coefDf <- data.frame()
for(i in 8:39){
  subsetForI <- subset(allData, allData$averageMin == i)
  meanMin <- mean(subsetForI$minsInBench)
  varMin <- var(subsetForI$minsInBench)
  
  scalePar = varMin / meanMin
  shapePar = meanMin / scalePar
  
  optimPar <- optim(par = c(log(shapePar), log(scalePar)), 
                    fn = optimizeForMedianVarianceGamma, 
                    wantedMean = mean(subsetForI$minsInBench), 
                    wantedVar = var(subsetForI$minsInBench))
  
  coefDf <- rbind(coefDf, data.frame(meanMin = i, scaleMinBench = exp(optimPar$par[2]), shapeMinBench = exp(optimPar$par[1])))
}

plot.ts(coefDf$shapeMinBench)
plot.ts(coefDf$scaleMinBench)

numbRows <- aggregate(Min ~ averageMin, allData, length)
colnames(numbRows) <-c("meanMin", "numberObs")

coefDf <- merge(coefDf, numbRows)
coefDf <- subset(coefDf, coefDf$numberObs>20)


#Testing
n = 36
subsetTest <- subset(allData, round(allData$predGivenPlayed) == n)
sampleGammaProbs <- discretizeGamma(sc = coefDf$scaleMinBench[coefDf$meanMin==n], sh = coefDf$shapeMinBench[coefDf$meanMin==n])
sampleGamma <- sample(1:length(sampleGammaProbs), size = 10000, prob = sampleGammaProbs, replace = T)
summary(sampleGamma)
summary(subsetTest$minsInBench)

plot(density(48 - subsetTest$Min))
lines(density(sampleGamma), col = "red")
