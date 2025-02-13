library(stringr)
library(zoo)

BASE_DIR <- "C:/nba-player-props/model/final-nba-props/"

source(paste0(BASE_DIR, "mappings/mappings-service.R"))
source(paste0(BASE_DIR, "data/Add player position.R"))
source(paste0(BASE_DIR, "data/Clean sbr odds.R"))

BASE_DATA_DIR = "C:/nba-player-props/data/"

loadDataInDirectory <- function(dir){
  folders <- list.files(dir, full.names = T)  
  df <- data.frame()
  for(folder in folders){
    seasonData <- data.frame()
    for(file in list.files(folder, full.names = T)){
      a = read.csv(file, row.names = NULL)
      if(ncol(a) == 34 & !"bookmaker" %in% colnames(a)){
        colNames <- colnames(a)[2:34]
        a = a[,1:33]
        colnames(a) <- colNames
      }
      if(nrow(seasonData) > 0){
        seasonData <- rbind(seasonData, a[colnames(seasonData)])
        
      }else{
        seasonData <- rbind(seasonData, a)
        
      }
    }
    #seasonData <- do.call(rbind, lapply(list.files(folder, full.names = T), read.csv))

    if(nrow(df) > 0){
      df <- rbind(df, seasonData[colnames(df)])
    }else{
      df <- rbind(df, seasonData)
    }
  }
  return(df)
}

loadEspnDataInDirectory <- function(dir){
  folders <- list.files(dir, full.names = T)  
  df <- data.frame()
  for(folder in folders){
    season <- str_remove(folder, dir)
    seasonData <- do.call(rbind, lapply(list.files(folder, full.names = T), read.csv))
    seasonData$seasonYear <- season
    
    df <- rbind(df, seasonData)
  }
  return(df)
}

sbrOdds <- loadDataInDirectory(paste0(BASE_DATA_DIR, "sbr-odds-cleaned\\"))
players <- loadEspnDataInDirectory(paste0(BASE_DATA_DIR, "espn/Players/"))
events <- loadEspnDataInDirectory(paste0(BASE_DATA_DIR, "espn/Boxscores/"))

#fix sbrodds df columsn
# before22 <- subset(sbrOdds, sbrOdds$seasonYear <= 2021)
# after22 <- subset(sbrOdds, sbrOdds$seasonYear > 2021)
# 
# before22 <- before22[c("bookmaker", "seasonYear", "seasonPeriod", "date", "awayTeam", "homeTeam",
#                        "matchSpread", "homeOdds", "awayOdds", "totalPoints", "overOdds","underOdds")]
# after22 <- after22[c("bookmaker", "seasonYear", "seasonPeriod", "date", "awayTeam", "homeTeam",
#                      "home1QScore", "home2QScore", "home3QScore", "home4QScore", "home1OTScore", "home2OTScore")]
# 
# colnames(after22) <- colnames(before22)
# 
# sbrOdds <- rbind(before22, after22)

#Clean espn data
players <- unique(players)

players$id <- paste0(players$GameId, "_", players$PlayerId)
dups <- data.frame(table(players$id))

dupsRows <- subset(dups, dups$Freq > 1)
noDups <- subset(dups, dups$Freq == 1)

cleanDups <- data.frame()
for(i in unique(dupsRows$Var1)){
  cleanDups <- rbind(cleanDups, subset(players, players$id %in% i)[1,])
}

players <- rbind(cleanDups, subset(players, players$id %in% noDups$Var1))
rm(cleanDups, noDups, dups, dupsRows)

#Add cum data
players <- merge(players, events[c("GameId", "Date")])

playedData <- subset(players, players$Min > 0)

playedData <- playedData[order(playedData$PlayerId, playedData$Date),]

playedData$cumMinInSeason <- unlist(aggregate(Min ~ seasonYear + PlayerId, playedData, FUN = function(x){cumsum(c(0, x[-length(x)]))})$Min)
playedData$cumGamesPlayedInSeason <- unlist(aggregate(Min ~ seasonYear + PlayerId, playedData, FUN = function(x){(0: (length(x) - 1))})$Min)
playedData$averageMinutesInSeason <- playedData$cumMinInSeason / playedData$cumGamesPlayedInSeason

playedData$rowId <- 1:nrow(playedData)

playedData <- sqldf("SELECT t1.*, t2.Min AS lastGameMin FROM playedData t1 
                  LEFT JOIN playedData t2 ON t1.PlayerId = t2.PlayerId AND t1.rowId = t2.rowId + 1")

playedData$lastGameMin[playedData$cumGamesPlayedInSeason==0] <- -1
playedData$averageMinutesInSeason[playedData$cumGamesPlayedInSeason==0] <- -1

players <- merge(players, playedData[c("GameId", "PlayerId", "cumMinInSeason", "cumGamesPlayedInSeason", "averageMinutesInSeason", "lastGameMin", "Date")], all.x = T)
players <- players[order(players$PlayerId, players$Date),]

#First game per season for players
firstDate <- aggregate(Date ~ PlayerId + seasonYear, players, min)
colnames(firstDate)[3] <- "FirstDate"

players <- merge(players, firstDate)

players$firstGame <- 1 * (players$FirstDate == players$Date)

players$averageMinutesInSeason[players$firstGame == 1] <- -1
players$lastGameMin[players$firstGame == 1] <- -1

players$averageMinutesInSeason[!is.na(players$cumGamesPlayedInSeason) & players$cumGamesPlayedInSeason == 0] <- -1
players$lastGameMin[!is.na(players$cumGamesPlayedInSeason) & players$cumGamesPlayedInSeason == 0] <- -1

players$averageMinutesInSeason <- na.locf(players$averageMinutesInSeason)
players$lastGameMin <- na.locf(players$lastGameMin)



#Map to odds
mappedOdds <- mapSbrDataToEspn(eventsData = events, sbrData = sbrOdds, baseDir = BASE_DIR)
dups <- data.frame(table(mappedOdds$GameId))

dupsRows <- subset(dups, dups$Freq > 1)
noDups <- subset(dups, dups$Freq == 1)

cleanDups <- data.frame()
for(i in unique(dupsRows$Var1)){
  cleanDups <- rbind(cleanDups, subset(mappedOdds, mappedOdds$GameId %in% i)[1,])
}

mappedOdds <- rbind(cleanDups, subset(mappedOdds, mappedOdds$GameId %in% noDups$Var1))

#Clean odds data
players <- merge(players, events[c("GameId", "AwayTeam", "HomeTeam")], by = "GameId", all.x = T)
players <- merge(players, mappedOdds[c("GameId", "matchSpread", "totalPoints")], by = "GameId", all.x = T)

players$id <- paste0(players$GameId, "_", players$PlayerId)

players <- fillOutPositions(players)

write.csv(players, paste0(BASE_DIR, "data/allPlayers.csv"))
