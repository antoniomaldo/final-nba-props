
library(arm)

BASE_DIR <- "C:\\czrs-ds-models\\nba-player-props\\"

allPlayers <- read.csv(paste0(BASE_DIR, "data\\allPlayers.csv"))

javaPreds <- read.csv(paste0(BASE_DIR, "backtest_analysis\\backtest.csv"))
javaPreds$pointsAvg <- 3 * javaPreds$threesAvg + 2 * javaPreds$twosAvg + javaPreds$ftsAvg

merged <- merge(javaPreds, allPlayers[c("GameId", "Min", "PlayerId", "Name", "Team", "Points", "Fg.Attempted","Fg.Made", "Three.Made", "Total.Rebounds", "Blocks", "Steals", "HomeTeam", "AwayTeam",  "matchSpread", "totalPoints")], by = c("GameId", "PlayerId"))
merged$homeExp <- (merged$totalPoints - merged$matchSpread) / 2
merged$awayExp <- merged$totalPoints - merged$homeExp

merged$ownExp <- ifelse(merged$HomeTeam == merged$Team, merged$homeExp, merged$awayExp)
merged$oppExp <- ifelse(merged$HomeTeam == merged$Team, merged$awayExp, merged$homeExp)

merged <- subset(merged, !is.na(merged$ownExp))

merged$resid <- merged$Points - merged$pointsAvg
merged$residFg <- merged$Fg.Attempted - merged$fgAttemptedPred
merged$residThree <- merged$Three.Made - merged$threesAvg
merged$residRebounds <- merged$rebounds - merged$Total.Rebounds
merged$residSteals <- merged$steals - merged$Steals
merged$residBlocks <- merged$blocks - merged$Blocks

binnedplot(merged$pointsAvg, merged$resid)
binnedplot(merged$pointsAvg[merged$Min > 0], merged$resid[merged$Min > 0])



binnedplot(merged$pointsAvg[merged$ownExp > 120], merged$resid[merged$ownExp > 120])
binnedplot(merged$pointsAvg[merged$ownExp - merged$oppExp > 10], merged$resid[merged$ownExp - merged$oppExp > 10])
binnedplot(merged$pointsAvg[merged$ownExp - merged$oppExp > 5], merged$resid[merged$ownExp - merged$oppExp > 5])
binnedplot(merged$pointsAvg[merged$ownExp - merged$oppExp < -5], merged$resid[merged$ownExp - merged$oppExp < -5])

binnedplot(merged$pointsAvg[merged$ownExp < 105], merged$resid[merged$ownExp < 105])
binnedplot(merged$pointsAvg[merged$ownExp < 100], merged$resid[merged$ownExp < 100])

binnedplot(merged$fgAttemptedPred[merged$ownExp < 100], merged$residFg[merged$ownExp < 100])
binnedplot(merged$fgAttemptedPred[merged$ownExp < 105], merged$residFg[merged$ownExp < 105])
binnedplot(merged$fgAttemptedPred[merged$ownExp > 110], merged$residFg[merged$ownExp > 110])
binnedplot(merged$fgAttemptedPred[merged$ownExp > 120], merged$residFg[merged$ownExp > 120])

binnedplot(merged$ownExp, merged$resid)
binnedplot(merged$fgAttemptedPred, merged$residFg)
binnedplot(merged$fgAttemptedPred[merged$Min > 0], merged$residFg[merged$Min > 0])

binnedplot(merged$ownExp, merged$residFg)
binnedplot(merged$oppExp, merged$residFg)

binnedplot(merged$threesAvg, merged$residThree)
binnedplot(merged$threesAvg[merged$Min > 0], merged$residThree[merged$Min > 0])

binnedplot(merged$ownExp, merged$residThree)
binnedplot(merged$ownExp, merged$residRebounds)
binnedplot(merged$Fg_Attempted, merged$residRebounds)
binnedplot(merged$Min, merged$residRebounds)
binnedplot(merged$pointsAvg, merged$residRebounds)

binnedplot(merged$Fg.Attempted, merged$residSteals)
binnedplot(merged$Min, merged$residSteals)

binnedplot(merged$rebounds, merged$residRebounds)
binnedplot(merged$steals, merged$residSteals)
binnedplot(merged$blocks, merged$residBlocks)
binnedplot(merged$Min, merged$residBlocks)
binnedplot(merged$Fg_Attempted, merged$residBlocks)


agg <- aggregate(pointsAvg ~ GameId + Team, merged, sum)
agg <- merge(agg, aggregate(Points ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(Fg.Attempted ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(Three.Made ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(threesAvg ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(twosAvg ~ GameId + Team, merged, sum), by = c("GameId", "Team"))

agg <- merge(agg, aggregate(Total.Rebounds ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(rebounds ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(blocks ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(Blocks ~ GameId + Team, merged, sum), by = c("GameId", "Team"))

agg <- merge(agg, aggregate(fgAttemptedPred ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(ownExp ~ GameId + Team, merged, mean), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(oppExp ~ GameId + Team, merged, mean), by = c("GameId", "Team"))

agg$resid <- agg$pointsAvg - agg$Points
agg$residFg <- agg$fgAttemptedPred - agg$Fg.Attempted
agg$reboundsResid <- agg$Total.Rebounds - agg$rebounds
agg$blocksResid <- agg$Blocks - agg$blocks
agg$threeResid <- agg$Three.Made - agg$threesAvg

binnedplot(agg$pointsAvg, agg$resid)
binnedplot(agg$fgAttemptedPred, agg$residFg)
binnedplot(agg$fgAttemptedPred[agg$resid < -10], agg$residFg[agg$resid < -10])
binnedplot(agg$fgAttemptedPred[agg$resid > 10], agg$residFg[agg$resid > 10])

binnedplot(agg$ownExp, agg$residFg)
binnedplot(agg$ownExp, agg$resid)
binnedplot(agg$oppExp, agg$resid)
binnedplot(agg$ownExp, agg$threeResid)
binnedplot(agg$oppExp, agg$threeResid)
binnedplot(agg$ownExp, agg$resid)

binnedplot(agg$oppExp + agg$ownExp, agg$resid)
binnedplot(agg$oppExp + agg$ownExp, agg$residFg)

binnedplot(agg$ownExp, agg$reboundsResid)
binnedplot(agg$rebounds, agg$reboundsResid)
binnedplot(agg$blocks, agg$blocksResid)
binnedplot(agg$oppExp, agg$reboundsResid)

agg$teamResid <- agg$resid

merged <- merge(merged, agg[c("GameId", "Team", "teamResid")], by = c("GameId", "Team"))

binnedplot(merged$pointsAvg, merged$resid)
binnedplot(merged$ownExp, merged$resid)

binnedplot(merged$pointsAvg[merged$teamResid<0], merged$resid[merged$teamResid<0])
binnedplot(merged$pointsAvg[merged$teamResid>0], merged$resid[merged$teamResid>0])
binnedplot(merged$pointsAvg[merged$teamResid>10], merged$resid[merged$teamResid>10])
binnedplot(merged$fgAttemptedPred[merged$teamResid>10], merged$residFg[merged$teamResid>10])
binnedplot(merged$fgAttemptedPred[merged$teamResid>0], merged$residFg[merged$teamResid>0])
binnedplot(merged$fgAttemptedPred[merged$teamResid<0], merged$residFg[merged$teamResid<0])

#Model fg pred

merged$fgAttemptedPredAvg <- merged$fgAttemptedPred * (1 - merged$zeroProb)
merged$fgResid <- merged$Fg.Attempted - merged$fgAttemptedPredAvg

binnedplot(merged$fgAttemptedPredAvg, merged$fgResid)

sumPerTeam <- aggregate(fgAttemptedPredAvg ~ GameId + Team, merged, sum)
sumPerTeam <- merge(sumPerTeam, aggregate(Fg.Attempted ~ GameId + Team, merged, sum))

sumPerTeam$resid <- sumPerTeam$fgAttemptedPredAvg - sumPerTeam$Fg.Attempted
mean(sumPerTeam$resid)

binnedplot(sumPerTeam$fgAttemptedPredAvg, sumPerTeam$resid)

colnames(sumPerTeam)[3] <- "teamFgAttemptedPredAvg"

merged <- merge(merged, sumPerTeam[c("GameId", "Team", "teamFgAttemptedPredAvg")])

binnedplot(merged$teamFgAttemptedPredAvg, merged$fgResid)

binnedplot(merged$fgAttemptedPredAvg[merged$teamFgAttemptedPredAvg < 85], merged$fgResid[merged$teamFgAttemptedPredAvg < 85])
binnedplot(merged$fgAttemptedPredAvg[merged$teamFgAttemptedPredAvg > 91], merged$fgResid[merged$teamFgAttemptedPredAvg > 91])

set.seed(1)
trainDataIds <- sample(unique(merged$GameId), size = 0.75*length(unique(merged$GameId)))
trainData <- subset(merged, merged$GameId %in% trainDataIds)
testData <- subset(merged, !merged$GameId %in% trainDataIds)

model <- glm(Fg.Attempted ~ log(fgAttemptedPredAvg) + 
               log(teamFgAttemptedPredAvg) + 
               ownExp + 
               I(ownExp - oppExp) + 
               pmax(fgAttemptedPredAvg - 19, 0) 
             , data = merged, family = poisson)
summary(model)

testData$pred <- predict(model, testData, type = "response")
testData$resid <- testData$Fg.Attempted - testData$pred

testData$predGivenPlayed <- testData$pred / (1 - testData$zeroProb)
testData$reidGivenPlayed <- testData$predGivenPlayed - testData$Fg.Attempted

binnedplot(testData$pred, testData$resid)
binnedplot(testData$pred[testData$Min > 0], testData$resid[testData$Min > 0])

binnedplot(testData$predGivenPlayed[testData$Min > 0], testData$reidGivenPlayed[testData$Min > 0])


binnedplot(testData$predGivenPlayed[testData$teamFgAttemptedPredAvg < 80], testData$reidGivenPlayed[testData$teamFgAttemptedPredAvg < 80])
binnedplot(testData$pred[testData$teamFgAttemptedPredAvg < 80], testData$resid[testData$teamFgAttemptedPredAvg < 80])

binnedplot(testData$pred[testData$teamFgAttemptedPredAvg < 85], testData$resid[testData$teamFgAttemptedPredAvg < 85])
binnedplot(testData$predGivenPlayed[testData$teamFgAttemptedPredAvg < 85 & testData$Min > 0], testData$reidGivenPlayed[testData$teamFgAttemptedPredAvg < 85 & testData$Min > 0])

binnedplot(testData$pred[testData$teamFgAttemptedPredAvg > 90], testData$resid[testData$teamFgAttemptedPredAvg > 90])
binnedplot(testData$pred[testData$teamFgAttemptedPredAvg > 92], testData$resid[testData$teamFgAttemptedPredAvg > 92])

binnedplot(testData$pred[testData$pred > 20], testData$resid[testData$pred > 20])
binnedplot(testData$fgAttemptedPredAvg[testData$fgAttemptedPredAvg > 20], testData$resid[testData$fgAttemptedPredAvg > 20])

binnedplot(testData$ownExp, testData$resid)
binnedplot(testData$ownExp- testData$oppExp, testData$resid)

team <- aggregate(pred ~ GameId + Team, testData, sum)
team <- merge(team, aggregate(Fg.Attempted ~ GameId + Team, testData, sum))

binnedplot(team$pred, team$pred - team$Fg.Attempted)


trainData$pred <- predict(model, trainData, type = "response")
trainData$resid <- trainData$Fg.Attempted - trainData$pred

binnedplot(trainData$pred, trainData$resid)
binnedplot(trainData$pred[trainData$teamFgAttemptedPredAvg < 80], trainData$resid[trainData$teamFgAttemptedPredAvg < 80])

binnedplot(trainData$pred[trainData$teamFgAttemptedPredAvg < 85], trainData$resid[trainData$teamFgAttemptedPredAvg < 85])
binnedplot(trainData$pred[trainData$teamFgAttemptedPredAvg > 90], trainData$resid[trainData$teamFgAttemptedPredAvg > 90])
binnedplot(trainData$pred[trainData$teamFgAttemptedPredAvg > 92], trainData$resid[trainData$teamFgAttemptedPredAvg > 92])

binnedplot(trainData$pred[trainData$pred > 20], trainData$resid[trainData$pred > 20])
binnedplot(trainData$pred[trainData$pred > 20 & trainData$teamFgAttemptedPredAvg < 85], trainData$resid[trainData$pred > 20 & trainData$teamFgAttemptedPredAvg < 85])

binnedplot(trainData$fgAttemptedPredAvg[trainData$fgAttemptedPredAvg > 20], trainData$resid[trainData$fgAttemptedPredAvg > 20])

binnedplot(trainData$ownExp, trainData$resid)
binnedplot(trainData$ownExp- trainData$oppExp, trainData$resid)

team <- aggregate(pred ~ GameId + Team, trainData, sum)
team <- merge(team, aggregate(Fg.Attempted ~ GameId + Team, trainData, sum))

binnedplot(team$pred, team$pred - team$Fg.Attempted)



##Java tests

set.seed(100)
javaData <- trainData[sample(1:nrow(trainData), 100),]
predictions <- predict(model, javaData, type = "response")

Covariates <- with(javaData, paste(fgAttemptedPredAvg,
                                   ownExp, oppExp, teamFgAttemptedPredAvg
                                   , sep = ","))


test <- paste("Assert.assertEquals(", predictions, "d, TeamFgAttemptedNormalizationModel.adjustPrediction(", Covariates, ") , DELTA);", sep = "")

cat(test, sep="\n")
