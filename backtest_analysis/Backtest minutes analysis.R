library(arm)

BASE_DIR <- "C:\\models\\nba-player-props\\"

allPlayers <- read.csv(paste0(BASE_DIR, "data\\allPlayers.csv"))

javaPreds <- read.csv(paste0(BASE_DIR, "backtest_analysis\\minutesPrediction.csv"))

colnames(javaPreds)[1:2] <- c("GameId", "Name")


javaPreds <- merge(javaPreds, allPlayers[c("GameId", "Team", "seasonYear", "PlayerId", "Min")])

aggOt <- aggregate(Min ~ GameId + Team, allPlayers, sum)
colnames(aggOt)[3] <- "sumMinutes"

javaPreds <- merge(javaPreds, aggOt)
javaPreds <- subset(javaPreds, javaPreds$sumMinutes<=241)

#javaPreds <- subset(javaPreds, javaPreds$Min > 0 & javaPreds$Min <= 48)

mean(javaPreds$MinutesPredicted)
mean(javaPreds$Min)

javaPreds$resid <- javaPreds$MinutesPredicted - javaPreds$Min


javaPreds$residTenTwenty <- javaPreds$probTenToTwenty - 1 * (javaPreds$Min >= 10 & javaPreds$Min <= 20)
javaPreds$residTwentyOneThirty <- javaPreds$probTwentyOneThirty - 1 * (javaPreds$Min >= 21 & javaPreds$Min <= 30)
javaPreds$residThirtyOneForty <- javaPreds$probThirtyOneForty - 1 * (javaPreds$Min >= 31 & javaPreds$Min <= 40)
javaPreds$residOverForty <- javaPreds$probOverForty - 1 * (javaPreds$Min >= 41 & javaPreds$Min <= 48)

mean(javaPreds$resid)

binnedplot(javaPreds$MinutesPredicted[javaPreds$Min > 0], javaPreds$resid[javaPreds$Min > 0])
binnedplot(javaPreds$MinutesPredicted, javaPreds$resid)

binnedplot(javaPreds$MinutesPredicted[javaPreds$Min > 0], javaPreds$residTenTwenty[javaPreds$Min > 0])
binnedplot(javaPreds$MinutesPredicted[javaPreds$Min > 0], javaPreds$residTwentyOneThirty[javaPreds$Min > 0])
binnedplot(javaPreds$MinutesPredicted[javaPreds$Min > 0], javaPreds$residThirtyOneForty[javaPreds$Min > 0])
binnedplot(javaPreds$MinutesPredicted[javaPreds$Min > 0], javaPreds$residOverForty[javaPreds$Min > 0])


binnedplot(javaPreds$MinutesPredicted[javaPreds$Min > 0 & javaPreds$MinutesPredicted > 35], javaPreds$resid[javaPreds$Min > 0& javaPreds$MinutesPredicted > 35])

agg <- aggregate(Min ~ GameId, javaPreds, sum)
agg <- subset(agg, agg$Min<482)

javaPreds <- subset(javaPreds, javaPreds$GameId %in% agg$GameId)



