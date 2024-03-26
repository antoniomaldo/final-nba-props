library(DBI)
library(bigrquery)
library(stringr)
library(zoo)

BASE_DIR <- "C:\\czrs-ds-models\\nba-player-props\\"

source(paste0(BASE_DIR, "mappings\\mappings-service.R"))

bq_auth()

con <- dbConnect(
  bigrquery::bigquery(),
  project = "ea-moneyball",
  dataset = "NBA"
)

#dbListTables(con)
# 
# events = dbGetQuery(con, "SELECT * FROM espn_games")
# players = dbGetQuery(con, "SELECT * FROM espn_boxscores")
# sbrOdds <- dbGetQuery(con, "SELECT * FROM nba_sbr_odds")

BASE_DATA_DIR = "C:/Users/amaldonado/Documents/NBA/data/"

loadDataInDirectory <- function(dir){
  folders <- list.files(dir, full.names = T)  
  df <- data.frame()
  for(folder in folders){
    season <- str_remove(folder, dir)
    seasonData <- do.call(rbind, lapply(list.files(folder, full.names = T), read.csv))
    seasonData$seasonYear = season
    df <- rbind(df, seasonData)
  }
  return(df)
}

sbrOdds <- loadDataInDirectory(paste0(BASE_DATA_DIR, "sbr-odds/"))
players <- loadDataInDirectory(paste0(BASE_DATA_DIR, "espn/Players/"))
events <- loadDataInDirectory(paste0(BASE_DATA_DIR, "espn/Boxscores/"))

#Clean espn data
players <- players[,-which(colnames(players) == "seasonYear")]
players <- merge(players, events[c("GameId", "seasonYear")], by = "GameId")
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


players <- merge(players, playedData[c("GameId", "PlayerId", "cumMinInSeason", "cumGamesPlayedInSeason", "averageMinutesInSeason", "Date")], all.x = T)
players <- players[order(players$PlayerId, players$Date),]
players$averageMinutesInSeason[!is.na(players$cumGamesPlayedInSeason) & players$cumGamesPlayedInSeason == 0] <- -1

players$averageMinutesInSeason <- na.locf(players$averageMinutesInSeason)

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
View(table(players$id))

write.csv(players, paste0(BASE_DIR, "data/allPlayers.csv"))
