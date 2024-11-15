library(arm)

inputs <- read.csv("C:\\models\\nba-player-props\\backtest_analysis\\backtestInputs.csv")
BASE_DIR <- "C:\\models\\nba-player-props\\"

allPlayers <- read.csv(paste0(BASE_DIR, "data\\allPlayers.csv"))

javaPreds <- read.csv(paste0(BASE_DIR, "backtest_analysis\\backtestRaw.csv"))
javaPreds$pointsAvg <- 3 * javaPreds$threesAvg + 2 * javaPreds$twosAvg + javaPreds$ftsAvg

allData <- merge(javaPreds, allPlayers[c("seasonYear", "GameId", "Min", "PlayerId", "Name", "Team", "Points", "Fg.Attempted","Fg.Made", "Three.Attempted", "Three.Made", "Total.Rebounds", "Blocks", "Steals", "HomeTeam", "AwayTeam",  "matchSpread", "totalPoints")], by = c("GameId", "PlayerId"))

allData$fgResid <- allData$fgAttemptedPred - allData$Fg.Attempted
allData$threeResid <- allData$threesAvg - allData$Three.Made

givenPlayed <- subset(allData, allData$Min > 0 )

givenPlayed$minThirtySixToFortyResid <- 1* (givenPlayed$Min >= 36 & givenPlayed$Min <= 40) - givenPlayed$minThirtySixToForty
givenPlayed$minThirtyOneToThirtyFiveResid <- 1* (givenPlayed$Min >= 31 & givenPlayed$Min <= 35) - givenPlayed$minThirtyOneToThirtyFive

binnedplot(givenPlayed$fgAttemptedPred, givenPlayed$fgResid)
binnedplot(givenPlayed$threesAvg, givenPlayed$threeResid)
binnedplot(givenPlayed$twosAvg, givenPlayed$fgResid)

binnedplot(givenPlayed$minThirtySixToForty, givenPlayed$minThirtySixToFortyResid)
binnedplot(givenPlayed$minThirtyOneToThirtyFive, givenPlayed$minThirtyOneToThirtyFiveResid)
binnedplot(givenPlayed$minOverForty, givenPlayed$minThirtyOneToThirtyFiveResid)

#Games