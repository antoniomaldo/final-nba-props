library(DBI)
library(bigrquery)
library(stringr)

BASE_DIR <- "C:\\czrs-ds-models\\nba-player-props\\"

source(paste0(BASE_DIR, "mappings\\mappings-service.R"))

bq_auth()

con <- dbConnect(
  bigrquery::bigquery(),
  project = "ea-moneyball",
  dataset = "NBA"
)

#dbListTables(con)

events = dbGetQuery(con, "SELECT * FROM espn_games")
players = dbGetQuery(con, "SELECT * FROM espn_boxscores")
sbrOdds <- dbGetQuery(con, "SELECT * FROM nba_sbr_odds")

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
players <- merge(players, events[c("GameId", "AwayTeam", "HomeTeam", "Date")], by = "GameId", all.x = T)
players <- merge(players, mappedOdds[c("GameId", "matchSpread", "totalPoints")], by = "GameId", all.x = T)

players$id <- paste0(players$GameId, "_", players$PlayerId)
View(table(players$id))

write.csv(players, paste0(BASE_DIR, "data\\allPlayers.csv"))
