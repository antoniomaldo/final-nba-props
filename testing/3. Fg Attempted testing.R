library(arm)

allPlayers <- read.csv("C:\\czrs-ds-models\\nba-player-props\\data\\allPlayers.csv")

BASE_DIR <- "C:\\czrs-ds-models\\nba-player-props\\testing\\"

javaPreds <- read.csv("C:\\czrs-ds-models\\nba-player-props\\testing\\fgAttemptedPerMin.csv")
javaPreds <- subset(javaPreds, javaPreds$seasonYear > 2017 & javaPreds$target >= 0)
javaPreds <- merge(javaPreds, allPlayers[c("GameId", "PlayerId", "Team", "HomeTeam", "AwayTeam",  "matchSpread", "totalPoints")])

doncic <- subset(javaPreds, javaPreds$PlayerId == 3945274)

plot.ts(doncic$target)
lines(doncic$targetPredicted, col = "red")

javaPreds$homeExp <- (javaPreds$totalPoints - javaPreds$matchSpread) / 2
javaPreds$awayExp <- javaPreds$totalPoints - javaPreds$homeExp

javaPreds$ownExp <- ifelse(javaPreds$HomeTeam == javaPreds$Team, javaPreds$homeExp, javaPreds$awayExp)
javaPreds$oppExp <- ifelse(javaPreds$HomeTeam == javaPreds$Team, javaPreds$awayExp, javaPreds$homeExp)

javaPreds <- subset(javaPreds, !is.na(javaPreds$totalPoints))

javaPreds$fgExp <- javaPreds$targetPredicted * javaPreds$minPlayed
javaPreds$fgResid <- javaPreds$fgAttempted - javaPreds$fgExp

plot.ts(javaPreds$target[javaPreds$PlayerId == 3992])
lines(javaPreds$targetPredicted[javaPreds$PlayerId == 3992], col = "red")


javaPreds$resid <- javaPreds$target - javaPreds$targetPredicted

binnedplot(javaPreds$targetPredicted[javaPreds$PlayerId == 3992], 
           javaPreds$resid[javaPreds$PlayerId == 3992])

binnedplot(javaPreds$targetPredicted, javaPreds$resid)
binnedplot(javaPreds$ownExp, javaPreds$resid)
binnedplot(javaPreds$ownExp, javaPreds$fgResid)
binnedplot(javaPreds$minPlayed, javaPreds$fgResid)


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

modelData <- subset(javaPreds, javaPreds$numbOfGames > 50)

model <- glm(fgAttempted ~ log(fgExp) + pmax(fgExp - 20, 0)+ pmax(fgExp - 10, 0)+ minPlayed  , data = modelData, family = "poisson") 

summary(model)

modelData$pred <- predict(model, modelData, type = "response")
modelData$resid <- modelData$pred - modelData$fgAttempted

binnedplot(modelData$pred, modelData$resid)
binnedplot(modelData$fgAttempted, modelData$resid)
binnedplot(modelData$minPlayed, modelData$resid)

binnedplot(modelData$pred[modelData$fgExp>7], modelData$resid[modelData$fgExp>7])
binnedplot(modelData$pred[modelData$fgExp<4], modelData$resid[modelData$fgExp<4])
binnedplot(modelData$pred[modelData$fgExp<2], modelData$resid[modelData$fgExp<2])
binnedplot(modelData$pred[modelData$fgExp<6], modelData$resid[modelData$fgExp<6])
binnedplot(modelData$playerCoef[modelData$fgAttempted<6], modelData$resid[modelData$fgAttempted<6])
binnedplot(modelData$playerCoef, modelData$resid)
