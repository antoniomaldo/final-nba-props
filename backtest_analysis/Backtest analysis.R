library(arm)

BASE_DIR <- "C:\\czrs-ds-models\\nba-player-props\\"

allPlayers <- read.csv(paste0(BASE_DIR, "data\\allPlayers.csv"))

javaPreds <- read.csv(paste0(BASE_DIR, "backtest analysis\\backtest.csv"))
javaPreds$pointsAvg <- 3 * javaPreds$threesAvg + 2 * javaPreds$twosAvg + javaPreds$ftsAvg

merged <- merge(javaPreds, allPlayers[c("GameId", "PlayerId", "Team", "Points", "Fg_Attempted", "Three_Made", "Total_Rebounds", "Blocks", "Steals", "HomeTeam", "AwayTeam",  "matchSpread", "totalPoints")], by = c("GameId", "PlayerId"))

merged$homeExp <- (merged$totalPoints - merged$matchSpread) / 2
merged$awayExp <- merged$totalPoints - merged$homeExp

merged$ownExp <- ifelse(merged$HomeTeam == merged$Team, merged$homeExp, merged$awayExp)
merged$oppExp <- ifelse(merged$HomeTeam == merged$Team, merged$awayExp, merged$homeExp)

merged <- subset(merged, !is.na(merged$ownExp))

merged$resid <- merged$Points - merged$pointsAvg
merged$residFg <- merged$Fg_Attempted - merged$fgAttemptedPred
merged$residThree <- merged$Three_Made - merged$threesAvg
merged$residRebounds <- merged$rebounds - merged$Total_Rebounds
merged$residSteals <- merged$steals - merged$Steals

binnedplot(merged$pointsAvg, merged$resid)
binnedplot(merged$pointsAvg[merged$ownExp > 120], merged$resid[merged$ownExp > 120])
binnedplot(merged$pointsAvg[merged$ownExp < 105], merged$resid[merged$ownExp < 105])
binnedplot(merged$ownExp, merged$resid)
binnedplot(merged$fgAttemptedPred, merged$residFg)
binnedplot(merged$threesAvg, merged$residThree)
binnedplot(merged$ownExp, merged$residThree)
binnedplot(merged$ownExp, merged$residRebounds)

binnedplot(merged$rebounds, merged$residRebounds)
binnedplot(merged$steals, merged$residSteals)

agg <- aggregate(Points ~ GameId + Team, merged, sum)

agg <- aggregate(pointsAvg ~ GameId + Team, merged, sum)
agg <- merge(agg, aggregate(Points ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(Fg_Attempted ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(Total_Rebounds ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(rebounds ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(fgAttemptedPred ~ GameId + Team, merged, sum), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(ownExp ~ GameId + Team, merged, mean), by = c("GameId", "Team"))
agg <- merge(agg, aggregate(oppExp ~ GameId + Team, merged, mean), by = c("GameId", "Team"))

agg$resid <- agg$pointsAvg - agg$Points
agg$residFg <- agg$fgAttemptedPred - agg$Fg_Attempted
agg$reboundsResid <- agg$Total_Rebounds - agg$rebounds

binnedplot(agg$pointsAvg, agg$resid)
binnedplot(agg$fgAttemptedPred, agg$residFg)
binnedplot(agg$ownExp, agg$residFg)
binnedplot(agg$ownExp, agg$resid)
binnedplot(agg$oppExp, agg$resid)
binnedplot(agg$ownExp, agg$reboundsResid)

agg$teamResid <- agg$resid

merged <- merge(merged, agg[c("GameId", "Team", "teamResid")], by = c("GameId", "Team"))

binnedplot(merged$pointsAvg, merged$resid)
binnedplot(merged$ownExp, merged$resid)

binnedplot(merged$pointsAvg[merged$resid<0], merged$resid[merged$resid<0])
binnedplot(merged$pointsAvg[merged$resid>0], merged$resid[merged$resid>0])
binnedplot(merged$pointsAvg[merged$resid>10], merged$resid[merged$resid>10])
binnedplot(merged$fgAttemptedPred[merged$residFg>10], merged$residFg[merged$residFg>10])

binnedplot(merged$fgAttemptedPred[merged$residFg<0], merged$residFg[merged$residFg<0])
binnedplot(merged$fgAttemptedPred[merged$residFg>0], merged$residFg[merged$residFg>0])
binnedplot(merged$fgAttemptedPred[merged$residFg>10], merged$residFg[merged$residFg>10])

