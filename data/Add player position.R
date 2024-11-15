library(sqldf)

fillOutPositions <- function(players){
  
  noNa <- subset(players, !is.na(players$Position))
  naData <- subset(players, is.na(players$Position))  
  
  agg <- sqldf("SELECT PlayerId, Position, COUNT(Position) AS numberPositions
                FROM noNa
                GROUP BY PlayerId, Position")
  
  maxPos <- aggregate(numberPositions ~ PlayerId, agg, max)
  colnames(maxPos) <- c("PlayerId", "maxPosition")
  
  agg <- merge(agg, maxPos)
  agg <- subset(agg, agg$numberPositions==agg$maxPosition)[1:2]
  
  colnames(agg) <- c("PlayerId", "Position")
  
  naData <- naData[-which(colnames(naData) == "Position")]  
  
  naData <- merge(naData, agg, all.x = T)
  
  noNa <- rbind(noNa, naData[colnames(noNa)])
  
  naData <- subset(noNa, is.na(noNa$Position))
  noNa <- subset(noNa, !is.na(noNa$Position))
  
  naData <- manuallyInputPosition(naData)
  noNa <- rbind(noNa, naData[colnames(noNa)])
  
  return(noNa)
}


manuallyInputPosition <- function(naData){
  map <- read.csv("C:\\models\\nba-player-props\\data\\manualPosition.csv")
  naData <- naData[-which(colnames(naData) == "Position")]  
  
  naData <- merge(naData, map[c("PlayerId", "Position")], all.x = T)
  return(naData)
}
