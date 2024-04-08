library(zoo)
library(dplyr)

source("C:\\czrs-ds-models\\nba-player-props\\mappings\\mappings-service.R")
BASE_DIR <- "C:\\czrs-ds-models\\nba-player-props\\"


allPreds <- read.csv("C:\\czrs-ds-models\\nba-player-props\\testing\\allPreds.csv")
#allPreds <- subset(allPreds, !is.na(allPreds$matchSpread))

allPlayers <- read.csv("C:\\czrs-ds-models\\nba-player-props\\data/allPlayers.csv")

allPreds <- allPreds[,-which(colnames(allPreds) %in% c("gamesPlayedInSeason", "Name", "totalPoints", "matchSpread", "Team", "seasonYear", "averageMinutesInSeason", "lastGameMin", "HomeTeam", "homeExp", "awayExp", "ownExp", "oppExp", "Date"))]
allPlayers <- merge(allPlayers[c("GameId", "PlayerId", "Name", "Position", "Team", "Min", "Starter", "Date", "totalPoints", "matchSpread", "seasonYear", "averageMinutesInSeason", "lastGameMin", "cumGamesPlayedInSeason", "HomeTeam")], allPreds,  all.x = T, by = c("GameId", "PlayerId"))

allPlayers <- allPlayers[order(allPlayers$Date),]
allPlayers$isNaCoef <- is.na(allPlayers$fgPlayerCoef)

agg <- aggregate(isNaCoef ~ GameId + Date, allPlayers, mean)

#Fill NA values
df <- data.frame()
missingCoefs <- c()
for(pid in unique(allPlayers$PlayerId)){
  playerData <- subset(allPlayers, allPlayers$PlayerId == pid)
  playerData <- playerData[order(playerData$Date),]
  
  if(sum(is.na(playerData$fgPlayerCoef))==0){
    df <- rbind(df, playerData)
  }
  
  else {
    start = which(colnames(playerData) == "fgPrior")
    end = which(colnames(playerData) == "ftsPercPlayerCoef")
    
    if(is.na(playerData$fgPlayerCoef[1])){
      missingCoefs <- c(missingCoefs, pid)
      
      for(i in start:end){
        nextValue <- as.numeric(first(playerData[,i], na_rm = T))
        playerData[1,i] <- nextValue
      }
    }
    
    playerData$minPlayed[is.na(playerData$minPlayed)] <- 0
  
    for(i in start:end){
      col <- as.numeric(playerData[,i])
      playerData[,i] <- na.locf(col)
    }
    df <- rbind(df, playerData)
  }
}

df$isNaCoef <- is.na(df$fgPlayerCoef)

agg <- aggregate(isNaCoef ~ GameId + Date, df, mean)

allPlayers <- df
#Load rotowire data 

csvs <- list.files("C:/Users/amaldonado/Documents/NBA/data/rotowire/rotowire/", full.names = T)
csvs <- csvs[!grepl("\\.R", csvs)]


loadCsvForDay <- function(fileDir, season){
  if(grepl("rotowire", fileDir)){
    dayCsv = read.csv(fileDir)
    colNames = as.character(dayCsv[1,])
    dayCsv = dayCsv[-1,]
    colnames(dayCsv) = colNames
    date = str_remove_all(fileDir, pattern = paste0("C:/Users/amaldonado/Documents/NBA/data/rotowire/rotowire/",season, "/rotowire-nba-projections-"))
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
    year = str_remove_all(pattern = "C:/Users/amaldonado/Documents/NBA/data/rotowire/rotowire/", string = csvPath)
    dayData <- loadCsvForDay(csvFilePath, year)
    rotowirePreds <- rbind(rotowirePreds, dayData)
  }
  rotowirePreds <- rotowirePreds[c("date", colnames(rotowirePreds)[colnames(rotowirePreds) != "date"])]
}

mappedData = mapMinutesForSeason(boxscores = allPlayers, 
                                 rotoPreds = rotowirePreds, 
                                 baseDir = BASE_DIR)


mappedData$pmin <- as.numeric(mappedData$pmin)
mappedData <- subset(mappedData, !is.na(mappedData$pmin) &  mappedData$pmin > 0)

agg <- aggregate(pmin ~ GameId + Team, mappedData, sum)
fullRoster <- subset(agg, agg$pmin %in% c(239, 240, 241))

fullRoster$teamId <- paste0(fullRoster$GameId, "-", fullRoster$Team)
mappedData$teamId <- paste0(mappedData$GameId, "-", mappedData$Team)

fullGames <- subset(mappedData, mappedData$teamId %in% fullRoster$teamId)

length(unique(fullGames$teamId))

allPlayers <- allPlayers[,-which(colnames(allPlayers) %in% c("gamesPlayedInSeason", "Name", "totalPoints", "matchSpread", "Team", "seasonYear", "averageMinutesInSeason", "lastGameMin", "HomeTeam", "homeExp", "awayExp", "ownExp", "oppExp"))]
mergedPreds <- merge(fullGames[c("GameId", "PlayerId", "Name", "Position", "Team", "Min", "Starter", "pmin", "Date", "totalPoints", "matchSpread", "seasonYear", "averageMinutesInSeason", "lastGameMin", "cumGamesPlayedInSeason", "HomeTeam")], allPlayers,  all.x = T)
mergedPreds <- subset(mergedPreds, !is.na(mergedPreds$pmin))
mergedPreds <- subset(mergedPreds, !is.na(mergedPreds$totalPoints))

mergedPreds <- subset(mergedPreds, mergedPreds$GameId %in% allPlayers$GameId)


mergedPreds$isNaFgCoef <- is.na(mergedPreds$fgPlayerCoef)
agg <- aggregate(isNaFgCoef ~ GameId, mergedPreds, mean)

fullRoster <- subset(agg, agg$isNaFgCoef == 0)

mergedPreds <- subset(mergedPreds, mergedPreds$GameId %in% fullRoster$GameId)

agg <- aggregate(pmin ~ GameId + Team, mergedPreds, sum)

mergedPreds$homeExp <- (mergedPreds$totalPoints - mergedPreds$matchSpread) / 2
mergedPreds$awayExp <- mergedPreds$totalPoints - mergedPreds$homeExp

mergedPreds$ownExp <- ifelse(mergedPreds$HomeTeam == mergedPreds$Team, mergedPreds$homeExp, mergedPreds$awayExp)
mergedPreds$oppExp <- ifelse(mergedPreds$HomeTeam != mergedPreds$Team, mergedPreds$homeExp, mergedPreds$awayExp)


write.csv(mergedPreds, file = "C:\\czrs-ds-models\\nba-player-props\\backtest_analysis\\backtestInputs.csv")
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
