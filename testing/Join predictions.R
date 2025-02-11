library(arm)

BASE_DIR <- "C:\\nba-player-props\\model\\final-nba-props\\"
COEFS_DIR <- paste0(BASE_DIR, "testing\\")

players <- read.csv(paste0(BASE_DIR, "data\\allPlayers.csv"))
players <- unique(players[c("GameId", "Date",  "Name", "PlayerId", "Team", "HomeTeam", "totalPoints", "matchSpread", "lastGameMin")])
players <- subset(players, !is.na(players$matchSpread) & !is.na(players$totalPoints))

players$homeExp <- (players$totalPoints - players$matchSpread) / 2
players$awayExp <- players$totalPoints - players$homeExp
players$ownExp <- ifelse(players$HomeTeam == players$Team, players$homeExp, players$awayExp)
players$oppExp <- ifelse(players$HomeTeam == players$Team, players$awayExp, players$homeExp)


fgPred <- read.csv(paste0(COEFS_DIR, "fgAttemptedPerMin.csv"))[c("seasonYear", "GameId", "PlayerId", "minPlayed", "gamesPlayedInSeason", "priorForPlayer", "playerCoef", "averageMinutesInSeason")]
threeProp <- read.csv(paste0(COEFS_DIR, "threeProp.csv"))[c("GameId", "PlayerId", "priorForPlayer", "playerCoef")]
twoPerc <- read.csv(paste0(COEFS_DIR, "twoPerc.csv"))[c("GameId", "PlayerId", "priorForPlayer", "playerCoef")]
threePerc <- read.csv(paste0(COEFS_DIR, "threePerc.csv"))[c("GameId", "PlayerId", "priorForPlayer", "playerCoef")]
assists <- read.csv(paste0(COEFS_DIR, "assistsPerMin.csv"))[c("GameId", "PlayerId", "priorForPlayer", "playerCoef")]
steals <- read.csv(paste0(COEFS_DIR, "stealsPerMin.csv"))[c("GameId", "PlayerId", "priorForPlayer", "playerCoef")]
blocks <- read.csv(paste0(COEFS_DIR, "blocksPerMin.csv"))[c("GameId", "PlayerId", "priorForPlayer", "playerCoef")]
rebounds <- read.csv(paste0(COEFS_DIR, "reboundsPerMin.csv"))[c("GameId", "PlayerId", "priorForPlayer", "playerCoef")]
ofReboundsProp <- read.csv(paste0(COEFS_DIR, "ofRebProp.csv"))[c("GameId", "PlayerId", "priorForPlayer", "playerCoef")]
turnovers <- read.csv(paste0(COEFS_DIR, "turnoversPerMin.csv"))[c("GameId", "PlayerId", "priorForPlayer", "playerCoef")]
personalFouls <- read.csv(paste0(COEFS_DIR, "personalFoulsPerMin.csv"))[c("GameId", "PlayerId", "priorForPlayer", "playerCoef")]
ftsAttemptedPerMin <- read.csv(paste0(COEFS_DIR, "ftsAttemptedPerMin.csv"))[c("GameId", "PlayerId", "priorForPlayer", "playerCoef")]
ftPerc <- read.csv(paste0(COEFS_DIR, "ftPerc.csv"))[c("GameId", "PlayerId", "priorForPlayer", "playerCoef")]

colnames(fgPred)[6:7] <- c("fgPrior", "fgPlayerCoef")
colnames(threeProp)[3:4] <- c( "threePropPrior", "threePropPlayerCoef")
colnames(twoPerc)[3:4] <- c( "twoPercPrior", "twoPercPlayerCoef")
colnames(threePerc)[3:4] <- c("threePercPrior", "threePercPlayerCoef")
colnames(assists)[3:4] <- c("assistsPrior", "assistsPlayerCoef")
colnames(steals)[3:4] <- c("stealsPrior", "stealsPlayerCoef")
colnames(blocks)[3:4] <- c("blocksPrior", "blocksPlayerCoef")
colnames(rebounds)[3:4] <- c("reboundsPrior", "reboundsPlayerCoef")
colnames(ofReboundsProp)[3:4] <- c("ofReboundsPropPrior", "ofReboundsPropPlayerCoef")
colnames(turnovers)[3:4] <- c("turnoversPrior", "turnoversPlayerCoef")
colnames(personalFouls)[3:4] <- c("personalFoulsPrior", "personalFoulsPlayerCoef")
colnames(ftsAttemptedPerMin)[3:4] <- c("ftsAttemptedPerMinPrior", "ftsAttemptedPerMinPlayerCoef")
colnames(ftPerc)[3:4] <- c("ftsPercPrior", "ftsPercPlayerCoef")

allPreds <- merge(fgPred, threeProp, by = c("GameId", "PlayerId"))
allPreds <- merge(allPreds, twoPerc, by = c("GameId", "PlayerId"))
allPreds <- merge(allPreds, threePerc, by = c("GameId", "PlayerId"))
allPreds <- merge(allPreds, assists, by = c("GameId", "PlayerId"))
allPreds <- merge(allPreds, steals, by = c("GameId", "PlayerId"))
allPreds <- merge(allPreds, blocks, by = c("GameId", "PlayerId"))
allPreds <- merge(allPreds, rebounds, by = c("GameId", "PlayerId"))
allPreds <- merge(allPreds, ofReboundsProp, by = c("GameId", "PlayerId"))
allPreds <- merge(allPreds, turnovers, by = c("GameId", "PlayerId"))
allPreds <- merge(allPreds, personalFouls, by = c("GameId", "PlayerId"))
allPreds <- merge(allPreds, ftsAttemptedPerMin, by = c("GameId", "PlayerId"))
allPreds <- merge(allPreds, ftPerc, by = c("GameId", "PlayerId"))

allPreds <- merge(allPreds, players, by = c("GameId", "PlayerId"), all.x = T)

latestPreds <- subset(allPreds, allPreds$GameId == -1)
#allPreds <- subset(allPreds, !is.na(allPreds$matchSpread))

write.csv(allPreds, paste0(COEFS_DIR, "allPreds.csv"))

noLatest <- subset(allPreds, allPreds$GameId != -1)
lastDate <- aggregate(Date ~ PlayerId, noLatest, max)
colnames(lastDate)[2] <- "LastDate"

allPreds <- merge(allPreds, lastDate)
lastDateData <- subset(allPreds, allPreds$LastDate == allPreds$Date)

lastDateData$totalMinPlayed <- lastDateData$averageMinutesInSeason * (lastDateData$gamesPlayedInSeason - 1) + lastDateData$minPlayed
lastDateData$averageMinutesInSeason <- lastDateData$totalMinPlayed / lastDateData$gamesPlayedInSeason
lastDateData$lastGameMin <- lastDateData$minPlayed

latestPreds <- latestPreds[,-which(colnames(latestPreds) %in% c("averageMinutesInSeason", "lastGameMin"))]
latestPreds <- merge(latestPreds, lastDateData[c("PlayerId", "averageMinutesInSeason", "lastGameMin")])

write.csv(latestPreds, paste0(COEFS_DIR, "lastUpdate.csv"))
