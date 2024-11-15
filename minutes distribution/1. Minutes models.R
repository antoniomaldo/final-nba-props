library(DBI)
library(stringr)
library(arm)
library(splines)
library(zoo)
library(h2o)
library(sqldf)

source("C:\\models\\nba-player-props\\mappings\\mappings-service.R")

BASE_DIR <- "C:\\models\\nba-player-props\\"

#Load rotowire data 

csvs <- list.files("C:/Users/Antonio/Documents/NBA/data/rotowire/", full.names = T)
csvs <- csvs[!grepl("\\.R", csvs)]

loadCsvForDay <- function(fileDir, season){
  if(grepl("rotowire", fileDir)){
    dayCsv = read.csv(fileDir)
    colNames = as.character(dayCsv[1,])
    dayCsv = dayCsv[-1,]
    colnames(dayCsv) = colNames
    date = str_remove_all(fileDir, pattern = paste0("C:/Users/Antonio/Documents/NBA/data/rotowire/",season, "/rotowire-nba-projections-"))
    dayCsv$date = str_remove_all(date, pattern = ".csv")
    
    dayCsv$name = dayCsv$NAME
    dayCsv = dayCsv[,-which(colnames(dayCsv) == "Team")]
    return(dayCsv)
  }else{
    return(read.csv(fileDir))
  }
}

rotowirePreds <- data.frame()

for(csvPath in csvs){
  csvFiles <- list.files(csvPath, full.names = T)
  for(csvFilePath in csvFiles){
    year = str_remove_all(pattern = "C:/Users/Antonio/Documents/NBA/data/rotowire/", string = csvPath)
    dayData <- loadCsvForDay(csvFilePath, year)
    rotowirePreds <- rbind(rotowirePreds, dayData)
  }
  rotowirePreds <- rotowirePreds[c("date", colnames(rotowirePreds)[colnames(rotowirePreds) != "date"])]
}

allPlayers <- read.csv(paste0(BASE_DIR, "data\\allPlayers.csv"))

#Add cum data
cumData <- subset(allPlayers, allPlayers$Min > 0)
cumData <- cumData[order(cumData$PlayerId, cumData$Date),]
cumData$cumMinInSeason <- unlist(aggregate(Min ~ seasonYear + PlayerId, cumData, FUN = function(x){cumsum(c(0, x[-length(x)]))})$Min)
cumData$cumGamesPlayedInSeason <- unlist(aggregate(Min ~ seasonYear + PlayerId, cumData, FUN = function(x){(0: (length(x) - 1))})$Min)
cumData$averageMinutesInSeason <- cumData$cumMinInSeason / cumData$cumGamesPlayedInSeason

cumData$rowId <- 1:nrow(cumData)

cumData <- sqldf("SELECT t1.*, t2.Min AS lastGameMin FROM cumData t1 
                  LEFT JOIN cumData t2 ON t1.PlayerId = t2.PlayerId AND t1.rowId = t2.rowId + 1")

cumData$lastGameMin[cumData$cumGamesPlayedInSeason==0] <- -1

##

allPlayers <- merge(allPlayers, cumData[c("GameId", "PlayerId", "cumMinInSeason", "cumGamesPlayedInSeason", "averageMinutesInSeason", "lastGameMin")], all.x = T)
allPlayers <- allPlayers[order(allPlayers$PlayerId, allPlayers$Date),]
allPlayers$averageMinutesInSeason[!is.na(allPlayers$cumGamesPlayedInSeason) & allPlayers$cumGamesPlayedInSeason == 0] <- -1

allPlayers$averageMinutesInSeason <- na.locf(allPlayers$averageMinutesInSeason)
allPlayers$lastGameMin <- na.locf(allPlayers$lastGameMin)

View(allPlayers[c("seasonYear", "GameId", "Name", "Min", "cumGamesPlayedInSeason", "averageMinutesInSeason")])

totalMin <- aggregate(Min ~ GameId + Team, allPlayers, sum)
idsNotToUse <- unique(subset(totalMin$GameId, !(totalMin$Min %in% c(238, 239, 240, 241, 242, 264, 265, 266))))

allPlayers <- subset(allPlayers, !allPlayers$GameId %in% idsNotToUse)


totalMin$hasOt <- 1 * (totalMin$Min >= 264)
totalMin <- unique(totalMin[c("GameId", "hasOt")])

rotowirePreds$date <- str_remove(rotowirePreds$date, "-test")

#Add possition

mappedData = mapMinutesForSeason(boxscores = allPlayers, rotoPreds = rotowirePreds, baseDir = BASE_DIR)


#write.csv(mappedData, file = "C:\\models\\nba-player-props\\minutes distribution\\mappedData.csv" )
#mappedData <- read.csv(file = "C:\\models\\nba-player-props\\minutes distribution\\mappedData.csv" )

mappedData <- subset(mappedData, !is.na(mappedData$pmin))
mappedData$pmin <- as.numeric(mappedData$pmin)
mappedData <- subset(mappedData, mappedData$pmin > 0)

aggPmin <- aggregate(pmin ~ GameId + Team + seasonYear, mappedData, sum)
fullGames <- subset(aggPmin, aggPmin$pmin %in% c(238, 239, 240, 241, 242))

fullGames$id <- paste0(fullGames$GameId, "-", fullGames$Team)
mappedData$id <- paste0(mappedData$GameId, "-", mappedData$Team)

fullGames <- subset(mappedData, mappedData$id %in% fullGames$id)
fullGames$averageMinutesInSeason[fullGames$averageMinutesInSeason == -1] <- fullGames$pmin[fullGames$averageMinutesInSeason == -1]

aggAvgMinutes <- aggregate(averageMinutesInSeason ~ id, fullGames, sum)
colnames(aggAvgMinutes)[2] <- "teamSumAvgMinutes"

numbPlayers <- aggregate(averageMinutesInSeason ~ id, fullGames, length)
colnames(numbPlayers)[2] <- "numbPlayers"

fullGames <- merge(fullGames, aggAvgMinutes)
fullGames <- merge(fullGames, numbPlayers)

fullGames <- subset(fullGames, !is.na(fullGames$pmin))
fullGames$pmin <- as.numeric(fullGames$pmin)

fullGames$averageMinutesInSeason[fullGames$averageMinutesInSeason == -1] <- fullGames$pmin[fullGames$averageMinutesInSeason == -1]

fullGames$homeExp <- (fullGames$totalPoints - fullGames$matchSpread) / 2
fullGames$awayExp <- fullGames$totalPoints - fullGames$homeExp

fullGames$ownExp <- ifelse(fullGames$HomeTeam == fullGames$Team, fullGames$homeExp, fullGames$awayExp)
fullGames$oppExp <- ifelse(fullGames$HomeTeam == fullGames$Team, fullGames$awayExp, fullGames$homeExp)

fullGames <- merge(fullGames, totalMin[c("GameId", "hasOt")], by = "GameId")
fullGames <- subset(fullGames, !is.na(fullGames$ownExp))


#Zero minutes model

zeroMinsModel <- glm(I(Min == 0) ~ 1 + 
            pmin + 
           # abs(ownExp - oppExp):pmin + 
            I(Starter == 1)+  
            hasOt:pmin,
            
            data = fullGames[fullGames$seasonYear %in% c(2023, 2024),], family = binomial)
summary(zeroMinsModel)
AIC(zeroMinsModel) #5827.819

fullGames$zeroPred <- predict(zeroMinsModel, fullGames, type = "response")
summary(fullGames$zeroPred)

fullGames$resid <- 1 * (fullGames$Min == 0) - fullGames$zeroPred


binnedplot(fullGames$zeroPred, fullGames$resid)
binnedplot(fullGames$zeroPred[fullGames$seasonYear == 2025], fullGames$resid[fullGames$seasonYear == 2025])

binnedplot(fullGames$pmin, fullGames$resid)
binnedplot(fullGames$pmin[fullGames$seasonYear == 2025], fullGames$resid[fullGames$seasonYear == 2025])
binnedplot(fullGames$pmin[fullGames$seasonYear == 2024], fullGames$resid[fullGames$seasonYear == 2024])

binnedplot(fullGames$lastGameMin, fullGames$resid)

binnedplot(fullGames$pmin, fullGames$resid)
binnedplot(fullGames$pmin[fullGames$hasOt == 1], fullGames$resid[fullGames$hasOt == 1])
binnedplot(fullGames$pmin[fullGames$hasOt == 0], fullGames$resid[fullGames$hasOt == 0])

binnedplot(fullGames$pmin[fullGames$pmin > 20], fullGames$resid[fullGames$pmin > 20])
binnedplot(fullGames$pmin[fullGames$pmin < 10], fullGames$resid[fullGames$pmin < 10])

binnedplot(fullGames$ownExp, fullGames$resid)
binnedplot(fullGames$ownExp - fullGames$oppExp, fullGames$resid)


## Java Tests

set.seed(100)
javaData <- fullGames[sample(1:nrow(fullGames), 100),]
predictions <- predict(zeroMinsModel, javaData, type = "response")

covariates <- with(javaData, paste(pmin, Starter, hasOt,
                                   sep = ","))

test <- paste("Assert.assertEquals(", predictions, "d, ZeroMinutesModel.zeroMinutesProb(", covariates, ") , DELTA);", sep = "")
cat(test, sep="\n")


#Given played model
noZeroData <- subset(fullGames, fullGames$pmin > 0 & fullGames$Min > 0)
noZeroData$lastGameMin[noZeroData$lastGameMin == -1] <- noZeroData$pmin[noZeroData$lastGameMin == -1]  

lm <- glm(Min ~  
            pmax(pmin - 30, 0) +
            pmax(pmin - 35, 0) +
            I(pmin) + I(pmin ^ 2) + I(pmin ^ 3) +
            
           # bs(pmin, knots = c(20, 37, 40), Boundary.knots = c(0,45)) +
            averageMinutesInSeason + 
            I(ownExp - oppExp) + 
            abs(ownExp - oppExp) + 
            pmin : abs(ownExp - oppExp) + 
            pmax(pmin - averageMinutesInSeason, 0) + 
            + ifelse(Starter == 1, pmax(30 - pmin, 0), 0) 
            + ifelse(Starter == 0, pmax(pmin - 20, 0), 0) 
            + I(lastGameMin - pmin)
            + pmax(pmin - 30, 0) : I(lastGameMin - 30)
            + pmax(30 - pmin, 0) : I(lastGameMin - 15)
            + ifelse(hasOt == 1, log(pmax(pmin - 20, 1)), 0) 
            + I(teamSumAvgMinutes - 240)
            + I(teamSumAvgMinutes - 240) : pmin
          
            + abs(teamSumAvgMinutes - 240)
            
          
          , data = noZeroData[noZeroData$seasonYear %in% c(2023, 2024),], family = poisson)
summary(lm)
AIC(lm) #143269

noZeroData$pmin2 <- predict(lm, noZeroData, type = "response")
noZeroData$minResid2 <- noZeroData$Min - noZeroData$pmin2

binnedplot(noZeroData$pmin2, noZeroData$minResid2)
binnedplot(noZeroData$pmin2[noZeroData$seasonYear == 2025], noZeroData$minResid2[noZeroData$seasonYear == 2025])

binnedplot(noZeroData$pmin2[noZeroData$teamSumAvgMinutes > 250], noZeroData$minResid2[noZeroData$teamSumAvgMinutes > 250])
binnedplot(noZeroData$pmin2[noZeroData$teamSumAvgMinutes < 230], noZeroData$minResid2[noZeroData$teamSumAvgMinutes < 230])

binnedplot(noZeroData$teamSumAvgMinutes, noZeroData$minResid2)
binnedplot(noZeroData$teamSumAvgMinutes, noZeroData$minResid2)

binnedplot(noZeroData$lastGameMin, noZeroData$minResid2)
binnedplot(noZeroData$lastGameMin[noZeroData$pmin > 35], noZeroData$minResid2[noZeroData$pmin > 35])

binnedplot(noZeroData$lastGameMin[noZeroData$pmin > 30], noZeroData$minResid2[noZeroData$pmin > 30])
binnedplot(noZeroData$lastGameMin[noZeroData$pmin < 30], noZeroData$minResid2[noZeroData$pmin < 30])
binnedplot(noZeroData$lastGameMin[noZeroData$pmin < 10], noZeroData$minResid2[noZeroData$pmin < 10])

binnedplot(noZeroData$pmin, noZeroData$minResid2)
binnedplot(noZeroData$pmin[noZeroData$seasonYear==2024], noZeroData$minResid2[noZeroData$seasonYear==2024])
binnedplot(noZeroData$pmin[noZeroData$seasonYear==2024 & noZeroData$pmin>34], noZeroData$minResid2[noZeroData$seasonYear==2024 & noZeroData$pmin>34])

binnedplot(noZeroData$averageMinutesInSeason[!is.na(noZeroData$averageMinutesInSeason)], noZeroData$minResid2[!is.na(noZeroData$averageMinutesInSeason)])

binnedplot(noZeroData$pmin2[abs(noZeroData$ownExp - noZeroData$oppExp) > 10], noZeroData$minResid2[abs(noZeroData$ownExp - noZeroData$oppExp) > 10])
binnedplot(noZeroData$pmin2[abs(noZeroData$ownExp - noZeroData$oppExp) < 10], noZeroData$minResid2[abs(noZeroData$ownExp - noZeroData$oppExp) < 10])

binnedplot(noZeroData$ownExp, noZeroData$minResid2)
binnedplot(noZeroData$pmin - noZeroData$averageMinutesInSeason, noZeroData$minResid2)

binnedplot(noZeroData$averageMinutesInSeason[noZeroData$cumGamesPlayedInSeason < 10], noZeroData$minResid2[noZeroData$cumGamesPlayedInSeason < 10])

binnedplot(noZeroData$averageMinutesInSeason[noZeroData$cumGamesPlayedInSeason < 5], noZeroData$minResid2[noZeroData$cumGamesPlayedInSeason < 5])
binnedplot(noZeroData$averageMinutesInSeason[noZeroData$cumGamesPlayedInSeason < 2], noZeroData$minResid2[noZeroData$cumGamesPlayedInSeason < 2])

binnedplot(abs(noZeroData$ownExp - noZeroData$oppExp), noZeroData$minResid2)

binnedplot(noZeroData$pmin2[noZeroData$seasonYear == 2024], noZeroData$minResid2[noZeroData$seasonYear == 2024])
binnedplot(noZeroData$pmin2[noZeroData$seasonYear == 2024 & noZeroData$Name == "L. Doncic"], noZeroData$minResid2[noZeroData$seasonYear == 2024 & noZeroData$Name == "L. Doncic"])
binnedplot(noZeroData$pmin2[noZeroData$seasonYear == 2024 & noZeroData$Name == "K. Durant"], noZeroData$minResid2[noZeroData$seasonYear == 2024 & noZeroData$Name == "K. Durant"])

binnedplot(noZeroData$pmin2[noZeroData$pmin>35 & noZeroData$Name == "L. Doncic"], noZeroData$minResid2[noZeroData$pmin>35 & noZeroData$Name == "L. Doncic"])
binnedplot(noZeroData$pmin2[noZeroData$pmin>35 & noZeroData$Name == "K. Durant"], noZeroData$minResid2[noZeroData$pmin>35 & noZeroData$Name == "K. Durant"])
binnedplot(noZeroData$pmin2[noZeroData$pmin>35 & noZeroData$Name == "N. Jokic"], noZeroData$minResid2[noZeroData$pmin>35 & noZeroData$Name == "N. Jokic"])
binnedplot(noZeroData$averageMinutesInSeason[noZeroData$pmin>35 & noZeroData$Name == "N. Jokic"], noZeroData$minResid2[noZeroData$pmin>35 & noZeroData$Name == "N. Jokic"])


binnedplot(noZeroData$pmin2[noZeroData$Starter==1], noZeroData$minResid2[noZeroData$Starter==1])
binnedplot(noZeroData$pmin2[noZeroData$Starter==0], noZeroData$minResid2[noZeroData$Starter==0])

binnedplot(noZeroData$pmin2[noZeroData$hasOt==1], noZeroData$minResid2[noZeroData$hasOt==1])
binnedplot(noZeroData$pmin[noZeroData$hasOt==1], noZeroData$minResid2[noZeroData$hasOt==1])

## Java Tests

set.seed(100)
javaData <- noZeroData[sample(1:nrow(noZeroData), 100),]
predictions <- predict(lm, javaData, type = "response")

covariates <- with(javaData, paste(pmin, averageMinutesInSeason, ownExp, oppExp, seasonYear, Starter, hasOt, teamSumAvgMinutes,lastGameMin,
                                   sep = ","))

test <- paste("Assert.assertEquals(", predictions, "d, MinutesGivenPlayedModel.getAverageGivenPlayed(", covariates, ") , DELTA);", sep = "")
cat(test, sep="\n")

predictTerms.glm <- function(obj, newdata) {
  t(t(model.matrix(obj$formula, newdata)) * coef(obj))
}

predTerms <- predictTerms.glm(lm, javaData)
predTerms[1,]


fullGames$lastGameMin[fullGames$lastGameMin == -1] <- fullGames$pmin[fullGames$lastGameMin == -1]  
fullGames$predGivenPlayed <- predict(lm, fullGames, type = "response")

write.csv(fullGames, file = "C:\\models\\nba-player-props\\minutes distribution\\mappedDataWithPreds.csv" )
### Attach a gamma distribution for no OT

discretizeGamma <- function(sc, sh){
  probs <- c()
  for(i in 1:60){
    probs <- c(probs, pgamma(i-1, shape = sh, scale = sc) - pgamma(i, shape = sh, scale = sc))
  }
  probs[1:5] <- 0
  probs <- probs / sum(probs)
  return(probs)
}

optimizeForMedianVarianceGamma <- function(par, wantedMean, wantedVar){
  sh = exp(par[1])
  sc = exp(par[2])
  
  probs = discretizeGamma(sc = sc, sh = sh)
  
  meanProbs <- sum(1:60 * probs)
  varProbs <- sum((1:60 - meanProbs) ^ 2 * probs)
  
  meanDistance = (wantedMean - meanProbs) ^ 2
  varDistance = (wantedVar - varProbs) ^ 2
  
  return(meanDistance + varDistance)
}

noOt <- subset(noZeroData, noZeroData$hasOt == 0)

noOt$averageMin <- round(noOt$pmin2)
noOt$minsInBench <- 48 - noOt$Min 

coefDf <- data.frame()
for(i in 8:39){
  subsetForI <- subset(noOt, noOt$averageMin == i)
  meanMin <- mean(subsetForI$minsInBench)
  varMin <- var(subsetForI$minsInBench)
  
  scalePar = varMin / meanMin
  shapePar = meanMin / scalePar
  
  optimPar <- optim(par = c(log(shapePar), log(scalePar)), 
                    fn = optimizeForMedianVarianceGamma, 
                    wantedMean = mean(subsetForI$minsInBench), 
                    wantedVar = var(subsetForI$minsInBench))
  
  coefDf <- rbind(coefDf, data.frame(meanMin = i, scaleMinBench = exp(optimPar$par[2]), shapeMinBench = exp(optimPar$par[1])))
}

plot.ts(coefDf$shapeMinBench)
plot.ts(coefDf$scaleMinBench)

numbRows <- aggregate(Min ~ averageMin, noZeroData, length)
colnames(numbRows) <-c("meanMin", "numberObs")

coefDf <- merge(coefDf, numbRows)

#Testing
n = 32
subsetTest <- subset(noOt, round(noOt$pmin2) == n)
sampleGammaProbs <- discretizeGamma(sc = coefDf$scaleMinBench[coefDf$meanMin==n], sh = coefDf$shapeMinBench[coefDf$meanMin==n])
sampleGamma <- sample(1:length(sampleGammaProbs), size = 10000, prob = sampleGammaProbs, replace = T)
summary(sampleGamma)
summary(subsetTest$minsInBench)

plot(density(48 - subsetTest$Min))
lines(density(sampleGamma), col = "red")

#Simulate game

agg <- aggregate(pmin ~ GameId + Team, mappedData, sum)
fullGames <- unique(subset(agg, agg$pmin %in% c(239,240,241)))

#Move minutes avg towards their pmin pred
optimizeCoef <- function(par, gameToAdjust){
  gameToAdjust$adjMinutes <- ifelse(gameToAdjust$ratio > 1, gameToAdjust$minutesAvg / par, gameToAdjust$minutesAvg * par)
  return((sum(gameToAdjust$adjMinutes) - 240) ^ 2)
}

allData <- data.frame()

noOt <- subset(mappedData, mappedData$hasOt == 0)

noOt <- subset(noOt, noOt$pmin > 0)
numbPlayers <- aggregate(Name ~ GameId + Team, noOt, length)
colnames(numbPlayers)[3] <- "numbPlayers"

noOt <- merge(noOt, numbPlayers)

aggMinutesPerNumb <- aggregate(Min ~ GameId + Team + numbPlayers, noOt, sum)

fullGames$id <- paste0(fullGames$GameId, "-", fullGames$Team)
aggMinutesPerNumb$id <- paste0(aggMinutesPerNumb$GameId, "-", aggMinutesPerNumb$Team)

aggMinutesPerNumb <- subset(aggMinutesPerNumb, aggMinutesPerNumb$id %in% fullGames$id)

aggMins <- aggregate(Min ~ numbPlayers, aggMinutesPerNumb, mean)



for(i in 1:nrow(fullGames)){
  game <- subset(noOt, noOt$GameId == fullGames$GameId[i] & noOt$Team == fullGames$Team[i])
  if(nrow(game) > 0){
    

  game <- subset(game, game$pmin > 0)
  game$zeroPred <- predict(zeroMinsModel, game, type = "response")
  game$predGivenPlayed <- predict(lm, game, type = "response")
  game$minutesAvg <- (1 - game$zeroPred) * game$predGivenPlayed
  
  game$sumRawAvg <- sum(game$minutesAvg)
  game$adjMinutes <- game$minutesAvg * sum(game$pmin) / game$sumRawAvg
  
  #game$ratio <- game$minutesAvg / game$pmin
  
  #optimPar <- optim(par = 1, optimizeCoef, gameToAdjust = game, method = "Brent", lower = 1, upper = 2)
  #game$adjMinutes <- ifelse(game$ratio > 1, game$minutesAvg / optimPar$par, game$minutesAvg * optimPar$par)
  allData <- rbind(allData, game[c("GameId", "Team", "Name", "Min", "pmin", "zeroPred", "predGivenPlayed", "minutesAvg", "sumRawAvg", "adjMinutes")])
  }
}


allData$resid <- allData$Min - allData$adjMinutes
mean(allData$resid)

binnedplot(allData$numbPlayers, allData$resid)


binnedplot(allData$adjMinutes, allData$resid  + 0.7356016)
binnedplot(allData$pmin, allData$resid  + 0.7356016)
binnedplot(allData$sumRawAvg, allData$resid  + 0.7356016)
binnedplot(allData$pmin[allData$sumRawAvg < 230], allData$resid[allData$sumRawAvg < 230]  + 0.7356016)
binnedplot(allData$adjMinutes[allData$sumRawAvg < 230], allData$resid[allData$sumRawAvg < 230]  + 0.7356016)
binnedplot(allData$adjMinutes[allData$sumRawAvg > 245], allData$resid[allData$sumRawAvg  > 245]  + 0.7356016)
binnedplot(allData$adjMinutes[allData$sumRawAvg > 245], allData$resid[allData$sumRawAvg  > 245])
binnedplot(allData$adjMinutes[allData$sumRawAvg < 230], allData$resid[allData$sumRawAvg  < 230])

summary(allData$sumRawAvg)
