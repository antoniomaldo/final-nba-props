library(zoo)
library(dplyr)

BASE_DIR <- "C:\\models\\nba-player-props\\"

mappedData <- read.csv(file = "C:\\models\\nba-player-props\\minutes distribution\\mappedData.csv" )


mappedData$pmin <- as.numeric(mappedData$pmin)
mappedData <- subset(mappedData, !is.na(mappedData$pmin) &  mappedData$pmin > 0)

agg <- aggregate(pmin ~ GameId + Team, mappedData, sum)
fullRoster <- subset(agg, agg$pmin %in% c(239, 240, 241))

fullRoster$teamId <- paste0(fullRoster$GameId, "-", fullRoster$Team)
mappedData$teamId <- paste0(mappedData$GameId, "-", mappedData$Team)

fullGames <- subset(mappedData, mappedData$teamId %in% fullRoster$teamId)

length(unique(fullGames$teamId))

mappedData <- subset(fullGames, fullGames$seasonYear == 2025)

mappedData$homeExp <- (mappedData$totalPoints - mappedData$matchSpread) / 2
mappedData$awayExp <- mappedData$totalPoints - mappedData$homeExp

mappedData$ownExp <- ifelse(mappedData$HomeTeam == mappedData$Team, mappedData$homeExp, mappedData$awayExp)
mappedData$oppExp <- ifelse(mappedData$HomeTeam == mappedData$Team, mappedData$awayExp, mappedData$homeExp)


mappedData <- mappedData[c("GameId", "seasonYear", "Team", "Name", "PlayerId", "Min", "pmin", "ownExp", "oppExp", "HomeTeam", "Starter" )]

write.csv(mappedData, file = "C:\\models\\nba-player-props\\backtest_analysis\\backtestInputsMinutes.csv")
#Sometimes the player is not in the coef preds, but it in the roto preds





#testing if missing players


lastseason <- subset(mappedData, mappedData$seasonYear <= 2024)
lastseason$MIN <- as.numeric(as.character(lastseason$MIN))

lastseason <- subset(lastseason, !is.na(lastseason$matchSpread) & !is.na(lastseason$totalPoints))

agg <- aggregate(MIN ~ GameId + Team, lastseason, sum)
agg <- subset(agg, agg$MIN %in% c(239, 240, 241))

agg$id <- paste0(agg$GameId, "-", agg$Team)

lastseason$idTeam <- paste0(lastseason$GameId, "-", lastseason$Team)
lastseason <- subset(lastseason, lastseason$idTeam %in% agg$id & !is.na(lastseason$MIN))

#
alwasyMissing <- subset(aggIsNa, aggIsNa$isNaMapping==1)
