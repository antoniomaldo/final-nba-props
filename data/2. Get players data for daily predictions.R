library(stringr)
library(zoo)

BASE_DIR <- "C:\\models\\nba-player-props\\"

source(paste0(BASE_DIR, "data\\Add player position.R"))

# 
# bq_auth()
# 
# con <- dbConnect(
#   bigrquery::bigquery(),
#   project = "ea-moneyball",
#   dataset = "NBA"
# )

#dbListTables(con)
# 
# events = dbGetQuery(con, "SELECT * FROM espn_games")
# players = dbGetQuery(con, "SELECT * FROM espn_boxscores")
# sbrOdds <- dbGetQuery(con, "SELECT * FROM nba_sbr_odds")

BASE_DATA_DIR = "C:/Users/Antonio/Documents/NBA/data/"

loadDataInDirectory <- function(dir){
  folders <- list.files(dir, full.names = T)  
  #folders <- folders[grepl("2024|2025", folders)]
  df <- data.frame()
  for(folder in folders){
    season <- str_remove(folder, dir)
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
    seasonData$seasonYear = season
    if(!"bookmaker" %in% colnames(seasonData)){
      seasonData$bookmaker = ""
    }
    if(nrow(df) > 0){
      df <- rbind(df, seasonData[colnames(df)])
    }else{
      df <- rbind(df, seasonData)
    }
  }
  return(df)
}

players <- loadDataInDirectory(paste0(BASE_DATA_DIR, "espn/Players/"))
events <- loadDataInDirectory(paste0(BASE_DATA_DIR, "espn/Boxscores/"))

players <- merge(players, events[c("GameId", "Date")])
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

players$lastGameMin[players$firstGame == 1] <- -1
players$averageMinutesInSeason[is.na(players$averageMinutesInSeason) & players$firstGame == 1] <- -1


players$averageMinutesInSeason <- na.locf(players$averageMinutesInSeason)
players$lastGameMin <- na.locf(players$lastGameMin)

players <- fillOutPositions(players)

print(mean(is.na(players$Position)))

write.csv(players, paste0(BASE_DIR, "data/allPlayersTodayPreds.csv"))
