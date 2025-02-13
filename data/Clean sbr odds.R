DIR <- "C:/nba-player-props/data/sbr-odds/"

folders <- list.files(DIR, full.names = T)

for (folder in folders[1:4]){
  csvs <- list.files(folder, full.names = T)
  newFolder <- str_replace(folder, "sbr-odds", "sbr-odds-cleaned")
  season <- str_remove(newFolder, "C:/nba-player-props/data/sbr-odds-cleaned/")
  for(csv in csvs){
   dayData <- read.csv(csv, row.names = NULL) 
   
   if(ncol(dayData) == 34 ){
     colNames <- colnames(dayData)[2:34]
     dayData = dayData[,1:33]
     colnames(dayData) <- colNames
   }
   
   if(!"bookmaker" %in% colnames(dayData)){
     dayData$bookmaker <- "bet365"
     dayData <- dayData[c(ncol(dayData), 1:(ncol(dayData) - 1))]
   }
   dayData2 <- dayData[c(1:6, 21:32)]
   dayData2$seasonYear <- as.numeric(season)
   write.csv(dayData2, str_replace(csv, "sbr-odds", "sbr-odds-cleaned"))
  }
}

for (folder in folders[5:7]){
  csvs <- list.files(folder, full.names = T)
  newFolder <- str_replace(folder, "sbr-odds", "sbr-odds-cleaned")
  season <- str_remove(newFolder, "C:/nba-player-props/data/sbr-odds-cleaned/")
  
  for(csv in csvs){
    dayData <- read.csv(csv, row.names = NULL)
    
    if(!"bookmaker" %in% colnames(dayData)){
      dayData$bookmaker <- "bet365"
      dayData <- dayData[c(ncol(dayData), 1:(ncol(dayData) - 1))]
    }
    dayData2 <- dayData[c(1:18)]
    colnames(dayData2) <- c("bookmaker",	"seasonYear",	"seasonPeriod",	"date",	"awayTeam",	"homeTeam",	"matchSpread",	"homeOdds",	"awayOdds",	"totalPoints",	"overOdds",	"underOdds",	"fhMatchSpread",	"fhHomeOdds",	"fhAwayOdds",	"fhTotalPoints",	"fhOverOdds",	"fhUnderOdds")
    write.csv(dayData2, str_replace(csv, "sbr-odds", "sbr-odds-cleaned"))
  }
}

for (folder in folders[8]){
  csvs <- list.files(folder, full.names = T)
  newFolder <- str_replace(folder, "sbr-odds", "sbr-odds-cleaned")
  season <- str_remove(newFolder, "C:/nba-player-props/data/sbr-odds-cleaned/")
  
  for(csv in csvs){
    dayData <- read.csv(csv, row.names = NULL)
    
    if(!"bookmaker" %in% colnames(dayData)){
      dayData$bookmaker <- "bet365"
      dayData <- dayData[c(ncol(dayData), 1:(ncol(dayData) - 1))]
    }
    dayData2 <- dayData[c(1:18)]
    colnames(dayData2) <- c("bookmaker",	"seasonYear",	"seasonPeriod",	"date",	"awayTeam",	"homeTeam",	"matchSpread",	"homeOdds",	"awayOdds",	"totalPoints",	"overOdds",	"underOdds",	"fhMatchSpread",	"fhHomeOdds",	"fhAwayOdds",	"fhTotalPoints",	"fhOverOdds",	"fhUnderOdds")
    write.csv(dayData2, str_replace(csv, "sbr-odds", "sbr-odds-cleaned"))
  }
}

rm(dayData, dayData2)