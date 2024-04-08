library(arm)

fullGames <- read.csv(file = "C:\\czrs-ds-models\\nba-player-props\\minutes distribution\\mappedDataWithPreds.csv" )
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

discretizeGamma <- function(sc, sh, wantedMean){
  probs <- c()
  for(i in 1:48){
    probs <- c(probs, pgamma(i, shape = sh, scale = sc) - pgamma(i-1, shape = sh, scale = sc))
  }
  probs[1:8] <- (-0.5/7 + 0.5/7 * 1:8) * probs[1:8]
  probs[48] <- 0
  
  probs <- probs / sum(probs)
  
  return(probs)
}

optimizeForMedianVarianceGamma <- function(par, wantedMean, wantedVar){
  sh = exp(par[1])
  sc = exp(par[2])
  
  probs = discretizeGamma(sc = sc, sh = sh, wantedMean = wantedMean)
  
  meanProbs <- sum((1:length(probs)) * probs)
  varProbs <- sum(((1:length(probs)) - meanProbs) ^ 2 * probs)
  
  meanDistance = (wantedMean - meanProbs) ^ 2
  varDistance = (wantedVar - varProbs) ^ 2
  
  return(meanDistance + varDistance)
}

optimizeForMedianVarianceGamma2 <- function(par, wantedMean, wantedVar){
  sh = exp(par[1])
  sc = exp(par[2])
  
  probs = discretizeGamma(sc = sc, sh = sh)
  
  meanProbs <- sum((1:length(probs)) * probs)
  varProbs <- sum(((1:length(probs)) - meanProbs) ^ 2 * probs)
  
  meanDistance = (wantedMean - meanProbs) ^ 2
  varDistance = abs(wantedVar - varProbs) 
  
  return(meanDistance + varDistance)
}

allData <- subset(allData, allData$Min > 0)

allData$averageMin <- round(allData$predGivenPlayed)
allData$minsInBench <- 48 - allData$Min 

coefDf <- data.frame()
for(i in 8:37){
  subsetForI <- subset(allData, allData$averageMin == i)
  meanMin <- 48 - i
  varMin <- var(48 - subsetForI$Min)
  
  scalePar = varMin / meanMin
  shapePar = meanMin / scalePar
  
  optimPar <- optim(par = c(log(shapePar), log(scalePar)), 
                    fn = optimizeForMedianVarianceGamma, 
                    wantedMean = meanMin, 
                    wantedVar = varMin)
  
  coefDf <- rbind(coefDf, data.frame(meanMin = i, 
                                     varMin = varMin,
                                     scaleMinBench = exp(optimPar$par[2]), 
                                     shapeMinBench = exp(optimPar$par[1])))
}

coefDf$ratio <- coefDf$varMin / coefDf$meanMin

varModel <- glm(ratio ~ meanMin + I(meanMin ^ 2) + I(meanMin ^ 3), data = coefDf, family = Gamma(link="log"))
summary(varModel)

coefDf$ratioPred <- predict(varModel, coefDf, type = "response")

plot.ts(coefDf$ratio)

lines(coefDf$ratioPred, col = "red")


#extend coefDf

extendedCoefs <- data.frame(meanMin=1:48)
extendedCoefs$ratioPred <- predict(varModel, extendedCoefs, type = "response")


extendedCoefDf <- data.frame()
for(i in 5:47){
  meanMin <- 48 - i
  varMin <- extendedCoefs$ratioPred[extendedCoefs$meanMin==i] * i
  
  scalePar = varMin / meanMin
  shapePar = meanMin / scalePar
  
  optimPar <- optim(par = c(log(shapePar), log(scalePar)), 
                    fn = optimizeForMedianVarianceGamma, 
                    wantedMean = meanMin, 
                    wantedVar = varMin)
  
  extendedCoefDf <- rbind(extendedCoefDf, data.frame(
                                      meanMin = i, 
                                     varMin = varMin,
                                     scaleMinBench = exp(optimPar$par[2]), 
                                     shapeMinBench = exp(optimPar$par[1])))
}

#24
n = 35
subsetTest <- subset(allData, round(allData$predGivenPlayed) == n)
sampleGammaProbs <- discretizeGamma(sc = extendedCoefDf$scaleMinBench[extendedCoefDf$meanMin==n], sh = extendedCoefDf$shapeMinBench[extendedCoefDf$meanMin==n], wantedMean = 48 - n)
#sampleGammaProbs <- discretizeGamma(sc = coefDf$scale[coefDf$meanMin==n], sh = coefDf$shape[coefDf$meanMin==n])

sampleGamma <- sample(1:length(sampleGammaProbs), size = 10000, prob = sampleGammaProbs, replace = T)
summary(sampleGamma)
summary(48 -subsetTest$Min)

plot(density(subsetTest$Min), ylim=c(0,0.20))
lines(density(48 - sampleGamma), col = "red")


table(48 - sampleGamma) / length(sampleGamma)
table(subsetTest$Min) / length(subsetTest$Min)

a = rbinom(10000, 1.5*48, 37/(1.5*48))
mean(a)
var(a)

lines(density(a))
#save outputs to java

javaData <- data.frame()
for(i in 7:40){
 
  sh = extendedCoefDf$shapeMinBench[extendedCoefDf$meanMin==i]
  sc = extendedCoefDf$scaleMinBench[extendedCoefDf$meanMin==i]
  
  sampleGammaProbs <- discretizeGamma(sc = sc, 
                                      sh = sh)
  sampleGamma <- 48 - sample(1:length(sampleGammaProbs), size = 1000000, prob = sampleGammaProbs, replace = T)
  
  probs <- data.frame(table(sampleGamma))
  probs$Freq <- probs$Freq / sum(probs$Freq)
  probs$minExpected <- i
  
  probs <- probs[c("minExpected", "sampleGamma", "Freq")]
  colnames(probs) <- c("minExpected", "MinPlayed", "Prob")
  
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

write.csv(javaData, file = "C:\\czrs-ds-models\\nba-player-props\\minutes distribution\\javaData.csv" )

