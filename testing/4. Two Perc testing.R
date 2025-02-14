library(arm)

allPlayers <- read.csv("C:\\nba-player-props\\model\\final-nba-props\\data\\allPlayers.csv")

BASE_DIR <- "C:\\nba-player-props\\model\\final-nba-props\\testing\\"

threePredAdj <- read.csv(file = "C:\\nba-player-props\\model\\final-nba-props\\testing\\threePropAdj.csv")
fgPredAdj <- read.csv(file = "C:\\nba-player-props\\model\\final-nba-props\\testing\\fgPredAdj.csv")


javaPreds <- read.csv("C:\\nba-player-props\\model\\final-nba-props\\testing\\twoPerc.csv")
javaPreds <- subset(javaPreds, javaPreds$seasonYear > 2017 & javaPreds$target >= 0)
javaPreds <- merge(javaPreds, allPlayers[c("GameId", "Name", "PlayerId", "Team", "HomeTeam", "AwayTeam",  "matchSpread", "totalPoints")])
javaPreds <- merge(javaPreds, fgPredAdj[c("GameId", "PlayerId", "fgAttemptedPred")], all.x = T)
javaPreds <- merge(javaPreds, threePredAdj[c("GameId", "PlayerId", "threePropPred")], all.x = T)

javaPreds$homeExp <- (javaPreds$totalPoints - javaPreds$matchSpread) / 2
javaPreds$awayExp <- javaPreds$totalPoints - javaPreds$homeExp

javaPreds$ownExp <- ifelse(javaPreds$HomeTeam == javaPreds$Team, javaPreds$homeExp, javaPreds$awayExp)
javaPreds$oppExp <- ifelse(javaPreds$HomeTeam == javaPreds$Team, javaPreds$awayExp, javaPreds$homeExp)

javaPreds <- subset(javaPreds, !is.na(javaPreds$totalPoints))

javaPreds$twoAttempted <- javaPreds$fgAttempted - javaPreds$threeAttempted
javaPreds$twoMade <- javaPreds$fgMade - javaPreds$threeMade

javaPreds <- subset(javaPreds, javaPreds$twoAttempted > 0)

javaPreds$twoPerc <-  javaPreds$targetPredicted 
javaPreds$fgResid <- javaPreds$twoPerc - javaPreds$twoMade / javaPreds$twoAttempted

plot.ts(javaPreds$target[javaPreds$PlayerId == 3992])
lines(javaPreds$targetPredicted[javaPreds$PlayerId == 3992], col = "red")


javaPreds$resid <- javaPreds$target - javaPreds$targetPredicted

binnedplot(javaPreds$targetPredicted[javaPreds$PlayerId == 3992], 
           javaPreds$resid[javaPreds$PlayerId == 3992])

binnedplot(javaPreds$twoPerc, javaPreds$resid)
binnedplot(javaPreds$ownExp, javaPreds$resid)

binnedplot(javaPreds$twoPerc[javaPreds$ownExp > 120], javaPreds$resid[javaPreds$ownExp > 120])
binnedplot(javaPreds$twoPerc[javaPreds$ownExp < 105], javaPreds$resid[javaPreds$ownExp < 105])
binnedplot(javaPreds$twoPerc[javaPreds$ownExp < 105], javaPreds$resid[javaPreds$ownExp < 105])
binnedplot(javaPreds$twoPerc[javaPreds$ownExp > 130], javaPreds$resid[javaPreds$ownExp > 130])

binnedplot(javaPreds$threeExp, javaPreds$threeResid)
binnedplot(javaPreds$ownExp, javaPreds$threeResid)

binnedplot(javaPreds$threeExp, javaPreds$threeResid)
binnedplot(javaPreds$totalPoints, javaPreds$threeResid)

binnedplot(javaPreds$fgAttempted, javaPreds$ftResid)
binnedplot(javaPreds$minPlayed, javaPreds$ftResid)

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

modelData <- subset(javaPreds, javaPreds$numbOfGames > 5)
modelData$rowWeight <- (-2018 / 7 + modelData$seasonYear / 7)

trainData <- subset(modelData, modelData$seasonYear < 2025 & modelData$seasonYear >= 2020)
testData <- subset(modelData, modelData$seasonYear == 2025)

model <- glm(target ~ 1 +
               targetPredictedLogit + 
               pmin(0, (1 - threePropPred) * fgAttempted - twoAttempted) +
              # twoAttempted + 
               I(fgAttemptedPred - fgAttempted) + 
               pmax(twoAttempted - 10, 0) + 

               fgAttempted+
               ownExp +
               I(minPlayed < 15)  + 
               pmin(8 - fgAttemptedPred, 0) + 
               I(seasonYear == 2025) + 
               pmax(30 - minPlayed, 0) + 
               
               pmax(threeAttempted - 8, 0) + 
               minPlayed : twoAttempted
               
             , data = modelData, family = "quasibinomial", weights = modelData$rowWeight) 

summary(model)


testData$pred <- predict(model, testData, type = "response")
testData$resid <- testData$pred - testData$target


binnedplot(testData$pred, testData$resid)
binnedplot(testData$targetPredicted, testData$resid)
binnedplot(testData$ownExp, testData$resid)
binnedplot(testData$minPlayed, testData$resid)
binnedplot(testData$minPlayed[testData$fgAttempted > 15], testData$resid[testData$fgAttempted > 15])
binnedplot(testData$minPlayed[testData$fgAttempted > 20], testData$resid[testData$fgAttempted > 20])

binnedplot(testData$threeAttempted, testData$resid)
binnedplot(testData$twoAttempted, testData$resid)
binnedplot(testData$twoAttempted - (1 - testData$threePropPred) * testData$fgAttempted, testData$resid)

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

binnedplot(javaPreds$pred[javaPreds$PlayerId==3945274], javaPreds$resid[javaPreds$PlayerId==3945274])
binnedplot(javaPreds$fgAttempted[javaPreds$PlayerId==3945274], javaPreds$resid[javaPreds$PlayerId==3945274])
binnedplot(javaPreds$fgAttempted[javaPreds$PlayerId==3945274], javaPreds$threeResid[javaPreds$PlayerId==3945274])

binnedplot(javaPreds$ownExp[javaPreds$pred>0.38], javaPreds$threeResid[javaPreds$pred>0.38])
binnedplot(javaPreds$ownExp[javaPreds$fgAttemptedPred>15], javaPreds$threeResid[javaPreds$fgAttemptedPred>15])


##
set.seed(100)
javaData <- javaPreds[sample(1:nrow(javaPreds), 100),]
predictions <- predict(model, javaData, type = "response")

Covariates <- with(javaData, paste(targetPredicted, fgAttemptedPred, fgAttempted, ownExp, minPlayed, threeAttempted, threePropPred, seasonYear, sep = ","))


test <- paste("Assert.assertEquals(", predictions, "d, TwoPercModelGivenOwnExp.adjustRate(", Covariates, ") , DELTA);", sep = "")

cat(test, sep="\n")

predictTerms.glm <- function(obj, newdata) {
  t(t(model.matrix(obj$formula, newdata)) * coef(obj))
}