library(arm)

allPlayers <- read.csv("C:\\models\\nba-player-props\\data\\allPlayers.csv")

BASE_DIR <- "C:\\models\\nba-player-props\\testing\\"


threeProp <- read.csv("C:\\models\\nba-player-props\\testing\\threeProp.csv")[c("GameId", "PlayerId", "playerCoef", "targetPredicted")]
twoPerc <- read.csv("C:\\models\\nba-player-props\\testing\\twoPerc.csv")[c("GameId", "PlayerId", "playerCoef", "targetPredicted")]
threePerc <- read.csv("C:\\models\\nba-player-props\\testing\\twoPerc.csv")[c("GameId", "PlayerId", "playerCoef", "targetPredicted")]
ftAttemptedPerMin <- read.csv("C:\\models\\nba-player-props\\testing\\ftsAttemptedPerMin.csv")[c("GameId", "PlayerId", "playerCoef", "targetPredicted")]
ftPerc <- read.csv("C:\\models\\nba-player-props\\testing\\ftPerc.csv")[c("GameId", "PlayerId", "playerCoef", "targetPredicted")]

colnames(threeProp)[3:4] <- c("threePropCoef", "threePropTarget")
colnames(twoPerc)[3:4] <- c("twoPercCoef", "twoPercTarget")
colnames(threePerc)[3:4] <- c("threePercCoef", "threePercTarget")
colnames(ftAttemptedPerMin)[3:4] <- c("ftPerMinCoef", "ftPerMinTarget")
colnames(ftPerc)[3:4] <- c("ftPercCoef", "ftPercTarget")

allPlayers <- merge(allPlayers, threeProp)
allPlayers <- merge(allPlayers, twoPerc)
allPlayers <- merge(allPlayers, threePerc)
allPlayers <- merge(allPlayers, ftAttemptedPerMin)
allPlayers <- merge(allPlayers, ftPerc)

allPlayers$oddFts <- 1 * (allPlayers$FT.Attempted %% 2 == 1)

allPlayers$setOfFts <- (allPlayers$FT.Attempted - allPlayers$oddFts) / 2

testData <- subset(allPlayers, allPlayers$seasonYear <= 2025)

#zero model
modelZero <- glm(I(setOfFts == 0) ~ 1 
                  + sqrt(ftPred) 
                  + Fg.Attempted
                  + I(Fg.Attempted ^ 2)
                  + Three.Attempted
                  + I(Fg.Made == 1)
                  + I(Fg.Made == 0)
                  + I(Fg.Made == 2)
                  + I(Fg.Made ^ 2)
                  + Three.Made
                  + pmax(Fg.Made - 9, 0)
                  + pmax(0.3 - ftPred, 0)

                   , 
                 , data = subset(allPlayers, allPlayers$seasonYear < 2025), family = binomial)

summary(modelZero)

testData$predZero <- predict(modelZero, testData, type = "response")
testData$residZero <- testData$predZero - 1 * (testData$setOfFts == 0)


binnedplot(testData$ftPred, testData$residZero)
binnedplot(testData$Min, testData$residZero)
binnedplot(testData$Fg.Attempted, testData$residZero)
binnedplot(testData$Fg.Made, testData$residZero)
binnedplot(testData$Three.Made, testData$residZero)

binnedplot(testData$Fg.Made[testData$ftPred >5], testData$residZero[testData$ftPred >5])

#allPlayers <- subset(allPlayers, allPlayers$FT.Attempted > 0)

allPlayers$oddFts <- 1 * (allPlayers$FT.Attempted %% 2 == 1)

allPlayers$ftPred <- allPlayers$Min * allPlayers$ftPerMinTarget

testData <- subset(allPlayers, allPlayers$seasonYear == 2025)

model <- glm(oddFts ~ 
               Min +
               I(Min ^ 2) +
               ftPred + 
               pmax(ftPred - 8, 0) +
               threePropCoef + 
               twoPercCoef + 
               twoPercTarget #+ 
              # pmax(ftPercTarget - 0.8, 0)
             , data = subset(allPlayers, allPlayers$seasonYear < 2025), family = binomial)

summary(model)

testData$predOdd <- predict(model, testData, type = "response")
testData$resid <- testData$predOdd - testData$oddFts

binnedplot(testData$predOdd, testData$resid)
binnedplot(testData$ftPred, testData$resid)
binnedplot(testData$twoPercCoef, testData$resid)
binnedplot(testData$twoPercTarget, testData$resid)
binnedplot(testData$Min, testData$resid)
binnedplot(testData$ftPercCoef, testData$resid)
binnedplot(testData$ftPercTarget, testData$resid)


testData$ftPred <- pmax(testData$ftPred, testData$predOdd)

testData$zero <- ppois(0, (testData$ftPred - testData$predOdd)/2)

testData$zeroResid <- testData$zero - 1 * (testData$setOfFts == 0)

mean(testData$zeroResid)

binnedplot(testData$ftPred, testData$zeroResid)
