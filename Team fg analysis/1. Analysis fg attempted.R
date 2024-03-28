
BASE_DIR <- "C:\\czrs-ds-models\\nba-player-props\\data\\"

allPlayers <- read.csv(paste0(BASE_DIR, "allPlayers.csv"))

allPlayers <- subset(allPlayers, !is.na(allPlayers$totalPoints) & !is.na(allPlayers$matchSpread))

allPlayers$homeExp <- (allPlayers$totalPoints - allPlayers$matchSpread) / 2
allPlayers$awayExp <- allPlayers$totalPoints - allPlayers$homeExp

allPlayers$ownExp <- ifelse(allPlayers$HomeTeam == allPlayers$Team, allPlayers$homeExp, allPlayers$awayExp)
allPlayers$oppExp <- ifelse(allPlayers$HomeTeam == allPlayers$Team, allPlayers$awayExp, allPlayers$homeExp)


teamFg <- aggregate(Min ~ GameId + Team + seasonYear, allPlayers, sum)
teamFg <- merge(teamFg, aggregate(Fg_Attempted ~ GameId + Team+ seasonYear, allPlayers, sum))
teamFg <- merge(teamFg, aggregate(Three_Attempted ~ GameId + Team+ seasonYear, allPlayers, sum))
teamFg <- merge(teamFg, aggregate(ownExp ~ GameId + Team+ seasonYear, allPlayers, mean))
teamFg <- merge(teamFg, aggregate(oppExp ~ GameId + Team+ seasonYear, allPlayers, mean))

teamFg <- subset(teamFg, teamFg$Min > 237)

# does fg attempted correlate with own exp points?

trainData <- subset(teamFg, teamFg$seasonYear < 2024)
testData <- subset(teamFg, teamFg$seasonYear == 2024)

trainData$ratio <- trainData$Fg_Attempted / trainData$Three_Attempted
testData$ratio <- testData$Fg_Attempted / testData$Three_Attempted

model <- lm(Fg_Attempted ~ ownExp + ratio, data = trainData)
summary(model)

testData$pred <- predict(model, testData)
testData$resid <- testData$Fg_Attempted - testData$pred

binnedplot(testData$pred, testData$resid)
binnedplot(testData$ratio, testData$resid)

binnedplot(testData$pred[testData$Team == "IND"], testData$resid[testData$Team == "IND"])

aggResid <- aggregate(resid ~ Team, testData, mean)


thisYear <- subset(teamFg, teamFg$seasonYear == 2024)

agg <- aggregate(Fg_Attempted ~ Team, thisYear, mean)
