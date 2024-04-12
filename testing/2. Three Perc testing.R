library(arm)

allPlayers <- read.csv("C:\\czrs-ds-models\\nba-player-props\\data\\allPlayers.csv")

BASE_DIR <- "C:\\czrs-ds-models\\nba-player-props\\testing\\"
backtest <- read.csv(paste0("C:\\czrs-ds-models\\nba-player-props\\", "backtest_analysis\\backtest.csv"))

javaPreds <- read.csv("C:\\czrs-ds-models\\nba-player-props\\testing\\threePerc.csv")
javaPreds <- subset(javaPreds, javaPreds$seasonYear > 2017 & javaPreds$target >= 0)
javaPreds <- merge(javaPreds, allPlayers[c("GameId", "PlayerId", "Team", "HomeTeam", "AwayTeam",  "matchSpread", "totalPoints")])
javaPreds <- merge(javaPreds, backtest[c("GameId", "PlayerId", "fgAttemptedPred", "threesAvg")])

javaPreds$homeExp <- (javaPreds$totalPoints - javaPreds$matchSpread) / 2
javaPreds$awayExp <- javaPreds$totalPoints - javaPreds$homeExp

javaPreds$ownExp <- ifelse(javaPreds$HomeTeam == javaPreds$Team, javaPreds$homeExp, javaPreds$awayExp)
javaPreds$oppExp <- ifelse(javaPreds$HomeTeam == javaPreds$Team, javaPreds$awayExp, javaPreds$homeExp)

javaPreds <- subset(javaPreds, !is.na(javaPreds$totalPoints))

javaPreds$threeExp <- javaPreds$targetPredicted * javaPreds$threeAttempted
javaPreds$threeResid <- javaPreds$threeMade - javaPreds$threeExp

plot.ts(javaPreds$target[javaPreds$PlayerId == 3992])
lines(javaPreds$targetPredicted[javaPreds$PlayerId == 3992], col = "red")


javaPreds$resid <- javaPreds$target - javaPreds$targetPredicted

binnedplot(javaPreds$targetPredicted[javaPreds$PlayerId == 3992], 
           javaPreds$resid[javaPreds$PlayerId == 3992])

binnedplot(javaPreds$targetPredicted, javaPreds$resid)

binnedplot(javaPreds$ownExp, javaPreds$resid)
binnedplot(javaPreds$ownExp, javaPreds$resid)

binnedplot(javaPreds$threeExp, javaPreds$threeResid)
binnedplot(javaPreds$ownExp, javaPreds$threeResid)

binnedplot(javaPreds$threeExp, javaPreds$threeResid)
binnedplot(javaPreds$totalPoints, javaPreds$threeResid)

binnedplot(javaPreds$fgAttempted, javaPreds$threeResid)
binnedplot(javaPreds$fgAttempted - javaPreds$fgAttemptedPred, javaPreds$threeResid)

binnedplot(javaPreds$minPlayed, javaPreds$threeResid)

binnedplot(javaPreds$playerCoef, javaPreds$ftResid)

binnedplot(javaPreds$targetPredicted[javaPreds$seasonYear == 2018], javaPreds$resid[javaPreds$seasonYear == 2018])
binnedplot(javaPreds$targetPredicted[javaPreds$seasonYear == 2019], javaPreds$resid[javaPreds$seasonYear == 2019])
binnedplot(javaPreds$targetPredicted[javaPreds$seasonYear == 2020], javaPreds$resid[javaPreds$seasonYear == 2020])
binnedplot(javaPreds$targetPredicted[javaPreds$seasonYear == 2021], javaPreds$resid[javaPreds$seasonYear == 2021])
binnedplot(javaPreds$targetPredicted[javaPreds$seasonYear == 2022], javaPreds$resid[javaPreds$seasonYear == 2022])
binnedplot(javaPreds$targetPredicted[javaPreds$seasonYear == 2023], javaPreds$resid[javaPreds$seasonYear == 2023])

binnedplot(javaPreds$targetPredicted[javaPreds$numbOfGames >50], javaPreds$resid[javaPreds$numbOfGames>50])

binnedplot(javaPreds$targetPredicted[javaPreds$numbOfGames <10], javaPreds$resid[javaPreds$numbOfGames<10])
binnedplot(javaPreds$playerCoef[javaPreds$numbOfGames <10], javaPreds$resid[javaPreds$numbOfGames<10])
binnedplot(javaPreds$targetPredicted[javaPreds$numbOfGames ==1], javaPreds$resid[javaPreds$numbOfGames==1])

binnedplot(javaPreds$targetPredicted[javaPreds$seasonYear == 2023 & javaPreds$numbOfGames < 10], 
           javaPreds$resid[javaPreds$seasonYear == 2023& javaPreds$numbOfGames < 10])
binnedplot(javaPreds$targetPredicted[javaPreds$seasonYear == 2023 & javaPreds$numbOfGames >10], 
           javaPreds$resid[javaPreds$seasonYear == 2023& javaPreds$numbOfGames > 10])
binnedplot(javaPreds$playerCoef[javaPreds$seasonYear == 2018], javaPreds$resid[javaPreds$seasonYear == 2018])
binnedplot(javaPreds$playerCoef[javaPreds$seasonYear == 2019], javaPreds$resid[javaPreds$seasonYear == 2019])
binnedplot(javaPreds$playerCoef[javaPreds$seasonYear == 2020], javaPreds$resid[javaPreds$seasonYear == 2020])
binnedplot(javaPreds$playerCoef[javaPreds$seasonYear == 2021], javaPreds$resid[javaPreds$seasonYear == 2021])
binnedplot(javaPreds$playerCoef[javaPreds$seasonYear == 2022], javaPreds$resid[javaPreds$seasonYear == 2022])
binnedplot(javaPreds$playerCoef[javaPreds$seasonYear == 2023], javaPreds$resid[javaPreds$seasonYear == 2023])

summary(javaPreds$resid)

javaPreds$threePred <- javaPreds$fgAttempted * javaPreds$targetPredicted
javaPreds$threeResid <- javaPreds$threeAttempted - javaPreds$threePred

javaPreds$fgPredicted <- javaPreds$targetPredicted * javaPreds$minPlayed
javaPreds$fgResid <- javaPreds$fgPredicted - javaPreds$fgAttempted

binnedplot(javaPreds$targetPredicted, javaPreds$resid)
binnedplot(javaPreds$fgPredicted, javaPreds$fgResid)

binnedplot(javaPreds$fgAttempted, javaPreds$resid)
binnedplot(javaPreds$threeAttempted, javaPreds$resid)

binnedplot(javaPreds$targetPredicted[javaPreds$numbOfGames > 20], javaPreds$resid[javaPreds$numbOfGames > 20])
binnedplot(javaPreds$targetPredicted[javaPreds$numbOfGames < 5], javaPreds$resid[javaPreds$numbOfGames < 5])

binnedplot(javaPreds$minPlayed, javaPreds$resid)
binnedplot(javaPreds$playerCoef, javaPreds$resid)
binnedplot(javaPreds$playerCoef[javaPreds$numbOfGames > 5], javaPreds$resid[javaPreds$numbOfGames > 5])




#

library(splines)

model <- glm(target ~ 1 +
               log(targetPredicted / (1 - targetPredicted)) + 
               I(fgAttemptedPred - fgAttempted) + 
               fgAttempted+
               ownExp +
               I(minPlayed < 20) +
               I(minPlayed < 15)  + 
               I(minPlayed < 10) + 
               pmax(threeAttempted - 5, 0) + 
               pmin(8 - fgAttemptedPred, 0)
               
             , data = javaPreds, family = "quasibinomial") 

summary(model)

javaPreds$pred <- predict(model, javaPreds, type = "response")
javaPreds$resid <- javaPreds$pred - javaPreds$target

javaPreds$threeExp <- javaPreds$threeAttempted * javaPreds$pred
javaPreds$threeResid <- javaPreds$threeExp - javaPreds$threeMade

binnedplot(javaPreds$pred, javaPreds$resid)
binnedplot(javaPreds$targetPredicted, javaPreds$resid)
binnedplot(javaPreds$ownExp, javaPreds$resid)
binnedplot(javaPreds$threeAttempted, javaPreds$resid)
binnedplot(javaPreds$fgAttemptedPred, javaPreds$resid)

binnedplot(javaPreds$threeExp, javaPreds$threeResid)
binnedplot(javaPreds$pred[javaPreds$threeExp > 4], javaPreds$resid[javaPreds$threeExp > 4])
binnedplot(javaPreds$pred[javaPreds$threeExp > 3], javaPreds$resid[javaPreds$threeExp > 3])
binnedplot(javaPreds$threeAttempted, javaPreds$resid)
binnedplot(javaPreds$minPlayed, javaPreds$resid)
binnedplot(javaPreds$minPlayed, javaPreds$target - javaPreds$targetPredicted)

binnedplot(javaPreds$fgAttempted - javaPreds$fgAttemptedPred, javaPreds$resid)

binnedplot(javaPreds$pred[javaPreds$ownExp > 120], javaPreds$resid[javaPreds$ownExp > 120])
binnedplot(javaPreds$pred[javaPreds$ownExp < 100], javaPreds$resid[javaPreds$ownExp < 100])
binnedplot(javaPreds$targetPredicted[javaPreds$ownExp < 100], javaPreds$resid[javaPreds$ownExp < 100])

binnedplot(javaPreds$pred[javaPreds$fgAttempted>7], javaPreds$resid[javaPreds$fgAttempted>7])
binnedplot(javaPreds$pred[javaPreds$fgAttempted<4], javaPreds$resid[javaPreds$fgAttempted<4])
binnedplot(javaPreds$pred[javaPreds$fgAttempted<2], javaPreds$resid[javaPreds$fgAttempted<2])
binnedplot(javaPreds$pred[javaPreds$fgAttempted<6], javaPreds$resid[javaPreds$fgAttempted<6])
binnedplot(javaPreds$playerCoef[javaPreds$fgAttempted<6], javaPreds$resid[javaPreds$fgAttempted<6])
binnedplot(javaPreds$playerCoef, javaPreds$resid)

binnedplot(javaPreds$pred[javaPreds$PlayerId == 3992], javaPreds$resid[javaPreds$PlayerId == 3992])
binnedplot(javaPreds$fgAttempted[javaPreds$PlayerId == 3992], javaPreds$resid[javaPreds$PlayerId == 3992])
binnedplot(javaPreds$fgAttempted[javaPreds$PlayerId == 3992], javaPreds$threeResid[javaPreds$PlayerId == 3992])
binnedplot(javaPreds$threeAttempted[javaPreds$PlayerId == 3992], javaPreds$threeResid[javaPreds$PlayerId == 3992])

binnedplot(javaPreds$pred[javaPreds$PlayerId==3945274], javaPreds$resid[javaPreds$PlayerId==3945274])
binnedplot(javaPreds$fgAttempted[javaPreds$PlayerId==3945274], javaPreds$resid[javaPreds$PlayerId==3945274])
binnedplot(javaPreds$fgAttempted[javaPreds$PlayerId==3945274], javaPreds$threeResid[javaPreds$PlayerId==3945274])
binnedplot(javaPreds$ownExp[javaPreds$PlayerId==3945274], javaPreds$threeResid[javaPreds$PlayerId==3945274])
binnedplot(javaPreds$threeAttempted[javaPreds$PlayerId == 3945274], javaPreds$threeResid[javaPreds$PlayerId == 3945274])
binnedplot(javaPreds$threeAttempted[javaPreds$fgAttemptedPred > 18], javaPreds$threeResid[javaPreds$fgAttemptedPred > 18])

binnedplot(javaPreds$ownExp[javaPreds$pred>0.38], javaPreds$threeResid[javaPreds$pred>0.38])
binnedplot(javaPreds$ownExp[javaPreds$fgAttemptedPred>15], javaPreds$threeResid[javaPreds$fgAttemptedPred>15])

##
set.seed(100)
javaData <- javaPreds[sample(1:nrow(javaPreds), 100),]
predictions <- predict(model, javaData, type = "response")

Covariates <- with(javaData, paste(targetPredicted, fgAttemptedPred, fgAttempted, ownExp, minPlayed, threeAttempted, sep = ","))


test <- paste("Assert.assertEquals(", predictions, "d, ThreePercModelGivenOwnExp.adjustRate(", Covariates, ") , DELTA);", sep = "")

cat(test, sep="\n")

predictTerms.glm <- function(obj, newdata) {
  t(t(model.matrix(obj$formula, newdata)) * coef(obj))
}