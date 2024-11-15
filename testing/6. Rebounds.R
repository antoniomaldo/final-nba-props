library(arm)

allPlayers <- read.csv("C:\\czrs-ds-models\\nba-player-props\\data\\allPlayers.csv")

aggMin <- aggregate(Min ~ GameId + Team, allPlayers, sum)

idsNotToUse <- unique(subset(aggMin$GameId, !(aggMin$Min %in% c(238, 239, 240, 241, 242, 264, 265, 266))))

allPlayers <- subset(allPlayers, !allPlayers$GameId %in% idsNotToUse)
allPlayers$fgMissed <- allPlayers$Fg_Attempted - allPlayers$Fg_Made

allPlayers$oppTeam <- ifelse(allPlayers$Team == allPlayers$HomeTeam, allPlayers$AwayTeam, allPlayers$HomeTeam) 
teamFgMissed <- aggregate(fgMissed ~ GameId + oppTeam, allPlayers, sum)
colnames(teamFgMissed) <- c("GameId", "Team", "oppFgMissed")

BASE_DIR <- "C:\\czrs-ds-models\\nba-player-props\\testing\\"

javaPreds <- read.csv("C:\\czrs-ds-models\\nba-player-props\\testing\\reboundsPerMin.csv")
javaPreds <- subset(javaPreds, javaPreds$seasonYear > 2017 & javaPreds$target >= 0)
javaPreds <- merge(javaPreds, allPlayers[c("GameId", "PlayerId", "Total_Rebounds", "Team", "HomeTeam", "AwayTeam",  "matchSpread", "totalPoints")])
javaPreds <- merge(javaPreds, teamFgMissed)

javaPreds$homeExp <- (javaPreds$totalPoints - javaPreds$matchSpread) / 2
javaPreds$awayExp <- javaPreds$totalPoints - javaPreds$homeExp

javaPreds$ownExp <- ifelse(javaPreds$HomeTeam == javaPreds$Team, javaPreds$homeExp, javaPreds$awayExp)
javaPreds$oppExp <- ifelse(javaPreds$HomeTeam == javaPreds$Team, javaPreds$awayExp, javaPreds$homeExp)


javaPreds$targetResid <- javaPreds$targetPredicted - javaPreds$target
javaPreds$pred <- javaPreds$targetPredicted * javaPreds$minPlayed
javaPreds$resid <- javaPreds$pred - javaPreds$Total_Rebounds

binnedplot(javaPreds$targetPredicted, javaPreds$targetResid)
binnedplot(javaPreds$pred, javaPreds$resid)
binnedplot(javaPreds$minPlayed, javaPreds$resid)

binnedplot(javaPreds$oppFgMissed, javaPreds$resid)
binnedplot(javaPreds$pred[javaPreds$oppFgMissed < 40], javaPreds$resid[javaPreds$oppFgMissed < 40])
binnedplot(javaPreds$pred[javaPreds$oppFgMissed>60], javaPreds$resid[javaPreds$oppFgMissed >60])

javaPreds$threeResid <- javaPreds$threeAttempted - javaPreds$threeExp


aggTotalRebounds <- aggregate(Defensive_Rebound ~ GameId + Team, allPlayers, sum)
aggTotalRebounds <- merge(aggTotalRebounds,  aggregate(oppFgMissed ~ GameId + Team, teamFgMissed, mean))
