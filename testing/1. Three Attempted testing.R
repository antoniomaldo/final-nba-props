library(arm)

allPlayers <- read.csv("C:\\models\\nba-player-props\\data\\allPlayers.csv")

BASE_DIR <- "C:\\models\\nba-player-props\\testing\\"

backtest <- read.csv(paste0("C:\\models\\nba-player-props\\", "backtest_analysis\\backtest.csv"))

javaPreds <- read.csv("C:\\models\\nba-player-props\\testing\\threeProp.csv")
javaPreds <- subset(javaPreds, javaPreds$seasonYear > 2017 & javaPreds$target >= 0)
javaPreds <- merge(javaPreds, allPlayers[c("GameId", "PlayerId", "Name", "FT.Attempted", "Team", "HomeTeam", "AwayTeam",  "matchSpread", "totalPoints")])
#javaPreds <- merge(javaPreds, backtest[c("GameId", "PlayerId", "fgAttemptedPred", "threesAvg")])


javaPreds$homeExp <- (javaPreds$totalPoints - javaPreds$matchSpread) / 2
javaPreds$awayExp <- javaPreds$totalPoints - javaPreds$homeExp

javaPreds$ownExp <- ifelse(javaPreds$HomeTeam == javaPreds$Team, javaPreds$homeExp, javaPreds$awayExp)
javaPreds$oppExp <- ifelse(javaPreds$HomeTeam == javaPreds$Team, javaPreds$awayExp, javaPreds$homeExp)

javaPreds <- subset(javaPreds, !is.na(javaPreds$totalPoints))

javaPreds$threeExp <- javaPreds$targetPredicted * javaPreds$fgAttempted
javaPreds$threeResid <- javaPreds$threeAttempted - javaPreds$threeExp

plot.ts(javaPreds$target[javaPreds$PlayerId == 3992])
lines(javaPreds$targetPredicted[javaPreds$PlayerId == 3992], col = "red")

plot.ts(javaPreds$target[javaPreds$Name == "L. Doncic" & javaPreds$seasonYear >= 2024])
lines(javaPreds$targetPredicted[javaPreds$Name == "L. Doncic" & javaPreds$seasonYear >= 2024], col = "red")

plot.ts(javaPreds$target[javaPreds$Name == "Z. Risacher"])
lines(javaPreds$targetPredicted[javaPreds$Name == "Z. Risacher"], col = "red")

javaPreds$resid <- javaPreds$target - javaPreds$targetPredicted

binnedplot(javaPreds$playerCoef[javaPreds$Name == "L. Doncic"], 
           javaPreds$resid[javaPreds$Name == "L. Doncic"])


binnedplot(javaPreds$playerCoef[javaPreds$PlayerId == 3992], 
           javaPreds$resid[javaPreds$PlayerId == 3992])

binnedplot(javaPreds$threeExp, javaPreds$threeResid)

binnedplot(javaPreds$fgAttemptedPred, javaPreds$threeResid)
binnedplot(javaPreds$fgAttemptedPred - javaPreds$fgAttempted, javaPreds$threeResid)

binnedplot(javaPreds$fgAttempted, javaPreds$threeResid)
binnedplot(javaPreds$threesAvg, javaPreds$resid)

binnedplot(javaPreds$threeExp[javaPreds$seasonYear==2024], javaPreds$threeResid[javaPreds$seasonYear==2024])
binnedplot(javaPreds$playerCoef[javaPreds$seasonYear==2024], javaPreds$threeResid[javaPreds$seasonYear==2024])

binnedplot(javaPreds$ownExp, javaPreds$threeResid)

binnedplot(javaPreds$threeExp, javaPreds$threeResid)
binnedplot(javaPreds$threeExp[javaPreds$playerCoef>1], javaPreds$threeResid[javaPreds$playerCoef>1])
binnedplot(javaPreds$threeExp[javaPreds$playerCoef<1], javaPreds$threeResid[javaPreds$playerCoef<1])

binnedplot(javaPreds$totalPoints, javaPreds$threeResid)

binnedplot(javaPreds$fgAttempted, javaPreds$ftResid)
binnedplot(javaPreds$minPlayed, javaPreds$resid)

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

javaPreds$targetPredictedLogit <- log(javaPreds$targetPredicted / (1 - javaPreds$targetPredicted))

model <- glm(target ~ -1 +
               (targetPredictedLogit) + 
               I(fgAttemptedPred - fgAttempted)
             
             , data = javaPreds, family = "quasibinomial") 

summary(model)

javaPreds$pred <- predict(model, javaPreds, type = "response")
javaPreds$resid <- javaPreds$pred - javaPreds$target

binnedplot(javaPreds$pred, javaPreds$resid)
binnedplot(javaPreds$targetPredictedLogit, javaPreds$resid)

binnedplot(javaPreds$fgAttempted, javaPreds$resid)
binnedplot(javaPreds$fgAttempted - javaPreds$fgAttemptedPred, javaPreds$resid)
binnedplot(javaPreds$minPlayed, javaPreds$resid)
binnedplot(javaPreds$targetPredicted, javaPreds$resid)

javaPreds$threeExp <- javaPreds$fgAttempted * javaPreds$pred
javaPreds$threeResid <- javaPreds$threeAttempted - javaPreds$threeExp

binnedplot(javaPreds$threeExp, javaPreds$threeResid)
binnedplot(javaPreds$oppExp - javaPreds$ownExp, javaPreds$threeResid)

binnedplot(javaPreds$pred[javaPreds$PlayerId >= 0], javaPreds$resid[javaPreds$PlayerId >= 0])

binnedplot(javaPreds$pred[javaPreds$PlayerId == 3992], javaPreds$resid[javaPreds$PlayerId == 3992])
binnedplot(javaPreds$fgAttempted[javaPreds$PlayerId == 3992], javaPreds$resid[javaPreds$PlayerId == 3992])
binnedplot(javaPreds$fgAttempted[javaPreds$PlayerId == 3992], javaPreds$threeResid[javaPreds$PlayerId == 3992])

binnedplot(javaPreds$pred[javaPreds$Name=="L. Doncic"], javaPreds$resid[javaPreds$Name=="L. Doncic"])
binnedplot(javaPreds$fgAttempted[javaPreds$Name=="L. Doncic"], javaPreds$resid[javaPreds$Name=="L. Doncic"])
binnedplot(javaPreds$fgAttempted[javaPreds$Name=="L. Doncic"], javaPreds$threeResid[javaPreds$Name=="L. Doncic"])
binnedplot(javaPreds$pred[javaPreds$Name=="L. Doncic"], javaPreds$threeResid[javaPreds$Name=="L. Doncic"])

plot.ts(javaPreds$target[javaPreds$PlayerId == 3992])
lines(javaPreds$pred[javaPreds$PlayerId == 3992], col = "red")

plot.ts(javaPreds$target[javaPreds$Name == "L. Doncic"])
lines(javaPreds$pred[javaPreds$Name == "L. Doncic"], col = "red")


binnedplot(javaPreds$pred, javaPreds$threeResid)
binnedplot(javaPreds$threeExp, javaPreds$threeResid)

binnedplot(javaPreds$fgAttempted, javaPreds$threeResid)
binnedplot(javaPreds$fgAttempted - javaPreds$fgAttemptedPred, javaPreds$threeResid)

summary(javaPreds$pred)

binnedplot(javaPreds$pred[javaPreds$fgAttempted>7], javaPreds$resid[javaPreds$fgAttempted>7])
binnedplot(javaPreds$pred[javaPreds$fgAttempted<4], javaPreds$resid[javaPreds$fgAttempted<4])
binnedplot(javaPreds$pred[javaPreds$fgAttempted<2], javaPreds$resid[javaPreds$fgAttempted<2])
binnedplot(javaPreds$pred[javaPreds$fgAttempted<6], javaPreds$resid[javaPreds$fgAttempted<6])
binnedplot(javaPreds$playerCoef[javaPreds$fgAttempted<6], javaPreds$resid[javaPreds$fgAttempted<6])
binnedplot(javaPreds$playerCoef, javaPreds$resid)

#

set.seed(100)
javaData <- javaPreds[sample(1:nrow(javaPreds), 100),]
predictions <- predict(model, javaData, type = "response")

Covariates <- with(javaData, paste(targetPredicted, fgAttemptedPred, fgAttempted, sep = ","))


test <- paste("Assert.assertEquals(", predictions, "d, ThreeProportionModelGivenFgAttempted.adjustRate(", Covariates, ") , DELTA);", sep = "")

cat(test, sep="\n")

predictTerms.glm <- function(obj, newdata) {
  t(t(model.matrix(obj$formula, newdata)) * coef(obj))
}

predTerms <- predictTerms.glm(model, javaData)

predTerms[1,]
