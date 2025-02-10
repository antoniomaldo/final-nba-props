library(arm)

BASE_DIR <- "C:\\models\\nba-player-props\\"

allPlayers <- read.csv(paste0(BASE_DIR, "data\\allPlayers.csv"))

javaPreds <- read.csv(paste0(BASE_DIR, "backtest_analysis\\backtestRaw.csv"))
javaPreds$pointsAvg <- 3 * javaPreds$threesAvg + 2 * javaPreds$twosAvg + javaPreds$ftsAvg

merged <- merge(javaPreds, allPlayers[c("GameId", "Min", "PlayerId", "Name", "Team", "Points", "Fg.Attempted","Fg.Made", "Three.Attempted", "Three.Made", "Total.Rebounds", "Blocks", "Steals", "HomeTeam", "AwayTeam",  "matchSpread", "totalPoints")], by = c("GameId", "PlayerId"))
merged$homeExp <- (merged$totalPoints - merged$matchSpread) / 2
merged$awayExp <- merged$totalPoints - merged$homeExp

merged$ownExp <- ifelse(merged$HomeTeam == merged$Team, merged$homeExp, merged$awayExp)
merged$oppExp <- ifelse(merged$HomeTeam == merged$Team, merged$awayExp, merged$homeExp)
merged$pointsAvgNorm <- merged$pointsAvg * (1 - merged$zeroProb)

merged <- subset(merged, !is.na(merged$ownExp))

merged$resid <- merged$Points - merged$pointsAvg
merged$residNorm <- merged$Points - merged$pointsAvgNorm

merged$residFg <- merged$Fg.Attempted - merged$fgAttemptedPred
merged$residThree <- merged$Three.Made - merged$threesAvg
merged$residTwo <- merged$Fg.Made - merged$Three.Made - merged$twosAvg
merged$residRebounds <- merged$rebounds - merged$Total.Rebounds
merged$residSteals <- merged$steals - merged$Steals
merged$residBlocks <- merged$blocks - merged$Blocks

binnedplot(merged$pointsAvg, merged$resid)
binnedplot(merged$pointsAvgNorm, merged$residNorm)
binnedplot(merged$fgAttemptedPred, merged$residFg)
binnedplot(merged$fgAttemptedPred[merged$Min>0], merged$residFg[merged$Min>0])

binnedplot(merged$pointsAvg[merged$Min > 0], merged$resid[merged$Min > 0])

givenPlayed <- subset(merged, merged$Min > 0)

binnedplot(givenPlayed$fgAttemptedPred, givenPlayed$residFg)
binnedplot(givenPlayed$pointsAvg, givenPlayed$residFg)
binnedplot(givenPlayed$pointsAvg, givenPlayed$resid)

binnedplot(givenPlayed$pointsAvg[givenPlayed$ownExp > 120], givenPlayed$resid[givenPlayed$ownExp > 120])
binnedplot(givenPlayed$pointsAvg[givenPlayed$ownExp - givenPlayed$oppExp > 10], givenPlayed$resid[givenPlayed$ownExp - givenPlayed$oppExp > 10])
binnedplot(givenPlayed$pointsAvg[givenPlayed$ownExp - givenPlayed$oppExp > 5], givenPlayed$resid[givenPlayed$ownExp - givenPlayed$oppExp > 5])
binnedplot(givenPlayed$pointsAvg[givenPlayed$ownExp - givenPlayed$oppExp < -5], givenPlayed$resid[givenPlayed$ownExp - givenPlayed$oppExp < -5])

binnedplot(givenPlayed$pointsAvg[givenPlayed$ownExp < 105], givenPlayed$resid[givenPlayed$ownExp < 105])
binnedplot(givenPlayed$pointsAvg[givenPlayed$ownExp < 100], givenPlayed$resid[givenPlayed$ownExp < 100])

binnedplot(givenPlayed$fgAttemptedPred[givenPlayed$ownExp < 100], givenPlayed$residFg[givenPlayed$ownExp < 100])
binnedplot(givenPlayed$fgAttemptedPred[givenPlayed$ownExp < 105], givenPlayed$residFg[givenPlayed$ownExp < 105])
binnedplot(givenPlayed$fgAttemptedPred[givenPlayed$ownExp > 110], givenPlayed$residFg[givenPlayed$ownExp > 110])
binnedplot(givenPlayed$fgAttemptedPred[givenPlayed$ownExp > 120], givenPlayed$residFg[givenPlayed$ownExp > 120])

binnedplot(givenPlayed$ownExp, givenPlayed$resid)
binnedplot(givenPlayed$fgAttemptedPred, givenPlayed$residFg)
binnedplot(givenPlayed$fgAttemptedPred[givenPlayed$Min > 0], givenPlayed$residFg[givenPlayed$Min > 0])

binnedplot(givenPlayed$ownExp, givenPlayed$residFg)
binnedplot(givenPlayed$oppExp, givenPlayed$residFg)

binnedplot(merged$threesAvg, merged$residThree)
binnedplot(merged$threesAvg[merged$Min > 0], merged$residThree[merged$Min > 0])
binnedplot(merged$twosAvg[merged$Min > 0], merged$residTwo[merged$Min > 0])

binnedplot(merged$ownExp, merged$residThree)
binnedplot(merged$ownExp, merged$residTwo)
binnedplot(merged$Fg.Attempted, merged$residRebounds)
binnedplot(merged$Min, merged$residRebounds)
binnedplot(merged$pointsAvg, merged$residRebounds)

binnedplot(merged$Fg.Attempted, merged$residSteals)
binnedplot(merged$Min, merged$residSteals)

binnedplot(merged$rebounds, merged$residRebounds)
binnedplot(merged$steals, merged$residSteals)
binnedplot(merged$blocks, merged$residBlocks)
binnedplot(merged$Min, merged$residBlocks)
binnedplot(merged$Fg.Attempted, merged$residBlocks)

merged$fgAttemptedPredAvg <- merged$fgAttemptedPred * (1 - merged$zeroProb)
merged$threesAvgNorm <- merged$threesAvg * (1 - merged$zeroProb)
merged$pointsAvgNorm <- merged$pointsAvg * (1 - merged$zeroProb)

agg <- aggregate(pointsAvg ~ GameId + Team, merged, sum)
agg <- merge(agg, aggregate(pointsAvgNorm ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(Points ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(Fg.Attempted ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(Three.Made ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(threesAvg ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(threesAvgNorm ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(twosAvg ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(Total.Rebounds ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(rebounds ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(blocks ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(Blocks ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(Fg.Made ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(fgAttemptedPred ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(fgAttemptedPredAvg ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(ownExp ~ GameId + Team, merged, mean), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(oppExp ~ GameId + Team, merged, mean), by = c("GameId", "Team"))

agg$resid <- agg$pointsAvgNorm - agg$Points
agg$residFg <- agg$fgAttemptedPredAvg - agg$Fg.Attempted
agg$reboundsResid <- agg$Total.Rebounds - agg$rebounds
agg$blocksResid <- agg$Blocks - agg$blocks
agg$threeResid <- agg$Three.Made - agg$threesAvgNorm
agg$twoResid <- agg$Fg.Made - agg$Three.Made - agg$twosAvg

agg$residExp <- agg$pointsAvgNorm - agg$ownExp

binnedplot(agg$pointsAvgNorm, agg$resid)
binnedplot(agg$ownExp, agg$resid)
binnedplot(agg$pointsAvgNorm[abs(agg$resid) <= 2], agg$resid[abs(agg$resid) <= 2])
binnedplot(agg$pointsAvgNorm[(agg$resid)< 0], agg$resid[(agg$resid)< 0])
binnedplot(agg$pointsAvgNorm[(agg$resid)> 0], agg$resid[(agg$resid)> 0])

binnedplot(agg$fgAttemptedPredAvg, agg$residExp)
binnedplot(agg$fgAttemptedPredAvg[agg$resid > 0], agg$residFg[agg$resid > 0])
binnedplot(agg$fgAttemptedPredAvg[agg$resid < 0], agg$residFg[agg$resid < 0])

binnedplot(agg$threesAvgNorm[agg$residExp < 0], agg$threeResid[agg$residExp < 0])
binnedplot(agg$threesAvgNorm[agg$residExp > 0], agg$threeResid[agg$residExp > 0])

binnedplot(agg$ownExp, agg$resid)
binnedplot(agg$ownExp, agg$resid)
binnedplot(agg$oppExp, agg$resid)
binnedplot(agg$ownExp, agg$threeResid)
binnedplot(agg$ownExp, agg$twoResid)
binnedplot(agg$ownExp, agg$resid)
binnedplot(agg$threesAvg, agg$threeResid)
binnedplot(agg$twosAvg, agg$twoResid)

binnedplot(agg$oppExp + agg$ownExp, agg$resid)
binnedplot(agg$oppExp + agg$ownExp, agg$residFg)

binnedplot(agg$ownExp, agg$reboundsResid)
binnedplot(agg$rebounds, agg$reboundsResid)
binnedplot(agg$blocks, agg$blocksResid)
binnedplot(agg$oppExp, agg$reboundsResid)

agg$teamPointsResid <- agg$residExp
agg$teamThreesResid <- agg$threeResid
agg$fgAttemptedPredAvgTeam <- agg$fgAttemptedPredAvg
merged <- merge(merged, agg[c("GameId", "Team", "fgAttemptedPredAvgTeam", "teamPointsResid", "teamThreesResid")], by = c("GameId", "Team"))

merged$residFgNorm <- merged$fgAttemptedPredAvg - merged$Fg.Attempted
binnedplot(merged$pointsAvgNorm, merged$residNorm)
binnedplot(merged$pointsAvgNorm, merged$teamPointsResid)

binnedplot(merged$pointsAvgNorm[merged$teamPointsResid > 0], merged$residNorm[merged$teamPointsResid > 0])
binnedplot(merged$pointsAvgNorm[merged$teamPointsResid < 0], merged$residNorm[merged$teamPointsResid < 0])

binnedplot(merged$fgAttemptedPredAvg, merged$residFgNorm)
binnedplot(merged$fgAttemptedPredAvg[merged$teamPointsResid < 0], merged$residFgNorm[merged$teamPointsResid < 0])

binnedplot(merged$fgAttemptedPredAvg, merged$residFgNorm)

binnedplot(merged$fgAttemptedPredAvg[merged$teamPointsResid < -2], merged$residFgNorm[merged$teamPointsResid < -2])
binnedplot(merged$fgAttemptedPredAvg[merged$teamPointsResid > 2], merged$residFgNorm[merged$teamPointsResid > 2])
binnedplot(merged$fgAttemptedPredAvg[merged$teamPointsResid > 6], merged$residFgNorm[merged$teamPointsResid > 6])


#Model fg pred
sumFgPred <- aggregate(fgAttemptedPredAvg ~ GameId + Team, merged, sum)
colnames(sumFgPred)[3] <- "teamSumFgPredAvg"

merged <- merge(merged, sumFgPred)
trainIds <- sample(unique(merged$GameId), 0.75 * length(unique(merged$GameId)))

trainData <- subset(merged, merged$GameId %in% trainIds)
testData <- subset(merged, !merged$GameId %in% trainIds)

model <- glm(Fg.Attempted ~ -1 + 
               offset(log(fgAttemptedPredAvg)) + 
               teamPointsResid :  fgAttemptedPredAvg + 
               pmax(fgAttemptedPredAvg - 15, 0) : pmin(teamPointsResid, 0) +
               teamSumFgPredAvg + 
               I(1 / teamSumFgPredAvg)
             
             
             , data = trainData, family = poisson)

summary(model)

testData$pred <- predict(model, testData, type = "response")
testData$resid <- testData$Fg.Attempted - testData$pred

binnedplot(testData$pred, testData$resid)
binnedplot(testData$pred[testData$Min>0], testData$resid[testData$Min>0])

binnedplot(testData$teamSumFgPredAvg, testData$resid)
binnedplot(testData$fgAttemptedPredAvg, testData$resid)
binnedplot(testData$teamPointsResid, testData$resid)
binnedplot(testData$ownExp, testData$resid)

binnedplot(testData$pred[testData$teamSumFgPredAvg > 88], testData$resid[testData$teamSumFgPredAvg > 88])

binnedplot(testData$pred[testData$teamPointsResid > 2], testData$resid[testData$teamPointsResid > 2])
binnedplot(testData$pred[testData$teamPointsResid > 2], testData$resid[testData$teamPointsResid > 2])
binnedplot(testData$pred[testData$teamPointsResid > 6], testData$resid[testData$teamPointsResid > 6])
binnedplot(testData$pred[testData$teamPointsResid > 10], testData$resid[testData$teamPointsResid > 10])

binnedplot(testData$pred[testData$teamPointsResid < -2], testData$resid[testData$teamPointsResid < -2])
binnedplot(testData$pred[testData$teamPointsResid < -6], testData$resid[testData$teamPointsResid < -6])
binnedplot(testData$pred[testData$teamPointsResid < -10], testData$resid[testData$teamPointsResid < -10])
binnedplot(testData$pred[testData$teamPointsResid < -15], testData$resid[testData$teamPointsResid < -15])
binnedplot(testData$pred[testData$teamPointsResid < -20], testData$resid[testData$teamPointsResid < -20])


sumPerTeam <- aggregate(pred ~ GameId + Team, testData, sum)
sumPerTeam <- merge(sumPerTeam, aggregate(Fg.Attempted ~ GameId + Team, testData, sum))

sumPerTeam$resid <- sumPerTeam$pred - sumPerTeam$Fg.Attempted
mean(sumPerTeam$resid)

binnedplot(sumPerTeam$pred, sumPerTeam$resid)

#Java tests
set.seed(100)
javaData <- testData[sample(1:nrow(testData), 100),]
predictions <- predict(model, javaData, type = "response")

Covariates <- with(javaData, paste(fgAttemptedPredAvg, teamPointsResid, teamSumFgPredAvg, sep = ","))


test <- paste("Assert.assertEquals(", predictions, "d, TeamBasedFgAdjuster.adjust(", Covariates, ") , DELTA);", sep = "")

  cat(test, sep="\n")

predictTerms.glm <- function(obj, newdata) {
  t(t(model.matrix(obj$formula, newdata)) * coef(obj))
}

predTerms <- predictTerms.glm(model, javaData)

predTerms[1,]
