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

mappedOdds <- mapSbrDataToEspn(eventsData = events, sbrData = sbrOdds, baseDir = BASE_DIR)

players <- merge(players, events[c("GameId", "AwayTeam", "HomeTeam", "Date")], by = "GameId", all.x = T)
players <- merge(players, mappedOdds[c("GameId", "matchSpread", "totalPoints")], by = "GameId", all.x = T)

write.csv(players, paste0(BASE_DIR, "data\\allPlayers.csv"))
