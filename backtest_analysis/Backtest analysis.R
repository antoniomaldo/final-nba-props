
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

agg <- aggregate(Points ~ GameId + Team, merged, sum)

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

#Model