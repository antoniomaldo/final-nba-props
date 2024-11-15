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
  
  sbrData$date <- as.Date(sbrData$date, format = "%d/%m/%Y")
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


#Map rotowire predictions to espn data

mapToPlayerId <- function(df, baseDir){
  mappingsFile = paste0(baseDir, "mappings/rotowire-mappings.csv")
  playerMappins <- unique(read.csv(mappingsFile)[c(1,3)])
  
  playerMappins <- unique(playerMappins)
  
  colnames(playerMappins) <- c("PlayerId", "playerName")
  
  merged <- merge(df, playerMappins, by = "PlayerId", all.x = T)
  
  return(merged)
}

removeDupPredictions <- function(df){
  #If for a gameID, there are more than 1 players, keep the one with the predictions, or keep 1 row without a prediction
  
  dupPreds <- aggregate(Min ~ PlayerId, df, length)
  dupPreds <- subset(dupPreds, dupPreds$Min > 1)
  
  uniquePred <- subset(df, !df$PlayerId %in% dupPreds$PlayerId)
  multiplePred <- subset(df, df$PlayerId %in% dupPreds$PlayerId)
  
  cleanedDf <- data.frame()
  for(i in unique(multiplePred$PlayerId)){
    dupPlayer <- subset(multiplePred, multiplePred$PlayerId == i)
    dupPlayer$hasPred <- !is.na(dupPlayer$pmin)
    
    if(sum(dupPlayer$hasPred) > 0){
      cleanedDf <- rbind(cleanedDf,(subset(dupPlayer, dupPlayer$hasPred == 1)[1,]))
    }else{
      cleanedDf <- rbind(cleanedDf,dupPlayer[1,])
    }
  }
  
  return(rbind(uniquePred, cleanedDf[-which(colnames(cleanedDf) == "hasPred")]))
}


mapMinutesForSeason <- function(boxscores,
                                rotoPreds,
                                baseDir){
  
  boxscores <- mapToPlayerId(boxscores, baseDir)
  
  allData <- data.frame()
  for(d in unique(rotoPreds$date)){
    csvData <- subset(rotoPreds, rotoPreds$date == d)
    
    csvData$playerName = str_trim(as.character(csvData$NAME))
    
    dayBoxscore = subset(boxscores, boxscores$Date == str_remove_all(csvData$date[1], "-"))
    
    if(nrow(dayBoxscore) > 0){
      dayBoxscore = merge(dayBoxscore, csvData, by = "playerName", all.x = T)
      
      if(!("pmin" %in% colnames(dayBoxscore))){
        dayBoxscore$pmin = dayBoxscore$MIN
      }
      
      cleanData <- removeDupPredictions(dayBoxscore)
      
      allData <- rbind(allData, cleanData)
      #write.csv(cleanData, file = paste0("C:\\Users\\Antonio\\Documents\\NBA\\data\\RotoGrinders\\mapped slate\\",SEASON,"\\", formatData$date[1], ".csv"))
    }
  }
  return(allData)
}


#Mapped not mapped players

#boxscores$isNaPlayerName <- 1 * is.na(boxscores$playerName)

# agg <- aggregate(isNaPlayerName ~ PlayerId, boxscores, mean)
# agg <- merge(agg, aggregate(seasonYear ~ PlayerId, boxscores, max))
# agg <- merge(agg, aggregate(GameId ~ PlayerId, boxscores, length))
# agg <- subset(agg, agg$isNaPlayerName==1 & agg$seasonYear>=2019)
# agg <- subset(agg, agg$seasonYear>=2024)
# 
# notMapped <- unique(subset(boxscores[c("PlayerId", "Name", "Team")], boxscores$PlayerId %in% agg$PlayerId))
# write.csv(notMapped,"C:\\models\\nba-player-props\\mappings\\notMapped.csv")
