library(stringr)
library(sqldf)

## Map sbr data
mapSbrDataToEspn <- function(eventsData, sbrData, baseDir){
  mappings <- read.csv(paste0(baseDir, "\\mappings\\sbr_espn_team_mappings.csv"))
  eventsData <- sqldf("SELECT t.*, t1.sbr_name AS homeTeamSbrName, t2.sbr_name AS awayTeamSbrName
                       FROM eventsData t
                       LEFT JOIN mappings t1 ON t.HomeTeam = t1.espn_abbrev
                       LEFT JOIN mappings t2 ON t.AwayTeam = t2.espn_abbrev")
  
  eventsData <- subset(eventsData, !is.na(eventsData$awayTeamSbrName))
  sbrData <- pickWantedBookamer(sbrData)
  eventsData$date <- as.Date(as.character(eventsData$Date), format = "%Y%m%d")
  
  sbrData$date <- as.Date(sbrData$date)
  sbrData$prevDate <- sbrData$date - 1
  
  eventsData = eventsData[,-which(colnames(eventsData) == "Date")]
  
  odds <- sqldf("SELECT g.GameId, g.seasonYear, g.AwayTeam, g.HomeTeam, o.* FROM eventsData g
               LEFT JOIN sbrData o
               ON g.homeTeamSbrName = o.homeTeam AND g.awayTeamSbrName = o.awayTeam
               AND (g.date = o.prevDate)")
  
  #try to mapped the ones not mapped using the same day
  notMapped <- subset(odds, is.na(odds$totalPoints))
  bxNotMapped <- subset(eventsData, eventsData$GameId %in% notMapped$GameId)
  bxMapped <- subset(eventsData, !eventsData$GameId %in% notMapped$GameId) 
  
  oddsNotMapped <- sqldf("SELECT g.GameId, g.seasonYear, g.AwayTeam, g.HomeTeam, o.* FROM bxNotMapped g
                          LEFT JOIN sbrData o
                          ON g.homeTeamSbrName = o.homeTeam AND g.awayTeamSbrName = o.awayTeam
                          AND (g.date = o.date)")
  
  odds <- subset(odds, odds$GameId %in% bxMapped$GameId)
  odds <- rbind(odds, oddsNotMapped)
  
  odds <- unique(odds[c("GameId", "AwayTeam", "HomeTeam", "matchSpread", "totalPoints")])
  return(odds)
}

pickWantedBookamer <- function(sbrData){
  dates <- unique(sbrData$date)
  finalData <- data.frame()
  for(dt in dates){
    oddsData <- subset(sbrData, sbrData$date == dt)
    oddsData <- subset(oddsData, !grepl("Opening", oddsData$bookmaker))
    for(at in unique(oddsData$awayTeam)){
      game <- subset(oddsData, oddsData$awayTeam == at)
      if("bet365" %in% oddsData$bookmaker){
        finalData <- rbind(finalData, subset(game, game$bookmaker == "bet365")[1,])
      }else if("williamhill" %in% oddsData$bookmaker){
        finalData <- rbind(finalData, subset(game, game$bookmaker == "williamhill")[1,])
      }else if("betway" %in% oddsData$bookmaker){
        finalData <- rbind(finalData, subset(game, game$bookmaker == "betway")[1,])
      }else if("888sport" %in% oddsData$bookmaker){
        finalData <- rbind(finalData, subset(game, game$bookmaker == "888sport")[1,])
      }else {
        finalData <- rbind(finalData, game[1,])
      }
    }
  }
  return(finalData)
}


